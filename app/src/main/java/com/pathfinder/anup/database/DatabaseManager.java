package com.pathfinder.anup.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.pathfinder.anup.bean.NewTodoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 6/30/2017.
 */

public class DatabaseManager {

    public DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase sqLiteDb;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public DatabaseManager open() throws SQLiteException{
        dbHelper = new DatabaseHelper(context);
        sqLiteDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void close (){
        dbHelper.close();
    }

    public void insertTodo(NewTodoBean bean){
        open();
        ContentValues values = new ContentValues();
        Log.i("Anup", "todo "+bean.getTodoTitle());
        values.put(DatabaseHelper.TODO_TITLE, bean.getTodoTitle());
        Log.i("Anup", "todo "+bean.getTodoDesc());
        values.put(DatabaseHelper.TODO_DESC, bean.getTodoDesc());
        if(!bean.getMarkDate().isEmpty()){
            Log.i("Anup", "todo "+bean.getMarkDate());
            values.put(DatabaseHelper.DATE, bean.getMarkDate());
        }
        if(!bean.getMarkTime().isEmpty()){
            Log.i("Anup", "todo "+bean.getMarkTime());
            values.put(DatabaseHelper.TIME, bean.getMarkTime());
        }
        Log.i("Anup", "todo "+ "inserted!");
        sqLiteDb.insert(DatabaseHelper.TB_TODO_MAIN, null, values);
        close();
    }

    public List<NewTodoBean> fetchAllTodos() {
        List<NewTodoBean> todoList = new ArrayList<>();
        Cursor cursor = null;

        open();
        if (sqLiteDb.isOpen()) {
            String selectQuery = "SELECT ROWID, * FROM " + DatabaseHelper.TB_TODO_MAIN;
            cursor = sqLiteDb.rawQuery(selectQuery, null);
        }
        if (cursor != null && cursor.getCount() > -1) {
            while (cursor.moveToNext()) {
                NewTodoBean bean = new NewTodoBean();
                bean.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID)));
                Log.i("Anup", "todo "+ "inserted data! "+bean.getId());
                bean.setTodoTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_TITLE)));
                Log.i("Anup", "todo "+ "inserted data! "+bean.getTodoTitle());
                bean.setTodoDesc(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TODO_DESC)));
                Log.i("Anup", "todo "+ "inserted data! "+bean.getTodoDesc());
                bean.setMarkDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATE)));
                Log.i("Anup", "todo "+ "inserted data! "+bean.getMarkDate());
                bean.setMarkTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TIME)));
                Log.i("Anup", "todo "+ "inserted data! "+bean.getMarkTime());
                todoList.add(bean);
            }
        }
        close();
        return todoList;
    }

    public Cursor fetchAllTodo(){
        String[] coloumns = new String[]{DatabaseHelper.ID, DatabaseHelper.TODO_TITLE, DatabaseHelper.TODO_DESC,
                                          DatabaseHelper.DATE, DatabaseHelper.TIME};
        Cursor cursor = sqLiteDb.query(DatabaseHelper.TB_TODO_MAIN, coloumns, null, null, null,null, null);

        if(cursor != null){
            cursor.moveToNext();
        }
        return cursor;
    }

}
