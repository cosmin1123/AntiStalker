package com.example.antistalker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Person> blockedPersons;
    private CallBlocker callBlocker = new CallBlocker();

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

                if (name == null || phoneNumber == null || name == "" || phoneNumber == "")
                    return;

                Person blocked = new Person(name, phoneNumber);
                if(! blocked.containedInArray(blockedPersons))
                    blockedPersons.add(blocked);

                callBlocker.block(blockedPersons);
               // SmsBlocker.block(blockedPersons);
                TwitterBlocker.block(blocked);
            }
        });

        Button blockedButton = (Button) findViewById(R.id.viewBlockedButton);

        blockedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.v("TAG", "Persons: " + blockedPersons);
                Intent intent = new Intent(getBaseContext(), TwitterActivity.class);
                intent.putExtra("blockedPersons", blockedPersons);
                startActivity(intent);
            }
        });
    }


}
