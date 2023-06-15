package net.ntzw.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Camera camera;
    private static boolean isFlashing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera = Camera.open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.release();
        isFlashing = false;
    }

    public void onButtonClick(View view) {
        isFlashing = !isFlashing;
        updateFlashing();
    }

    private void updateFlashing() {
        if(isFlashing) {
            enableFlashlight();
        } else {
            disableFlashlight();
        }
    }

    private void enableFlashlight() {
        Camera.Parameters p = camera.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(p);
        camera.startPreview();
    }

    private void disableFlashlight() {
        Camera.Parameters p = camera.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(p);
        camera.stopPreview();
    }
}
