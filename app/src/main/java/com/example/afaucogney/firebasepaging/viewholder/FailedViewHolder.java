package com.example.afaucogney.firebasepaging.viewholder;

import android.content.Context;
import android.view.View;

/**
 * Created by afaucogney on 08/12/2016.
 */

public class FailedViewHolder extends AbstractCardViewHolder {

    public static FailedViewHolder newInstance(View view) {
        final FailedViewHolder viewHolder = new FailedViewHolder(view, view.getContext());
        return viewHolder;
    }


    protected FailedViewHolder(View itemView, Context context) {
        super(itemView, context);
    }
}
