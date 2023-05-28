package com.example.medicinealarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<Alarm> alarmList;
    private RecyclerView recyclerView;
    private AlarmAdapter alarmAdapter;
    ImageButton calendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        alarmList = new ArrayList<>();

        // RecyclerView 초기화
        recyclerView = findViewById(R.id.alarmListContainer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        alarmAdapter = new AlarmAdapter(alarmList, new AlarmAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteAlarmItem(position);
            }
        }, new AlarmAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(Alarm alarm) {
                editAlarmItem(alarm);
            }
        });
        recyclerView.setAdapter(alarmAdapter);

        calendarButton = findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 알람 추가 버튼 초기화
        ImageButton addAlarmButton = findViewById(R.id.addAlarmButton);
        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlarmSettingDialog();
                Toast.makeText(HomeActivity.this, "Add Button Click", Toast.LENGTH_SHORT).show();
            }
        });

        // 알람 삭제 버튼(휴지통) 초기화
        ImageButton deleteAlarmButton = findViewById(R.id.deleteAlarmButton);
        deleteAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Del Button Click", Toast.LENGTH_SHORT).show();
            }
        });

        // 슬라이드 시 삭제
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                switch (direction) {
                    // 왼쪽으로 스와이프 했을 때 삭제
                    case ItemTouchHelper.LEFT:
                        // 삭제할 아이템 담아두기
                        Alarm deleteAlarm = alarmList.get(position);

                        // 삭제 기능
                        alarmAdapter.removeAlarm(position);
                        alarmAdapter.notifyItemRemoved(position);

                        // 복구 기능
                        Snackbar.make(recyclerView, deleteAlarm.getTitle(), Snackbar.LENGTH_LONG)
                                .setAction("복구",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view){
                                        alarmAdapter.addAlarm(position,deleteAlarm);
                                        alarmAdapter.notifyItemInserted(position);
                                    }
                                }).show();
                        break;

                }
            }

            // 삭제할 때 decorator
//            @Override
//            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//
//                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                        .addSwipeLeftBackgroundColor(Color.RED)
//                        .addSwipeLeftActionIcon(R.drawable.delete_button)
//                        .addSwipeLeftLabel("삭제")
//                        .setSwipeLeftLabelColor(Color.WHITE)
//                        .create()
//                        .decorate();
//
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }
        }).attachToRecyclerView(recyclerView);

    }

    private void openAlarmSettingDialog() {
        // 알람 설정 창을 띄우는 로직 구현
        // 사용자로부터 알람 정보를 입력받고, Alarm 객체를 생성하여 리스트에 추가
        // alarmList.add(newAlarm);
        // alarmAdapter.notifyDataSetChanged();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알람 설정");

        // === 이 아래로 알람 설정 화면에 관한 내용 ===

//        // 알람 설정을 위한 레이아웃 파일을 inflate하여 설정 창에 표시
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View dialogView = inflater.inflate(R.layout.dialog_alarm_setting, null);
//        builder.setView(dialogView);
//
//        // 알람 설정을 위한 필요한 뷰들을 찾음
//        EditText alarmNameEditText = dialogView.findViewById(R.id.alarmNameEditText);
//        TimePicker timePicker = dialogView.findViewById(R.id.timePicker);
//        Switch enableSwitch = dialogView.findViewById(R.id.enableSwitch);

        // === 이 위로 알람 설정 화면에 관한 내용 ===

        // 확인 버튼 클릭 시 알람 설정 정보를 가져와서 리스트에 추가하고 어댑터를 갱신
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                String alarmName = alarmNameEditText.getText().toString();
//                int hour = timePicker.getCurrentHour();
//                int minute = timePicker.getCurrentMinute();
//                boolean isEnabled = enableSwitch.isChecked();

                String alarmName = "약 이름이 들어감";
                int id = 10;
                String time = "10:40";
                boolean isEnabled = true;

                // 알람 객체 생성 및 리스트에 추가
                Alarm alarm = new Alarm(id, alarmName, time);
                alarmList.add(alarm);
                alarmAdapter.notifyDataSetChanged();
                Toast.makeText(HomeActivity.this, "추가!", Toast.LENGTH_SHORT).show();
            }
        });

        // 취소 버튼 클릭 시 창을 닫음
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // 알람 설정 창을 표시
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteAlarmItem(int position) {
        // 알람 아이템 삭제 로직 구현


        // alarmList.remove(position);
        // alarmAdapter.notifyDataSetChanged();
    }

    private void editAlarmItem(Alarm alarm) {
        // 알람 아이템 편집 로직 구현
        // 사용자로부터 편집할 알람 정보를 입력받고, 해당 Alarm 객체의 정보 업데이트
        // alarmAdapter.notifyDataSetChanged();
    }

}