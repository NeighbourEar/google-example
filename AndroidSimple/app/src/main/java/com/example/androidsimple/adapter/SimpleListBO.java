package com.example.androidsimple.adapter;
@SuppressWarnings("rawtypes")
public class SimpleListBO {
	public String name;
	public Class activity;
	/**
	 * @param name
	 * @param activity
	 */
	public SimpleListBO(String name, Class activity) {
		this.name = name;
		this.activity = activity;
	}

}