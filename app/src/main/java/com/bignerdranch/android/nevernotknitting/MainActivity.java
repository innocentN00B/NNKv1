
package com.bignerdranch.android.nevernotknitting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        //Front page fragment initialised
        Fragment frontPageFragment = fm.findFragmentById(R.id.fragment_container);

        //YoutubeFragment

        if(frontPageFragment == null) {
            frontPageFragment = new FrontPageFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, frontPageFragment)
                    .commit();
        }
    }
}
