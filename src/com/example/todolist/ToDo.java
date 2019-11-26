package com.example.todolist;

import org.json.JSONException;
import org.json.JSONObject;

public class ToDo {
	private static final String JSON_TODO_DESC = null;
	private static final String JSON_TODO_TITLE = null;
	private String title;
	private String description;
	
	public ToDo(String title, String description)
	{
		this.title = title;
		this.description = description;
	}
	
	public ToDo (JSONObject json) throws JSONException
	{
		if(json.has(JSON_TODO_TITLE))
		{
			title = json.getString(JSON_TODO_TITLE);
			
			
		}
		if(json.has(JSON_TODO_DESC))
		{
			description = json.getString(JSON_TODO_DESC);
			
			
		}
		
	}
	
	
	
	public JSONObject toJSON() throws JSONException
	{
		JSONObject json = new JSONObject();
		json.put(JSON_TODO_TITLE,title);
		json.put(JSON_TODO_DESC,description);
		
		return json;
		
	}
	
	
	
	
	
	
	
	
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDesc(String desc)
	{
		this.description = desc;
	}
	
	public String toString()
	{
		return title;
	}
}












