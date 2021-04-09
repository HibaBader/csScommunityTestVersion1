package graduation.project.bzu.cscomunity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import graduation.project.bzu.cscomunity.Adapters.spinnerAdapter;
import graduation.project.bzu.cscomunity.DataModels.Subject;
import graduation.project.bzu.cscomunity.R;

public class CreatePostHome extends AppCompatActivity {
    EditText postSubject, postTitle, postTags, postBody;
    Button submit;
    Spinner subjectsSpinner;
    Spinner subjectsSpinner2;
    ArrayList<String> subjectsListSpinner = new ArrayList<>();
    private spinnerAdapter adapter;
    private ArrayAdapter<String> subjectSpinnerAdapter;
    private ArrayAdapter <CharSequence>subjectSpinnerAdapter2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_post_home_layout);
        BottomNavigationView BttomnavigationView = findViewById(R.id.bottomNavigationView);
      //  BttomnavigationView.setSelectedItemId(R.id.menu);
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
                        startActivity(new Intent(getApplicationContext(), CreatePost.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), Menu.class));
                        overridePendingTransition(0, 0);

                        return true;
                }
                return false;
            }
        });
        subjectsSpinner = findViewById(R.id.spinner1);
        subjectsSpinner2 = findViewById(R.id.spinner2);
        populateSpinner();
        subjectSpinnerAdapter2= ArrayAdapter.createFromResource(this, R.array.PostType,android.R.layout.simple_spinner_item);
        subjectSpinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectsSpinner2.setAdapter(subjectSpinnerAdapter2);

        submit = (Button) findViewById(R.id.post_submit1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitPost();
                Toast.makeText(CreatePostHome.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void submitPost() {
        String post_url = "http://192.168.1.111:8080/api/post";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // postSubject = findViewById(R.id.post_subject);
        postTitle = findViewById(R.id.post_title);
        postTags = findViewById(R.id.post_tags1);
        postBody = findViewById(R.id.post_body1);
        subjectsSpinner= findViewById(R.id.spinner1);
        JSONObject postData = new JSONObject();



        try {
            postData.put("postType", subjectsSpinner2.getSelectedItem().toString().trim());
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
        String url = "http://192.168.1.111:8080/api/subject";

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
                        subjectSpinnerAdapter= new ArrayAdapter<>(CreatePostHome.this, android.R.layout.simple_spinner_item,subjectsListSpinner);
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
