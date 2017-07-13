package com.pathfinder.anup.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anup on 6/30/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todos_handler";
    public static final int DB_VERSION = 1;

    //Table(s)
    public static final String TB_TODO_MAIN = "tb_todo_main";

    //Coloumn(s)
    public static final String ID = "id";
    public static final String TODO_TITLE = "title";
    public static final String TODO_DESC = "desc";
    public static final String DATE = "date";
    public static final String TIME = "remainder";


    public static final String DROP = "DROP TABLE IF EXISTES ";

    String queryTbTodoMainCreate = "CREATE TABLE " + TB_TODO_MAIN + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TODO_TITLE + " TEXT NOT NULL, "
            + TODO_DESC + " TEXT,  "
            + DATE + " TEXT, "
            + TIME + " TEXT" + " )";






    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        sqLiteDb.execSQL(queryTbTodoMainCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDb, int olderVersion, int newerVersion) {
        if(newerVersion > olderVersion){
            dropTables(sqLiteDb);
        }
        onCreate(sqLiteDb);
    }

    private void dropTables(SQLiteDatabase db){
        db.execSQL(DROP + queryTbTodoMainCreate);
    }
}
