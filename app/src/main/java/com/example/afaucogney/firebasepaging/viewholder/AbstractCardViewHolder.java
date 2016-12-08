package com.example.afaucogney.firebasepaging.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by afaucogney on 07/12/2016.
 */

public abstract class AbstractCardViewHolder extends RecyclerView.ViewHolder {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    Context context;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    protected AbstractCardViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context.getApplicationContext();
        ButterKnife.bind(this, itemView);
    }

    ///////////////////////////////////////////////////////////////////////////
    // VIEW TYPES
    ///////////////////////////////////////////////////////////////////////////

    public interface VIEW_TYPES {
        int LABEL = 6;
        int SECTION = 4;
        int FOOTER = 3;
        int HEADER = 2;
        int ITEM_SIMPLE = 0;
        int ITEM_DOUBLE = 1;
        int PROGRESS = 5;


    }
}
