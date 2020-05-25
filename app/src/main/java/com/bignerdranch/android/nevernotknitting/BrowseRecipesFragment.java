package com.bignerdranch.android.nevernotknitting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;
import java.util.List;
import java.util.UUID;


public class BrowseRecipesFragment extends Fragment  {

    //RecyclerView
    private RecyclerView mRecipeRecyclerView;

    //Database
    private RecipeDatabase mRecipeDatabase;

    //Adapter
    private RecipeAdapter mAdapter;


    //public void update(Observable observable, Object data) {
      //  mAdapter.notifyDataSetChanged();
    //}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialise RecipeDatabase
        mRecipeDatabase = RecipeDatabase.get(getContext());
        //mRecipeDatabase.addObserver(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_browse, container, false);

        //List of recipes
        mRecipeRecyclerView = (RecyclerView) v.
                findViewById(R.id.recycler_view_recipes);
        mRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        RecipeDatabase recipeDatabase = RecipeDatabase.get(getContext());
        List<Recipe> recipes = recipeDatabase.getRecipes();

        if (mAdapter == null) {
            mAdapter = new RecipeAdapter(recipes);
            mRecipeRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Recipe r;
        private UUID recipeId;
        private boolean isKnitted;
        //CardView
        private CardView cardview;
        private TextView recipeName;
        private TextView recipeLevel;
        private TextView recipeType;
        private Button mSetKnittedButton;


        public RecipeHolder(View recipeView) {
            super(recipeView);

            //Cardview
            cardview = (CardView) recipeView.findViewById(R.id.cardview);

            //Button
            mSetKnittedButton = recipeView.findViewById(R.id.cardview_button_viewRecipe);
            mSetKnittedButton.setOnClickListener(this);

            //Fields from recipe

            recipeName = recipeView.findViewById(R.id.name_recipe);
            recipeLevel = recipeView.findViewById(R.id.level_recipe);
            recipeType = recipeView.findViewById(R.id.type_recipe);
        }

        private void bind(Recipe recipe, int position) {
            recipeId = recipe.getId();
            recipeName.setText(recipe.getName());
            recipeLevel.setText(recipe.getLevel());
            recipeType.setText(recipe.getType());
            isKnitted = recipe.getIsKnitted();
            r = mRecipeDatabase.getRecipe(recipeId);

        }

        @Override
        public void onClick(View v) {
            showAlertDialog(recipeId);
        }


        private void showAlertDialog(final UUID recipeId) {
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setMessage(R.string.alert_dialog);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Show fragment
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, CurrentProjectFragment.newInstance(r.getId()));
                    ft.commit();
                    updateUI();
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.create().show();
        }
    }

    private class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {
        private List<Recipe> mRecipes;
        private LayoutInflater inflater;

        public RecipeAdapter(List<Recipe> data) { mRecipes = data; }

        @Override
        public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.cardview_recipe_layout, parent, false);



            return new RecipeHolder(v);
        }

        @Override
        public void onBindViewHolder(RecipeHolder holder, int position) {
            final Recipe recipe = mRecipes.get(position);
            holder.bind(recipe, position);
        }

        @Override
        public int getItemCount() {
            return mRecipes.size();
        }

        public void setRecipes(List<Recipe> recipes) {
            mRecipes = recipes;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }
}

