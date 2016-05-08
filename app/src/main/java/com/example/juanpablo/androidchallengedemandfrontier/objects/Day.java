package com.example.juanpablo.androidchallengedemandfrontier.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by Juan Pablo on 06/05/16.
 */
public class Day implements Parcelable{

    int id;
    String summary;
    String icon;
    Calendar date;
    float temperature;
    float humidity;
    float windSpeed;
    float pressure;
    float cloudCover;

    public Day() {
    }

    public Day(Parcel in) {
        id = in.readInt();
        summary = in.readString();
        icon = in.readString();
        temperature = in.readFloat();
        humidity = in.readFloat();
        windSpeed = in.readFloat();
        pressure = in.readFloat();
        cloudCover = in.readFloat();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(float cloudCover) {
        this.cloudCover = cloudCover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(summary);
        dest.writeString(icon);
        dest.writeSerializable(date);
        dest.writeFloat(temperature);
        dest.writeFloat(humidity);
        dest.writeFloat(windSpeed);
        dest.writeFloat(pressure);
        dest.writeFloat(cloudCover);
    }

    private void readFromParcel(Parcel in){
        id=in.readInt();
        summary=in.readString();
        icon=in.readString();
        date=(Calendar) in.readSerializable();
        temperature=in.readFloat();
        humidity=in.readFloat();
        windSpeed=in.readFloat();
        pressure=in.readFloat();
        cloudCover=in.readFloat();
    }
}
