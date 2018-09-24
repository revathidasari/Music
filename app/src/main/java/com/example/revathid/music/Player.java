package com.example.revathid.music;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Player extends AppCompatActivity implements View.OnClickListener {

    Button previous,play,next;
    MediaPlayer mediaPlayer;
    int number;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        String TempList = getIntent().getStringExtra(List.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.txName);
        textView.setText(TempList);

        previous = (Button) findViewById(R.id.bPrevious);
        play = (Button) findViewById(R.id.bPlay);
        next = (Button) findViewById(R.id.bNext);

        previous.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.kalimba);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.maidwiththeflaxenhair);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sleepaway);
        }
        mediaPlayer.setLooping(true);
        number=  mediaPlayer.getCurrentPosition();
        switch (v.getId()) {
            case R.id.bPrevious:
                if(number>0 ){
                    mediaPlayer.seekTo( number-1);
                    mediaPlayer.start();
                    number--;
                }else {
                    mediaPlayer.seekTo(number);
                    mediaPlayer.start();
                }
                break;
            case R.id.bPlay:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(number);
                    mediaPlayer.pause();
                } else  {
                    mediaPlayer.seekTo(number);
                    mediaPlayer.start();
                }
                break;
            case R.id.bNext:
                if(number<2 ){
                    mediaPlayer.seekTo( number+1);
                    mediaPlayer.start();
                    number++;
                }else {
                    mediaPlayer.seekTo(number);
                    mediaPlayer.start();
                }
                break;
            default: mediaPlayer.stop();
        }
    }
}

