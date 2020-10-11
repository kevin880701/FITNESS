package com.hr.fitness.ui.health.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.hr.fitness.Model.LineChartData;
import com.hr.fitness.Model.Model;
import com.hr.fitness.Model.HealthRecord;
import com.hr.fitness.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class bmiLineChartFragment extends Fragment {


    LineChartData lineChartData;
    Model model;
    ArrayList<String> dateList;
    public ArrayList<HealthRecord> healthRecordList;
    DecimalFormat df;
    Float min = 9999f;
    Float max = 0f;
    boolean isFirstLoad = true;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;

    public bmiLineChartFragment(Model model) {
        this.model = model;
        healthRecordList = model.getHealthRecordList();
        dateList = new ArrayList<>();
        df = new DecimalFormat("##0.0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bmi_linechart, container, false);
        ButterKnife.bind(this, rootView);

        lineChartData = new LineChartData(lineChart, getContext());

        // greenLine
        ArrayList<Entry> valuesList = new ArrayList<>();

        if (healthRecordList.size() > 0) {
            if (healthRecordList.size() <= 10) {
                for (int i = 1; i <= healthRecordList.size(); i++) {
                    Float currentMin = Float.parseFloat(healthRecordList.get(i - 1).getBMI());
                    Float currentMax = Float.parseFloat(healthRecordList.get(i - 1).getBMI());
                    if (currentMin <= min) {
                        min = currentMin;
                    }
                    if (currentMin >= max) {
                        max = currentMax;
                    }
                    valuesList.add(new Entry(i - 1, Float.parseFloat(healthRecordList.get(i - 1).getBMI())));
                    dateList.add(healthRecordList.get(i - 1).getDate().substring(6, 11));
                }
            } else {
                int j = 1;
                for (int i = 10; i >= 0; i--) {
                    valuesList.add(new Entry(j - 1, Float.parseFloat(healthRecordList.get(j - 1).getBMI())));
                    dateList.add(healthRecordList.get(healthRecordList.size() - 1 - i).getDate().substring(6, 11));
                    j++;
                }
            }

            lineChartData.initX(dateList);
            lineChartData.initY(min, max);
            lineChartData.initDataSet(valuesList);
        } else {
            lineChartData.initDataSet(valuesList);
        }

        return rootView;
    }
}