package com.example.realizeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import cn.iwgang.countdownview.CountdownView;
import io.paperdb.Paper;

public class Jair_Counter extends AppCompatActivity {

    private static final String IS_START_KEY = "IS_START";
    private static final String LAST_TIME_KEY = "LAST_TIME_SAVE";
    private static final String TIME_REMAIN_KEY = "TIME_REMAIN";
    private static final long TIME_LIMIT = 15*1000;

    private boolean isStart;

    CountdownView countdownView;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jair_counter);
        //Init
        init();
        //Event
        setupview();
    }

    private void setupview(){
        btnStart.setOnClickListener(view -> {
            if(!isStart){
                countdownView.start(TIME_LIMIT);
                Paper.book().write(IS_START_KEY,true);
                btnStart.setEnabled(false);
            }
        });

        countdownView.setOnCountdownEndListener(cv -> {

            Toast.makeText(this, "Terminado!", Toast.LENGTH_SHORT).show();
            reset();
        });

        countdownView.setOnCountdownIntervalListener(1000, (cv, remainTime) -> {

            Log.d("TIMER", ""+remainTime);

        });

    }

    private void reset(){
        btnStart.setEnabled(true);
        Paper.book().delete(IS_START_KEY);
        Paper.book().delete(LAST_TIME_KEY);
        Paper.book().delete(TIME_REMAIN_KEY);

        isStart = false;
    }

    private void init(){
        countdownView = (CountdownView)findViewById(R.id.countdownView);
        btnStart = (Button) findViewById(R.id.btnStart);

        Paper.init(this);
        isStart = Paper.book().read(IS_START_KEY, false);
        //Check time
        if(isStart){
            btnStart.setEnabled(false);
            checkTime();
        }
        else
            btnStart.setEnabled(true);
    }

    private void checkTime(){

        long currentTime = System.currentTimeMillis();
        long lastTimeSaved = Paper.book().read(LAST_TIME_KEY);
        long timeRemain = Paper.book().read(TIME_REMAIN_KEY);
        long result = timeRemain + (lastTimeSaved - currentTime);
        if(result > 0){
            countdownView.start(result);
        }
        else{
            countdownView.stop();
            reset();
        }

    }

    @Override
    protected void onStop() {
        Paper.book().write(TIME_REMAIN_KEY, countdownView.getRemainTime());
        Paper.book().write(LAST_TIME_KEY,System.currentTimeMillis());
        super.onStop();
    }
}