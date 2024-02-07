package com.example.battery_monitor_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {
    private BatteryChangeListener listener;

    public void setBatteryChangeListener(BatteryChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int maxBatteryLevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if (batteryLevel != -1 && maxBatteryLevel != -1 && listener != null) {
            float batteryPercentage = batteryLevel * 100f / maxBatteryLevel;
            listener.onBatteryLevelChanged(batteryPercentage);
        }
    }

    public interface BatteryChangeListener {
        void onBatteryLevelChanged(float batteryPercentage);
    }
}

