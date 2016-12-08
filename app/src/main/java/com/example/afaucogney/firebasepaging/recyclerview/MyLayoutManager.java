package com.example.afaucogney.firebasepaging.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by afaucogney on 05/12/2016.
 */

public class MyLayoutManager extends GridLayoutManager {

    public MyLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }
}
