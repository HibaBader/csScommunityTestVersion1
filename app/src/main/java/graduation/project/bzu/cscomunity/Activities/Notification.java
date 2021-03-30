package graduation.project.bzu.cscomunity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import graduation.project.bzu.cscomunity.R;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        BottomNavigationView BttomnavigationView =findViewById(R.id.bottomNavigationView);
        BttomnavigationView.setSelectedItemId(R.id.notifications);
        BttomnavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeIcon:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.question:
                        startActivity(new Intent(getApplicationContext(),CreatePost.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.topic:
                        startActivity(new Intent(getApplicationContext(), Topic.class));
                        overridePendingTransition(0,0);
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
    }
}
