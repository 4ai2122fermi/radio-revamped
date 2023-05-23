package com.example.radio;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Queue {
    private static RequestQueue queue = null;
    private Queue() {}

    public static RequestQueue getInstance(Context ctx) {
        if (queue == null) queue = Volley.newRequestQueue(ctx);
        return queue;
    }
}
