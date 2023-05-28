package com.example.medicinealarm;

import android.util.Log;
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
    static CalendarViewHolder calendarViewHolder;
    View view;

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        //this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.1666666);
        calendarViewHolder = new CalendarViewHolder(view, onItemListener);
        return calendarViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);

        void applyEmoji(int id);
    }


public void attachEmoji(int id, int position, RecyclerView calendarRecyclerView) {

    RecyclerView.ViewHolder viewHolder = calendarRecyclerView.findViewHolderForAdapterPosition(position);
    if (viewHolder instanceof CalendarViewHolder) {
        CalendarViewHolder calendarViewHolder = (CalendarViewHolder) viewHolder;

        ImageView emojiOfDay = calendarViewHolder.emojiOfDay;

        if(calendarRecyclerView == null){
            Log.d("CalendarAdapter","calendarRecyclerView is null");
            return;
        }
        Log.d("CalendarAdapter","attachEmoji called. position : "+position);
        if (id == R.id.emoji_verygood) {
            emojiOfDay.setImageResource(R.drawable.verygood);
        } else if (id == R.id.emoji_good) {
            emojiOfDay.setImageResource(R.drawable.good);
        } else if (id == R.id.emoji_normal) {
            emojiOfDay.setImageResource(R.drawable.normal);
        } else if (id == R.id.emoji_bad) {
            emojiOfDay.setImageResource(R.drawable.bad);
        } else if (id == R.id.emoji_verybad) {
            emojiOfDay.setImageResource(R.drawable.verybad);
        } else {
            // 선택된 Emoji가 없는 경우 이미지 제거
            emojiOfDay.setImageDrawable(null);
        }
    }
}

}