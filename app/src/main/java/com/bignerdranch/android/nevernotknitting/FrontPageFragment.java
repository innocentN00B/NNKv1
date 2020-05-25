package com.bignerdranch.android.nevernotknitting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FrontPageFragment extends Fragment {

    private Button mCurrentProject;
    private Button mBrowseRecipes;
    private Button mGuidance;

    private static final String ARG_RECIPE_ID = "recipe_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frontpage, container, false);

        //Browse project button
        mBrowseRecipes = (Button) v.findViewById(R.id.browseRecipes);
        mBrowseRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BrowseRecipesFragment nextFrag= new BrowseRecipesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        //Guidance button
        mGuidance = (Button) v.findViewById(R.id.guidance);
        mGuidance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuidanceFragment nextFrag= new GuidanceFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }


}
