package android.test.whencleartopmeetstandard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String hashCode = "";
    Handler mHandler;
    int count = 0;
    TextView timestamp;
    Button a, b, c, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hashCode = String.valueOf(hashCode());
        Log.d("AAA", "main activity|" + hashCode + "|has been launched");
        setContentView(R.layout.activity_main);
        timestamp = (TextView) findViewById(R.id.timestamp);
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.postDelayed(mRunnable, 1000L);
        a = (Button) findViewById(R.id.launchA);
        b = (Button) findViewById(R.id.launchB);
        c = (Button) findViewById(R.id.launchC);
        d = (Button) findViewById(R.id.launchD);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, A.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
        Log.d("AAA", "main activity|" + hashCode + "|has been destroy");
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            count++;
            if (timestamp != null) {
                timestamp.setText("MainActivity has been exist for " + count + " seconds");
            }
            mHandler.postDelayed(mRunnable, 1000L);
        }
    };
}
