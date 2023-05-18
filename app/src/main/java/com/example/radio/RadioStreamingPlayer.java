package com.example.radio;

import android.media.MediaPlayer;

import java.io.IOException;

public class RadioStreamingPlayer implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    private MediaPlayer mediaPlayer;
    private String streamUrl;
    private boolean isPrepared;

    public RadioStreamingPlayer(String streamUrl) {
        this.streamUrl = streamUrl;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    public void play() throws IOException {
        mediaPlayer.setDataSource(streamUrl);
        mediaPlayer.prepareAsync();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void start() {
        mediaPlayer.start();
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        isPrepared = false;
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        isPrepared = true;
        mediaPlayer.start();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        isPrepared = false;
        mediaPlayer.reset();
        return false;
    }
}
