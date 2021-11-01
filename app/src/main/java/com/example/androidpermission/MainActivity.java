package com.example.androidpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 112;
    private static final int STORAGE_PERMISSION_CODE = 113;
    Button btnCamera,btnStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCamera=findViewById(R.id.btnCamera);
        btnStorage=findViewById(R.id.btnStorage);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE);

            }
        });

        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE);

            }
        });


    }

    public void checkPermission(String permission, int requestCode){


        if (ContextCompat.checkSelfPermission(MainActivity.this,permission)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

        } else {
            Toast.makeText(MainActivity.this,"Permission already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==CAMERA_PERMISSION_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                btnCamera.setText("Permission Granted");
                Toast.makeText(MainActivity.this, "Camera permission Granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Camera permission Dinied", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode==STORAGE_PERMISSION_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                btnStorage.setText("Permission Granted");
                Toast.makeText(MainActivity.this, "Storage permission Granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Storage permission Dinied", Toast.LENGTH_SHORT).show();
            }
        }


    }
}