package com.example.admin.health_app.Common_package;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.admin.health_app.R;


/**
 * Created by AshuRajput on 12/22/2017.
 */

public class Dialog_popup extends Dialog {

    Context context;
    TextView okTV;
    TextView msgTextViewTV;
    String message;

    public Dialog_popup(Context activity, String message){
        super(activity);
        context = activity;
        this.message = message;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialogpopop_layout);

        okTV = (TextView) findViewById(R.id.okTV_id);
        msgTextViewTV = (TextView) findViewById(R.id.msgTextViewTV_id);
        msgTextViewTV.setText(message);
        okTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
    public void onclick(){
        okTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}


