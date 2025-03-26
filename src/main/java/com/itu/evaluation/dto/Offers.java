package com.itu.evaluation.dto;

import java.util.List;

public class Offers {
    private List<Offer> won;
    private List<Offer> lost;
    private String inProgress;
    private List<Object> unknown;

    public List<Offer> getWon() {
        return won;
    }

    public void setWon(List<Offer> won) {
        this.won = won;
    }

    public List<Offer> getLost() {
        return lost;
    }

    public void setLost(List<Offer> lost) {
        this.lost = lost;
    }

    public String getInProgress() {
        return inProgress;
    }

    public void setInProgress(String inProgress) {
        this.inProgress = inProgress;
    }

    public List<Object> getUnknown() {
        return unknown;
    }

    public void setUnknown(List<Object> unknown) {
        this.unknown = unknown;
    }
}
