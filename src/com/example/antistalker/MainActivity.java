package com.example.antistalker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.banButton);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<Person> temp = new ArrayList<Person>();
                temp.add(new Person("Cosmin", "0751122566"));

                new CallBlocker().block(temp);
                SmsBlocker.block("name", "4342432432");
                TwitterBlocker.block("name", "03244324");
            }
        });

    }


}
