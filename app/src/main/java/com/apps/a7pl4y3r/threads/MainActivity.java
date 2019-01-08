package com.apps.a7pl4y3r.threads;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private Handler mainHandler = new Handler();
    private Button buttonStartThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartThread = findViewById(R.id.button_start_thread);

    }

    private void showMessage(String me) {
        Toast.makeText(this, me, Toast.LENGTH_SHORT).show();
    }

    public void startThread(View v) {

        //ExampleThread thread = new ExampleThread();
        //thread.start();

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 2; i < 10000; i++)
                    if (isPrime(i))
                        Log.d(TAG, String.valueOf(i) + " is prime");
                    else
                        Log.d(TAG, String.valueOf(i) + " is not prime");

            }}).start();*/

        ExampleRunnable runnable = new ExampleRunnable(10);
        new Thread(runnable).start();

    }

    public void stopThread(View v) {

    }

    private boolean isPrime(int x) {

        if (x < 2 || x % 2 == 0)
            return false;
        for (int d = 3; d * d <= x; d += 2)
            if (x % d == 0)
                return false;

        return true;
    }

    class ExampleRunnable implements Runnable {

        private int seconds;

        public ExampleRunnable(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {

            for (int i = 0; i < seconds; i++) {

                if (i == 5)
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                             buttonStartThread.setText("50%");
                        }
                    });

                Log.d(TAG, "thread sleep " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    class ExampleThread extends Thread {

        @Override
        public void run() {

            for (int i = 2; i < 100; i++)
                if (isPrime(i))
                    Log.d(TAG, String.valueOf(i) + " is prime");
                else
                    Log.d(TAG, String.valueOf(i) + " is not prime");
        }
    }

}
