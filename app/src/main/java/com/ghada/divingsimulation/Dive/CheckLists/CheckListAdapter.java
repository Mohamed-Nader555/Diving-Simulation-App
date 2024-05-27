package com.ghada.divingsimulation.Dive.CheckLists;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListAdapter.ViewHolder> {

    private final List<String> items;
    private final List<Boolean> itemCheckedStates;

    public CheckListAdapter(List<String> items) {
        this.items = items;
        this.itemCheckedStates = new ArrayList<>(Collections.nCopies(items.size(), false));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checklist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.titleTextView.setText(item);
        holder.checkBox.setChecked(itemCheckedStates.get(position));
        holder.titleTextView.setPaintFlags(itemCheckedStates.get(position) ? holder.titleTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG : holder.titleTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            itemCheckedStates.set(holder.getAdapterPosition(), isChecked);
            holder.titleTextView.setPaintFlags(isChecked ? holder.titleTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG : holder.titleTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void reset() {
        for (int i = 0; i < itemCheckedStates.size(); i++) {
            itemCheckedStates.set(i, false);
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.list_item);
            checkBox = itemView.findViewById(R.id.check_box);
        }
    }
}
