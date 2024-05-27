package com.ghada.divingsimulation.Profile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ghada.divingsimulation.Models.User.Certificates;
import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class AddCertActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "AddCertActivity";

    private TextInputLayout certDateTIL, certOrgTIL, certLevelTIL, certNumberTIL;
    private TextInputEditText certDateET, certOrgET, certLevelET, certNumberET;
    private Spinner certTypeSpinner;
    private ImageView backBtn;
    private CardView saveBtn;
    private String selectedDate;

    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    private CustomProgress mCustomProgress = CustomProgress.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cert);

        initViews();

    }

    private void initViews() {

        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);


        certDateTIL = findViewById(R.id.cert_date_tvl);
        certOrgTIL = findViewById(R.id.cert_org_tvl);
        certLevelTIL = findViewById(R.id.cert_level_tvl);
        certNumberTIL = findViewById(R.id.cert_number_tvl);

        certDateET = findViewById(R.id.cert_date_et);
        certOrgET = findViewById(R.id.cert_org_et);
        certLevelET = findViewById(R.id.cert_level_et);
        certNumberET = findViewById(R.id.cert_number_et);

        certTypeSpinner = findViewById(R.id.cert_type_spinner);

        backBtn = findViewById(R.id.back_btn);
        saveBtn = findViewById(R.id.cert_save_cert_book_btn);

        initPlacesPricesSpinner();


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        certDateTIL.setEndIconOnClickListener(v -> showDatePicker());
        saveBtn.setOnClickListener(v -> saveCertificate());


    }


    private void showDatePicker() {
        Log.e(TAG, "showDatePicker: test");
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddCertActivity.this, R.style.DialogTheme, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.main_color_dark));
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.main_color_dark));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        selectedDate = year + "/" + (month + 1) + "/" + dayOfMonth;
        certDateET.setText(selectedDate);

    }

    private void initPlacesPricesSpinner() {

        ArrayList<String> types = new ArrayList<>();
        types.add("Select Cert Type...");
        types.add("Open Water");
        types.add("Advance Open Water");
        types.add("EFR");
        types.add("Rescue");
        types.add("Nitrox");
        types.add("Deep Diver");
        types.add("Dive Master");
        types.add("Open Water Scuba Instructor");
        types.add("Boat Diver");
        types.add("Cave Diver");
        types.add("Underwater Photography");
        types.add("Side Mount");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        certTypeSpinner.setAdapter(adapter);
        certTypeSpinner.setSelection(0);
    }


    private void saveCertificate() {
        String certDate = certDateET.getText().toString();
        String certOrg = certOrgET.getText().toString();
        String certLevel = certLevelET.getText().toString();
        String certNumber = certNumberET.getText().toString();
        String certType = certTypeSpinner.getSelectedItem().toString();

        // Validate inputs
        if (validateInputs(certDate, certOrg, certLevel, certNumber, certType)) {
            // Create a Certificates object
            Certificates newCertificate = new Certificates(certDate, certOrg, certLevel, certNumber, certType);

            mCustomProgress.showProgress(this, "Loading...", false);
            mUsersRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        userData = snapshot.getValue(UserDataModel.class);
                        ArrayList<Certificates> certificatesList = new ArrayList<>();
                        if (userData.getCertificates() != null) {
                            certificatesList.addAll(userData.getCertificates());
                        }
                        certificatesList.add(newCertificate);
                        mUsersRef.child(getResources().getString(R.string.database_certificates)).setValue(certificatesList);
                    }
                    mCustomProgress.hideProgress();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle onCancelled
                    mCustomProgress.hideProgress();
                }
            });

            Toast.makeText(this, "Certificate saved successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs(String... inputs) {
        for (String input : inputs) {
            if (input.isEmpty() || input.equals("Select Certification Type ...")) {
                Toast.makeText(this, "Please fill all the fields correctly", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


}