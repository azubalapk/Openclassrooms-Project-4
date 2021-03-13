package com.example.projet_3_oc_maru.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void displayToastLong(CharSequence text,Context context){
        int duration = Toast.LENGTH_LONG;
        Toast toast ;
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
