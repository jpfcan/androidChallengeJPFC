package com.example.juanpablo.androidchallengedemandfrontier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.juanpablo.androidchallengedemandfrontier.objects.Day;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Juan Pablo on 07/05/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Day> days;

    public PagerAdapter(FragmentManager fm, ArrayList<Day> d) {
        super(fm);
        days=d;
    }

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = new PagerObjectFragment();
        Bundle args = new Bundle();
        args.putParcelable("day",days.get(i));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());
        String weekdays[] = dfs.getWeekdays();
        return weekdays[days.get(position).getDate().get(Calendar.DAY_OF_WEEK)];
    }
}