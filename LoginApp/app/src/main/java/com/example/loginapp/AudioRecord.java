package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class AudioRecord extends AppCompatActivity {


    private static int MICROPHONE_PERMISSION_CODE = 200;

    Button btnRecord,btnStop,btnPlay;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        initView();
        CLickOfRecord();
        CLickOfStop();
        CLickOfPlay();

        if(isMicrophonePrsent())
        {
            getMicrophonePermission();
        }
    }
    private boolean isMicrophonePrsent()
    {
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }
        else
        {
            return false;
        }
    }
    private void getMicrophonePermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},MICROPHONE_PERMISSION_CODE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(AudioRecord.this,DialogBox.class);
        startActivity(i);
        finish();
    }

    private void CLickOfPlay() {

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    mediaPlayer =new MediaPlayer();
                    mediaPlayer.setDataSource(getRecordingFilePath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(),"Recording is Playing.",Toast.LENGTH_LONG).show();

                }
                catch (Exception ex)
                {

                }
            }
        });
    }

    private void CLickOfStop() {

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;

                    Toast.makeText(getApplicationContext(),"Recording is Stopped.",Toast.LENGTH_LONG).show();

                }
                catch (Exception ex)
                {

                }
            }
        });

    }

    private void CLickOfRecord() {
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setOutputFile(getRecordingFilePath());
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    Toast.makeText(getApplicationContext(),"Recording is Started...",Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {

                }
            }
        });
    }
    private String getRecordingFilePath()
    {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirctory =contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirctory,"testRecordingFile "+ ".mp3");
        return file.getPath();
    }
    private void initView() {
        btnRecord =findViewById(R.id.btnRecord);
        btnStop =findViewById(R.id.btnStop);
        btnPlay =findViewById(R.id.btnPlay);
    }
}