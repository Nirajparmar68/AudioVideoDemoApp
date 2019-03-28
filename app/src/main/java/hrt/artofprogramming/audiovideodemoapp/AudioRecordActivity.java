package hrt.artofprogramming.audiovideodemoapp;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioRecordActivity extends AppCompatActivity {

    Button btnPlay, btnRecord, btnStopRecord;
    MediaRecorder mr;
    File f;
    Uri audioURI;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        int pc1 = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA);
        int pc2 = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO);
        int pc3 = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (pc1 != 0 && pc2 != 0 && pc3 != 0) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 123);
        }
        mediaPlayer = new MediaPlayer();
        btnPlay = findViewById(R.id.btnPlayAudio);
        btnRecord = findViewById(R.id.btnRecordAudio);
        btnStopRecord = findViewById(R.id.btnStopRecord);
        btnStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStopRecord.setEnabled(false);
                btnRecord.setEnabled(true);
                mr.stop();
            }
        });
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRecord.setEnabled(false);
                btnStopRecord.setEnabled(true);
                mr = new MediaRecorder();
                mr.setAudioSource(MediaRecorder.AudioSource.MIC);
                mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String mFileName = "JPEG_" + timeStamp + "_";
                File storageDir = getExternalFilesDir(Environment.getExternalStorageDirectory()+
                        "/hrtmusic");
                try {
                    f = File.createTempFile(mFileName, ".mp3", storageDir);
                    audioURI = FileProvider.getUriForFile(getApplicationContext(),
                            BuildConfig.APPLICATION_ID + ".provider",
                            f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mr.setOutputFile(f);

                }
                try {
                    mr.prepare();
                    mr.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    mediaPlayer.setDataSource(getApplicationContext(),audioURI);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
