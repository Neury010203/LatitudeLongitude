package com.test.nafl.test;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Neury on 2/21/2017.
 */


//Esta clase no se usa//

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {


    public static final String TAG= "NumerologiaDominicana";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Token: "+token);

    }
}
