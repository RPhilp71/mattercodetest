package com.matter.test.interfaces.services;

import com.matter.test.interfaces.models.ITradeResult;
import com.matter.test.models.Quote;

import java.util.UUID;

public interface IQuoteService {
    void AddOrUpdateQuote(Quote QuoteImpl);

    void RemoveQuote(UUID id);

    void RemoveAllQuotes(String symbol);

    Quote GetBestQuoteWithAvailableVolume(String symbol);

    ITradeResult ExecuteTrade(String symbol, int volumeRequested);
}
