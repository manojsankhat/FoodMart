package com.example.testapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.activities.ActivityVendorStoreProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentChangePassWord extends Fragment {
    private ActivityVendorStoreProfile activityParent;
    private View fragmentView;

    private TextInputLayout tilCurrentPassWord,tilNewPassWord;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activityParent = (ActivityVendorStoreProfile) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_change_password, container, false);
        initUI();
        return fragmentView;
    }

    private void initUI() {
        tilCurrentPassWord=fragmentView.findViewById(R.id.TIL_PASSWORD);
        tilNewPassWord=fragmentView.findViewById(R.id.TIL_NEW_PASSWORD);

        fragmentView.findViewById(R.id.BTN_VERIFY).setOnClickListener(view -> {
            if(tilCurrentPassWord.getEditText().getText().toString().equals(activityParent.getFoodMartApp().getFoodMartVendor().getPassword())){
                tilNewPassWord.setVisibility(View.VISIBLE);
                fragmentView.findViewById(R.id.BTN_CHANGE_PASSWORD).setVisibility(View.VISIBLE);
            }
            else{
                Toast.makeText(activityParent, "Invalid PassWord", Toast.LENGTH_SHORT).show();
            }
        });

        fragmentView.findViewById(R.id.BTN_CHANGE_PASSWORD).setOnClickListener(view -> {
                activityParent.getFoodMartApp().getFirebaseAuth().getCurrentUser().updatePassword(tilNewPassWord.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            activityParent.getFoodMartApp().getFoodMartVendor().setPassword(tilNewPassWord.getEditText().getText().toString());
                            activityParent.getFoodMartApp().getDbRefVendor().setValue(activityParent.getFoodMartApp().getFoodMartVendor()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(activityParent, "PassWord is Changed", Toast.LENGTH_SHORT).show();
                                        activityParent.onBackPressed();
                                    }else{
                                        Toast.makeText(activityParent, "Failed To Update PassWord", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(activityParent, "Failed To update Password", Toast.LENGTH_SHORT).show();
                            System.out.println(task.getException().toString());
                        }
                    }
                });
        });

    }

}
