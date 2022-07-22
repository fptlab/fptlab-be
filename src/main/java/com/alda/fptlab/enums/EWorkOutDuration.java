package com.alda.fptlab.enums;

public enum EWorkOutDuration {
    HALF_AN_HOUR(30), ONE_HOUR(60);

    private int duration;

    EWorkOutDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
