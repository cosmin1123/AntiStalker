package com.example.antistalker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Person> blockedPersons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.banButton);
        blockedPersons = new ArrayList<Person>();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText nameTextField = (EditText) findViewById(R.id.nameTextField);
                EditText phoneTextField = (EditText) findViewById(R.id.phoneTextField);

                String name = nameTextField.getText().toString();
                String phoneNumber = phoneTextField.getText().toString();

                Person blocked = new Person(name, phoneNumber);
                blockedPersons.add(blocked);

                new CallBlocker().block(blockedPersons);
                SmsBlocker.block(blockedPersons);
                TwitterBlocker.block(blocked);
            }
        });

        Button blockedButton = (Button) findViewById(R.id.viewBlockedButton);

        blockedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ViewBlockedActivity.class);
                startActivity(intent);
            }
        });
    }


}
