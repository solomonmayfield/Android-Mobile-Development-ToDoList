package com.example.todolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ToDoFragment extends Fragment implements ToDoConstants
{
	private ToDo todo;
	private int id;
	private View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		id = getActivity().getIntent().getIntExtra(EXTRA_TODO_ID, 0);
		
		if(id == -1)
		{
			todo = new ToDo("", "");
		}
		else
		{
			todo = ToDoSet.newInstance(getActivity()).getToDos().get(id);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_to_do, container, false);
		initTitle();
		initDesc();
		
		return view;
	}
	
	public void initTitle()
	{
		EditText editText = (EditText)view.findViewById(R.id.edittext_todo_title);
		editText.setText(todo.getTitle());
	}

	public void initDesc()
	{
		EditText editText = (EditText)view.findViewById(R.id.edittext_todo_desc);
		editText.setText(todo.getDescription());
	}
	
	
	

	@Override
	public void onPause() {
		super.onPause();
		
		EditText editText = (EditText)view.findViewById(R.id.edittext_todo_title);
		todo.setTitle(editText.getText().toString());
		
		editText = (EditText)view.findViewById(R.id.edittext_todo_desc);
		todo.setDesc(editText.getText().toString());
		
		if(id == -1)ToDoSet.addToDo(todo);
		
		ToDoSet.newInstance(getActivity()).saveToDos();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
