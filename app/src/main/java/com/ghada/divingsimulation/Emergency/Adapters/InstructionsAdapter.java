package com.ghada.divingsimulation.Emergency.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Models.Diving.Instructions;
import com.ghada.divingsimulation.R;

import java.util.ArrayList;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Instructions> instList;


    public InstructionsAdapter(Context context, ArrayList<Instructions> instList) {
        this.context = context;
        this.instList = instList;

    }

    @NonNull
    @Override
    public InstructionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instruction, parent, false);

        InstructionsAdapter.ViewHolder viewHolder = new InstructionsAdapter.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsAdapter.ViewHolder holder, int position) {

        Instructions inst = instList.get(position);
        holder.title.setText(inst.getInstructionTitle());
        holder.details.setText(inst.getInstructionDetails());

    }

    @Override
    public int getItemCount() {
        return instList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView title, details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.title_text_view);
            details = itemView.findViewById(R.id.sub_title_text_view);

        }
    }


}
