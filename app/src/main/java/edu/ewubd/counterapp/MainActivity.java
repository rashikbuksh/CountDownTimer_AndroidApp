package edu.ewubd.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mInterval = 100;
    private Handler mHandler;
    private Button Start,Reset,Pause,Exit;
    private TextView timer;
    private int number = 2018360088;

    @Override
    protected void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        Start = findViewById(R.id.start);
        Reset = findViewById(R.id.reset);
        Pause = findViewById(R.id.pause);
        Exit = findViewById(R.id.exit);
        Start.setOnClickListener(v->startRepeatingTask());
        Reset.setOnClickListener(v->reset());
        Pause.setOnClickListener(v->stopRepeatingTask());
        Exit.setOnClickListener(v->finish());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateStatus();
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void updateStatus(){
        if(number>0) number = number-1;
        timer = findViewById(R.id.count_number);
        timer.setText(String.valueOf(number));
    }
    void reset(){
        number = 2018360088;
        stopRepeatingTask();
        timer = findViewById(R.id.count_number);
        timer.setText(String.valueOf(number));
    }

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
}