package com.example.practice144;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.practice144.Adapter.CustomAdapter;
import com.example.practice144.Class.AppController;
import com.example.practice144.Models.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonRequestActivity extends AppCompatActivity {

    Button btnSendJsonObjectRequest, btnSendJsonArrayRequest;
    ListView lstContacts;

    ArrayList<Contact> contacts = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_request);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewJson();
    }

    private void findViewJson() {
        btnSendJsonObjectRequest = findViewById(R.id.btnSendJsonObjectRequest);
        btnSendJsonArrayRequest = findViewById(R.id.btnSendJsonArrayRequest);
        lstContacts = findViewById(R.id.lstContacts);

        customAdapter = new CustomAdapter(getApplicationContext(), contacts);
        lstContacts.setAdapter(customAdapter);
        lstContacts.setVisibility(View.GONE);

        btnSendJsonObjectRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts.clear();
                sendJsonObjectRequest();
            }
        });

        btnSendJsonArrayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts.clear();
                sendJsonArrayRequest();
            }
        });
    }

    private void sendJsonObjectRequest() {
        String url = "http://wiadevelopers.ir/api/volley/contactJsonObject.json";
        //String url = "http://192.168.1.100/PracticeVolley/contactJsonObject.json";

        ProgressDialog objectDialog = new ProgressDialog(this);
        objectDialog.setMessage("Loading...");
        objectDialog.setCancelable(false);
        objectDialog.show();

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String mName = response.getString("name");
                    String mPhone = response.getString("phone");
                    String mEmail = response.getString("email");

                    contacts.add(new Contact(mName, mPhone, mEmail));
                    ((BaseAdapter) lstContacts.getAdapter()).notifyDataSetChanged();
                    lstContacts.setVisibility(View.VISIBLE);
                    objectDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                objectDialog.dismiss();
            }
        };

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener);
        AppController.getInstance().addToRequestQueue(request);
    }

    private void sendJsonArrayRequest() {
        String url = "http://wiadevelopers.ir/api/volley/contactJsonArray.json";
        //String url = "http://192.168.1.100/PracticeVolley/contactJsonArray.json";

        ProgressDialog arrayDialog = new ProgressDialog(this);
        arrayDialog.setMessage("Loading...");
        arrayDialog.setCancelable(false);
        arrayDialog.dismiss();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);

                        String mName = object.getString("name");
                        String mPhone = object.getString("phone");
                        String mEmail = object.getString("email");

                        contacts.add(new Contact(mName, mPhone, mEmail));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayDialog.dismiss();
                lstContacts.setVisibility(View.VISIBLE);
                ((BaseAdapter) lstContacts.getAdapter()).notifyDataSetChanged();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                arrayDialog.dismiss();
            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);

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