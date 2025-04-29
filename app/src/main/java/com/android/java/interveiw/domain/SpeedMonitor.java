package com.android.java.interveiw.domain;

public class SpeedMonitor {

    public boolean isSpeeding(float currentSpeed, int speedLimit) {
        return currentSpeed > speedLimit;
    }
}