package com.example.afaucogney.firebasepaging;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.afaucogney.firebasepaging.adapter.CardSectionedRecyclerViewAdapter;
import com.example.afaucogney.firebasepaging.common.DefaultSubscriber;
import com.example.afaucogney.firebasepaging.common.FirebaseFakeDataFactory;
import com.example.afaucogney.firebasepaging.decorator.MyItemDecorator;
import com.example.afaucogney.firebasepaging.model.CardModel;
import com.example.afaucogney.firebasepaging.recyclerview.EndlessRecyclerOnScrollListener;
import com.example.afaucogney.firebasepaging.recyclerview.MyLayoutManager;
import com.example.afaucogney.firebasepaging.recyclerview.MyRecyclerView;
import com.example.afaucogney.firebasepaging.viewholder.CardViewHolder;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.Semaphore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends RxAppCompatActivity implements CardViewHolder.CardEventInterface {

    ///////////////////////////////////////////////////////////////////////////
    // VIEWS
    ///////////////////////////////////////////////////////////////////////////

    @BindView(R.id.rv)
    MyRecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private int mPageEndOffset = 0;
    private String mLatestItemValueRead = null;
    private int mPageLimit = 10;
    private CardSectionedRecyclerViewAdapter adapter;
    Semaphore s = new Semaphore(1);

    boolean lock = false;
    int count = mPageLimit;
    int c;


    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        setupToolBar();
        setupRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    ///////////////////////////////////////////////////////////////////////////
    // UI EVENT
    ///////////////////////////////////////////////////////////////////////////

    @OnClick(R.id.fab)
    public void OnFabClick() {
        FirebaseFakeDataFactory.generateFakeData();
    }

    @Override
    public void onMainPictureClick() {
        Snackbar.make(rv, "Main Picture Click", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void onProfilePictureClick() {
        Snackbar.make(rv, "Profile Picture Click", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    ///////////////////////////////////////////////////////////////////////////
    // SETUP UI
    ///////////////////////////////////////////////////////////////////////////

    private void setupRecyclerView() {
        adapter = new CardSectionedRecyclerViewAdapter(this, this);
        MyLayoutManager manager = new MyLayoutManager(this.getApplicationContext(), 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        rv.addOnScrollListener(new EndlessRecyclerOnScrollListener(manager) {
            @Override
            public void onLoadMore(int current_page) {
                Log.v("RV", "Load More at " + current_page);
                mPageEndOffset += mPageLimit;
                getNextItems();
            }
        });

//

//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                switch (adapter.getItemViewType(position)) {
//                    case VIEW_TYPES.ITEM_SIMPLE:
//                        return 2;
//                    case VIEW_TYPES.PROGRESS:
//                        return 1; //number of columns of the grid
//                    default:
//                        return -1;
//                }
//            }
//        });

        rv.addItemDecoration(new MyItemDecorator());

        getNextItems();
    }

    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    ///////////////////////////////////////////////////////////////////////////
    // WEB SERVICES
    ///////////////////////////////////////////////////////////////////////////

    private void getNextItems() {

        try {
            s.acquire();
            c = 0;
            Query query = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("test")
                    .orderByChild("name")
                    .startAt(mLatestItemValueRead)
                    .limitToFirst(mPageLimit);

            RxFirebaseDatabase
                    .observeChildEvent(query, CardModel.class)
                    .filter(new Func1<RxFirebaseChildEvent<CardModel>, Boolean>() {
                        @Override
                        public Boolean call(RxFirebaseChildEvent<CardModel> cardModelRxFirebaseChildEvent) {
                            return cardModelRxFirebaseChildEvent.getEventType().equals(RxFirebaseChildEvent.EventType.ADDED);
                        }
                    })
                    .doOnNext(new Action1<RxFirebaseChildEvent<CardModel>>() {
                        @Override
                        public void call(RxFirebaseChildEvent<CardModel> cardModelRxFirebaseChildEvent) {
                            Log.v("FIREBASE", "Update from " + mPageEndOffset + " limitied to " + mPageLimit + " items = " + cardModelRxFirebaseChildEvent.getValue().getCount());
                        }
                    })
                    .limit(mPageLimit)
                    .takeLast(count)
                    .doOnNext(new Action1<RxFirebaseChildEvent<CardModel>>() {
                        @Override
                        public void call(RxFirebaseChildEvent<CardModel> cardModelRxFirebaseChildEvent) {
                            adapter.addItem(cardModelRxFirebaseChildEvent.getValue());
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .doOnNext(new Action1<RxFirebaseChildEvent<CardModel>>() {
                        @Override
                        public void call(RxFirebaseChildEvent<CardModel> cardModelRxFirebaseChildEvent) {
                            mLatestItemValueRead = cardModelRxFirebaseChildEvent.getValue().getName();
                            if (count == mPageLimit) {
                                count--;
                            }
                            c++;
                            s.release();
                        }
                    })
                    .compose(this.<RxFirebaseChildEvent<CardModel>>bindToLifecycle())
                    .subscribe(new DefaultSubscriber<RxFirebaseChildEvent<CardModel>>(this.getClass().getSimpleName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
