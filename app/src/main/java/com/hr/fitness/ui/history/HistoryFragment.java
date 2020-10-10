package com.hr.fitness.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hr.fitness.MainActivity;
import com.hr.fitness.R;
import com.hr.fitness.ui.health.EditData.EditDataActivity;
import com.hr.fitness.ui.history.HistoryEdit.HistoryEditActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryFragment extends Fragment {

    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.titleBar)
    LinearLayout titleBar;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.textDate)
    TextView textDate;
    @BindView(R.id.btnAdd)
    ImageView btnAdd;

    private HistoryViewModel historyViewModel;
    MainActivity mainActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, root);

        mainActivity = (MainActivity) getActivity();


        Calendar mCal = Calendar.getInstance();
        CharSequence s = DateFormat.format("yyyy年MM月dd日", mCal.getTime());    // kk:24小時制, hh:12小時制
        textDate.setText(s);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                textDate.setText(year + "年" + month + "月" + dayOfMonth + "日");
            }
        });


        return root;
    }

    @OnClick(R.id.btnAdd)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                Log.v("PPP", "btnAdd Click");
                Intent intent = new Intent(getContext(), HistoryEditActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}