package com.pathfinder.anup.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pathfinder.anup.Utils.Constants;
import com.pathfinder.anup.adapters.TodoListAdapter;
import com.pathfinder.anup.bean.NewTodoBean;
import com.pathfinder.anup.database.DatabaseManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity implements View.OnClickListener{
    List<NewTodoBean> todoBeanList;
    TodoListAdapter listAdapter;
    ListView listView;
    Toolbar toolbar;
    FloatingActionButton fab;
    TextView emptyMessage;
    LinearLayout emptyLayout;
    ConstraintLayout todoMainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGUI();
        todoBeanList = new ArrayList<>();

        //setTodoList(todoBeanList);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivityForResult(new Intent(TodoListActivity.this, NewTodoScreen.class), Constants.REQUEST_CODE);
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
            }
        });*/
    }


    @Override
    protected void onStart() {
        super.onStart();
        fetchAllTodos();
    }

    private void setGUI(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview) ;
        emptyLayout = (LinearLayout) findViewById(R.id.empty_layout);
        todoMainLayout = (ConstraintLayout) findViewById(R.id.todo_main_layout);
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

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void fetchAllTodos(){
        todoBeanList = new DatabaseManager(TodoListActivity.this).fetchAllTodos();
        setTodoList(todoBeanList);

    }

    private void setTodoList(List<NewTodoBean> todoBeanList){
        if(todoBeanList.size() > 0){
           // EmptyScreenManager.hideEmptyScreen(emptyLayout);
            Log.i("Anup", "setTodoList ");
            todoMainLayout.setVisibility(View.VISIBLE);
            emptyLayout.setVisibility(View.GONE);
            listAdapter = new TodoListAdapter(TodoListActivity.this, todoBeanList);
            listAdapter.notifyDataSetChanged();
            listView.setAdapter(listAdapter);
        } else {
            todoMainLayout.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
            //showEmptyScreen(true);
        }


    }

   /* private void showEmptyScreen() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.VISIBLE);
            }
        });

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_CODE){
            if(data.hasExtra(Constants.IS_DATA_ADDED)){
                Log.i("Anup", "onActivityResult - rc - "+requestCode);
                fetchAllTodos();
                listAdapter.notifyDataSetChanged();
                listView.setAdapter(listAdapter);
            }

        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab){
            Intent todoScreenIntent = new Intent(TodoListActivity.this, NewTodoScreen.class);
            startActivityForResult(todoScreenIntent, Constants.REQUEST_CODE);
        }
    }

    /*public void showEmptyScreen(final boolean isEmpty) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isEmpty){
                    emptyLayout.setVisibility(View.VISIBLE);
                    EmptyScreenManager.showEmptyScreen(emptyLayout, EmptyScreenManager.EmptyScreenType.TODO_LIST_ACTIVITY);
                }else{
                    emptyLayout.setVisibility(View.GONE);
                }
            }
        });
    }*/
}
