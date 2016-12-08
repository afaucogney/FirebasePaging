package com.example.afaucogney.firebasepaging.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.afaucogney.firebasepaging.model.CardModel;
import com.example.afaucogney.firebasepaging.viewholder.AbstractCardViewHolder;
import com.example.afaucogney.firebasepaging.viewholder.CardViewHolder;
import com.example.afaucogney.firebasepaging.viewholder.ProgressViewHolder;

import static com.example.afaucogney.firebasepaging.viewholder.AbstractCardViewHolder.VIEW_TYPES.ITEM_SIMPLE;
import static com.example.afaucogney.firebasepaging.viewholder.AbstractCardViewHolder.VIEW_TYPES.PROGRESS;

/**
 * Created by afaucogney on 05/12/2016.
 */

public class RecyclerViewCardAdapter extends RecyclerView.Adapter<AbstractCardViewHolder> {

    ///////////////////////////////////////////////////////////////////////////
    // CONST
    ///////////////////////////////////////////////////////////////////////////

    public static final int ITEM_PROGRESS_COUNT = 1;

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    SparseArray<CardModel> data = new SparseArray<>();
    Context context;
    CardViewHolder.CardEventInterface listener;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    public RecyclerViewCardAdapter(Context context, CardViewHolder.CardEventInterface listener) {
        this.context = context.getApplicationContext();
        this.listener = listener;
    }

    ///////////////////////////////////////////////////////////////////////////
    // API IMPLEMENTATION
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public AbstractCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case PROGRESS:
                return ProgressViewHolder.newInstance(parent);
            default:
                return CardViewHolder.newInstance(parent, listener);
        }
    }

    @Override
    public void onBindViewHolder(AbstractCardViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case PROGRESS:
                break;
            case ITEM_SIMPLE:
                ((CardViewHolder) holder).setListener(listener);
                CardModel cm = data.get(position);
                ((CardViewHolder) holder).bind(cm);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + ITEM_PROGRESS_COUNT;
    }


    @Override
    public int getItemViewType(int position) {
        int result;
        // Last Item
        if (position == (getItemCount() - 1)) {
            result = PROGRESS;
        }
        // Other
        else {
            result = ITEM_SIMPLE;
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////
    // API PUBLIC
    ///////////////////////////////////////////////////////////////////////////


    public void addItem(CardModel value) {
        data.append(data.size(), value);

    }

    public CardModel getItem(int position) {
        return data.get(position);
    }

}


