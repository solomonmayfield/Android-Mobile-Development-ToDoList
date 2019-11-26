package com.example.todolist;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class ToDoSet 
{
	private static ToDoSet toDoSet;
	private static ArrayList<ToDo> todos = new ArrayList<ToDo>();
	private static final String FILE_NAME = "todos.json";
	private Context context;
	private ToDoJSONSerializer serializer;
	
	private ToDoSet(Context context)
	{
		this.context = context;
		serializer = new ToDoJSONSerializer(context,FILE_NAME );
		
		
		try{
			todos = serializer.loadToDos();
		}
		catch(Exception e){
			todos = new ArrayList<ToDo>();
			Log.e("Error","i messed up");
			
			
		}
		
		
		
	}
	
	public boolean saveToDos() 
	{
		try{
			serializer.saveToDos(todos);
			return true;
		}
		catch(Exception e){
			todos = new ArrayList<ToDo>();
			Log.e("Error","i messed up");
			
			return false;
		}
		
	}
	
	
	
	public static ToDoSet newInstance(Context context)
	{
		if(toDoSet == null)
		{
			toDoSet = new ToDoSet(context);
		}
		
		return toDoSet;
	}
	
	public ArrayList<ToDo> getToDos()
	{
		return todos;
	}
	
	public static void addToDo(ToDo todo)
	{
		todos.add(todo);
	}
	
	public static void deleteToDo(ToDo todo)
	{
		todos.remove(todo);
	}
}
