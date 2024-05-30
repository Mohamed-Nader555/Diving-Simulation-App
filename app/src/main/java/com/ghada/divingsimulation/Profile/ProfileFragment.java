package com.ghada.divingsimulation.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ghada.divingsimulation.Auth.EditProfileActivity;
import com.ghada.divingsimulation.Dialogs.AddCertDialogFragment;
import com.ghada.divingsimulation.Dialogs.MedicalDialogFragment;
import com.ghada.divingsimulation.Dive.Certificate.CertificateActivity;
import com.ghada.divingsimulation.Dive.Medical.AddMedicalActivity;
import com.ghada.divingsimulation.Dive.Medical.ShowMedicalActivity;
import com.ghada.divingsimulation.Models.User.LogBook;
import com.ghada.divingsimulation.Models.User.Medical;
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

import java.text.DecimalFormat;

public class ProfileFragment extends Fragment {

    ProgressDialog mLoading;
    AddCertDialogFragment addCertDialog = new AddCertDialogFragment();
    MedicalDialogFragment medicalDialog = new MedicalDialogFragment();
    CardView mAddCertBtn, mMedicalInformationBtn;
    TextView mNameTV, mEmailTV;
    ImageView editProfileImage;
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
    TextView total_dives_et, total_dive_time_et, avg_depth_et, max_depth_et;


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


        mAddCertBtn = view.findViewById(R.id.add_cert_card_view);
        mMedicalInformationBtn = view.findViewById(R.id.medical_information_card_view);
        mNameTV = view.findViewById(R.id.name_text);
        mEmailTV = view.findViewById(R.id.email_text);
        editProfileImage = view.findViewById(R.id.edit_profile_image);
        total_dives_et = view.findViewById(R.id.total_dives_et);
        total_dive_time_et = view.findViewById(R.id.total_dive_time_et);
        avg_depth_et = view.findViewById(R.id.avg_depth_et);
        max_depth_et = view.findViewById(R.id.max_depth_et);

        getData();

        mAddCertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), CertificateActivity.class);
                startActivity(intent);
            }
        });


        editProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), EditProfileActivity.class);
                startActivity(intent);
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


                    if(userData.getLogBook() != null){

                        String totalDives = String.valueOf(userData.getLogBook().size());
                        String totalDiveTime;
                        int totalDiveTimeInt = 0 ;
                        int maxDepth = 0 ;
                        double totalMaxDepth = 0;
                        double avgDepth = 0 ;
                        for (LogBook log: userData.getLogBook()) {
                                totalDiveTimeInt += Integer.parseInt(log.getBottomTime());
                                if(Integer.parseInt(log.getMaxDepth()) > maxDepth){
                                    maxDepth = Integer.parseInt(log.getMaxDepth());
                                }
                                totalMaxDepth += Integer.parseInt(log.getMaxDepth());
                        }
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        avgDepth = totalMaxDepth / userData.getLogBook().size();
                        avgDepth = Double.parseDouble(decimalFormat.format(avgDepth));
                        totalDiveTime = totalDiveTimeInt + " min";

                        total_dives_et.setText(totalDives);
                        total_dive_time_et.setText(totalDiveTime);
                        avg_depth_et.setText(avgDepth + " m");
                        max_depth_et.setText(maxDepth + " m");
                    }else {
                        total_dives_et.setText("No Dives");
                        total_dive_time_et.setText("0 min");
                        avg_depth_et.setText("0 m");
                        max_depth_et.setText("0 m");
                    }

                    Medical medical = userData.getMedical();

                    if (medical != null) {
                        mMedicalInformationBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(requireContext(), ShowMedicalActivity.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        mMedicalInformationBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(requireContext(), AddMedicalActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

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