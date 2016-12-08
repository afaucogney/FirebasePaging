package com.example.afaucogney.firebasepaging.adapter;

import android.content.Context;

import com.example.afaucogney.firebasepaging.model.CardModel;
import com.example.afaucogney.firebasepaging.section.SectionThema;
import com.example.afaucogney.firebasepaging.viewholder.CardViewHolder;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by afaucogney on 08/12/2016.
 */

public class CardSectionedRecyclerViewAdapter extends SectionedRecyclerViewAdapter {

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // DATA
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private final CardViewHolder.CardEventInterface listener;
    Context context;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public CardSectionedRecyclerViewAdapter(Context context, CardViewHolder.CardEventInterface listener) {
        super();
        this.context = context.getApplicationContext();
        this.listener = listener;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addItem(CardModel value) {

        String cat = CardModel.getCategorie(value);
        SectionThema section;
        if (getSection(cat) != null) {
            section = ((SectionThema) getSection(cat));
        } else {
            section = new SectionThema(context, cat, listener);
            addSection(cat, section);
        }
        section.addCardModel(value);
        notifyDataSetChanged();
    }
}
