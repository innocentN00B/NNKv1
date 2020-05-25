package com.bignerdranch.android.nevernotknitting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.UUID;


public class CurrentProjectFragment extends Fragment {

    protected boolean onCreateCalled;
    private static final String ARG_RECIPE_ID = "recipe_id";

    private CardView cardView;
    private Recipe mRecipe;
    private TextView recipeName;
    private TextView recipeLevel;
    private TextView recipeType;
    private TextView recipeDescription;

    private RecipeDatabase mRecipeDatabase;

    public static CurrentProjectFragment newInstance(UUID recipeId) {
        CurrentProjectFragment fragment = new CurrentProjectFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        UUID recipeId = (UUID) getArguments().getSerializable(ARG_RECIPE_ID);
        mRecipeDatabase = RecipeDatabase.get(getContext());
        mRecipe = mRecipeDatabase.getRecipe(recipeId);
        onCreateCalled = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        RecipeDatabase.get(getActivity()).updateRecipe(mRecipe);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current, container, false);

        cardView = (CardView) v.findViewById(R.id.cardview_currentRecipe);

        recipeName = v.findViewById(R.id.name_current_recipe);
        recipeLevel = v.findViewById(R.id.level_current_recipe);
        recipeType = v.findViewById(R.id.type_current_recipe);
        recipeDescription = v.findViewById(R.id.description_current_recipe);

        recipeName.setText(mRecipe.getName());
        recipeLevel.setText(mRecipe.getLevel());
        recipeType.setText(mRecipe.getType());
        recipeDescription.setText(mRecipe.getDescription());

        return v;
    }
}
