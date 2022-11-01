package com.starwars.cst2335lab7;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StarWarApiResp implements Serializable {

    @SerializedName("results")
    private ArrayList<Result> results;


    public ArrayList<Result> getResults(){
        return results;
    }
}
