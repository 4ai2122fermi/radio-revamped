package com.example.radio;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

public class XMLRequest extends StringRequest {
    private String requestBody;
    public XMLRequest(int method, String url, String body, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.requestBody = body;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        byte[] body = null;
        if (!TextUtils.isEmpty(this.requestBody)) {
            try {
                body = this.requestBody.getBytes(getParamsEncoding());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Encoding not supported: " + getParamsEncoding(), e);
            }
        }

        return body;
    }

}
