package com.example.demo_db.util;

import com.google.android.material.snackbar.Snackbar;
import android.view.View;



public class Utils {
    public static void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
