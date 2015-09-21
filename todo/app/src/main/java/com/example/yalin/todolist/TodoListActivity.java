package com.example.yalin.todolist;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.yalin.todolist.db.TaskContract;
import com.example.yalin.todolist.db.TaskDBHelper;

import java.util.List;

public class TodoListActivity extends ActionBarActivity {
    private TaskDBHelper helper;
    private ListAdapter listAdapter;
    private TodoListActivity mActivity;
    private ListView listView = (ListView) findViewById(R.id.list);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        updateUI();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Adapter adapter = parent.getAdapter();

                Animation fadeOut = new AlphaAnimation(1, 0);
                //Animation fadeOut = AnimationUtils.loadAnimation(TodoListActivity.this, R.anim.fade_out_anim);
                fadeOut.setInterpolator(new AccelerateInterpolator());
                fadeOut.setDuration(500);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //adapter.pointItems.remove(position);
                        //adapter.notifyDataSetChanged();
                        listView.removeViewAt(position);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                view.startAnimation(fadeOut);

            }

        });
    }

    private void updateUI() {
        helper = new TaskDBHelper(TodoListActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.TABLE,
                new String[]{TaskContract.Columns._ID, TaskContract.Columns.TASK},
                null, null, null, null, null, null);

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.task_view,
                cursor,
                new String[] {TaskContract.Columns.TASK},
                new int[] {R.id.taskTextView},
                0
        );
//        //Display the list view

//



        listView.setAdapter(listAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add_task:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.addTaskDialog);
                builder.setMessage(R.string.addTaskDialogMessage);

                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String task = inputField.getText().toString();
                        Log.d("MainActivity", task);

                        helper = new TaskDBHelper(TodoListActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.clear();
                        values.put(TaskContract.Columns.TASK, task);

                        db.insertWithOnConflict(TaskContract.TABLE, null, values,
                                SQLiteDatabase.CONFLICT_IGNORE);

                        updateUI();
                    }
                });

                builder.setNegativeButton("Cancel", null);

                builder.create().show();
                return true;
            default:
                return false;
        }
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();

        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                                TaskContract.TABLE,
                                TaskContract.Columns.TASK,
                                task);

        helper = new TaskDBHelper(TodoListActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql);
        updateUI();
    }


}
