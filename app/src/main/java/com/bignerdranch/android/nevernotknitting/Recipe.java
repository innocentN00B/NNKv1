package com.bignerdranch.android.nevernotknitting;

import java.util.UUID;

public class Recipe {

    private UUID mId;
    private String mName;
    private String mLevel;
    private String mType;
    private String mDescription;
    private boolean mIsKnitted;

    public Recipe() {
        this(UUID.randomUUID());
    }

    public Recipe(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLevel() {
        return mLevel;
    }

    public void setLevel(String level) {
        mLevel = level;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean getIsKnitted() {
        return mIsKnitted;
    }

    public void setIsKnitted(boolean isKnitted) {
        mIsKnitted = isKnitted;
    }


}
