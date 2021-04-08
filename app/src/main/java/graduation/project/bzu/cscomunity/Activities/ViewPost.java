package graduation.project.bzu.cscomunity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import graduation.project.bzu.cscomunity.Adapters.GetPostsAdapter;
import graduation.project.bzu.cscomunity.DataModels.Post;
import graduation.project.bzu.cscomunity.DataModels.User;
import graduation.project.bzu.cscomunity.R;

public class ViewPost extends AppCompatActivity {
   // private String JSON_URL="http://192.168.1.113:8080/api/get";
    List<Post> posts;
    List<User> users;
    RecyclerView recyclerView;
    GetPostsAdapter adapter;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_post_main);
        BottomNavigationView BttomnavigationView =findViewById(R.id.bottomNavigationView);
        BttomnavigationView.setSelectedItemId(R.id.homeIcon);
        BttomnavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeIcon:

                        return true;
                    case R.id.question:
                        startActivity(new Intent(getApplicationContext(), Question.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.topic:
                        startActivity(new Intent(getApplicationContext(), Topic.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.notifications:
                        startActivity(new Intent(getApplicationContext(), CreatePost.class));
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
        recyclerView = findViewById(R.id.subjectsList);

        posts=new ArrayList<>();
        users=new ArrayList<>();
        extractPosts("http://192.168.1.113:8080/api/get");

        FloatingActionButton fab_addNewPost = findViewById(R.id.fab_add);
        fab_addNewPost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(), CreatePostHome.class));
            }
        }
        );

    }
    private void extractPosts(String JSON_URL) {
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i< response.length();i++){
                    try {
                        JSONObject postObject = response.getJSONObject(i);
                        Post post = new Post();

                        post.setAttachment(postObject.getString("attachment").toString());
                        post.setPostBody(postObject.getString("postBody").toString());
                        post.setPostID(postObject.getInt("postID"));
                        post.setPostSubject(postObject.getString("postSubject").toString());
                        post.setPostTags(postObject.getString("postTags").toString());
                        post.setPostTitle(postObject.getString("postTitle").toString());
                        post.setPostType(postObject.getString("postType").toString());
                        String user1=  postObject.getString("user");
                        Gson g = new Gson();
                        User user = g.fromJson(user1, User.class);

                    post.setUser(user);





                        posts.add(post);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                adapter = new GetPostsAdapter(getApplicationContext(),posts);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: testExtractPosts" + error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }


}