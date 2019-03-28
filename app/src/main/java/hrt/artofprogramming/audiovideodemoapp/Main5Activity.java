package hrt.artofprogramming.audiovideodemoapp;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener
{

    Button btnAudio,btnVideo,btnImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        btnAudio = findViewById(R.id.btnAudio);
        btnAudio.setOnClickListener(this);
        btnImage = findViewById(R.id.btnImage);
        btnImage.setOnClickListener(this);
        btnVideo = findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()){
            case R.id.btnAudio:
                intent=new Intent(getApplicationContext(),AudioRecordActivity.class);
                break;
            case R.id.btnVideo:
                intent=new Intent(getApplicationContext(),VideoCaptureActivity.class);
                break;
            case R.id.btnImage:
                intent=new Intent(getApplicationContext(),ImageCaptureActivity.class);
                break;
        }
        startActivity(intent);
    }
}
