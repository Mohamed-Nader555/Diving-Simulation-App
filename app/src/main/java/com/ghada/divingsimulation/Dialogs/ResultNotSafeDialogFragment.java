package com.ghada.divingsimulation.Dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import com.ghada.divingsimulation.Common.APIService;
import com.ghada.divingsimulation.Common.WebServiceClient;
import com.ghada.divingsimulation.Models.API.PredictionResponse;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CustomProgress;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResultNotSafeDialogFragment extends DialogFragment {

    String TAG = "ResultNotSafeDialogFragment";

    View view;
    TextView mResponse, recommendationTV;
    String result = "";
    String maxDepth = "";
    String bottomTime = "";
    String o2 = "";
    String ppo2 = "";
    String surfaceInterval = "";
    String diveNumber = "";
    String Output = "";
    private CardView recommendBtn;
    CustomProgress mCustomProgress = CustomProgress.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.result_not_safe_dialog_fragment, container, false);

        result = getArguments().getString("result");
        maxDepth = getArguments().getString("maxDepth");
        bottomTime = getArguments().getString("bottomTime");
        o2 = getArguments().getString("o2");
        ppo2 = getArguments().getString("ppo2");
        surfaceInterval = getArguments().getString("surfaceInterval");
        diveNumber = getArguments().getString("diveNumber");
        initViews();


        return view;
    }

    private void initViews() {
        Output = result;
        mResponse = view.findViewById(R.id.response);
        recommendBtn = view.findViewById(R.id.recommend_btn);
        recommendationTV = view.findViewById(R.id.recommendation_tv);

        recommendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendDive();
            }
        });

        mResponse.setText(result);

    }


