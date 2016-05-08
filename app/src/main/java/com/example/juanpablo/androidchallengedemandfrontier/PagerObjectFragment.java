package com.example.juanpablo.androidchallengedemandfrontier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juanpablo.androidchallengedemandfrontier.objects.Day;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Juan Pablo on 07/05/16.
 */
public class PagerObjectFragment extends Fragment {

    Day day;

    TextView tvTemperature;
    ImageView ivIcon;
    TextView tvDay;
    TextView tvDate;
    TextView tvSummary;
    TextView tvHumidity;
    TextView tvWindSpeed;
    TextView tvPressure;
    TextView tvCloudCover;
    FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
        Bundle args = getArguments();

        day=args.getParcelable("day");
        tvTemperature=(TextView)rootView.findViewById(R.id.textViewTemperature);
        ivIcon=(ImageView)rootView.findViewById(R.id.imageViewIcon);
        tvDay=(TextView)rootView.findViewById(R.id.textViewDayOfWeek);
        tvDate=(TextView)rootView.findViewById(R.id.textViewDate);
        tvSummary=(TextView)rootView.findViewById(R.id.textViewSummary);
        tvHumidity=(TextView)rootView.findViewById(R.id.textViewHumidity);
        tvWindSpeed=(TextView)rootView.findViewById(R.id.textViewWindSpeed);
        tvPressure=(TextView)rootView.findViewById(R.id.textViewPressure);
        tvCloudCover=(TextView)rootView.findViewById(R.id.textViewCloudCover);
        frameLayout=(FrameLayout)rootView.findViewById(R.id.frameLayout);

        tvTemperature.setText((int)day.getTemperature()+"°F");
        String iconName=day.getIcon();
        int iconId = getActivity().getResources().getIdentifier(iconName+"icon", "drawable", getActivity().getPackageName());
        ivIcon.setImageResource(iconId);
        int backgroundId = getActivity().getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName());
        frameLayout.setBackgroundResource(backgroundId);
        Calendar c=day.getDate();
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());
        String weekdays[] = dfs.getWeekdays();
        String[] months = dfs.getMonths();
        tvDay.setText(weekdays[c.get(Calendar.DAY_OF_WEEK)]);
        tvDate.setText(months[c.get(Calendar.MONTH)]+" "+c.get(Calendar.DAY_OF_MONTH));
        tvSummary.setText(day.getSummary());
        tvHumidity.setText(((int)(day.getHumidity()*100))+" %");
        tvWindSpeed.setText(day.getWindSpeed()+" MPS");
        tvPressure.setText(day.getPressure()+" PSI");
        tvCloudCover.setText(((int)(day.getCloudCover()*100))+" %");

        return rootView;
    }


}