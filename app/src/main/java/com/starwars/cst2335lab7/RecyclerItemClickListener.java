package com.starwars.cst2335lab7;

import android.content.Context;
import android.view.GestureDetector;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MotionEvent;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
  private OnItemClickListener listenerItem;

  public interface OnItemClickListener {
    public void onItemClick(View clickView, int pos);

    public void onLongItemClick(View clickView, int pos);
  }

  GestureDetector gestureDetector;

  public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listen) {
      listenerItem = listen;
      gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (listenerItem != null && childView != null) {
                listenerItem.onLongItemClick(childView, recyclerView.getChildAdapterPosition(childView));
            }
        }
    });
}
    @Override
    public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}


  @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent event) {
    View view1 = view.findChildViewUnder(event.getX(), event.getY());
    if (view1 != null && listenerItem != null && gestureDetector.onTouchEvent(event)) {
        listenerItem.onItemClick(view1, view.getChildAdapterPosition(view1));
      return true;
    }
    return false;
  }

  @Override public void onTouchEvent(RecyclerView view, MotionEvent event) { }


}
