package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showFirstFragment = findViewById(R.id.button1);
        Button showSecondFragment = findViewById(R.id.button2);

        showFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new FirstFragment(), "FirstFragment");
            }
        });

        showSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SecondFragment(), "SecondFragment");
            }
        });

    }

    private void loadFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        // check if fragment is already in backstack
        Fragment existingFragment = fragmentManager.findFragmentByTag(tag);
        if (existingFragment != null && existingFragment.isVisible()) {
            return;
        }

        // backstack functionality
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayoutFragment, fragment, tag)
                .addToBackStack(tag)
                .commit();

    }
}