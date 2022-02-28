package com.matter.test.services;

import com.matter.test.interfaces.models.ITradeResult;
import com.matter.test.interfaces.repositories.IQuoteRepository;
import com.matter.test.interfaces.repositories.ITradeResultRepository;
import com.matter.test.interfaces.services.IQuoteService;
import com.matter.test.models.Quote;
import com.matter.test.models.TradeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class QuoteService implements IQuoteService {
    @Autowired
    private IQuoteRepository quoteRepository;

    @Autowired
    private ITradeResultRepository tradeResultRepository;

    @Override
    public void AddOrUpdateQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    @Override
    public void RemoveQuote(UUID id) {
        quoteRepository.deleteById(id);
    }

    @Override
    public void RemoveAllQuotes(String symbol) {
        quoteRepository.deleteBySymbol(symbol);
    }

    @Override
    public Quote GetBestQuoteWithAvailableVolume(String symbol) {
        try(Stream<Quote> bestAvailableQuotes = GetBestAvailableQuotes(symbol)) {
            return bestAvailableQuotes.findFirst().orElse(null);
        }
    }

    @Override
    public ITradeResult ExecuteTrade(String symbol, int volumeRequested) {
        try(Stream<Quote> bestAvailableQuotes = GetBestAvailableQuotes(symbol)) {
            var updatedQuotes = new ArrayList<Quote>();
            var quoteIterator = bestAvailableQuotes.iterator();
            var totalPrice = 0.0;
            var totalVolumeTaken = 0;

            while (totalVolumeTaken < volumeRequested && quoteIterator.hasNext()) {
                var quote = quoteIterator.next();
                var originalVolume = quote.getAvailableVolume();
                var volumeTaken = Math.min(originalVolume, volumeRequested - totalVolumeTaken);

                quote.setAvailableVolume(originalVolume - volumeTaken);
                updatedQuotes.add(quote);

                totalPrice += volumeTaken * quote.getPrice();
                totalVolumeTaken += volumeTaken;
            }

            var tradeResult = TradeResult.FromTradeInfo(symbol, totalPrice, volumeRequested, totalVolumeTaken);

            SaveQuotesIfPresent(updatedQuotes);
            tradeResultRepository.save(tradeResult);
            return tradeResult;
        }
    }

    // Returns a list of available quotes, from lowest to highest priced
    private Stream<Quote> GetBestAvailableQuotes(String symbol) {
        return quoteRepository.findBySymbol(symbol)
                .filter(Quote::isAvailable)
                .sorted(Comparator.comparingDouble(Quote::getPrice));
    }

    private void SaveQuotesIfPresent(List<Quote> quotes) {
        if (!quotes.isEmpty()) {
            quoteRepository.saveAll(quotes);
        }
    }
}
