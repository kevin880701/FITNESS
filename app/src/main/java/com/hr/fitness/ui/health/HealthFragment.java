package com.hr.fitness.ui.health;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hr.fitness.MainActivity;
import com.hr.fitness.Model.Model;
import com.hr.fitness.Model.Record;
import com.hr.fitness.Model.ViewPagerAdapter;
import com.hr.fitness.R;
import com.hr.fitness.SQLite.SQLiteDBHelper;
import com.hr.fitness.ui.health.EditData.EditDataActivity;
import com.hr.fitness.ui.health.bmi.bmiLineChartFragment;
import com.hr.fitness.ui.health.bodyFat.bodyFatLineChartFragment;
import com.hr.fitness.ui.health.height.heightLineChartFragment;
import com.hr.fitness.ui.health.waist.waistLineChartFragment;
import com.hr.fitness.ui.health.weight.weightLineChartFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HealthFragment extends Fragment {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.editBtn)
    ImageView editBtn;

    SQLiteDBHelper dbHelper;
    SQLiteDatabase healthListDB;
    HealthPresenter healthPresenter;
    ViewPagerAdapter pagerAdapter;
    MainActivity mainActivity;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    Model model;
    ArrayList<Record> recordList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_health, container, false);
        ButterKnife.bind(this, root);

        mainActivity = (MainActivity) getActivity();

        dbHelper = new SQLiteDBHelper(getContext());
        healthListDB = dbHelper.getWritableDatabase();

        model = new Model();
        healthPresenter = new HealthPresenter(healthListDB,model);

        //創建資料庫
        healthPresenter.createDB();
        //讀取資料庫
        healthPresenter.getData();
        recordList = healthPresenter.getRecordList();

        fragmentArrayList.add(new weightLineChartFragment(model));
        fragmentArrayList.add(new heightLineChartFragment(model));
        fragmentArrayList.add(new waistLineChartFragment(model));
        fragmentArrayList.add(new bmiLineChartFragment(model));
        fragmentArrayList.add(new bodyFatLineChartFragment(model));
        pagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentArrayList);


        healthPresenter.setPagerAdapter(pagerAdapter);
        healthPresenter.setupViewPager(pager);
        tabs.setupWithViewPager(pager);

        return root;
    }

    @OnClick(R.id.editBtn)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editBtn:
                Log.v("PPP","editBtn Click");
                Intent intent = new Intent(getContext(), EditDataActivity.class);
                intent.putExtra("List", recordList);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}