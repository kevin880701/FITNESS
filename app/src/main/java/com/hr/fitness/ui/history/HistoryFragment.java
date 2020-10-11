package com.hr.fitness.ui.history;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hr.fitness.MainActivity;
import com.hr.fitness.Model.HistoryRecord;
import com.hr.fitness.Model.Model;
import com.hr.fitness.R;
import com.hr.fitness.SQLite.SQLiteDBHelper;
import com.hr.fitness.ui.history.HistoryEdit.HistoryEditActivity;

import java.util.ArrayList;
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
    @BindView(R.id.textDateRecord)
    TextView textDateRecord;

    SQLiteDBHelper dbHelper;
    SQLiteDatabase historyLiteDB;
    MainActivity mainActivity;
    HistoryPresenter presenter;
    Model model;
    String datePosition;
    ArrayList<HistoryRecord> historyRecordList;
    String record = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, root);

        mainActivity = (MainActivity) getActivity();

        dbHelper = new SQLiteDBHelper(getContext());
        historyLiteDB = dbHelper.getWritableDatabase();

        model = new Model();
        presenter = new HistoryPresenter(historyLiteDB, model,this);

        //創建資料庫
        presenter.createDB();
        historyRecordList = presenter.getData();


        Calendar mCal = Calendar.getInstance();
        CharSequence s = DateFormat.format("yyyy年MM月dd日", mCal.getTime());    // kk:24小時制, hh:12小時制
        datePosition = s.toString();
        textDate.setText(s);

        record = presenter.changeTextDateRecord(datePosition);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int mm = month+1;
                textDate.setText(year + "年" + mm + "月" + dayOfMonth + "日");
                datePosition = year + "年" + mm + "月" + dayOfMonth + "日";
                record = presenter.changeTextDateRecord(year + "年" + mm + "月" + dayOfMonth + "日");
            }
        });
        return root;
    }

    @OnClick(R.id.btnAdd)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                Intent intent = new Intent(getContext(), HistoryEditActivity.class);
                intent.putExtra("date", datePosition);
                if(!record.equals("")){
                    intent.putExtra("idArray", presenter.getIdArray());
                    intent.putExtra("nameArray", presenter.getNameArray());
                    intent.putExtra("contentArray", presenter.getContentArray());
                }
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}