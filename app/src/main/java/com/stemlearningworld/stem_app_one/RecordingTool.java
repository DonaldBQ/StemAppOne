package com.stemlearningworld.stem_app_one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

public class RecordingTool extends AppCompatActivity {

    private CameraView cameraView;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_tool);

        setUpCameraView();
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    private void setUpCameraView() {
        cameraView = findViewById(R.id.camera);
        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {
            }

            @Override
            public void onError(CameraKitError cameraKitError) {
            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {
                // The File parameter is an MP4 file.
                Log.d("testing-tag", String.valueOf(cameraKitVideo.getVideoFile()));
            }
        });

        Button buttonView = findViewById(R.id.recordButton);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRecording) {
                    cameraView.stopVideo();
                    isRecording = false;
                    return;
                }

                cameraView.captureVideo();
                isRecording = true;
            }
        });
    }
}
