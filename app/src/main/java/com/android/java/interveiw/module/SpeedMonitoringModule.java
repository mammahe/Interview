package com.android.java.interveiw.module;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.hardware.property.CarPropertyManager;
import android.car.hardware.property.CarPropertyValue;
import android.content.Context;
import android.util.Log;

import com.android.java.interveiw.domain.FirebaseNotificationSender;
import com.android.java.interveiw.domain.SpeedMonitor;
import com.android.java.interveiw.domain.UserAlert;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This class is responsible for monitoring the speed of the car and alerting the user and rental company if the speed exceeds the limit.
 * It uses the Android Car API to get the car's speed and Firebase to send notifications.
 */
public class SpeedMonitoringModule {
    private static final String TAG = "SpeedMonitoringModule";
    private static final int VEHICLE_SPEED_PROPERTY = 0x11600400; // Vehicle speed property ID

    private final Context context;
    private final FirebaseDatabase firebaseDatabase;
    private Car car;
    private CarPropertyManager carPropertyManager;
    private int speedLimit;
    private String customerId;
    private SpeedMonitor speedMonitor;
    private UserAlert userAlert;
    private FirebaseNotificationSender notificationSender;

    /**
     * Constructor for the SpeedMonitoringModule.
     * @param context The application context.
     */
    public SpeedMonitoringModule(Context context) {
        this.context = context;
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.speedMonitor = new SpeedMonitor();
        this.userAlert = new UserAlert(context);
        this.notificationSender = new FirebaseNotificationSender(firebaseDatabase);
    }

    /**
     * Initializes the Car API and registers a listener for speed changes.
     */
    public void initializeCarApi() {
        try {
            car = Car.createCar(context);
            carPropertyManager = (CarPropertyManager) car.getCarManager(Car.PROPERTY_SERVICE);

            if (carPropertyManager != null) {
                carPropertyManager.registerCallback(new CarPropertyManager.CarPropertyEventCallback() {
                    @Override
                    public void onChangeEvent(CarPropertyValue value) {
                        if (value.getPropertyId() == VEHICLE_SPEED_PROPERTY) {
                            Float currentSpeed = (Float) value.getValue();
                            Log.d(TAG, "Current Speed: " + currentSpeed);

                            // Check if speed exceeds the limit
                            if (speedMonitor.isSpeeding(currentSpeed, speedLimit)) {
                                notifyRentalCompany(currentSpeed);
                                alertUser();
                            }
                        }
                    }

                    @Override
                    public void onErrorEvent(int propertyId, int zone) {
                        Log.e(TAG, "Error in Vehicle HAL Property: " + propertyId);
                    }
                }, VEHICLE_SPEED_PROPERTY, CarPropertyManager.SENSOR_RATE_NORMAL);
            } else {
                Log.e(TAG, "CarPropertyManager is not initialized.");
            }
        } catch (CarNotConnectedException e) {
            Log.e(TAG, "Car is not connected", e);
        }
    }

    /**
     * Sets the speed limit for the customer.
     * @param customerId The ID of the customer.
     * @param speedLimit The speed limit in km/h.
     */
    public void setSpeedLimit(String customerId, int speedLimit) {
        this.customerId = customerId;
        this.speedLimit = speedLimit;
    }

    /**
     * Notifies the rental company through Firebase if the speed exceeds the limit.
     * @param currentSpeed The current speed of the car.
     */
    private void notifyRentalCompany(float currentSpeed) {
        notificationSender.sendNotification(customerId, currentSpeed);
    }

    /**
     * Alerts the user if the speed exceeds the limit.
     * For AAOS, customize for HMI display or audio alerts.
     */
    private void alertUser() {
        userAlert.alertUser(speedLimit);
    }
}