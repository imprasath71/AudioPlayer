package com.example.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;    // this MediaPlayer datatype is defined which is used in playing audio
    AudioManager audioManager;   // this manages the functions for the audio played
    public void play(View view){
     mp = MediaPlayer.create(this,R.raw.demo);   // this starts playing the audio whenever the button play is pressed
    mp.start();

}
public void pause(View view){

        mp.pause();   // this pauses the song when button is pressed (pause)
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);  // this is used for seekbar purpose
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //getting max volume of mobile phones
        int currentvolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mp = MediaPlayer.create(this,R.raw.demo);   //song selected from resources.
        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);  // declaring seekbar found from id(seekbar)
        volumeControl.setMax(maxVolume);          //setting up max volume
        volumeControl.setProgress(currentvolume);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // this is a listener whenever change is happened in the seekbar
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_SHOW_UI);
                // this flag show UI shows the volume gets reduced in the UI.

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar seekvolume = (SeekBar) findViewById(R.id.seekBar2);
        seekvolume.setMax(mp.getDuration());
        seekvolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               mp.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekvolume.setProgress(mp.getCurrentPosition());

            }
        }, 0, 300);

    }
}