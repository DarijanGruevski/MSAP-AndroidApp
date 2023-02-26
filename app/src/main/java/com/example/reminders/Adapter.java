package com.example.reminders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {

    LayoutInflater inflater;
    List<Reminder> reminders;

    public Adapter(Context context, List<Reminder> reminders){
        this.inflater = LayoutInflater.from(context);
        this.reminders = reminders;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Note, Date , Time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Note = itemView.findViewById(R.id.Note);
            Date = itemView.findViewById(R.id.Datum);
            Time = itemView.findViewById(R.id.Vreme);
        }
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.reminder_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.Note.setText(reminders.get(position).getNote());
        holder.Date.setText(reminders.get(position).getDate());
        holder.Time.setText(reminders.get(position).getTime());


    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }
}
