package com.sliit.smartserviceassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Youtube extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener,
        YouTubePlayer.PlaybackEventListener,
        YouTubePlayer.PlayerStateChangeListener {

    YouTubePlayerView playerView;

    String API_KEY = "AIzaSyCas7l8YUnnKXq2af07l-vT7FNvUb-1fBw";
    String VIDEO_ID = "d4Sn6ny_5LI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        playerView = (YouTubePlayerView)findViewById(R.id.playerView);
        playerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

}