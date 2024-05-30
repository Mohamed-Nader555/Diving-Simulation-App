package com.ghada.divingsimulation.Dive.Calculators;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

public class ModCalculatorActivity extends AppCompatActivity {


    private TextInputEditText fo2EditText, po2EditText, modEditText;
    private TextInputLayout fo2TextInputLayout, po2TextInputLayout, modTextInputLayout;
    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_calculator);

        initViews();
    }

    private void initViews() {
        fo2EditText = findViewById(R.id.fo2_et);
        po2EditText = findViewById(R.id.po2_et);
        modEditText = findViewById(R.id.mod_et);

        fo2TextInputLayout = findViewById(R.id.fo2_tvl);
        po2TextInputLayout = findViewById(R.id.po2_tvl);
        modTextInputLayout = findViewById(R.id.mod_tvl);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        handelTextFields();

    }


    private void handelTextFields() {
        final double minFO2 = 0.21;
        final double maxFO2 = 0.5;
        final double minPO2 = 1.4;
        final double maxPO2 = 1.6;

        fo2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (!input.isEmpty()) {
                    try {
                        double value = Double.parseDouble(input);
                        if (value < minFO2 || value > maxFO2) {
                            fo2TextInputLayout.setError("FO2 must be between " + minFO2 + " and " + maxFO2);
                        } else {
                            fo2TextInputLayout.setError(null);
                        }
                    } catch (NumberFormatException e) {
                        fo2TextInputLayout.setError("Invalid number");
                    }
                } else {
                    fo2TextInputLayout.setError(null);
                }
                updateMOD();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        po2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (!input.isEmpty()) {
                    try {
                        double value = Double.parseDouble(input);
                        if (value < minPO2 || value > maxPO2) {
                            po2TextInputLayout.setError("PO2 must be between " + minPO2 + " and " + maxPO2);
                        } else {
                            po2TextInputLayout.setError(null);
                        }
                    } catch (NumberFormatException e) {
                        po2TextInputLayout.setError("Invalid number");
                    }
                } else {
                    po2TextInputLayout.setError(null);
                }
                updateMOD();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void updateMOD() {
        double fo2 = 0, po2 = 0;
        double mod = 0;

        if (!fo2EditText.getText().toString().isEmpty()) {
            fo2 = Double.parseDouble(fo2EditText.getText().toString());
        }
        if (!po2EditText.getText().toString().isEmpty()) {
            po2 = Double.parseDouble(po2EditText.getText().toString());
        }

        if (po2 > 0 && fo2 > 0) {
            mod = ((po2 / fo2) - 1) * 10;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String roundedMOD = decimalFormat.format(mod)  +  " m";


        modEditText.setText(roundedMOD);
    }


}