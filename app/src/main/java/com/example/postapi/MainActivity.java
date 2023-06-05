package com.example.postapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    TextView mTextView;
    EditText mTextTitle;
    EditText mTextBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);
        mTextTitle = findViewById(R.id.textTitle);
        mTextBody = findViewById(R.id.textBody);

        mButton.setOnClickListener(View -> {
            try {
                postSubmit();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void postSubmit() throws JSONException {
        String url = "https://jsonplaceholder.typicode.com/posts";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", mTextTitle.getText());
        jsonObject.put("body", mTextBody.getText());
        jsonObject.put("userId", 1);

        mTextView.setText("Loading...");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Handle response
                    mTextView.setText("Success Input Data!");
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle error
                    mTextView.setText("Error!");
                }
            }
        );

        // Add the request to the RequestQueue.
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}