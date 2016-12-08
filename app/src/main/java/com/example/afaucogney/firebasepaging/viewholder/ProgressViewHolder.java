package com.example.afaucogney.firebasepaging.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afaucogney.firebasepaging.R;

/**
 * Created by afaucogney on 07/12/2016.
 */

public class ProgressViewHolder extends AbstractCardViewHolder {

    ///////////////////////////////////////////////////////////////////////////
    // FACTORY
    ///////////////////////////////////////////////////////////////////////////

    public static ProgressViewHolder newInstance(final ViewGroup parent) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
         ProgressViewHolder viewHolder = new ProgressViewHolder(view, context);
        return viewHolder;
    }

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    private ProgressViewHolder(View viewItem, Context context) {
        super(viewItem, context);
    }

}
