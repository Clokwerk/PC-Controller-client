package com.studio0.tester1;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;


public class RemoteNavigator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_navigator);
        Button sendbutton=(Button)findViewById(R.id.button);
         final EditText urlfield=(EditText)findViewById(R.id.editText);
        sendbutton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String url=urlfield.getText().toString();
                    Message msg= Message.obtain();
msg.what=10;
                    msg.obj=url;
                    MainActivity.mHandler.sendMessage(msg);

                    if(urlfield.getText().toString().equals("0")){
                        finish();
                    }


            }

        });


    }


    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}
