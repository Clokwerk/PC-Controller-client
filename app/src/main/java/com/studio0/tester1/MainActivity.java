package com.studio0.tester1;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.AsyncTask;
import android.graphics.Color;
import org.w3c.dom.Text;

import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.jar.Manifest;
// Sockets work, UI needs work *fixed*
/* MAIN MECHANISM COMPLETED
* TO DO: OPTIMIZATION
* */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "testingmessage";
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    public static Socket s1;
    public static Handler mHandler;


    private TextView statustext;
    String exceptiontype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);


        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        Button connectorButton = (Button) findViewById(R.id.connectionButton);
        Button sound1 = (Button) findViewById(R.id.button38);
        Button sound2 = (Button) findViewById(R.id.button37);
        Button sound3 = (Button) findViewById(R.id.button36);
        Button sound4 = (Button) findViewById(R.id.button35);
        Button sound5 = (Button) findViewById(R.id.button34);
        Button sound6 = (Button) findViewById(R.id.button33);
        Button sound7 = (Button) findViewById(R.id.button32);
        Button sound8 = (Button) findViewById(R.id.button30);
        Button sound9 = (Button) findViewById(R.id.button31);
        final EditText IPfield = (EditText) findViewById(R.id.editText2);
        final EditText PortField = (EditText) findViewById(R.id.editText4);
        statustext = (TextView) findViewById(R.id.textView);


        connectorButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String ipaddress = IPfield.getText().toString();
                        int port = Integer.parseInt(PortField.getText().toString());
                        ConnectClient c1 = new ConnectClient(ipaddress, port);
                        new Thread(c1).start();


                    }
                }
        );


        sound1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Message msg = Message.obtain();
                msg = Message.obtain();
                msg.what = 1;
                mHandler.sendMessage(msg);

            }
        });


        sound2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg = Message.obtain();
                msg.what = 2;
                mHandler.sendMessage(msg);
            }
        });
        sound3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = 3;
                mHandler.sendMessage(msg);
            }
        });
        sound4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = 4;
                mHandler.sendMessage(msg);

            }
        });
        sound5.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = 5;
                mHandler.sendMessage(msg);
            }
        });
        sound6.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = 6;
                mHandler.sendMessage(msg);
            }
        });
        sound7.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = 7;
                mHandler.sendMessage(msg);
            }
        });
        sound8.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = 8;
                mHandler.sendMessage(msg);
            }
        });
        sound9.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.what = 9;
                mHandler.sendMessage(msg);
            }
        });

        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.option1) {


                                Intent i = new Intent(MainActivity.this, RemoteNavigator.class);
                                i.putExtra("OpenNavigator", "Open RemoteNavigator");
                                startActivity(i);
                                Log.i(TAG, "Remote Navigator");


                            mDrawer.closeDrawers();
                            Log.i(TAG, "Clicked");
                            return true;
                        }


                        Log.i(TAG, "MainActivity created");
                        return true;
                    }
                });
    }








    private class ConnectClient implements Runnable{
    String ipaddress;

    int port;
    ConnectClient(String ipaddress,int port){
        this.ipaddress=ipaddress;
        this.port=port;
    }
    @SuppressLint("HandlerLeak")
    public void run(){
        try{
            InetAddress inetaddr=InetAddress.getByName(ipaddress);
            s1=new Socket(inetaddr,port);
            statustext.post(new Runnable() {
                                public void run() {
                                    statustext.setText("Connected");
                                    statustext.setTextColor(Color.GREEN);
                                }
                            });
            Log.i(TAG,"Connected");
            Looper.prepare();
            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    switch(msg.what){
                        case 1:
                            try {
                                String command="1";
                                PrintWriter out = new PrintWriter(s1.getOutputStream(), true);

                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound1");
                                statustext.setTextColor(Color.RED);
                            }
                            break;
                        case 2:
                            try {
                                String command="2";
                                PrintWriter out = new PrintWriter(s1.getOutputStream(), true);

                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound2");
                                statustext.setTextColor(Color.RED);
                            }
                            break;
                        case 3:
                            try {
                                String command="3";
                                PrintWriter out = new PrintWriter(s1.getOutputStream(), true);

                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound3");
                                statustext.setTextColor(Color.RED);
                            }
                            break;
                        case 4:
                            try {
                                String command="4";

                                PrintWriter out=new PrintWriter(s1.getOutputStream(),true);
                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound4");
                                statustext.setTextColor(Color.RED);
                            }
                            break;
                        case 5:
                            try {
                                String command="5";

                                PrintWriter out=new PrintWriter(s1.getOutputStream(),true);
                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound5");
                                statustext.setTextColor(Color.RED);
                            }
                            break;
                        case 6:
                            try {
                                String command="6";

                                PrintWriter out=new PrintWriter(s1.getOutputStream(),true);
                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound6");
                                statustext.setTextColor(Color.RED);
                            }
                            break;
                        case 7:
                            try {
                                String command="4";

                                PrintWriter out=new PrintWriter(s1.getOutputStream(),true);
                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound4");
                                statustext.setTextColor(Color.RED);
                            }
                            break;
                        case 8:
                            try {
                                String command="4";

                                PrintWriter out=new PrintWriter(s1.getOutputStream(),true);
                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound4");
                                statustext.setTextColor(Color.RED);
                            }
                            break;
                        case 9:
                            try {
                                String command="9";

                                PrintWriter out=new PrintWriter(s1.getOutputStream(),true);
                                out.println(command);
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("Error writing to outputstream, Sound4");
                                statustext.setTextColor(Color.RED);
                            }
                            break;

                        case 10:
                            try {
                                String url=(String)msg.obj;
                                PrintWriter out = new PrintWriter(MainActivity.s1.getOutputStream(), true);
                                out.println("10");
                                out.println(url);
                                break;
                            }catch(IOException e1){
                                e1.printStackTrace();
                                statustext.setText("URL SENDING ERROR");
                            }

                    }
                }
            };
            Looper.loop();

        }catch(UnknownHostException e1){
            e1.printStackTrace();
            exceptiontype="UnknownHostException occured.";
            statustext.post(new Runnable() {
                @Override
                public void run() {

                }
            });



        }catch (IOException e1){
            e1.printStackTrace();
            exceptiontype="IOException occured.";
            statustext.post(new Runnable() {
                @Override
                public void run() {
                    statustext.setText(exceptiontype);
                    statustext.setTextColor(Color.RED);
                }
            });

        }

    }
}


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "MainActivity Start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"MainActivity Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"MainActivity Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"MainActivity Destroyed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"MainActivity Resumed");
    }


    }

