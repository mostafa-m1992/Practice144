package com.example.practice144;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.practice144.Class.AppController;
import com.google.android.material.card.MaterialCardView;

public class StringRquestActivity extends AppCompatActivity {

    TextView txtResult;
    MaterialCardView crdHoldResult;
    Button btnSendStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_rquest);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewString();
    }

    private void findViewString() {
        txtResult = findViewById(R.id.txtResult);
        crdHoldResult = findViewById(R.id.crdHoldResult);
        btnSendStringRequest = findViewById(R.id.btnSendStringRequest);

        crdHoldResult.setVisibility(View.GONE);

        btnSendStringRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendStringRequest();
            }
        });
    }

    private void sendStringRequest() {
        //String url = "http://192.168.1.100/PracticeVolley/stringRequest.php";
        String url = "http://wiadevelopers.ir/api/volley/stringRequest.php";

        final ProgressDialog dialog;
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                crdHoldResult.setVisibility(View.VISIBLE);
                txtResult.setText(response);
                dialog.dismiss();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        };

        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);

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