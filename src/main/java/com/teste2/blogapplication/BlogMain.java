package com.teste2.blogapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BlogMain extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView navigationView;

    private HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_main);
        //TOOLBAR
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        //NavMENU
       // navigationView = findViewById(R.id.blogMainBottomNav);


        //FRAGMENTS

        homeFragment = new HomeFragment();


        //navMenu Buttons
/*
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_account:
                        ChangeFragment(accountFragment);
                        return true;
                    case R.id.nav_home:
                        ChangeFragment(homeFragment);
                        return true;
                    case R.id.nav_notification:
                        ChangeFragment(notificationFragment);
                        return true;
                    default:
                        return false;

                }


            }
        });
*/
ChangeFragment(homeFragment);
if(getIntent().getExtras()!=null)
{
   String key = getIntent().getStringExtra("Update");
   if(key.compareTo("true")==0)
   {
       ChangeFragment(homeFragment);
   }
}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items, menu);

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Action_logout_btn:
                MainActivity.curUser = null;
                Intent intent = new Intent(BlogMain.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.Action_add_btn:
                Add();
                return true;
            default:
                return false;
        }


    }




    public void Add() {
        Intent intent = new Intent(BlogMain.this, AddActivity.class);
        startActivity(intent);
    }

    public void ChangeFragment(Fragment destination) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.blog_container, destination);
        fragmentTransaction.commit();

    }
}
