package com.example.kshyo.notificationex;

import android.content.Context;
import android.os.PowerManager;

import static android.content.ContentValues.TAG;

/**
 * Created by kshyo on 2018-01-09.
 */

public class WakeLockUtil {
    private static PowerManager.WakeLock mCpuWakeLock;

    static void acquireCpuWakeLock(Context context) {
        if (mCpuWakeLock != null) return;
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mCpuWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
        mCpuWakeLock.acquire();
    }

    static void releaseCpuWakeLock() {
        if (mCpuWakeLock != null) {
            mCpuWakeLock.release();
            mCpuWakeLock = null;
        }
    }

    public static boolean isScreenOn(Context context) {
        return ((PowerManager) context.getSystemService(Context.POWER_SERVICE)).isScreenOn();
    }
}
