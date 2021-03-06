/*
 * Copyright (C) 2015 Willi Ye
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grarak.kerneladiutor.utils.kernel;

import android.content.Context;
import android.util.Log;

import com.grarak.kerneladiutor.R;
import com.grarak.kerneladiutor.utils.Constants;
import com.grarak.kerneladiutor.utils.Utils;
import com.grarak.kerneladiutor.utils.root.Control;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by willi on 02.01.15.
 */
public class Wake implements Constants {

    private static String S2W_FILE;
    private static String SLEEP_MISC_FILE;
    private static String T2W_FILE;
    private static String WAKE_MISC_FILE;
    private static String DT2S_FILE;
    private static String WAKE_TIMEOUT_FILE;

    public static void activatePowerKeySuspend(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", POWER_KEY_SUSPEND, Control.CommandType.GENERIC, context);
    }

    public static boolean isPowerKeySuspendActive() {
        return Utils.readFile(POWER_KEY_SUSPEND).equals("1");
    }

    public static boolean hasPowerKeySuspend() {
        return Utils.existFile(POWER_KEY_SUSPEND);
    }

    public static boolean hasVibStrength() {
        if (Utils.existFile(WAKE_VIB_STRENGTH)) return true;
        return false;
    }

    public static void setvibstrength(int value, Context context) {
        Control.runCommand(String.valueOf(value), WAKE_VIB_STRENGTH, Control.CommandType.GENERIC, context);
    }

    public static int getvibstrength() {
        return Utils.stringToInt(Utils.readFile(WAKE_VIB_STRENGTH));
    }

