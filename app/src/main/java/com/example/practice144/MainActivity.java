package com.example.practice144;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgStringRequest, imgJsonRequest, imgImageRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewImages();
    }

    private void findViewImages() {
        imgStringRequest = findViewById(R.id.imgStringRequest);
        imgJsonRequest = findViewById(R.id.imgJsonRequest);
        imgImageRequest = findViewById(R.id.imgImageRequest);

        onClickImages();
    }

    private void onClickImages() {
        imgStringRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StringRquestActivity.class));
            }
        });

        imgJsonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), JsonRequestActivity.class));
            }
        });

        imgImageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ImageRequestActivity.class));
            }
        });
    }
}