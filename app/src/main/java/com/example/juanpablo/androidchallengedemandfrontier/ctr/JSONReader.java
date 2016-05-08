package com.example.juanpablo.androidchallengedemandfrontier.ctr;

import android.util.Log;

import com.example.juanpablo.androidchallengedemandfrontier.MyApplication;
import com.example.juanpablo.androidchallengedemandfrontier.objects.Day;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Juan Pablo on 06/05/16.
 */
public class JSONReader {

    private static String tag = "JSONReader";
    private static String stream;
    private static String apiKey="2a73a114fabab94642b7c8033effe4ad";
    static String url = "https://api.forecast.io/forecast/"+apiKey+"/";


    public static ArrayList<Day> getDays(double latitude,double longitude) {
        ArrayList<Day> days = new ArrayList<Day>();
        String urlGetDays = url+latitude+","+longitude;

        JSONObject json=getItemFromUrl(urlGetDays);

        try {
            JSONObject daily=json.getJSONObject("daily");
            JSONArray data=daily.getJSONArray("data");
            for(int i=0;i<data.length();i++){
                JSONObject obj=data.getJSONObject(i);
                Day d=new Day();
                d.setId(i);
                d.setSummary(obj.getString("summary"));
                d.setIcon(obj.getString("icon").replace("-",""));
                d.setDate(getCalendar(obj.getLong("time")));
                double minT=obj.getDouble("temperatureMin");
                double maxT=obj.getDouble("temperatureMax");
                d.setTemperature((float)((minT+maxT)/2f));
                d.setHumidity((float)obj.getDouble("humidity"));
                d.setWindSpeed((float)obj.getDouble("windSpeed"));
                d.setPressure((float)obj.getDouble("pressure"));
                d.setCloudCover((float)obj.getDouble("cloudCover"));
                days.add(d);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return days;
    }

    public static Calendar getCalendar(long timestamp){
        Calendar myDate = Calendar.getInstance();
        myDate.setTimeInMillis(timestamp*1000);
        return myDate;
    }

    private static JSONObject getItemFromUrl(String url){
        //ArrayList<JSONObject> tempArr = new ArrayList<JSONObject>();
        JSONObject object=null;
        Log.i(tag, "Searching from: " + url);
        stream = urlReader(url);
        try {
            object=new JSONObject(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return tempArr;
        return object;
    }

    private static String urlReader(String url_){
        StringBuilder builder = new StringBuilder();
        URL url = null;
        try{
            url=new URL(url_);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        URLConnection urlConnection=null;
        try{
            urlConnection=url.openConnection();
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            InputStream content=urlConnection.getInputStream();
            Log.e(tag,"input: "+content);
            BufferedReader reader=new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line=reader.readLine())!=null){
                builder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }

}
