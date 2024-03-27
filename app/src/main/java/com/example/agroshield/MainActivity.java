package com.example.agroshield;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.widget.Button;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId())
                    {
                        case R.id.menu:
                            startActivity(new Intent(getApplicationContext(), menupage.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.home:
                            return true;


                    case R.id.you:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    }
                    return false;
                }
            });
        Button pest_Detection = findViewById(R.id.pest_Detection);
        pest_Detection.setOnClickListener(v -> {
            Intent pest = new Intent(MainActivity.this, pest_Detection.class);
            startActivity(pest);
        });


    }


}