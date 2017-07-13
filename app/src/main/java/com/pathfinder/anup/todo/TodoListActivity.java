package com.pathfinder.anup.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.pathfinder.anup.adapters.TodoListAdapter;
import com.pathfinder.anup.bean.NewTodoBean;
import com.pathfinder.anup.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {
    List<NewTodoBean> todoBeanList;
    TodoListAdapter listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchAllTodos();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTodoList();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TodoListActivity.this, NewTodoScreen.class));
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchAllTodos(){
        todoBeanList = new ArrayList<NewTodoBean>();
        todoBeanList = new DatabaseManager(TodoListActivity.this).fetchAllTodos();

    }

    private void setTodoList(){
        listView = (ListView) findViewById(R.id.listview) ;
        if(todoBeanList.size() > 0){
            listAdapter = new TodoListAdapter(TodoListActivity.this, todoBeanList);
        }
        listView.setAdapter(listAdapter);
    }
}
