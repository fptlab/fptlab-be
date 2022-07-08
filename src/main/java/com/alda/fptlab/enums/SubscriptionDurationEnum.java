package com.alda.fptlab.enums;

public enum SubscriptionDurationEnum {
    HALF_AN_HOUR(30), ONE_HOUR(60);

    private int duration;

    SubscriptionDurationEnum(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
