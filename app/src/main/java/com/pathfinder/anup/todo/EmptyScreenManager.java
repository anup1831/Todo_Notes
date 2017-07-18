package com.pathfinder.anup.todo;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anup on 7/17/2017.
 */

public class EmptyScreenManager{
    public enum EmptyScreenType
    {
        TODO_LIST_ACTIVITY,
        NEW_TODO_SCREEN
    }

    public static void showEmptyScreen(FrameLayout mEmptyScreenLayout, EmptyScreenType screenType, String abc) {
        ImageView emptyIcon=(ImageView)mEmptyScreenLayout.findViewById(R.id.imageView);
        TextView emptyTitle=(TextView)mEmptyScreenLayout.findViewById(R.id.textView);
        TextView emptyDes=(TextView)mEmptyScreenLayout.findViewById(R.id.textView_desc);

        switch (screenType){
            case TODO_LIST_ACTIVITY:
                emptyIcon.setImageResource(R.drawable.empty_cup);
                emptyTitle.setText(R.string.empty_message);
                emptyDes.setVisibility(View.GONE);
                break;
            case NEW_TODO_SCREEN:
                emptyIcon.setImageResource(R.drawable.empty_cup);
                emptyTitle.setText(R.string.empty_message);
                emptyDes.setVisibility(View.GONE);
                break;

        }
        mEmptyScreenLayout.setVisibility(View.VISIBLE);
    }

    public static void showEmptyScreen(FrameLayout mEmptyScreenLayout, EmptyScreenType screenType) {
        showEmptyScreen(mEmptyScreenLayout,screenType, null);
    }

    public static void hideEmptyScreen(FrameLayout mEmptyScreenLayout) {
        mEmptyScreenLayout.setVisibility(View.INVISIBLE);
    }
}
