package com.example.afaucogney.firebasepaging.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.afaucogney.firebasepaging.R;
import com.example.afaucogney.firebasepaging.adapter.RecyclerViewCardAdapter;
import com.example.afaucogney.firebasepaging.model.CardModel;
import com.example.afaucogney.firebasepaging.viewholder.CardViewHolder;
import com.example.afaucogney.firebasepaging.viewholder.FailedViewHolder;
import com.example.afaucogney.firebasepaging.viewholder.SectionViewHolder;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;

public class SectionThema extends Section {


    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private final String title;
    private final CardViewHolder.CardEventInterface listener;
    RecyclerViewCardAdapter itemsAdapter;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public SectionThema(Context context, String title, CardViewHolder.CardEventInterface listener) {
        // call constructor with layout resources for this Section header and items 
        super(R.layout.item_section, R.layout.item_cardmodel, R.layout.item_loading, R.layout.item_failed);
        this.title = title;
        this.listener = listener;
        itemsAdapter = new RecyclerViewCardAdapter(context, listener);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // SPECIALISATION ITEM
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getContentItemsTotal() {
        return itemsAdapter.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return CardViewHolder.newInstance(view, listener);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        CardViewHolder itemHolder = (CardViewHolder) holder;

        // bind your view here
        if (itemsAdapter.getItem(position) != null) {
            itemHolder.bind(itemsAdapter.getItem(position));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // SPECIALISATION HEADER
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return SectionViewHolder.newInstance(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        SectionViewHolder headerHolder = (SectionViewHolder) holder;
        headerHolder.bind(title);
    }

    @Override
    public RecyclerView.ViewHolder getFailedViewHolder(View view) {
        return FailedViewHolder.newInstance(view);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // SPECIALISATION FAILER
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
        FailedViewHolder footerHeader = (FailedViewHolder) holder;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addCardModel(CardModel value) {
        itemsAdapter.addItem(value);
        itemsAdapter.notifyDataSetChanged();

    }

}