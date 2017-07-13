package com.pathfinder.anup.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pathfinder.anup.bean.NewTodoBean;
import com.pathfinder.anup.todo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anup on 7/13/2017.
 */

public class TodoListAdapter extends BaseAdapter {
    private Context context;
    private List<NewTodoBean> listTodo = new ArrayList<NewTodoBean>();

    public TodoListAdapter(Context context, List<NewTodoBean> listTodo) {
        this.context = context;
        this.listTodo = listTodo;
    }

    @Override
    public int getCount() {
        Log.i("Anup", "getCount -call");
        return listTodo.size();
    }

    @Override
    public Object getItem(int pos) {
        Log.i("Anup", "getItem -call"+pos);
        return listTodo.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        Log.i("Anup", "getItemID -call"+pos);
        return listTodo.get(pos).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            Log.i("Anup", "getView - view null check -"+i);
           convertView = LayoutInflater.from(context).inflate(R.layout.todo_list_row, viewGroup, false);
        }

        Log.i("Anup", "getView -call"+i);
        NewTodoBean todoItem = listTodo.get(i);

        TextView title = (TextView) convertView.findViewById(R.id.tv_todo_title);
        TextView desc = (TextView) convertView.findViewById(R.id.tv_todo_desc);

        title.setText(todoItem.getTodoTitle());
        desc.setText(todoItem.getTodoDesc());

        return convertView;
    }
}
