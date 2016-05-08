package com.example.juanpablo.androidchallengedemandfrontier;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juanpablo.androidchallengedemandfrontier.ctr.Connection;
import com.example.juanpablo.androidchallengedemandfrontier.ctr.JSONReader;
import com.example.juanpablo.androidchallengedemandfrontier.objects.Day;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Juan Pablo on 05/05/16.
 */

public class FragmentDays extends Fragment{

    private static FragmentDays instance;
    static Context context;

    PagerAdapter mPagerAdapter;
    ViewPager mViewPager;

    ProgressDialog pd;
    LoadWeather LW;

    View myView;

    LocationManager lm;
    String provider;
    Location l;

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 0;

    public static FragmentDays getInstance(Context context_) {
        if (instance == null) {
            instance = new FragmentDays();
        }
        context = context_;
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_days, container, false);
        //lm = ((MyApplication) getActivity().getApplicationContext()).getLocationManager();
        mViewPager = (ViewPager) myView.findViewById(R.id.pager);
        LW=new LoadWeather();
        LW.execute();

        return myView;
    }

    private class LoadWeather extends AsyncTask<Void, Integer, ArrayList<Day>> {

        @Override
        protected void onPreExecute(){
            pd = new ProgressDialog(context);
            pd.setCanceledOnTouchOutside(false);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Loading weather forecast...");
            pd.setCancelable(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }

        @Override
        protected ArrayList<Day> doInBackground(Void... params) {
            ArrayList<Day> days = null;

            if (Connection.comprobarConectividad(context)) {
                days = new JSONReader().getDays(((MyApplication)getActivity().getApplicationContext()).getLatitude(),((MyApplication)getActivity().getApplicationContext()).getLongitude());
                //dias=days;
            }
            else{

            }

            return days;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            pd.setProgress(progreso);
        }

        @Override
        protected void onPostExecute(ArrayList<Day> result) {

            if (result == null) {
                LW.cancel(true);
                //errorHorario();
            } else {
                mPagerAdapter = new PagerAdapter(getChildFragmentManager(),result);
                mViewPager.setAdapter(mPagerAdapter);
            }

            myView.setVisibility(View.VISIBLE);
            pd.dismiss();
            LW.cancel(true);
        }
    }

}
