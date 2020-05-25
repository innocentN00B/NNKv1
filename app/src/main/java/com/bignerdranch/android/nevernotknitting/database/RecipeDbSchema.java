package com.bignerdranch.android.nevernotknitting.database;


public class RecipeDbSchema {
    public static final class RecipeTable {
        public static final String NAME = "recipes";
        public static Columns Cols;

        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String LEVEL = "level";
            public static final String TYPE = "type";
            public static final String DESCRIPTION = "description";
            public static final String KNITTED = "isKnitted";
        }
    }
}
