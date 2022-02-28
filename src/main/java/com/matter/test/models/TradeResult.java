package com.matter.test.models;

import com.matter.test.annotations.CashAmount;
import com.matter.test.annotations.Symbol;
import com.matter.test.annotations.VolumeAmount;
import com.matter.test.interfaces.models.ITradeResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Builder
@Document("trade_result")
public class TradeResult implements ITradeResult {
    @Id
    private UUID id;
    @Symbol
    private String symbol;
    @CashAmount
    private double volumeWeightedAveragePrice;
    @VolumeAmount
    private int volumeRequested;

    public static TradeResult FromTradeInfo(@Symbol String symbol, @CashAmount double totalPrice,
                                            @VolumeAmount int volumeRequested, @VolumeAmount int volumeTaken) {
        var volumeWeightedAveragePrice = totalPrice == 0 || volumeTaken == 0 ? 0
                : totalPrice / volumeTaken;

        return TradeResult.builder()
                .id(UUID.randomUUID())
                .symbol(symbol)
                .volumeWeightedAveragePrice(volumeWeightedAveragePrice)
                .volumeRequested(volumeRequested)
                .build();
    }
}
