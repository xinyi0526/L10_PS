package com.myapplicationdev.android.l10_ps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragPageAdapter adapter;
    ViewPager vPager;
    Button btnChange,btnRead;
    LinearLayout layout;
    int reqcode = 12345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = findViewById(R.id.viewpager1);
        btnChange = findViewById(R.id.btnChange1);
        layout = (LinearLayout) findViewById(R.id.changeColor);
        btnRead = findViewById(R.id.btnReadLtr);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());

        adapter = new MyFragPageAdapter(fm,al);

        vPager.setAdapter(adapter);



        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,300);

                Intent intent = new Intent(MainActivity.this,MyReceiver.class);

                PendingIntent pIntent = PendingIntent.getBroadcast(MainActivity.this,reqcode,intent,PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pIntent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_previous){
            if(vPager.getCurrentItem() > 0){
                int previousPage = vPager.getCurrentItem() - 1;
                vPager.setCurrentItem(previousPage,true);
            }
        }else if (id == R.id.action_random){
            int max = vPager.getChildCount();
            if(vPager.getCurrentItem() >= 0 && vPager.getCurrentItem() <= max ){
                int i = (int)(Math.random()*(max - 0 + 1) + 0);
                vPager.setCurrentItem(i,true);
            }

        }else{
            int max = vPager.getChildCount();
            if(vPager.getCurrentItem() < max - 1){
                int nextPage = vPager.getCurrentItem() + 1;
                vPager.setCurrentItem(nextPage,true);
            }

        }


        return super.onOptionsItemSelected(item);
    }
}
