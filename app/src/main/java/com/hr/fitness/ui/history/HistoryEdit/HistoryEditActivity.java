package com.hr.fitness.ui.history.HistoryEdit;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fitness.MainActivity;
import com.hr.fitness.R;
import com.hr.fitness.SQLite.SQLiteDBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryEditActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.btnSave)
    ImageView btnSave;
    @BindView(R.id.linearAddItem)
    LinearLayout linearAddItem;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.textTitle)
    TextView textTitle;

    HistoryEditPresenter presenter;
    int itemCount = 0;
    SQLiteDBHelper dbHelper;
    SQLiteDatabase historyLiteDB;
    String date;
    Boolean dataExist = false;
    String[] idArray;
    String[] nameArray;
    String[] contentArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_edit);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        Intent intent = this.getIntent();
        date = intent.getStringExtra("date");
        if(intent.getStringArrayExtra("idArray") != null){
            dataExist = true;
            idArray = intent.getStringArrayExtra("idArray");
            nameArray = intent.getStringArrayExtra("nameArray");
            contentArray = intent.getStringArrayExtra("contentArray");
        }

        textTitle.setText(date);

        dbHelper = new SQLiteDBHelper(this);
        historyLiteDB = dbHelper.getWritableDatabase();

        presenter = new HistoryEditPresenter(this, historyLiteDB);
        if(dataExist){
            for(int i=0;i<idArray.length;i++){
                Log.v("PPP","" + nameArray[i]);
                presenter.addItem(nameArray[i],contentArray[i],itemCount);
                itemCount++;
            }
        }
    }

    @OnClick({R.id.back, R.id.btnSave, R.id.linearAddItem})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent backIntent = new Intent(this, MainActivity.class);
                startActivity(backIntent);
                finish();
                break;
            case R.id.btnSave:
                if(dataExist){
                    presenter.updateDB(date);
                }else{
                    presenter.insertDB(date);
                }
                Intent Intent = new Intent(this, MainActivity.class);
                startActivity(Intent);
                finish();
                break;
            case R.id.linearAddItem:
                EditText editNewItem = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("新增項目")
                        .setView(editNewItem).setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.addItem(editNewItem.getText().toString(),"",itemCount);
                        itemCount++;
                    }
                })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
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
