package com.example.zadaniedomowev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class SecondActivity extends AppCompatActivity {
    private int selected_contact = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent received_intent = getIntent();

        onItemSelectedListener();
    }

    public void onItemSelectedListener(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        if(spinner != null){
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                    switch (i){
                        case 0: selected_contact = 0;break;
                        case 1: selected_contact = 1;break;
                        case 2: selected_contact = 2;break;
                        case 3: selected_contact = 3;break;
                        case 4: selected_contact = 4;break;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


    }
    public void setContactClick(View v){
        Intent data2 = new Intent();
        data2.putExtra(MainActivity.CONTACT_ID,selected_contact);
        setResult(RESULT_OK, data2);
        finish();

    }

    public void stopContactClick(View v) {
        Intent data2 = new Intent(getApplicationContext(),MainActivity.class);
        data2.putExtra(MainActivity.CONTACT_ID,selected_contact);
        setResult(RESULT_CANCELED, data2);
        finish();
    }
}
