package com.example.edgy.designmenu;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private Timer timer;
    private Button button;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        button = (Button) findViewById(R.id.button);
        list = new ArrayList<>();
        linearLayout = (LinearLayout) findViewById(R.id.linearAdd);
        for (int i=0; i<9; i++){
            list.add("text line : "+i);
        }
        AdapterMenu adapterMenu = new AdapterMenu(MainActivity.this, list);
        listView.setAdapter(adapterMenu);
        timer = new Timer();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.schedule(new TimerTask() {
                    private int left = 8;
                    private int right = 8;
                    private int top = 100;
                    private int bottom = 8;
                    private int countPoint = 1;

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                top+=10;
                                if(top < 1000) {
                                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
                                    params.height = top;
                                    linearLayout.setLayoutParams(params);
                                    Log.d("test MaskTimer",top + "");
                                } else {
                                    cancel();
                                }
                            }
                        });
                    }
                },0,10);
            }
        });

    }
}
