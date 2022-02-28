package com.matter.test.interfaces.models;

import java.util.Date;
import java.util.UUID;


public interface IQuote {
    UUID getId();
    void setId(UUID id);
    void setSymbol(String symbol);
    String getSymbol();
    double getPrice();
    void setPrice(double price);
    int getAvailableVolume();
    void setAvailableVolume(int volume);
    Date getExpiration();
    void setExpiration(Date expiration);
}
