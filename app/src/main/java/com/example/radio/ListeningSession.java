package com.example.radio;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.util.Calendar;
import java.util.Date;

public class ListeningSession {
    String uuid;
    Radio radio;
    Date start = null, end = null;
    final String ENDPOINT = "https://tc.heiz.cc/UlnaTracker/";

    public ListeningSession(Radio radio, String uuid) {
        this.radio = radio;
        this.uuid = uuid;
    }

    public void startSession() {
        this.start = new Date();
    }

    public void stopSession() {
        if (this.start == null) return;
        this.end = new Date();
    }

    private String escapeXML(String to_escape) {
        return to_escape.replaceAll("&", "&amp;")
                .replaceAll(">", "&gt;")
                .replaceAll("<", "&lt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&apos;");
    }
    public void send(Context ctx) {
        RequestQueue rq = Queue.getInstance(ctx);
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String startTime = formatter.format(start);
        String endTime = formatter.format(end);
        String body = "<session>";
        body += "<user>" + this.uuid + "</user>";
        body += "<webradio>" + this.escapeXML(this.radio.name) + "</webradio>";
        body += "<url>" + this.escapeXML(this.radio.url) + "</url>";
        body += "<start>" + startTime + "</start>";
        body += "<end>" + endTime + "</end>";
        body += "</session>";
        XMLRequest req = new XMLRequest(Request.Method.POST, this.ENDPOINT, body,
            response -> {
                Log.println(Log.ERROR, "Response Body", response);
            }, error -> {
                Toast.makeText(ctx, "Network error!", Toast.LENGTH_LONG).show();
                Log.println(Log.ERROR, "Response Error", new String(error.networkResponse.data));
            });
        rq.add(req);
    }
}