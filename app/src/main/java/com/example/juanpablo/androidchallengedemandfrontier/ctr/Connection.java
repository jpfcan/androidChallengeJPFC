package com.example.juanpablo.androidchallengedemandfrontier.ctr;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Juan Pablo on 06/05/16.
 */
public class Connection {

    public static boolean comprobarConectividad(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            return false;
        }
        return true;
    }

}
