package com.example.todolist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class ToDoJSONSerializer 
{
	private Context context;
	private String fileName;
	
	public ToDoJSONSerializer(Context context, String fileName)
	{
		this.context = context;
		this.fileName = fileName;
	}
	
	public ArrayList<ToDo> loadToDos() throws IOException, JSONException
	{
		ArrayList<ToDo> todos = new ArrayList<ToDo>();
		BufferedReader reader = null;
		
		try {
			InputStream in = context.openFileInput(fileName);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine()) != null)
			{
				jsonString.append(line);
			}
			
			JSONArray jsonArray = (JSONArray)new JSONTokener(jsonString.toString()).nextValue();
			
			for(int i=0; i < jsonArray.length(); i++)
			{
				todos.add(new ToDo(jsonArray.getJSONObject(i)));
			}
		}
		catch(FileNotFoundException e)
		{
			//this happens when starting a new file. it's ok
		}
		finally {
			if(reader != null)
			{
				reader.close();
			}
		}
		
		return todos;
	}
	
	public void saveToDos(ArrayList<ToDo> todos)
		throws JSONException, IOException
	{
		JSONArray jsonArray = new JSONArray();
		for(ToDo todo : todos)
		{
			jsonArray.put(todo.toJSON());
		}
		
		Writer writer = null;
		
		try {
			OutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(jsonArray.toString());
		}
		finally {
			if(writer != null)
				writer.close();
		}
	}
}
