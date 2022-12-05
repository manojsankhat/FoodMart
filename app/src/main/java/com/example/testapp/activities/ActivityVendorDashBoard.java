package com.example.testapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.FoodMartApp;
import com.example.testapp.R;
import com.example.testapp.dialog.CustomDialog;

public class ActivityVendorDashBoard extends AppCompatActivity implements View.OnClickListener {
    private FoodMartApp foodMartApp;

    public FoodMartApp getFoodMartApp() {
        return foodMartApp;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_vendor);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        foodMartApp = (FoodMartApp) getApplication();
        findViewById(R.id.CARD_FOOD_ITEMS).setOnClickListener(ActivityVendorDashBoard.this);
        findViewById(R.id.CARD_FOOD_ORDERS).setOnClickListener(ActivityVendorDashBoard.this);
        findViewById(R.id.CARD_ORDERS_TRANSACTIONS).setOnClickListener(ActivityVendorDashBoard.this);
        findViewById(R.id.CARD_STORE_PROFILE).setOnClickListener(ActivityVendorDashBoard.this);

        if(foodMartApp.getFoodMartVendor().getStallLocation()==null || foodMartApp.getFoodMartVendor().getStallImageUrl()==null){
            new CustomDialog(ActivityVendorDashBoard.this,false).setTitle("Missing Details").setMessage("Few Details Are Missing , Kindly Fill those details. Redirect To Profile ?").setImg(R.drawable.material_store_profile)
                    .setPositiveBtn("Yes", () -> startActivity(new Intent(ActivityVendorDashBoard.this,ActivityVendorStoreProfile.class))).setNegativeBtn("No",null).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView)findViewById(R.id.TV_VENDOR_NAME)).setText("Welcome "+getFoodMartApp().getFoodMartVendor().getName());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.CARD_FOOD_ITEMS:
                startActivity(new Intent(ActivityVendorDashBoard.this, ActivityVendorFoodItems.class));
                break;
            case R.id.CARD_FOOD_ORDERS:

                break;
            case R.id.CARD_ORDERS_TRANSACTIONS:

                break;
            case R.id.CARD_STORE_PROFILE:
                startActivity(new Intent(ActivityVendorDashBoard.this, ActivityVendorStoreProfile.class));

                break;

        }


    }
}
