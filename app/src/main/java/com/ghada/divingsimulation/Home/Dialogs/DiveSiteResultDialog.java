package com.ghada.divingsimulation.Home.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ghada.divingsimulation.Models.Diving.DiveSite;
import com.ghada.divingsimulation.R;

public class DiveSiteResultDialog extends DialogFragment {

    //Views
    private View view;
    private ImageView mDialogImage, mDialogClose;
    private TextView mDialogAddress, mDialogName, mDialogMaxDepth, mDialogEntry, mDialogBottom, mDialogAquaticLife, mDialogRate;

    //Variables
    private DiveSite diveSiteItem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.dive_site_result_dialog_fragment, container, false);

        diveSiteItem = (DiveSite) getArguments().getSerializable("result");

        initViews();

        return view;
    }

    private void initViews() {
        mDialogClose = view.findViewById(R.id.dialog_close);
        mDialogImage = view.findViewById(R.id.dialog_image);

        mDialogName = view.findViewById(R.id.dialog_name);
        mDialogAddress = view.findViewById(R.id.dialog_address);

        mDialogMaxDepth = view.findViewById(R.id.dialog_max_depth);
        mDialogEntry = view.findViewById(R.id.dialog_entry_type);
        mDialogBottom = view.findViewById(R.id.dialog_bottom);
        mDialogAquaticLife = view.findViewById(R.id.dialog_aquatic_life);
        mDialogRate = view.findViewById(R.id.dialog_rate);

        mDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        fillData();
    }

    private void fillData() {

        int resourceId = requireActivity().getResources().getIdentifier(diveSiteItem.getImg(), "drawable", requireActivity().getPackageName());
        Glide.with(requireActivity()).load(resourceId)
                .apply(new RequestOptions().override(200, 200))
                .into(mDialogImage);


        mDialogName.setText(diveSiteItem.getName());
        mDialogAddress.setText(diveSiteItem.getLocation());
        mDialogMaxDepth.setText("Max Depth:" + diveSiteItem.getMaxDepth());
        mDialogEntry.setText("Entry Type: " + diveSiteItem.getEntryType());
        mDialogBottom.setText("Bottom Composition: " + diveSiteItem.getBottomComposition());
        mDialogAquaticLife.setText("Aquatic Life: " + diveSiteItem.getAquaticLife());
        mDialogRate.setText("Rating:" + diveSiteItem.getRating());
    }


}