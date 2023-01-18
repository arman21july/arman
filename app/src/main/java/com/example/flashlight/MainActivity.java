package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.flashlight.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.button.getText().toString().equals("Turn on")){
                binding.button.setText(R.string.turnoff);
                binding.flash.setImageResource(R.drawable.fl);
                changeLightState(true);
                }else {
                    binding.button.setText(R.string.turnon);
                    binding.flash.setImageResource(R.drawable.f);
                    changeLightState(false);
                }
            }
        });
    }

    private void changeLightState(boolean state) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String camId=null;

            try {
                camId=cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(camId,state);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText(R.string.turnon);
    }
}