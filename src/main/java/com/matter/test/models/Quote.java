package com.matter.test.models;

import com.matter.test.annotations.CashAmount;
import com.matter.test.annotations.ExpirationDate;
import com.matter.test.annotations.Symbol;
import com.matter.test.annotations.VolumeAmount;
import com.matter.test.interfaces.models.IQuote;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@Document("quote")
public class Quote implements IQuote {
    @Id
    private UUID id;
    @Symbol
    @Field(name = "symbol")
    private String symbol;
    @CashAmount
    @Field(name = "price")
    private double price;
    @VolumeAmount
    @Field(name = "availableVolume")
    private int availableVolume;
    @ExpirationDate
    @Field(name = "expiration")
    private Date expiration;

    public boolean isExpired() {
        return expiration.before(new Date());
    }

    public boolean hasAvailableVolume() {
        return availableVolume > 0;
    }

    public boolean isAvailable() {
        return !isExpired() && hasAvailableVolume();
    }
}
