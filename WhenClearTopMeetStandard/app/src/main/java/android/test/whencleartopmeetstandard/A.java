package android.test.whencleartopmeetstandard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class A extends AppCompatActivity {

    String hashCode = "";
    TextView text;
    Button launchMainActivity, launchA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hashCode = String.valueOf(hashCode());
        Log.d("AAA", "activity|" + hashCode + "|has been launched");
        setContentView(R.layout.activity_a);
        text = (TextView) findViewById(R.id.desc);
        launchMainActivity = (Button) findViewById(R.id.launchMainActivity);
        launchMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        launchA = (Button) findViewById(R.id.launchA);
        launchA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 如果是普通的标记，A会调起一个新A，覆盖在自身上面，形成多个A
                // 但是用了CLEAR_TOP+standard，只会有一个A存在，而且不调onNewIntent
                Intent intent = new Intent(A.this, A.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 没有这个标记，启动动画会稍有不同
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("AAA", "activity|" + hashCode + "|has a new intent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA", "activity|" + hashCode + "|has been destroy");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (text != null) {
                text.setText("activity|" + hashCode + "|is being visible");
                Log.d("AAA", "activity|" + hashCode + "|is being visible");
            }
        } else {
            if (text != null) {
                text.setText("activity|" + hashCode + "|is being invisible");
                Log.d("AAA", "activity|" + hashCode + "|is being invisible");
            }
        }
    }
}
