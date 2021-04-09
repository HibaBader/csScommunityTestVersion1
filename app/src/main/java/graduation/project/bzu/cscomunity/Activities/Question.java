package graduation.project.bzu.cscomunity.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import graduation.project.bzu.cscomunity.Adapters.GridSubjectsListAdapter;
import graduation.project.bzu.cscomunity.DataModels.Subject;
import graduation.project.bzu.cscomunity.R;

public class Question extends AppCompatActivity {
    public static Context context;
    RecyclerView recyclerView;
    List<Subject> subjects;
    private String JSON_URL="http://192.168.1.111:8080/api/subject";
    GridSubjectsListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.subjectsList);
        subjects = new ArrayList<>();
        extractSubject();


        BottomNavigationView BttomnavigationView =findViewById(R.id.bottomNavigationView);
        BttomnavigationView.setSelectedItemId(R.id.question);
        BttomnavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeIcon:
                        startActivity(new Intent(getApplicationContext(), ViewPost.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.question:
                        return true;

                    case R.id.topic:
                        startActivity(new Intent(getApplicationContext(), Topic.class));
                        overridePendingTransition(0, 0);

                        return true;
                    case R.id.notifications:
                        startActivity(new Intent(getApplicationContext(),CreatePost.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), Menu.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
    private void extractSubject() {
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i< response.length();i++){
                    try {
                        JSONObject subjectObject = response.getJSONObject(i);
                        Subject subject = new Subject();

                        subject.setName(subjectObject.getString("name").toString());
                        subject.setImageURL(subjectObject.getString("image"));

                        subjects.add(subject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false));

                adapter = new GridSubjectsListAdapter(getApplicationContext(),subjects);
                recyclerView.setAdapter(adapter);
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

