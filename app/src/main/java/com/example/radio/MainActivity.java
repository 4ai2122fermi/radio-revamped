package com.example.radio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dovrei cambiarlo, ma non mi va
        ListView list = findViewById(R.id.fanculoandroidstudio);

        ArrayList<Radio> radios = new ArrayList<>();
        radios.add(new Radio("RTL 102.5 *lenta*", R.drawable.rtl, "https://streamingv2.shoutcast.com/rtl-1025"));
        radios.add(new Radio("Kiss Kiss", R.drawable.kisskiss, "http://wma08.fluidstream.net:4610/"));
        radios.add(new Radio("Virgin Radio", R.drawable.virginradio, "http://icecast.unitedradio.it/Virgin.mp3"));
        radios.add(new Radio("Radio Deejay", R.drawable.radiodeejay, "https://4c4b867c89244861ac216426883d1ad0.msvdn.net/radiodeejay/radiodeejay/play1.m3u8"));
        radios.add(new Radio("Radio Zeta *lenta*", R.drawable.radiozeta, "https://streamingv2.shoutcast.com/radio-zeta"));
        radios.add(new Radio("Radio Norba *non va", R.drawable.radionorba, "https://stream9.xdevel.com/audio0s975885-461/stream/icecast.audio"));

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), radios);
        list.setAdapter(customAdapter);
        list.setClickable(true);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = parent.getItemAtPosition(position);
                Radio item = (Radio) o;

                // quando avvio l'activity del player passo anche la lista completa delle radio,
                // per sapere quale radio avviare quando l'utente usa i pulsanti previous e next
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra("to_be_played", item);
                intent.putExtra("full_list", radios);
                startActivity(intent);
            }
        });
    }
}

