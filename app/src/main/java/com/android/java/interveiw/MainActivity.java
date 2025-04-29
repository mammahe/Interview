package com.android.java.interveiw;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.java.interveiw.module.SpeedMonitoringModule;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        speedMonitorTool();
    }

    private void speedMonitorTool() {
        SpeedMonitoringModule speedMonitoringModule = new SpeedMonitoringModule(this);

        // Set up Vehicle HAL and Speed Monitoring
        speedMonitoringModule.initializeCarApi();
        String customerId = "Sairamkrishna_Mammahe";
        int speedLimit = 100; // Example speed limit in km/h
        speedMonitoringModule.setSpeedLimit(customerId, speedLimit);
    }
}