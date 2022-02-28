package com.matter.test.controllers;

import com.matter.test.annotations.Symbol;
import com.matter.test.annotations.VolumeAmount;
import com.matter.test.interfaces.controllers.IQuoteManager;
import com.matter.test.interfaces.models.ITradeResult;
import com.matter.test.interfaces.services.IQuoteService;
import com.matter.test.models.Quote;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/quote")
public class QuoteManager implements IQuoteManager {
    @Autowired
    private IQuoteService quoteService;


    @PostMapping("/save")
    public void AddOrUpdateQuote(@Valid @RequestBody Quote quote) {
        if(quote.getId() == null) {
            quote.setId(UUID.randomUUID());
        }

        quoteService.AddOrUpdateQuote(quote);
    }

    @PostMapping("/remove")
    public void RemoveQuote(@NonNull @RequestParam UUID id) {
        quoteService.RemoveQuote(id);
    }

    @PostMapping("/remove-all")
    public void RemoveAllQuotes(@Symbol @RequestParam String symbol) {
        quoteService.RemoveAllQuotes(symbol);
    }

    @GetMapping("/best-quote")
    public Quote GetBestQuoteWithAvailableVolume(@Symbol @RequestParam String symbol) {
        return quoteService.GetBestQuoteWithAvailableVolume(symbol);
    }

    @GetMapping("/execute-trade")
    public ITradeResult ExecuteTrade(@Symbol @RequestParam String symbol,
                                     @Positive @VolumeAmount @RequestParam int volumeRequested) {
        return quoteService.ExecuteTrade(symbol, volumeRequested);
    }
}
