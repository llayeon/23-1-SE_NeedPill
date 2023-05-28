package com.example.medicinealarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<Alarm> alarmList;
    private OnEditClickListener editClickListener;
    private OnDeleteClickListener deleteClickListener;

    int lastPosition = -1;

    public AlarmAdapter(List<Alarm> alarmList,OnDeleteClickListener deleteClickListener,
                        OnEditClickListener editClickListener
    ) {
        this.alarmList = alarmList;
        this.editClickListener = editClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        holder.bind(alarm);
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public void addAlarm(Alarm alarm){
        alarmList.add(alarm);
    }

    public void addAlarm(int position, Alarm alarm){
        alarmList.add(position,alarm);
    }

    public void removeAlarm(int position){
        alarmList.remove(position);
        notifyItemRemoved(position);
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView timeTextView;
        private SwitchCompat switchButton;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.alarmTitle);
            timeTextView = itemView.findViewById(R.id.alarmDetail);
            switchButton = itemView.findViewById(R.id.alarmSwitch);
        }

        public void bind(Alarm alarm) {
            titleTextView.setText(alarm.getTitle());
            timeTextView.setText(alarm.getTime());
            switchButton.setChecked(alarm.isAlarmEnabled());

            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    alarm.setAlarmEnabled(isChecked);
                }
            });
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public interface OnEditClickListener {
        void onEditClick(Alarm alarm);
    }
}