//    public void recommendDive() {
//
//
//        Log.e(TAG, "recommendDive: this is maxDepth before call " + maxDepth);
//        Log.e(TAG, "recommendDive: this is bottomTime before call " + bottomTime);
//        Log.e(TAG, "recommendDive: this is ppo2 before call " + ppo2);
//
////        while (Output.equals("The dive is not safe.") || Output.equals("There is no Result")) {
//
//            int maxDepthInt = Integer.parseInt(maxDepth) - 2;
//            int bottomTimeInt = Integer.parseInt(bottomTime) - 2;
//
//            if (maxDepthInt <= 0 || bottomTimeInt <= 0) {
//                maxDepth = String.valueOf(18);
//                bottomTime = String.valueOf(25);
//                ppo2 = updatePPO2();
//            } else {
//                maxDepth = String.valueOf(maxDepthInt);
//                bottomTime = String.valueOf(bottomTimeInt);
//                ppo2 = updatePPO2();
//
//            }
//            Log.e(TAG, "recommendDive: this is maxDepth after call " + maxDepth);
//            Log.e(TAG, "recommendDive: this is bottomTime after call " + bottomTime);
//            Log.e(TAG, "recommendDive: this is ppo2 after call " + ppo2);
//
//            mCustomProgress.showProgress(getContext(), "Please Wait...", false);
//
//            Map<String, String> map = new HashMap<>();
//            map.put("max_depth", maxDepth);
//            map.put("bottom_time", bottomTime);
//            map.put("O2", o2);
//            map.put("PPO2", ppo2);
//            map.put("surface_interval", surfaceInterval);
//            map.put("dive_number", diveNumber);
//
//
//            APIService apiService = WebServiceClient.getAIRetrofit().create(APIService.class);
//            Call<PredictionResponse> call = apiService.Prediction_AI(map);
//
//            call.enqueue(new Callback<PredictionResponse>() {
//                @Override
//                public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
//                    try {
//                        Log.e(TAG, "onResponse: " + response.code());
//                        Log.e(TAG, "onResponse: " + response.toString());
//                        Log.e(TAG, "onResponse: " + response.body().getOutput());
//                        Output = response.body().getOutput();
//
//                        Log.e(TAG, " -------  -------- recommendDive: this is output : " + Output);
//
//                    } catch (Exception e) {
//                        Output = "There is no Result";
//                        Log.e(TAG, " -------  -------- recommendDive: ERRRRORRRR this is output : " + Output);
//                        Log.e(TAG, " -------  -------- recommendDive: ERRRRORRRR this is output : " + e.getMessage());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<PredictionResponse> call, Throwable t) {
//                    Log.e("Fail", "onFailure: " + t.getMessage());
//                    Output = "There is no Result";
//                    Log.e(TAG, " -------  -------- recommendDive: this is output : " + Output);
//                    Log.e(TAG, " -------  -------- recommendDive: ERROR this is output : " + t.getMessage());
//                }
//            });
////        }
//
//
//        String recommendation = "Be in\nDepth: " + maxDepth + " m\nBottom time: " + bottomTime + " min";
//        recommendationTV.setText(recommendation);
//
//    }



    public void recommendDive() {
        Log.e(TAG, "recommendDive: this is maxDepth before call " + maxDepth);
        Log.e(TAG, "recommendDive: this is bottomTime before call " + bottomTime);
        Log.e(TAG, "recommendDive: this is ppo2 before call " + ppo2);

        mCustomProgress.showProgress(getContext(), "Please Wait...", false);

        attemptDiveRecommendation();


    }

    private void attemptDiveRecommendation() {
        Map<String, String> map = new HashMap<>();
        map.put("max_depth", maxDepth);
        map.put("bottom_time", bottomTime);
        map.put("O2", o2);
        map.put("PPO2", ppo2);
        map.put("surface_interval", surfaceInterval);
        map.put("dive_number", diveNumber);

        APIService apiService = WebServiceClient.getAIRetrofit().create(APIService.class);
        Call<PredictionResponse> call = apiService.Prediction_AI(map);

        call.enqueue(new Callback<PredictionResponse>() {
            @Override
            public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
                try {
                    Log.e(TAG, "onResponse: " + response.code());
                    Log.e(TAG, "onResponse: " + response.toString());
                    Log.e(TAG, "onResponse: " + response.body().getOutput());
                    Output = response.body().getOutput();

                    Log.e(TAG, " -------  -------- recommendDive: this is output : " + Output);

                    if (Output.equals("The dive is not safe.") || Output.equals("There is no Result")) {
                        adjustDiveParameters();
                    } else {
                        String recommendation = "The Suggested Dive is\nDepth: " + maxDepth + " m\nBottom time: " + bottomTime + " min";
                        recommendationTV.setText(recommendation);
                        recommendationTV.setVisibility(View.VISIBLE);
                        recommendBtn.setVisibility(View.INVISIBLE);
                        mCustomProgress.hideProgress();

                    }

                } catch (Exception e) {
                    Output = "There is no Result";
                    Log.e(TAG, " -------  -------- recommendDive: ERRRRORRRR this is output : " + Output);
                    Log.e(TAG, " -------  -------- recommendDive: ERRRRORRRR this is output : " + e.getMessage());
                    adjustDiveParameters();
                }
            }

            @Override
            public void onFailure(Call<PredictionResponse> call, Throwable t) {
                Log.e("Fail", "onFailure: " + t.getMessage());
                Output = "There is no Result";
                Log.e(TAG, " -------  -------- recommendDive: this is output : " + Output);
                Log.e(TAG, " -------  -------- recommendDive: ERROR this is output : " + t.getMessage());
                adjustDiveParameters();
            }
        });
    }

    private void adjustDiveParameters() {
        int maxDepthInt = Integer.parseInt(maxDepth) - 2;
        int bottomTimeInt = Integer.parseInt(bottomTime) - 2;

        if (maxDepthInt <= 0 || bottomTimeInt <= 0) {
            maxDepth = String.valueOf(18);
            bottomTime = String.valueOf(25);
            ppo2 = updatePPO2();
        } else {
            maxDepth = String.valueOf(maxDepthInt);
            bottomTime = String.valueOf(bottomTimeInt);
            ppo2 = updatePPO2();
        }

        Log.e(TAG, "adjustDiveParameters: this is maxDepth after call " + maxDepth);
        Log.e(TAG, "adjustDiveParameters: this is bottomTime after call " + bottomTime);
        Log.e(TAG, "adjustDiveParameters: this is ppo2 after call " + ppo2);

        attemptDiveRecommendation();
    }




    private String updatePPO2() {
        double o2Double = Integer.parseInt(o2);
        double maxDepthDouble = Integer.parseInt(maxDepth);
        double ppo2ToCalc = (o2Double / 100) * ((maxDepthDouble / 10) + 1);
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String calcPPO2 = decimalFormat.format(ppo2ToCalc);
        return calcPPO2;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
