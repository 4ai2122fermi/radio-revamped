package com.example.radio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.media.MediaPlayer;
import android.widget.RadioGroup;

import java.io.IOException;






public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button stopButton;
    private Button resetButton;

    private RadioButton station1Button;
    private RadioButton station2Button;
    private RadioButton station3Button;
    private RadioButton station4Button;
    private RadioButton station5Button;
    private RadioButton station6Button;
    private RadioButton station7Button;
    private RadioButton station8Button;
    private RadioButton station9Button;
    private RadioButton station10Button;
    private RadioButton station11Button;
    private RadioButton station12Button;

    private RadioGroup radioGroup;
    private  MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton=findViewById(R.id.resetButton);

        station1Button = findViewById(R.id.station1Button);
        station2Button = findViewById(R.id.station2Button);
        station3Button = findViewById(R.id.station3Button);
        station4Button = findViewById(R.id.station4Button);
        station5Button = findViewById(R.id.station5Button);
        station6Button = findViewById(R.id.station6Button);
        station7Button = findViewById(R.id.station7Button);
        station8Button = findViewById(R.id.station8Button);
        station9Button = findViewById(R.id.station9Button);
        station10Button = findViewById(R.id.station10Button);
        station11Button = findViewById(R.id.station11Button);
        station12Button = findViewById(R.id.station12Button);



        playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playButton.setBackgroundResource(R.drawable.button_play);
                    stopButton.setBackgroundResource(R.drawable.button_normal);

                    String url = "";
                    if (station1Button.isChecked()) {
                        url = station1Button.getTag().toString();
                    } else if (station2Button.isChecked()) {
                        url = station2Button.getTag().toString();
                    } else if (station3Button.isChecked()) {
                        url = station3Button.getTag().toString();
                    } else if (station4Button.isChecked()) {
                        url = station4Button.getTag().toString();
                    } else if (station5Button.isChecked()) {
                        url = station5Button.getTag().toString();
                    } else if (station6Button.isChecked()) {
                        url = station6Button.getTag().toString();
                    } else if (station7Button.isChecked()) {
                        url = station7Button.getTag().toString();
                    } else if (station8Button.isChecked()) {
                        url = station8Button.getTag().toString();
                    } else if (station9Button.isChecked()) {
                        url = station9Button.getTag().toString();
                    } else if (station10Button.isChecked()) {
                        url = station10Button.getTag().toString();
                    } else if (station11Button.isChecked()) {
                        url = station11Button.getTag().toString();
                    } else if (station12Button.isChecked()) {
                        url = station12Button.getTag().toString();
                    }

                    if (url != null) {
                        try {
                            mediaPlayer = new MediaPlayer();
                            mediaPlayer.setDataSource(url);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });



            stopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playButton.setBackgroundResource(R.drawable.button_normal);
                    stopButton.setBackgroundResource(R.drawable.button_stop);
                    if(mediaPlayer != null && mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                }
            });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ripristina lo stato iniziale dei pulsanti delle stazioni radio
                station1Button.setChecked(false);
                station2Button.setChecked(false);
                station3Button.setChecked(false);
                station4Button.setChecked(false);
                station5Button.setChecked(false);
                station6Button.setChecked(false);
                station7Button.setChecked(false);
                station8Button.setChecked(false);
                station9Button.setChecked(false);
                station10Button.setChecked(false);
                station11Button.setChecked(false);
                station12Button.setChecked(false);


                playButton.setBackgroundResource(R.drawable.button_normal);
                stopButton.setBackgroundResource(R.drawable.button_normal);


                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
            }
        });


    }
    }

