package com.hr.fitness.ui.health.EditData;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fitness.MainActivity;
import com.hr.fitness.Model.InfoListAdapter;
import com.hr.fitness.Model.Record;
import com.hr.fitness.R;
import com.hr.fitness.SQLite.SQLiteDBHelper;
import com.hr.fitness.ui.health.EnterData.EnterDataActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditDataActivity extends AppCompatActivity {


    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.del)
    ImageView del;
    @BindView(R.id.titleBar)
    LinearLayout titleBar;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.waistline)
    TextView waistline;
    @BindView(R.id.bmi)
    TextView bmi;
    @BindView(R.id.bodyFat)
    TextView bodyFat;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.layout1)
    LinearLayout layout1;

    EditDataPresenter presenter;
    ArrayList<Record> recordList;
    InfoListAdapter infoListAdapter;

    SQLiteDBHelper dbHelper;
    SQLiteDatabase listDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

//        Intent intent = this.getIntent();
//        recordList = (ArrayList<Record>) intent.getSerializableExtra("List");

        dbHelper = new SQLiteDBHelper(this);
        listDB = dbHelper.getWritableDatabase();

        presenter = new EditDataPresenter(listDB);
        presenter.getData();
        recordList = presenter.getRecordList();

        list.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        list.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), DividerItemDecoration.VERTICAL));

        infoListAdapter = new InfoListAdapter(recordList, presenter);
        list.setAdapter(infoListAdapter);

    }

    @OnClick({R.id.back, R.id.add, R.id.del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent backIntent = new Intent(this, MainActivity.class);
                startActivity(backIntent);
                finish();
                break;
            case R.id.add:
                Intent addIntent = new Intent(this, EnterDataActivity.class);
                startActivity(addIntent);
                finish();
                break;
            case R.id.del:
                if (infoListAdapter.isClickStatus()) {
                    recordList.remove(infoListAdapter.getCurrentPosition());
                    presenter.delDB(infoListAdapter.getCurrentId());
                }
                infoListAdapter.setClickStatus(false);
                infoListAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent backIntent = new Intent(this, MainActivity.class);
            startActivity(backIntent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
