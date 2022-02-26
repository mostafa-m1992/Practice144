package com.example.practice144;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.practice144.Class.AppController;

public class ImageRequestActivity extends AppCompatActivity {

    Button btnSendImageRequest;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_request);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewImage();
    }

    private void findViewImage() {
        btnSendImageRequest = findViewById(R.id.btnSendImageRequest);
        imgView = findViewById(R.id.imgView);

        btnSendImageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImageRequest();
            }
        });
    }

    private void sendImageRequest() {
        String url = "http://wiadevelopers.ir/api/volley/wiadevelopers.png";

        ProgressDialog imageDialog = new ProgressDialog(this);
        imageDialog.setMessage("Loading...");
        imageDialog.setCancelable(false);
        imageDialog.show();

        Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imgView.setImageBitmap(response);
                imageDialog.dismiss();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                imageDialog.dismiss();
            }
        };

        ImageRequest request = new ImageRequest(url, listener, 0, 0, null, null, errorListener);

        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}