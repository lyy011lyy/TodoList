package com.example.yalin.todolist;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;

import com.example.yalin.todolist.presenter.MyListView;

import java.util.LinkedList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by yalin on 9/22/2015.
 */
public class MainActivity extends ActionBarActivity {

    private MyListView myListView;

    private List<String> contentList = new LinkedList<String>();
    private MyAdapter adapter;



    class MyAdapter extends BaseAdapter{

        private Context context;
        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return contentList.size();
        }

        @Override
        public Object getItem(int position) {
            return contentList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {




            return null;
        }
    }
}
