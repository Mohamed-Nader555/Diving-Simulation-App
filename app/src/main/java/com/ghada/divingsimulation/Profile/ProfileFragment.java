package com.ghada.divingsimulation.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ghada.divingsimulation.Dialogs.AddCertDialogFragment;
import com.ghada.divingsimulation.Dialogs.MedicalDialogFragment;
import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Splash.SplashActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class ProfileFragment extends Fragment {

    ProgressDialog mLoading;
    AddCertDialogFragment addCertDialog = new AddCertDialogFragment();
    MedicalDialogFragment medicalDialog = new MedicalDialogFragment();
    Button mAddCertBtn, mMedicalInformationBtn;
    TextView mNameTV, mEmailTV;
    private View view;
    private Button logoutBtn;
    private String currentUserID;
    private FirebaseUser user;
    private DatabaseReference mUsersRef;
    private FirebaseAuth mAuth;
    private StorageReference mUserProfileImageRef;
    private UserDataModel userData;
    private GoogleSignInClient mGoogleSignInClient;
    private AuthCredential credential;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        mLoading = new ProgressDialog(getContext());
        mAuth = FirebaseAuth.getInstance();


        try {
            user = mAuth.getCurrentUser();
            currentUserID = user.getUid();
            mUsersRef = FirebaseDatabase.getInstance().getReference(getString(R.string.users_reference)).child(currentUserID);
            //mUserProfileImageRef = FirebaseStorage.getInstance().getReference().child(getString(R.string.users_profile_image));
        } catch (NullPointerException e) {
            Log.e("ProfileFragment", "initViews: " + e.getMessage());
        }


        mAddCertBtn = view.findViewById(R.id.add_cert_btn);
        mMedicalInformationBtn = view.findViewById(R.id.medical_information_btn);
        mNameTV = view.findViewById(R.id.name_text);
        mEmailTV = view.findViewById(R.id.email_text);


        getData();

        mAddCertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCertDialog.show(((FragmentActivity) getContext()).getSupportFragmentManager(), "AddCertDialogFragment");
            }
        });

        mMedicalInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicalDialog.show(((FragmentActivity) getContext()).getSupportFragmentManager(), "MedicalDialogFragment");
            }
        });


        logoutBtn = view.findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }


    private void getData() {

        mLoading.setTitle("Getting Your Information");
        mLoading.setMessage("Please Wait Loading...");
        mLoading.show();
        mLoading.setCanceledOnTouchOutside(true);


        mUsersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    userData = snapshot.getValue(UserDataModel.class);
                    String name = userData.getFullName();
                    String email = userData.getEmail();
                    mNameTV.setText(name);
                    mEmailTV.setText(email);


                    mLoading.dismiss();
//
//                    address = userData.getAddress();
//                    try {
//                        password = AESCrypt.decrypt(userData.getPassword());
//                    } catch (Exception e) {
//                        password = userData.getPassword();
//                        Log.e(TAG, "onDataChange: " + e.getMessage());
//                    }
//                    anotherAddress = userData.getAnotherAddress();
//                    phoneNumber = userData.getPhoneNumber();
//                    anotherPhone = userData.getAnotherPhoneNumber();
//                    image = userData.getProfileImage();
//
//
//                    if (password.equals(""))
//                        havePassword = 0;
//                    else
//                        havePassword = 1;
//
//                    if (havePassword == 0) {
//                        mOldPassword.setVisibility(View.GONE);
//                        mShowOld.setVisibility(View.GONE);
//
//                    }


//                    mFirstName.setText(firstName);
//                    mLastName.setText(lastName);
//                    mAddress.setText(address);
//                    mAnotherAddress.setText(anotherAddress);
//                    mPhoneNumber.setText(phoneNumber);
//                    mAnotherPhoneNumber.setText(anotherPhone);
//                    try {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (image.equals("none") || image.equals(""))
//                                    Glide.with(getContext()).load(R.drawable.user).into(mProfileImage);
//                                else
//                                    Glide.with(getContext()).load(image).into(mProfileImage);
//                                mLoading.dismiss();
//                            }
//                        }, 100);
//                    } catch (Exception e) {
//                        Log.e(TAG, "onDataChange: getData : " + e.getMessage());
//                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void logout() {
        mGoogleSignInClient.signOut();
        mAuth.signOut();
        Intent userLogout = new Intent(getContext(), SplashActivity.class);
        userLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        userLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(userLogout);
        getActivity().finish();

    }

}