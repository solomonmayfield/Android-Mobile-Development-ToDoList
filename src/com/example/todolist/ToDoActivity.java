package com.example.todolist;

import android.support.v4.app.Fragment;

public class ToDoActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new ToDoFragment();
	}
	
}