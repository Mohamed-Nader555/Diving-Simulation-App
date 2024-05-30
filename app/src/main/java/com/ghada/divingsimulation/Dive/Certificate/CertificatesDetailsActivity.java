package com.ghada.divingsimulation.Dive.Certificate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Models.User.Certificates;
import com.ghada.divingsimulation.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Collections;
import java.util.List;

public class CertificatesDetailsActivity extends AppCompatActivity {

    private Certificates certificate;

    private TextInputEditText certDateET, certOrgET, certNumberET;
    private Spinner certTypeSpinner, certLevelSpinner;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates_details);

        initViews();

    }

    private void initViews() {


        Intent intent = getIntent();
        certificate = (Certificates) intent.getSerializableExtra("item");

        certDateET = findViewById(R.id.cert_date_et);
        certOrgET = findViewById(R.id.cert_org_et);

        certNumberET = findViewById(R.id.cert_number_et);

        certTypeSpinner = findViewById(R.id.cert_type_spinner);
        certLevelSpinner = findViewById(R.id.cert_level_spinner);

        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        fillDataFromCertification(certificate);

    }


    private void fillDataFromCertification(Certificates certification) {
        // Fill the data
        certDateET.setText(certification.getCertDate());
        certOrgET.setText(certification.getCertOrg());

        certNumberET.setText(certification.getCertNumber());
        // Set Spinner value
        populateSpinner(certTypeSpinner, certification.getCetType());
        populateSpinner(certLevelSpinner, certification.getCertLevel());
        // Disable all inputs
        disableCertificationInputs();
    }

    private void disableCertificationInputs() {
        certDateET.setEnabled(false);
        certOrgET.setEnabled(false);
        certNumberET.setEnabled(false);
        certTypeSpinner.setEnabled(false);
        certLevelSpinner.setEnabled(false);
    }


    private void populateSpinner(Spinner spinner, String certificationType) {
        List<String> certTypes = Collections.singletonList(certificationType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, certTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setEnabled(false);
    }

}