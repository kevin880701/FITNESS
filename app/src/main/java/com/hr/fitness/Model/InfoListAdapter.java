package com.hr.fitness.Model;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.hr.fitness.R;
import com.hr.fitness.ui.health.EditData.EditDataPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.ViewHolder> {
    ArrayList<HealthRecord> healthRecordList;
    EditDataPresenter presnter;

    boolean clickStatus = false;
    public int CurrentPosition;
    public int currentId;

    public InfoListAdapter(ArrayList recordList, EditDataPresenter presnter) {
        this.healthRecordList = recordList;
        this.presnter = presnter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickStatus = true;
                if(!(position == CurrentPosition)){
                    healthRecordList.get(CurrentPosition).setBeenClick(false);
//                    holder.date.setTextColor(Color.parseColor("#dc143c"));
                }
                CurrentPosition=position;
                healthRecordList.get(position).setBeenClick(!healthRecordList.get(position).isBeenClick());
                currentId = healthRecordList.get(position).getId();
                notifyDataSetChanged();
            }
        });


        if(healthRecordList.get(position).getGender() == 0){
            holder.gender.setText("女");
        }else{
            holder.gender.setText("男");
        }
        holder.height.setText( healthRecordList.get(position).getHeight());
        holder.weight.setText(healthRecordList.get(position).getWeight());
        holder.waistline.setText(healthRecordList.get(position).getWaistline());
        holder.bmi.setText(healthRecordList.get(position).getBMI());
        holder.bodyFat.setText(healthRecordList.get(position).getBodyFat());
        holder.date.setText(healthRecordList.get(position).getDate().substring(5,10));
        if(healthRecordList.get(position).isBeenClick()){
            holder.gender.setTextColor(Color.parseColor("#dc143c"));
            holder.height.setTextColor(Color.parseColor("#dc143c"));
            holder.weight.setTextColor(Color.parseColor("#dc143c"));
            holder.waistline.setTextColor(Color.parseColor("#dc143c"));
            holder.bmi.setTextColor(Color.parseColor("#dc143c"));
            holder.bodyFat.setTextColor(Color.parseColor("#dc143c"));
            holder.date.setTextColor(Color.parseColor("#dc143c"));
        }else{
            holder.gender.setTextColor(Color.parseColor("#000000"));
            holder.height.setTextColor(Color.parseColor("#000000"));
            holder.weight.setTextColor(Color.parseColor("#000000"));
            holder.waistline.setTextColor(Color.parseColor("#000000"));
            holder.bmi.setTextColor(Color.parseColor("#000000"));
            holder.bodyFat.setTextColor(Color.parseColor("#000000"));
            holder.date.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        return healthRecordList.size();
    }

    public int getCurrentPosition() {
        return CurrentPosition;
    }

    public int getCurrentId() {
        return currentId;
    }

    public boolean isClickStatus() {
        return clickStatus;
    }

    public void setClickStatus(boolean clickStatus) {
        this.clickStatus = clickStatus;
    }
}
