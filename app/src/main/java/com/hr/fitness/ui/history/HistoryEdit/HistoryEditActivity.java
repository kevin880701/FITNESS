package com.hr.fitness.ui.history.HistoryEdit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fitness.MainActivity;
import com.hr.fitness.R;
import com.hr.fitness.ui.health.EnterData.EnterDataActivity;

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

    EditText editNewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_edit);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        editNewItem = new EditText(this);
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
                break;
            case R.id.linearAddItem:
                Log.v("PPP", "AddItem Click");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("新增項目")
                        .setView(editNewItem)
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
