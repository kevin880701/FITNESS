package com.hr.fitness.ui.health.height;

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
import com.hr.fitness.Model.Record;
import com.hr.fitness.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class heightLineChartFragment extends Fragment {


    LineChartData lineChartData;
    Model model;
    ArrayList<String> dateList;
    public ArrayList<Record> recordList;
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

    public heightLineChartFragment(Model model) {
        this.model = model;
        recordList = model.getRecordList();
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

        if (recordList.size() > 0) {
            if (recordList.size() <= 10) {
                for (int i = 1; i <= recordList.size(); i++) {
                    Float currentMin = Float.parseFloat(recordList.get(i - 1).getHeight());
                    Float currentMax = Float.parseFloat(recordList.get(i - 1).getHeight());
                    if (currentMin <= min) {
                        min = currentMin;
                    }
                    if (currentMin >= max) {
                        max = currentMax;
                    }
                    valuesList.add(new Entry(i - 1, Float.parseFloat(recordList.get(i - 1).getHeight())));
                    dateList.add(recordList.get(i - 1).getDate().substring(6, 11));
                }
            } else {
                int j = 1;
                for (int i = 10; i >= 0; i--) {
                    valuesList.add(new Entry(j - 1, Float.parseFloat(recordList.get(j - 1).getHeight())));
                    dateList.add(recordList.get(recordList.size() - 1 - i).getDate().substring(6, 11));
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