    public static void setWakeTimeout(int value, Context context) {
        Control.runCommand(String.valueOf(value), WAKE_TIMEOUT_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getWakeTimeout() {
        return Utils.stringToInt(Utils.readFile(WAKE_TIMEOUT_FILE));
    }

    public static int getWakeTimeoutMax() {
        if (WAKE_TIMEOUT_FILE.equals(WAKE_TIMEOUT)) return 30;
        return 10;
    }

    public static boolean hasS2WTime() {
        if (Utils.existFile(WAKE_ST2W_TIME)) return true;
        return false;
    }

    public static void setS2WTime(int value, Context context) {
        Control.runCommand(String.valueOf(value), WAKE_ST2W_TIME, Control.CommandType.GENERIC, context);
    }

    public static int getS2WTime() {
        return Utils.stringToInt(Utils.readFile(WAKE_ST2W_TIME));
    }

    public static boolean hasDT2WTimeBetweenTaps() {
        if (Utils.existFile(WAKE_DT2W_TIMEBETWEENTAPS)) return true;
        return false;
    }

    public static void setDT2WTimeBetweenTaps(int value, Context context) {
        Control.runCommand(String.valueOf(value), WAKE_DT2W_TIMEBETWEENTAPS, Control.CommandType.GENERIC, context);
    }

    public static int getDT2WTimeBetweenTaps() {
        return Utils.stringToInt(Utils.readFile(WAKE_DT2W_TIMEBETWEENTAPS));
    }

    public static int getDT2WFeatherX() {
        return Utils.stringToInt(Utils.readFile(WAKE_DT2W_FEATHERX));
    }

    public static boolean hasDT2WFeatherX() {
        if (Utils.existFile(WAKE_DT2W_FEATHERX)) return true;
        return false;
    }

    public static void setDT2WFeatherX(int value, Context context) {
        Control.runCommand(String.valueOf(value), WAKE_DT2W_FEATHERX, Control.CommandType.GENERIC, context);
    }

    public static int getDT2WFeatherY() {
        return Utils.stringToInt(Utils.readFile(WAKE_DT2W_FEATHERY));
    }

    public static boolean hasDT2WFeatherY() {
        if (Utils.existFile(WAKE_DT2W_FEATHERY)) return true;
        return false;
    }

    public static void setDT2WFeatherY(int value, Context context) {
        Control.runCommand(String.valueOf(value), WAKE_DT2W_FEATHERY, Control.CommandType.GENERIC, context);
    }

    public static boolean hasWakeTimeout() {
        for (String file: WAKE_TIMEOUT_ARRAY)
            if (Utils.existFile(file)) {
                WAKE_TIMEOUT_FILE = file;
                return true;
            }
        return false;
    }

    public static void activatePocketMode(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", POCKET_MODE, Control.CommandType.GENERIC, context);
    }

    public static boolean isPocketModeActive() {
        return Utils.readFile(POCKET_MODE).equals("1");
    }

    public static boolean hasPocketMode() {
        return Utils.existFile(POCKET_MODE);
    }

    public static void activateCameraGesture(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", CAMERA_GESTURE, Control.CommandType.GENERIC, context);
    }

    public static boolean isCameraGestureActive() {
        return Utils.readFile(CAMERA_GESTURE).equals("1");
    }

    public static boolean hasCameraGesture() {
        return Utils.existFile(CAMERA_GESTURE);
    }

    public static void activateGesture(boolean active, int gesture, Context context) {
        Control.runCommand(GESTURE_STRING_VALUES[gesture] + "=" + active, GESTURE_CRTL, Control.CommandType.GENERIC,
            GESTURE_STRING_VALUES[gesture], context);
    }

    public static boolean isGestureActive(int gesture) {
        try {
            return (Long.decode(Utils.readFile(GESTURE_CRTL)) & GESTURE_HEX_VALUES[gesture]) > 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List < String > getGestures(Context context) {
        List < String > list = new ArrayList < > ();
        list.add(context.getString(R.string.slide_up));
        list.add(context.getString(R.string.slide_down));
        list.add(context.getString(R.string.slide_left));
        list.add(context.getString(R.string.slide_right));
        list.add(context.getString(R.string.draw_e));
        list.add(context.getString(R.string.draw_o));
        list.add(context.getString(R.string.draw_w));
        list.add(context.getString(R.string.draw_m));
        list.add(context.getString(R.string.draw_c));
        list.add(context.getString(R.string.dt2w));
        return list;
    }

    public static boolean hasGesture() {
        return Utils.existFile(GESTURE_CRTL);
    }

    public static void setDt2s(int value, Context context) {
        Control.runCommand(String.valueOf(value), DT2S_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getDt2sValue() {
        return Utils.stringToInt(Utils.readFile(DT2S_FILE));
    }

    public static List < String > getDt2sMenu(Context context) {
        List < String > list = new ArrayList < > ();
        if (DT2S_FILE != null) {
            list.add(context.getString(R.string.disabled));
            list.add(context.getString(R.string.enabled));
        }
        return list;
    }

    public static boolean hasDt2s() {
        if (DT2S_FILE == null)
            for (String file: DT2S_ARRAY)
                if (Utils.existFile(file)) {
                    DT2S_FILE = file;
                    return true;
                }
        return DT2S_FILE != null;
    }

    public static void setSleepMisc(int value, Context context) {
        Control.runCommand(String.valueOf(value), SLEEP_MISC_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getSleepMisc() {
        return Utils.stringToInt(Utils.readFile(SLEEP_MISC_FILE));
    }

    public static List < String > getSleepMiscMenu(Context context) {
        List < String > list = new ArrayList < > ();
        if (SLEEP_MISC_FILE != null) {
            list.add(context.getString(R.string.disabled));
            list.add(context.getString(R.string.right));
            list.add(context.getString(R.string.left));
            list.add(context.getString(R.string.right) + " " + context.getString(R.string.or) + " " + context.getString(R.string.left));
        }
        return list;
    }

    public static boolean hasSleepMisc() {
        for (String file: SLEEP_MISC_ARRAY)
            if (Utils.existFile(file)) {
                SLEEP_MISC_FILE = file;
                return true;
            }
        return SLEEP_MISC_FILE != null;
    }

    public static void setWakeMisc(int value, Context context) {
        Control.runCommand(String.valueOf(value), WAKE_MISC_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getWakeMisc() {
        return Utils.stringToInt(Utils.readFile(WAKE_MISC_FILE));
    }

    public static List < String > getWakeMiscMenu(Context context) {
        List < String > list = new ArrayList < > ();
        list.add(context.getString(R.string.disabled));
        if (WAKE_MISC_FILE != null) {
            switch (WAKE_MISC_FILE) {
                case SCREEN_WAKE_OPTIONS:
                    list.add(context.getString(R.string.s2w));
                    list.add(context.getString(R.string.s2w_charging));
                    list.add(context.getString(R.string.dt2w));
                    list.add(context.getString(R.string.dt2w_charging));
                    list.add(context.getString(R.string.dt2w) + " + " + context.getString(R.string.s2w));
                    list.add(context.getString(R.string.dt2w_s2w_charging));
                    break;
            }
        }
        return list;
    }

    public static boolean hasWakeMisc() {
        for (String file: WAKE_MISC_ARRAY)
            if (Utils.existFile(file)) {
                WAKE_MISC_FILE = file;
                return true;
            }
        return WAKE_MISC_FILE != null;
    }

    public static void setT2w(int value, Context context) {
        String command = String.valueOf(value);
        if (T2W_FILE.equals(TSP_T2W)) command = value == 0 ? "OFF" : "AUTO";

        Control.runCommand(command, T2W_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getT2w() {
        if (T2W_FILE != null && Utils.existFile(T2W_FILE)) {
            String value = Utils.readFile(T2W_FILE);
            if (T2W_FILE.equals(TSP_T2W)) return value.equals("OFF") ? 0 : 1;
            return Utils.stringToInt(value);
        }
        return 0;
    }

    public static List < String > getT2wMenu(Context context) {
        List < String > list = new ArrayList < > ();
        if (T2W_FILE != null) {
            list.add(context.getString(R.string.disabled));
            list.add(context.getString(R.string.enabled));
        }
        return list;
    }

    public static boolean hasT2w() {
        if (T2W_FILE == null)
            for (String file: T2W_ARRAY)
                if (Utils.existFile(file)) {
                    T2W_FILE = file;
                    return true;
                }
        return T2W_FILE != null;
    }

    public static void setS2w(int value, Context context) {
        Control.runCommand(String.valueOf(value), S2W_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getS2wValue() {
        int val = Utils.stringToInt(Utils.readFile(S2W_FILE));
        return val;
    }

    public static List < String > getS2wMenu(Context context) {
        List < String > list = new ArrayList < > ();
        String or = " " + context.getString(R.string.or) + " ";
        if (S2W_FILE != null) {
            list.add(context.getString(R.string.disabled));
            list.add(context.getString(R.string.right));
            list.add(context.getString(R.string.left));
            list.add(context.getString(R.string.right) + or + context.getString(R.string.left));
            list.add(context.getString(R.string.up));
            list.add(context.getString(R.string.right) + or + context.getString(R.string.up));
            list.add(context.getString(R.string.left) + or + context.getString(R.string.up));
            list.add(context.getString(R.string.right) + or + context.getString(R.string.left) + or + context.getString(R.string.up));
            list.add(context.getString(R.string.down));
            list.add(context.getString(R.string.right) + or + context.getString(R.string.down));
            list.add(context.getString(R.string.left) + or + context.getString(R.string.down));
            list.add(context.getString(R.string.right) + or + context.getString(R.string.left) + or + context.getString(R.string.down));
            list.add(context.getString(R.string.up) + or + context.getString(R.string.down));
            list.add(context.getString(R.string.right) + or + context.getString(R.string.up) + or + context.getString(R.string.down));
            list.add(context.getString(R.string.left) + or + context.getString(R.string.up) + or + context.getString(R.string.down));
            list.add(context.getString(R.string.any));
        }
        return list;
    }

    public static boolean hasS2w() {
        if (S2W_FILE == null)
            for (String file: S2W_ARRAY)
                if (Utils.existFile(file)) {
                    S2W_FILE = file;
                    break;
                }
        return S2W_FILE != null;
    }


    public static void activateLenient(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", LENIENT, Control.CommandType.GENERIC, context);
    }

    public static boolean isLenientActive() {
        return Utils.readFile(LENIENT).equals("1");
    }

    public static boolean hasLenient() {
        return Utils.existFile(LENIENT);
    }

    public static void activateDt2w(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", DT2W, Control.CommandType.GENERIC, context);
    }

    public static boolean isDt2wActive() {
        return Utils.readFile(DT2W).equals("1");
    }

    public static boolean hasDt2w() {
        return Utils.existFile(DT2W);
    }

    public static boolean hasWake() {
        for (String[] wakes: WAKE_ARRAY)
            for (String file: wakes)
                if (Utils.existFile(file)) return true;
        return false;
    }

}