package com.starwars.cst2335lab7;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Result implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("height")
	private String height;

	@SerializedName("mass")
	private String mass;


	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHeight(String height){
		this.height = height;
	}

	public String getHeight(){
		return height;
	}

	public void setMass(String mass){
		this.mass = mass;
	}

	public String getMass(){
		return mass;
	}


}