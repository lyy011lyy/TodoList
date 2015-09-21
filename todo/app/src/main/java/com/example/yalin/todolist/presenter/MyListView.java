package com.example.yalin.todolist.presenter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yalin.todolist.R;

import java.util.jar.Attributes;

/**
 * Created by yalin on 9/21/2015.
 */
public class MyListView extends ListView implements OnTouchListener,OnGestureListener {
    private GestureDetector gestureDetector;

    private View btnDelete;
    private ViewGroup viewGroup;
    private int seletcedItem;
    private boolean isDeletedShow;
    //private OnItemDeleListener onItemDeleListener;

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(getContext(), this);
        setOnTouchListener(this);
    }

    public void setOnItemDeleteListener(OnItemDeleteListener onItemDeleteListener) {
        this.onItemDeleteListener = onItemDeleteListener;
    }


    @Override
    public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {

    }

    @Override
    public void onGesture(GestureOverlayView overlay, MotionEvent event) {

    }

    @Override
    public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {

    }

    @Override
    public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        seletcedItem = pointToPosition((int)event.getX() , (int)event.getY());

        if(isDeletedShow) {
            btnHide(btnDelete);
            viewGroup.removeView(btnDelete);
            btnDelete = null;
            isDeletedShow = false;
            return false;
        }else{
            return gestureDetector.onTouchEvent(event);
        }

    }

    @Override
    public boolean onDown(MotionEvent e) {
        if(!isDeletedShow) {
            seletcedItem = pointToPosition((int)e.getX(), (int)e.getY());
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;

        if(!isDeletedShow && Math.abs(velocityX) > Math.abs(velocityY)) {
            btnDelete = LayoutInflater.from(getContext()).inflate(R.layout.layout_button,null);
        }
    }
}
