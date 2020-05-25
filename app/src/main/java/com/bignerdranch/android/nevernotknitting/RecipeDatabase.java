package com.bignerdranch.android.nevernotknitting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.nevernotknitting.database.RecipeCursorWrapper;
import com.bignerdranch.android.nevernotknitting.database.RecipeDbHelper;
import com.bignerdranch.android.nevernotknitting.database.RecipeDbSchema.RecipeTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class RecipeDatabase extends Observable {

    private static RecipeDatabase sRecipeDatabase;
    private static SQLiteDatabase mDatabase;
    private Context mContext;

    public static RecipeDatabase get(Context context) {
        if (sRecipeDatabase == null) {
            mDatabase = new RecipeDbHelper(context.getApplicationContext())
                    .getWritableDatabase();

            sRecipeDatabase = new RecipeDatabase(context);
        }
        return sRecipeDatabase;
    }

    private RecipeDatabase(Context context) {
        if (getRecipes().size() == 0) {
            Recipe vilma = new Recipe();
            vilma.setName("Vilma");
            vilma.setType("Sweater");
            vilma.setLevel("Beginner");
            vilma.setDescription("Sådan gør du:\n" +
                    "For- og bagstykke: Slå 132 (144) 156 (168) m op med fv 18/lys lilla på rundp 7 mm, og strik 7 cm rib (1 r, 1 vr) rundt. Skift til p 8 mm og fv 03/lys grå. Fortsæt rundt i glatstrik, og sæt en markør i starten af arb og igen efter 66 (72) 78 (86) m. Når hele arb måler 20 (20) 22 (22) cm, lukkes der af til ærmegab ved at lukke 3 (3) 4 (4) m på hver side af markørerne = 60 (66) 70 (78) m til hver del.\n" +
                    "\n" +
                    "Bagstykke: Strikkes frem og tilbage i glatstrik. Luk yderligere af til ærmegab for 2,1,1 (2,2,1) 2,2,1,1 (2,2,2,1) m på hver anden p i hver side = 52 (56) 58 (64) m. Strik lige op, til hele arb måler 47 (47) 49 (51) cm. Sæt de midterste 22 (24) 24 (26) m på en hjælpep til hals, og strik hver side færdig for sig. Luk yderligere på hver 2. p for 2,2,1 m mod hals = 10 (11) 12 (14) m. Luk af, når arb måler 50 (50) 52 (54) cm. Strik den anden side på samme måde, bare spejlvendt.\n" +
                    "\n" +
                    "Forstykke: Luk af til ærmegab som på ryggen. Når hele arb måler 40 (40) 42 (44) cm, sættes de midterste 18 (20) 20 (22) m på en hjælpep til hals, og hver side strikkes færdig for sig. Luk yderligere på hver 2. p for 2,2,2,1 m mod hals = 10 (11) 12 (14) m. Luk af, når arbejdet måler 50 (50) 52 (54) cm. Strik den anden side på samme måde, bare spejlvendt.\n" +
                    "Montering: Sy skuldrene sammen.\n" +
                    "\n" +
                    "Halskant: Saml fra vestens retside 84 (92) 92 (96) m (inkl hvilende m) op, i fv 21/lys gul, på rundp 7 mm, og strik 11 cm rib (1 r, 1 vr) rundt. Luk løst af i ret. Buk halskanten, og sy den fast, pas på ikke at stramme for meget.\n" +
                    "Ærmekant: Saml fra vestens retside 90 (94) 98 (100) m op i fv 17/pink på rundp nr. 7, og strik 2,5 cm rib (1 r, 1 vr) rundt. Luk løst af i rib. Hæft ender.\n");
            vilma.setIsKnitted(false);

            Recipe mille = new Recipe();
            mille.setName("Mille");
            mille.setType("Beanie");
            mille.setLevel("Advanced");
            mille.setDescription("Sådan gør du:\n" +
                    "For- og bagstykke: Slå 132 (144) 156 (168) m op med fv 18/lys lilla på rundp 7 mm, og strik 7 cm rib (1 r, 1 vr) rundt. Skift til p 8 mm og fv 03/lys grå. Fortsæt rundt i glatstrik, og sæt en markør i starten af arb og igen efter 66 (72) 78 (86) m. Når hele arb måler 20 (20) 22 (22) cm, lukkes der af til ærmegab ved at lukke 3 (3) 4 (4) m på hver side af markørerne = 60 (66) 70 (78) m til hver del.\n" +
                    "\n" +
                    "Bagstykke: Strikkes frem og tilbage i glatstrik. Luk yderligere af til ærmegab for 2,1,1 (2,2,1) 2,2,1,1 (2,2,2,1) m på hver anden p i hver side = 52 (56) 58 (64) m. Strik lige op, til hele arb måler 47 (47) 49 (51) cm. Sæt de midterste 22 (24) 24 (26) m på en hjælpep til hals, og strik hver side færdig for sig. Luk yderligere på hver 2. p for 2,2,1 m mod hals = 10 (11) 12 (14) m. Luk af, når arb måler 50 (50) 52 (54) cm. Strik den anden side på samme måde, bare spejlvendt.\n" +
                    "\n" +
                    "Forstykke: Luk af til ærmegab som på ryggen. Når hele arb måler 40 (40) 42 (44) cm, sættes de midterste 18 (20) 20 (22) m på en hjælpep til hals, og hver side strikkes færdig for sig. Luk yderligere på hver 2. p for 2,2,2,1 m mod hals = 10 (11) 12 (14) m. Luk af, når arbejdet måler 50 (50) 52 (54) cm. Strik den anden side på samme måde, bare spejlvendt.\n" +
                    "Montering: Sy skuldrene sammen.\n" +
                    "\n" +
                    "Halskant: Saml fra vestens retside 84 (92) 92 (96) m (inkl hvilende m) op, i fv 21/lys gul, på rundp 7 mm, og strik 11 cm rib (1 r, 1 vr) rundt. Luk løst af i ret. Buk halskanten, og sy den fast, pas på ikke at stramme for meget.\n" +
                    "Ærmekant: Saml fra vestens retside 90 (94) 98 (100) m op i fv 17/pink på rundp nr. 7, og strik 2,5 cm rib (1 r, 1 vr) rundt. Luk løst af i rib. Hæft ender.\n");
            mille.setIsKnitted(false);

            Recipe mette = new Recipe();
            mette.setName("Mette");
            mette.setType("Vest");
            mette.setLevel("Advanced");
            mette.setIsKnitted(false);
            mette.setDescription("Sådan gør du:\n" +
                    "For- og bagstykke: Slå 132 (144) 156 (168) m op med fv 18/lys lilla på rundp 7 mm, og strik 7 cm rib (1 r, 1 vr) rundt. Skift til p 8 mm og fv 03/lys grå. Fortsæt rundt i glatstrik, og sæt en markør i starten af arb og igen efter 66 (72) 78 (86) m. Når hele arb måler 20 (20) 22 (22) cm, lukkes der af til ærmegab ved at lukke 3 (3) 4 (4) m på hver side af markørerne = 60 (66) 70 (78) m til hver del.\n" +
                    "\n" +
                    "Bagstykke: Strikkes frem og tilbage i glatstrik. Luk yderligere af til ærmegab for 2,1,1 (2,2,1) 2,2,1,1 (2,2,2,1) m på hver anden p i hver side = 52 (56) 58 (64) m. Strik lige op, til hele arb måler 47 (47) 49 (51) cm. Sæt de midterste 22 (24) 24 (26) m på en hjælpep til hals, og strik hver side færdig for sig. Luk yderligere på hver 2. p for 2,2,1 m mod hals = 10 (11) 12 (14) m. Luk af, når arb måler 50 (50) 52 (54) cm. Strik den anden side på samme måde, bare spejlvendt.\n" +
                    "\n" +
                    "Forstykke: Luk af til ærmegab som på ryggen. Når hele arb måler 40 (40) 42 (44) cm, sættes de midterste 18 (20) 20 (22) m på en hjælpep til hals, og hver side strikkes færdig for sig. Luk yderligere på hver 2. p for 2,2,2,1 m mod hals = 10 (11) 12 (14) m. Luk af, når arbejdet måler 50 (50) 52 (54) cm. Strik den anden side på samme måde, bare spejlvendt.\n" +
                    "Montering: Sy skuldrene sammen.\n" +
                    "\n" +
                    "Halskant: Saml fra vestens retside 84 (92) 92 (96) m (inkl hvilende m) op, i fv 21/lys gul, på rundp 7 mm, og strik 11 cm rib (1 r, 1 vr) rundt. Luk løst af i ret. Buk halskanten, og sy den fast, pas på ikke at stramme for meget.\n" +
                    "Ærmekant: Saml fra vestens retside 90 (94) 98 (100) m op i fv 17/pink på rundp nr. 7, og strik 2,5 cm rib (1 r, 1 vr) rundt. Luk løst af i rib. Hæft ender.\n");

            addRecipe(vilma);
            addRecipe(mille);
           addRecipe(mette);
        }
    }


    public ArrayList<Recipe> getRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        RecipeCursorWrapper cursor = queryRecipes(null, null);;

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                recipes.add(cursor.getRecipe());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return recipes;
    }

    public void addRecipe(Recipe r) {
        ContentValues values = getContentValues(r);
        mDatabase.insert(RecipeTable.NAME, null, values);
        this.setChanged(); notifyObservers();
    }

    public Recipe getRecipe(UUID id) {
        RecipeCursorWrapper cursor = queryRecipes(
                RecipeTable.Columns.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getRecipe();
        } finally {
            cursor.close();
        }
    }

    public void updateRecipe(Recipe recipe) {
        String uuidString = recipe.getId().toString();
        ContentValues values = getContentValues(recipe);

        mDatabase.update(RecipeTable.NAME, values, RecipeTable.Columns.UUID + " = ?", new String[] { uuidString});
        this.setChanged(); notifyObservers();
    }

    private RecipeCursorWrapper queryRecipes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RecipeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new RecipeCursorWrapper(cursor);
    }


    private static ContentValues getContentValues(Recipe recipe) {
        ContentValues values = new ContentValues();
        values.put(RecipeTable.Columns.UUID, recipe.getId().toString());
        values.put(RecipeTable.Columns.NAME, recipe.getName());
        values.put(RecipeTable.Columns.LEVEL, recipe.getLevel());
        values.put(RecipeTable.Columns.TYPE, recipe.getType());
        values.put(RecipeTable.Columns.DESCRIPTION, recipe.getDescription());
        values.put(RecipeTable.Columns.KNITTED, recipe.getIsKnitted() ? 1 : 0);

        return values;
    }

}
