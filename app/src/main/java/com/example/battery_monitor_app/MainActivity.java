package com.example.battery_monitor_app;


import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements BatteryReceiver.BatteryChangeListener {
    private TextView batteryLevelTextView;
    private BatteryReceiver batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryLevelTextView = findViewById(R.id.batteryLevelTextView);

        // Start the battery service
        Intent batteryServiceIntent = new Intent(this, BatteryService.class);
        startService(batteryServiceIntent);

        // Set up the battery change listener
        batteryReceiver = new BatteryReceiver();
        batteryReceiver.setBatteryChangeListener(this);
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onBatteryLevelChanged(float batteryPercentage) {
        batteryLevelTextView.setText(getString(R.string.battery_level, batteryPercentage));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
    }
}
