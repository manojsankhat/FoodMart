package com.example.testapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.activities.ActivityLocationPicker;
import com.example.testapp.activities.ActivityVendorStoreProfile;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentStoreProfile extends Fragment {
    private ActivityVendorStoreProfile activityParent;
    private View fragmentView;
    private TextInputLayout tilName, tilPhone, tilPasWord, tilLocation;

    public TextInputLayout getTilName() {
        return tilName;
    }

    public TextInputLayout getTilPhone() {
        return tilPhone;
    }

    public TextInputLayout getTilPasWord() {
        return tilPasWord;
    }

    public TextInputLayout getTilLocation() {
        return tilLocation;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activityParent = (ActivityVendorStoreProfile) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_store_profile, container, false);
        initUI();
        return fragmentView;
    }

    private void initUI() {
        tilName = fragmentView.findViewById(R.id.TIL_NAME);
        tilPhone = fragmentView.findViewById(R.id.TIL_PHONE);
        tilPasWord = fragmentView.findViewById(R.id.TIL_PASSWORD);
        tilLocation = fragmentView.findViewById(R.id.TIL_LOCATION);

        tilName.getEditText().setText(activityParent.getFoodMartApp().getFoodMartVendor().getName());
        tilPhone.getEditText().setText(activityParent.getFoodMartApp().getFoodMartVendor().getPhone());
        tilPasWord.getEditText().setText(activityParent.getFoodMartApp().getFoodMartVendor().getPassword());
        tilLocation.getEditText().setText(activityParent.getFoodMartApp().getFoodMartVendor().getStallLocation() == null ? "" : activityParent.getFoodMartApp().getFoodMartVendor().getStallLocation());

        tilPasWord.getEditText().setOnClickListener(view -> {
            activityParent.loadFragment(activityParent.getFragmentChangePassWord(), true);
            activityParent.getBtnUpdateProfile().setVisibility(View.INVISIBLE);
        });

        tilLocation.getEditText().setOnClickListener(view -> {
           startActivity(new Intent(activityParent, ActivityLocationPicker.class));
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        activityParent.getBtnUpdateProfile().setVisibility(View.VISIBLE);
    }
}
