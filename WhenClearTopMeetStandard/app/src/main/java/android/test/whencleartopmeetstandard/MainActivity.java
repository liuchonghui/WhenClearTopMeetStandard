package android.test.whencleartopmeetstandard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String RequestUrl = "https://github.com/square/okhttp";
    TextView text;
    Button A, B, C, D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        A = (Button) findViewById(R.id.launchA);
        B = (Button) findViewById(R.id.launchB);
        C = (Button) findViewById(R.id.launchC);
        D = (Button) findViewById(R.id.launchD);
    }
}
