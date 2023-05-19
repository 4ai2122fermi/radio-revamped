package com.example.radio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dovrei cambiarlo, ma non mi va
        ListView list = findViewById(R.id.fanculoandroidstudio);
        FloatingActionButton add = findViewById(R.id.add);

        // arraylist che contiene tutte le radio che l'utente inserirà
        // aggiungo delle radio famose di base
        ArrayList<Radio> radios = new ArrayList<>();
        radios.add(new Radio("RTL 102.5", R.drawable.rtl, "https://streamingv2.shoutcast.com/rtl-1025"));
        radios.add(new Radio("Kiss Kiss", R.drawable.kisskiss, "http://wma08.fluidstream.net:4610/"));
        radios.add(new Radio("Virgin Radio", R.drawable.virginradio, "http://icecast.unitedradio.it/Virgin.mp3"));
        radios.add(new Radio("Radio Deejay", R.drawable.radiodeejay, "https://4c4b867c89244861ac216426883d1ad0.msvdn.net/radiodeejay/radiodeejay/play1.m3u8"));
        radios.add(new Radio("Radio Zeta", R.drawable.radiozeta, "https://streamingv2.shoutcast.com/radio-zeta"));
        // radios.add(new Radio("Radio Norba *non va*", R.drawable.radionorba, "https://stream9.xdevel.com/audio0s975885-461/stream/icecast.audio"));

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), radios);
        list.setAdapter(customAdapter);
        list.setClickable(true);
        list.setLongClickable(true);

        // premendo a lungo su un item della lista appare un dialog per la conferma
        // della rimozione della radio selezionata
        list.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirm");

            TextView message = new TextView(MainActivity.this);
            message.setText("Do you want to delete this radio from your list?");
            message.setTextSize(20);
            message.setPadding(50, 40, 40, 0);
            builder.setView(message);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                radios.remove(radios.get(position));
                customAdapter.notifyDataSetChanged();
            });

            builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

            builder.show();
            return true;
        });

        // con un semplice click parte l'activity dedicata al player della radio scelta
        // passo la lista delle radio completa come extra per sapere quale radio avviare
        // quando l'utente clicca sui tasti previous e next del player
        list.setOnItemClickListener((parent, view, position, id) -> {
            // quando avvio l'activity del player passo anche la lista completa delle radio,
            // per sapere quale radio avviare quando l'utente usa i pulsanti previous e next
            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
            intent.putExtra("to_be_played", radios.get(position));
            intent.putExtra("full_list", radios);
            startActivity(intent);
        });

        // pulsante per l'aggiunta di una radio alla lista
        add.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Radio info");

            // rendo il link nella textview cliccabile
            TextView link = new TextView(MainActivity.this);
            link.setTextSize(18);
            link.setPadding(0, 20, 0, 20);
            String text = "Click here for some popular radio urls!";
            String redirect = "https://www.maccanismi.it/2012/08/21/elenco-url-streaming-radio-italiane-sul-web-rtl-rds-radio-kiss-kiss-r101-virgin-radio-e-moltre-altre/";
            SpannableString spannableString = new SpannableString(text);
            spannableString.setSpan(new URLSpan(redirect), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            link.setText(spannableString);
            link.setMovementMethod(LinkMovementMethod.getInstance());

            EditText name = new EditText(MainActivity.this);
            name.setHint("Insert radio name here...");

            EditText url = new EditText(MainActivity.this);
            url.setHint("Insert radio url here...");

            // setup layout del dialog
            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 30, 30, 50);
            layout.addView(link);
            layout.addView(name);
            layout.addView(url);
            builder.setView(layout);

            builder.setPositiveButton("OK", (dialog, which) -> {
                radios.add(new Radio(name.getText().toString(), R.drawable.user, url.getText().toString()));
                customAdapter.notifyDataSetChanged();
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });
    }
}

