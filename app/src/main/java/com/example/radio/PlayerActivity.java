package com.example.radio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Radio radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);

        mediaPlayer = new MediaPlayer();

        ImageButton previous = findViewById(R.id.previous);
        ImageButton playPause = findViewById(R.id.playPause);
        ImageButton next = findViewById(R.id.next);

        Intent intent = getIntent();

        radio = (Radio) intent.getSerializableExtra("to_be_played");
        ArrayList<Radio> radios = (ArrayList<Radio>) intent.getSerializableExtra("full_list");

        // faccio così p\erché per qualche motivo radios(indexOf(radio)) ritorna sempre -1
        // per risolvere faccio questo passaggio (idk, non sono uno sviluppatore java)
        for (Radio r : radios) {
            if (radio.url.equals(r.url))
                radio = r;
        }

        ImageView logo = findViewById(R.id.image);
        logo.setImageResource(radio.logo);

        TextView radioName = findViewById(R.id.radioName);
        radioName.setText(radio.name);

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(radio.url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) { e.printStackTrace(); }

        // la seekBar per le radio è inutile, la blocco alla fine
        seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    seekBar.setProgress(seekBar.getMax());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { /* not implemented */ }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { /* not implemented */ }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radios.indexOf(radio) == 0) {
                    Toast.makeText(PlayerActivity.this, "Non ci sono stazioni radio prima di questa!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // estraggo dalla lista la radio precedente a quella che stiamo ascoltando prendendo
                // la radio che si trova di un indice sotto quella corrente
                Radio previousRadio = radios.get(radios.indexOf(radio) - 1);
                System.out.println(previousRadio.name);

                mediaPlayer.stop();
                mediaPlayer.release();
                finish();

                // dopo aver chiuso del tutto l'activity della radio corrente avvio un'activity
                // per la radio precedente
                Intent intent = new Intent(PlayerActivity.this, PlayerActivity.class);
                intent.putExtra("to_be_played", previousRadio);
                intent.putExtra("full_list", radios);
                startActivity(intent);
            }
        });

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playPause.setBackgroundResource(R.drawable.play);
                }
                else {
                    mediaPlayer.start();
                    playPause.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radios.indexOf(radio) == radios.size() - 1) {
                    Toast.makeText(PlayerActivity.this, "Non ci sono stazioni radio dopo questa!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // estraggo dalla lista la radio successiva a quella che stiamo ascoltando
                // prendendo la radio che si trova di un indice sopra quella corrente
                Radio nextRadio = radios.get(radios.indexOf(radio) + 1);
                System.out.println(nextRadio.name);

                mediaPlayer.stop();
                mediaPlayer.release();
                finish();

                // dopo aver chiuso del tutto l'activity della radio corrente avvio un'activity
                // per la radio successiva
                Intent intent = new Intent(PlayerActivity.this, PlayerActivity.class);
                intent.putExtra("to_be_played", nextRadio);
                intent.putExtra("full_list", radios);
                startActivity(intent);
            }
        });
    }

    // se l'utente clicca il tasto del telefono per tornare alla pagina principale,
    // mi assicuro di fermare il player e di chiudere del tutto l'activity
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            mediaPlayer.stop();
            mediaPlayer.release();
            finish();

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}