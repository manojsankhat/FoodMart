package com.example.testapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.testapp.FoodMartApp;
import com.example.testapp.R;
import com.example.testapp.fragments.FragmentChangePassWord;
import com.example.testapp.fragments.FragmentStoreProfile;

public class ActivityVendorStoreProfile extends AppCompatActivity {

    private FragmentStoreProfile fragmentStoreProfile;
    private FoodMartApp foodMartApp;
    private Uri imgURI;
    private ImageView imgStore;

    public Button getBtnUpdateProfile() {
        return btnUpdateProfile;
    }

    private Button btnUpdateProfile;

    public FragmentChangePassWord getFragmentChangePassWord() {
        return fragmentChangePassWord;
    }

    private FragmentChangePassWord fragmentChangePassWord;

    public FoodMartApp getFoodMartApp() {
        return foodMartApp;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        foodMartApp = (FoodMartApp) getApplication();
        fragmentStoreProfile = new FragmentStoreProfile();
        fragmentChangePassWord=new FragmentChangePassWord();
        imgStore = findViewById(R.id.IMG_VENDOR);
        btnUpdateProfile=findViewById(R.id.BTN_UPDATE_PROFILE);

        findViewById(R.id.BTN_PICK_IMG).setOnClickListener(view -> {
            Intent intentImgPicker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            intentImgPicker.setType("image/*");
            intentImgPicker.putExtra("return-data", true);
            startActivityForResult(intentImgPicker, 13);
        });

        loadFragment(fragmentStoreProfile, false);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();


    }

    public void loadFragment(Fragment fragment, boolean goBack) {
        if (goBack)
            getSupportFragmentManager().beginTransaction().replace(R.id.CONTAINER, fragment).addToBackStack(fragment.getClass().getName()).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.CONTAINER, fragment).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            imgURI = data.getData();
            imgStore.setImageURI(imgURI);
        }
    }


}
