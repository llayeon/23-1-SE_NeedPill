package com.example.medicinealarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    //한달 안에 일수
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    CalendarViewHolder calendarViewHolder;
    View view;
    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener){
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.calendar_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.1666666);
        calendarViewHolder= new CalendarViewHolder(view, onItemListener);
        return calendarViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }

    public void attachEmoji(int id,int position) {
        //저장한 이모티콘 보여주기
        //position이 ..인 cell에 이모티콘 보여주기

        ImageView img= view.findViewById(R.id.cell_emoji);
        //position인 cell의 emoji
        if (id == R.id.emoji_good) {
            img.setImageResource(R.drawable.good);
            //notifyItemChanged(position);
        }
    }

}
