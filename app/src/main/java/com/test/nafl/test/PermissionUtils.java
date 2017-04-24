package com.test.nafl.test;

/**
 * Created by Neury on 2/23/2017.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class PermissionUtils {

    /**
     * Empty Constructor
     */
    private PermissionUtils() {
    }

    /**
     * This method checks whether the given permission is already granted or not.
     *
     * @param activity      This is context of the current activity
     * @param permission    This is the permission we need to check
     * @return  boolean     Returns True if already permission granted for this permission else false.
     */
    private static boolean checkPermission(Activity activity,  String permission) {
        //Determine whether you have been granted a particular permission.
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * This method checks whether the given permission is already granted or not.
     * If not granted then check for whether the user has selected "NEVER ASK AGAIN" option for this permission.
     * If Option selected, then display only a dialog that we need to access these permission else request for new permission prompt.
     *
     * @param activity      This is context of the current activity
     * @param permissionId  This is the an Unique ID for each and every single permission we are passing.
     * @param permissionInfoMsg This is Message we need to display to the user when they selected "NEVER ASK AGAIN" option for the certain permission.
     * @param permission   This is the permission we need to check
     * @return  boolean     Returns True if already permission granted for this permission else false.
     */
    public static boolean checkAndRequestPermission(final Activity activity, final int permissionId, String permissionInfoMsg, final String permission){
        if (Build.VERSION.SDK_INT < 23)
            return true;
        boolean hasPermissionGranted = checkPermission(activity, permission);
        if (!hasPermissionGranted) {
            //Gets whether you should show UI with rationale for requesting a permission. You should do this only if you do not have the permission and the context in which
            // the permission is requested does not clearly communicate to the user what would be the benefit from granting this permission.
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showDialogMessage(activity, permissionInfoMsg,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        //Requests permissions to be granted to this application. These permissions must be requested in your manifest, they should not be granted to your app.
                                        ActivityCompat.requestPermissions(activity, new String[]{permission},
                                                permissionId);
                                        break;
                                }
                            }
                        });
                return false;
            }
            //Requests permissions to be granted to this application. These permissions must be requested in your manifest, they should not be granted to your app.
            ActivityCompat.requestPermissions(activity,new String[]{permission}, permissionId);
            return false;
        }
        return true;
    }

    /**
     * This method is used to display a Dialog Message to the user before prompting permission dialog.
     *
     * @param context   This is context of the current activity
     * @param message   This is the message we are displaying in the dialog to the user.
     * @param okListener    This is the dialog OnClickListener for positive button to request for permission.
     */
    private static void showDialogMessage(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
