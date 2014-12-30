package group.ripple.dhamaschool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(1 * 1000);
                    startActivity(new Intent(getBaseContext(),Home.class));
                    finish();
                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }}
