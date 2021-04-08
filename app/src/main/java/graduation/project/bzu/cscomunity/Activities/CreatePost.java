package graduation.project.bzu.cscomunity.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import graduation.project.bzu.cscomunity.Adapters.GridSubjectsListAdapter;
import graduation.project.bzu.cscomunity.Adapters.spinnerAdapter;
import graduation.project.bzu.cscomunity.DataModels.Subject;
import graduation.project.bzu.cscomunity.R;



public class CreatePost extends AppCompatActivity {
    EditText postSubject, postTitle, postTags, postBody;
    Button submit;
    Spinner subjectsSpinner;
    ArrayList<String> subjectsListSpinner = new ArrayList<>();
    private spinnerAdapter adapter;
    private ArrayAdapter <String>subjectSpinnerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_post_layout);
        BottomNavigationView BttomnavigationView = findViewById(R.id.bottomNavigationView);
        BttomnavigationView.setSelectedItemId(R.id.notifications);
        BttomnavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeIcon:
                        startActivity(new Intent(getApplicationContext(), ViewPost.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.question:
                        startActivity(new Intent(getApplicationContext(), Question.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.topic:
                        startActivity(new Intent(getApplicationContext(), Topic.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.notifications:
                        return true;

                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), Menu.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        subjectsSpinner = findViewById(R.id.spinner);
        populateSpinner();
       // adapter = new spinnerAdapter(this, subjectsListSpinner);
       // subjectsSpinner.setAdapter(adapter);
        //subjectsSpinner.setSelection(0);

//        subjectsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//               Subject clickedItem = (Subject) parent.getItemAtPosition(position);
//               String clickedItemName = clickedItem.getName();
//                Toast.makeText(CreatePost.this,clickedItemName + " selected", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(CreatePost.this, " Nothing selected", Toast.LENGTH_LONG).show();
//            }
//        });
        submit = (Button) findViewById(R.id.post_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitPost();
                Toast.makeText(CreatePost.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void submitPost() {
        String post_url = "http://192.168.1.113:8080/api/post";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
       // postSubject = findViewById(R.id.post_subject);
        postTitle = findViewById(R.id.post_title);
        postTags = findViewById(R.id.post_tags);
        postBody = findViewById(R.id.post_body);
        subjectsSpinner= findViewById(R.id.spinner);
        JSONObject postData = new JSONObject();



        try {
            postData.put("postType", "Question");
            Log.d("TAG", "submitPost: " + subjectsSpinner.getSelectedItem().toString());
           postData.put("postSubject",  subjectsSpinner.getSelectedItem().toString().trim());
          //  postData.put("postSubject", ob.getName().toString());
            postData.put("postTitle", postTitle.getText().toString().trim());
            postData.put("postTags", postTags.getText().toString().trim());
            postData.put("postBody", postBody.getText().toString().trim());
            postData.put("attachment", "atsztachment");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("tag", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("tag", "onErrorResponse:ERROR");
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
    private void populateSpinner(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.113:8080/api/subject";

            RequestQueue queue= Volley.newRequestQueue(this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    for (int i=0; i< response.length();i++){
                        try {
                            JSONObject subjectObject = response.getJSONObject(i);
                            Subject subject = new Subject();
                            subject.setName(subjectObject.getString("name").toString());
                          //  subject.setImageURL(subjectObject.getString("image"));


                            subjectsListSpinner.add(subject.getName());
                            subjectSpinnerAdapter= new ArrayAdapter<>(CreatePost.this, android.R.layout.simple_spinner_item,subjectsListSpinner);
                            subjectSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            subjectsSpinner.setAdapter(subjectSpinnerAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("tag", "onErrorResponse: " + error.getMessage());
                }
            });
        queue.add(jsonArrayRequest);
        }
}
