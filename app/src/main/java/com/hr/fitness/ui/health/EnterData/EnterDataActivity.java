package com.hr.fitness.ui.health.EnterData;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fitness.MainActivity;
import com.hr.fitness.R;
import com.hr.fitness.SQLite.SQLiteDBHelper;
import com.hr.fitness.ui.health.EditData.EditDataActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterDataActivity extends AppCompatActivity {

    EnterDataPresenter presnter;
    int gender = 1;

    SQLiteDBHelper dbHelper;
    SQLiteDatabase listDB;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.titleBar)
    LinearLayout titleBar;
    @BindView(R.id.boy)
    RadioButton boy;
    @BindView(R.id.girl)
    RadioButton girl;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.heightEdit)
    EditText heightEdit;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.weightEdit)
    EditText weightEdit;
    @BindView(R.id.layout3)
    LinearLayout layout3;
    @BindView(R.id.waistlineEdit)
    EditText waistlineEdit;
    @BindView(R.id.layout4)
    LinearLayout layout4;
    @BindView(R.id.complete)
    Button complete;
    @BindView(R.id.layout1)
    LinearLayout layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        dbHelper = new SQLiteDBHelper(this);
        listDB = dbHelper.getWritableDatabase();
        presnter = new EnterDataPresenter(listDB);
    }


    @OnClick(R.id.complete)
    public void onViewClicked() {
        String height = heightEdit.getText().toString();
        String weight = weightEdit.getText().toString();
        String waistline = waistlineEdit.getText().toString();
        if (height.equals("")) {
            Toast.makeText(this, "未輸入身高", Toast.LENGTH_LONG).show();
        } else if (weight.equals("")) {
            Toast.makeText(this, "未輸入體重", Toast.LENGTH_LONG).show();
        } else if (waistline.equals("")) {
            Toast.makeText(this, "未輸入腰圍", Toast.LENGTH_LONG).show();
        } else {
            presnter.insertDB(gender, height, weight, waistline);
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
        }
    }

    @OnClick({R.id.boy, R.id.girl, R.id.back, R.id.radioGroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.boy:
                gender = 1;
                break;
            case R.id.girl:
                gender = 0;
                break;
            case R.id.back:
                Intent backIntent = new Intent(this, EditDataActivity.class);
                startActivity(backIntent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent backIntent = new Intent(this, EditDataActivity.class);
            startActivity(backIntent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
