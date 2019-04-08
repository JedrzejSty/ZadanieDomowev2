package com.example.zadaniedomowev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThirdActivity extends AppCompatActivity {
    private int selected_sound = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent received_intent = getIntent();
        Integer sound_id = received_intent.getIntExtra(MainActivity.SOUND_ID,0);

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton)view).isChecked();
        if (checked) {
            RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
            switch (view.getId()){
                case R.id.sound1: selected_sound = 0;break;
                case R.id.sound2: selected_sound = 1;break;
                case R.id.sound3: selected_sound = 2;break;
                case R.id.sound4: selected_sound = 3;break;
                case R.id.sound5: selected_sound = 4;break;
                }

            }


        }
    public void setSoundClick(View v){
        Intent data = new Intent();
        data.putExtra(MainActivity.SOUND_ID,selected_sound);
        setResult(RESULT_OK, data);
        finish();

    }

    public void stopSoundClick(View v) {
        Intent data = new Intent(getApplicationContext(),MainActivity.class);
        data.putExtra("SOUND_ID", selected_sound);
        setResult(RESULT_CANCELED, data);
        finish();
    }
}
