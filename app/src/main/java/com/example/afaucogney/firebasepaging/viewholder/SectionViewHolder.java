package com.example.afaucogney.firebasepaging.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.afaucogney.firebasepaging.R;

import butterknife.BindView;

/**
 * Created by afaucogney on 08/12/2016.
 */

public class SectionViewHolder extends AbstractCardViewHolder {

    ///////////////////////////////////////////////////////////////////////////
    // VIEWS
    ///////////////////////////////////////////////////////////////////////////

    @BindView(R.id.textView)
    TextView textView;

    ///////////////////////////////////////////////////////////////////////////
    // FACTORY
    ///////////////////////////////////////////////////////////////////////////

    public static SectionViewHolder newInstance(final ViewGroup parent) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.item_section, parent, false);
        final SectionViewHolder viewHolder = new SectionViewHolder(view, context);
        return viewHolder;
    }

    public static SectionViewHolder newInstance(View view) {
        final SectionViewHolder viewHolder = new SectionViewHolder(view, view.getContext());
        return viewHolder;
    }

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    public SectionViewHolder(View itemView, Context context) {
        super(itemView, context);
    }

    ///////////////////////////////////////////////////////////////////////////
    // API PUBLIC
    ///////////////////////////////////////////////////////////////////////////

    public void bind(String sectionName) {
        textView.setText(sectionName);
    }
}
