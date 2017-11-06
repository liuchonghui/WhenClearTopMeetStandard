package android.test.whencleartopmeetstandard;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    final String RequestUrl = "https://github.com/square/okhttp";
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String retJson = null;
                try {
                    Request request = new Request.Builder().url(RequestUrl).build();
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();
                    retJson = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (retJson != null && retJson.length() > 0) {
                    final String content = retJson;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (text != null) {
                                text.setText(content);
                            }
                        }
                    });
                }
            }
        }).start();
    }
}
