package com.example.testapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.testapp.R;

public class CustomDialog extends Dialog {

    private String title;
    private String message;
    private Integer img;
    private Context context;
    private String positiveBtnText;
    private Runnable runnablePositive;
    private String negativeBtnText;
    private Runnable runnableNegative;

    public CustomDialog(@NonNull Context context,Boolean isDismissable) {
        super(context);
        this.context = context;
        this.setCancelable(isDismissable);
    }

    public String getNegativeBtnText() {
        return negativeBtnText;
    }

    public Runnable getRunnableNegative() {
        return runnableNegative;
    }

    public String getPositiveBtnText() {
        return positiveBtnText;
    }

    public Runnable getRunnablePositive() {
        return runnablePositive;
    }

    public Integer getImg() {
        return img;
    }

    public CustomDialog setPositiveBtn(String positiveBtnText, Runnable runnablePositive) {
        this.positiveBtnText = positiveBtnText;
        this.runnablePositive = runnablePositive;
        return this;

    }

    public CustomDialog setNegativeBtn(String negativeBtnText, Runnable runnableNegative) {
        this.negativeBtnText = negativeBtnText;
        this.runnableNegative = runnableNegative;
        return this;

    }

    public CustomDialog setImg(int img) {
        this.img=img;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CustomDialog setTitle(String title) {
        this.title=title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CustomDialog setMessage(String message) {
        this.message=message;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_custom);

        TextView tvTitle, tvMessage;
        ImageView imgView;
        Button btnPositive;
        Button btnNegative;

        tvTitle = findViewById(R.id.TITLE);
        tvMessage = findViewById(R.id.DESCRIPTION);
        imgView = findViewById(R.id.IMG);
        btnPositive = findViewById(R.id.BTN_POSITIVE);
        btnNegative = findViewById(R.id.BTN_NEGATIVE);

        tvTitle.setText(getTitle() != null ? getTitle() : "");
        tvMessage.setText(getMessage() != null ? getMessage() : "");
        imgView.setImageDrawable(getImg() != null ? AppCompatResources.getDrawable(context, getImg()) : null);

        btnPositive.setText(getPositiveBtnText() != null ? getPositiveBtnText() : "Dismiss");
        btnPositive.setOnClickListener(view -> {
            if (getRunnablePositive() != null) {
                getRunnablePositive().run();
            } else {
                this.dismiss();
            }
        });

        if (getNegativeBtnText() != null) {

            btnNegative.setOnClickListener(view -> {
                if (getRunnableNegative() != null) {
                    getRunnableNegative().run();
                } else {
                    this.dismiss();
                }
            });

        } else {
            btnNegative.setVisibility(View.GONE);
        }


    }


}
