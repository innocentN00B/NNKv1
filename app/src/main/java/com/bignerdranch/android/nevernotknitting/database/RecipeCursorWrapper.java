package com.bignerdranch.android.nevernotknitting.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.nevernotknitting.Recipe;


import java.util.UUID;

import static com.bignerdranch.android.nevernotknitting.database.RecipeDbSchema.*;

public class RecipeCursorWrapper extends CursorWrapper {

    public RecipeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Recipe getRecipe() {
        String uuidString = getString(getColumnIndex(RecipeTable.Cols.UUID));
        String name = getString(getColumnIndex(RecipeTable.Cols.NAME));
        String level = getString(getColumnIndex(RecipeTable.Cols.LEVEL));
        String type = getString(getColumnIndex(RecipeTable.Cols.TYPE));
        String description = getString(getColumnIndex(RecipeTable.Cols.DESCRIPTION));
        int isKnitted = getInt(getColumnIndex(RecipeTable.Cols.KNITTED));

        Recipe recipe = new Recipe(UUID.fromString(uuidString));
        recipe.setName(name);
        recipe.setLevel(level);
        recipe.setType(type);
        recipe.setDescription(description);
        recipe.setIsKnitted(isKnitted != 0);

        return recipe;
    }
}
