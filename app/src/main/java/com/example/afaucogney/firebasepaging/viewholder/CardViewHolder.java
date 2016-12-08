package com.example.afaucogney.firebasepaging.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.afaucogney.firebasepaging.model.CardModel;
import com.example.afaucogney.firebasepaging.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by afaucogney on 05/12/2016.
 */

public class CardViewHolder extends AbstractCardViewHolder {

    ///////////////////////////////////////////////////////////////////////////
    // FACTORY
    ///////////////////////////////////////////////////////////////////////////

    public static CardViewHolder newInstance(final ViewGroup parent, CardEventInterface eventListener) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.item_cardmodel, parent, false);
        CardViewHolder viewHolder = new CardViewHolder(view, context);
        viewHolder.setListener(eventListener);
        return viewHolder;
    }

    public static CardViewHolder newInstance(final View view, CardEventInterface eventListener) {
        final Context context = view.getContext();
        CardViewHolder viewHolder = new CardViewHolder(view, context);
        viewHolder.setListener(eventListener);
        return viewHolder;
    }

    ///////////////////////////////////////////////////////////////////////////
    // RESOURCES
    ///////////////////////////////////////////////////////////////////////////

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_count)
    TextView tvCount;

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    CardEventInterface listener;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    private CardViewHolder(View itemView, Context context) {
        super(itemView, context);
    }

    ///////////////////////////////////////////////////////////////////////////
    // ACCESSOORS
    ///////////////////////////////////////////////////////////////////////////

    public void setListener(CardEventInterface listener) {
        this.listener = listener;
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    public void bind(CardModel cm) {
        tvTitle.setText(cm.getName());
        tvComment.setText(cm.getComment());
        tvStatus.setText(String.valueOf(cm.getStatus()));
        tvCount.setText(String.valueOf(cm.getCount()));

        Glide.with(context).load(cm.getAvatarPath()).into(ivProfile);
        Glide.with(context).load(cm.getPhotoPath()).into(ivPhoto);
    }

    ///////////////////////////////////////////////////////////////////////////
    // UI EVENTS
    ///////////////////////////////////////////////////////////////////////////

    @OnClick(R.id.iv_profile)
    public void onProfilePictureClick(View view) {
        listener.onProfilePictureClick();
    }

    @OnClick(R.id.iv_photo)
    public void onPhotoPictureClick(View view) {
        listener.onMainPictureClick();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC INTERFACE
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public interface CardEventInterface {
        void onMainPictureClick();

        void onProfilePictureClick();
    }

}


