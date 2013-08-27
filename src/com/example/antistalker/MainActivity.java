package com.example.antistalker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.banButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CallBlocker.block("name", "0143234252");
                SmsBlocker.block("name", "4342432432");
                TwitterBlocker.block("name", "03244324");
            }
        });

    }


}
