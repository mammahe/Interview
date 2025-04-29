package com.android.java.interveiw.domain;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseNotificationSender {
    private static final String TAG = "FirebaseNotificationSender";
    private final FirebaseDatabase firebaseDatabase;

    public FirebaseNotificationSender(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public void sendNotification(String customerId, float currentSpeed) {
        DatabaseReference notificationRef = firebaseDatabase
                .getReference("notifications")
                .child(customerId);

        Map<String, Object> notificationData = new HashMap<>();
        notificationData.put("speed", currentSpeed);
        notificationData.put("timestamp", System.currentTimeMillis());

        notificationRef.push().setValue(notificationData)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Notification sent successfully!"))
                .addOnFailureListener(e -> Log.e(TAG, "Failed to send notification", e));
    }
}