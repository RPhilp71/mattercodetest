package com.matter.test.interfaces.models;

import java.util.UUID;

public interface ITradeResult {
    UUID getId();
    void setId(UUID id);
    String getSymbol();
    void setSymbol(String symbol);
    double getVolumeWeightedAveragePrice();
    void setVolumeWeightedAveragePrice(double avgPrice);
    int getVolumeRequested();
    void setVolumeRequested(int volumeRequested);
}
