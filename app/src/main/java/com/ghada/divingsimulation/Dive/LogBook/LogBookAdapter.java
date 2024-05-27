package com.ghada.divingsimulation.Dive.LogBook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Models.User.LogBook;
import com.ghada.divingsimulation.R;

import java.util.List;

public class LogBookAdapter extends RecyclerView.Adapter<LogBookAdapter.ViewHolder> {

    private final List<LogBook> logBookList;

    public LogBookAdapter(List<LogBook> logBookList) {
        this.logBookList = logBookList;
    }

    @NonNull
    @Override
    public LogBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_logbook, parent, false);
        return new LogBookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogBookAdapter.ViewHolder holder, int position) {
        LogBook log = logBookList.get(position);

        holder.titleTextView.setText(log.getDiveSite());
        holder.subTitleTextView.setText(log.getDate());

        holder.logbook_item_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return logBookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, subTitleTextView;
        ConstraintLayout logbook_item_row;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            logbook_item_row = itemView.findViewById(R.id.logbook_item_row);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            subTitleTextView = itemView.findViewById(R.id.sub_title_text_view);
        }
    }
}
