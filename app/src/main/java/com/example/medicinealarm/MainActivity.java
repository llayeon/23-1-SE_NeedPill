package com.example.medicinealarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener, CustomDialog.CustomDialogListener{
    int emoji_id;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    ArrayList<String> daysInMonth;
    CalendarViewHolder calendarViewHolder;
    CalendarAdapter calendarAdapter;
    //recyclerview에서 celltext가 현재날짜랑 같은 날 오늘표시
    //getemoji에서 받은 id값으로 cell에 이모티콘 표시
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();

        selectedDate = LocalDate.now();
        daysInMonth = daysInMonthArray(selectedDate);
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        //CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        calendarAdapter=new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i=1;i<=42;i++){
            if(i<=dayOfWeek || i> daysInMonth+dayOfWeek){
                //blank square
                daysInMonthArray.add("");
            }else{
                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter dateTimeFormatter = null;
        dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM YYYY");

        return date.format(dateTimeFormatter);

    }
    public void previousMonthAction(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
   }

   public void nextMonthAction(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
   }
    {}
    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String msg = "Selected Date "+position+" "+monthYearFromDate(selectedDate);
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }else{
            showMemoDialog(position);
            //
            calendarAdapter.attachEmoji(emoji_id,position);
        }
    }

    private void showMemoDialog(int position) {

        CustomDialog customDialog = new CustomDialog();
        customDialog.show(getSupportFragmentManager(),"custom dialog");

    }

    @Override
    public void applyEmoji(int id) {
        //저장한 이모티콘 보여주기
        //position이 ..인 cell에 이모티콘 보여주기
        //int emoji = id;
//        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
//        calendarAdapter.attachEmoji(emoji);
        //calendarAdapter.attachEmoji(id);
        emoji_id=id;
    }

}
