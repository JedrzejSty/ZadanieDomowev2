package com.example.zadaniedomowev2;

import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String SOUND_ID = "sound id";
    public static final int BUTTON_REQUEST = 1;
    private int current_sound = 0;

    public static final String CONTACT_ID = "contact id";
    public static final int BUTTON2_REQUEST = 2;
    private int current_contact = 0;

    //private MediaPlayer backgroundPlayer;
    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;

    private int licznik = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button SoundButton = (Button)findViewById(R.id.buttonSound);
        SoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soundPick = new Intent(getApplicationContext(),ThirdActivity.class);
                soundPick.putExtra(SOUND_ID,current_sound);
                startActivityForResult(soundPick, BUTTON_REQUEST);
            }
        });

        final Button ContactButton = (Button)findViewById(R.id.buttonContact);
        ContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactPick = new Intent(getApplicationContext(),SecondActivity.class);
                contactPick.putExtra(CONTACT_ID,current_contact);
                startActivityForResult(contactPick, BUTTON2_REQUEST);
            }
        });




        Random rand = new Random();
        final ImageView img = (ImageView) findViewById(R.id.imageView);
        final String str ="img_" + rand.nextInt(4);
        img.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(str, "drawable", getPackageName())));



        //tu było view

        sounds = new Uri[5];
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ringd);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring01);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring02);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring03);
        sounds[4] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring04);

        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                //backgroundPlayer.pause();
                mp.start();
            }
        });

        buttonPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //backgroundPlayer.start();
            }
        });

        final FloatingActionButton MusicButton = (FloatingActionButton) findViewById(R.id.floatingActionButton4);
        MusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                licznik^=1;
                buttonPlayer.reset();
                try{
                    buttonPlayer.setDataSource(getApplicationContext(),sounds[current_sound]);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                buttonPlayer.prepareAsync();
            }
        });
    }

    @Override
    protected void onPause() {

        super.onPause();
       // backgroundPlayer.pause();
        buttonPlayer.pause();
    }
    @Override
    protected void onResume() {

        super.onResume();
        //backgroundPlayer = MediaPlayer.create(this, R.raw.ring01);
      /*  backgroundPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();
            }
        });*/
    }
    @Override
    protected void onStop() {

        super.onStop();
        //backgroundPlayer.release();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if(requestCode == BUTTON2_REQUEST)
            {
                current_contact = data.getIntExtra(CONTACT_ID, 0);
                String[] yourArray = getResources().getStringArray(R.array.myContactArray);
                String yourString = yourArray[current_contact];
                TextView txV = (TextView)findViewById(R.id.textView);
                txV.setText(yourString);

                Random rand = new Random();
                final ImageView img = (ImageView) findViewById(R.id.imageView);
                final String str ="img_" + rand.nextInt(4);
                img.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(str, "drawable", getPackageName())));
            }
            else if(requestCode == BUTTON_REQUEST) {
                current_sound = data.getIntExtra(SOUND_ID, 0);

            }

            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),getText(R.string.back_message),Toast.LENGTH_SHORT).show();
                TextView txV = (TextView)findViewById(R.id.textView);
            }


        }
    }



