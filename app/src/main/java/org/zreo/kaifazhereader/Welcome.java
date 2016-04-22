package org.zreo.kaifazhereader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class Welcome extends Activity implements Runnable {
    private boolean isFirstUse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            SharedPreferences preferences = getSharedPreferences("isFirstUse",MODE_WORLD_READABLE);
            isFirstUse = preferences.getBoolean("isFirstUse", true);
            if (isFirstUse) {
                startActivity(new Intent(Welcome.this,
                        GuideActivity.class));
            } else {
                startActivity(new Intent(Welcome.this, MainActivity.class));
            }
            finish();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstUse", false);
            editor.commit();


        } catch (InterruptedException e) {

        }
    }
}
