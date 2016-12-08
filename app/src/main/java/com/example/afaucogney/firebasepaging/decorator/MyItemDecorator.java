package com.example.afaucogney.firebasepaging.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by afaucogney on 05/12/2016.
 */

public class MyItemDecorator extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = 20;
    }
}
