package com.android.java.interveiw.domain;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class UserAlert {
    private static final String TAG = "UserAlert";
    private Context context;

    public UserAlert(Context context) {
        this.context = context;
    }

    public void alertUser(int speedLimit) {
        Log.w(TAG, "User alert: Speed limit exceeded!");
        if (context != null) {
            new AlertDialog.Builder(context).setTitle("Speed Limit Exceeded").setMessage("You have exceeded the speed limit of " + speedLimit + " km/h!").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User clicked OK button
                    dialog.dismiss(); // Close the dialog
                }
            }).setIcon(android.R.drawable.ic_dialog_alert).show();
        } else {
            Log.e(TAG, "Context is null. Cannot display alert dialog.");
        }
    }
}