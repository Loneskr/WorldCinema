package com.example.worldcinema.Menu;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.worldcinema.R;
import com.example.worldcinema.UI.Collections.Fragment.CollectionFragment;
import com.example.worldcinema.UI.Home.CompilationFragment;
import com.example.worldcinema.UI.Home.HomeFragment;
import com.example.worldcinema.UI.Profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity  extends AppCompatActivity {

    private final Fragment homeFragment = new HomeFragment();
    private final Fragment FragmentCompilation = new CompilationFragment();
    private final Fragment collectionsFragment = new CollectionFragment();
    private final Fragment profileFragment = new ProfileFragment();
    private Fragment active = homeFragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_container, homeFragment, "home").commit();
        fragmentManager.beginTransaction().add(R.id.main_container, FragmentCompilation, "compilation").hide(FragmentCompilation).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, collectionsFragment, "collections").hide(collectionsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, profileFragment, "profile").hide(profileFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(onNavigationItemSelectedListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.page1:
                    fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;
                case R.id.page2:
                    fragmentManager.beginTransaction().hide(active).show(FragmentCompilation).commit();
                    active = FragmentCompilation;
                    return true;
                case R.id.page3:
                    fragmentManager.beginTransaction().hide(active).show(collectionsFragment).commit();
                    active = collectionsFragment;
                    return true;
                case R.id.page4:
                    fragmentManager.beginTransaction().hide(active).show(profileFragment).commit();
                    active = profileFragment;
                    return true;
            }
            return false;
        }
    };


}
