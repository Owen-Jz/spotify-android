package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.fragments.EventBusFragment;
import com.example.sergio.spotify_angular.utils.DividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractSimpleFragment<T> extends EventBusFragment {

    protected RecyclerViewBaseAdapter adapter;
    protected Map<String, Object> options;
    protected OnResultSearchListener listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        options = new HashMap<>();
        options.put("limit",5);
        adapter = getAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_search_simple, container, false);
        TextView titleTextView = (TextView) view.findViewById(R.id.result_title);
        titleTextView.setText(getString(getTitle()));
        TextView seeAll = (TextView) view.findViewById(R.id.see_all);
        seeAll.setText(getString(getSeeAllText()));
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbstractSimpleFragment.this.seeAllResults();
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.result_recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.white_divider),false, true));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(null);
        return view;
    }

    protected abstract RecyclerViewBaseAdapter getAdapter();

    protected abstract int getTitle();

    protected abstract int getSeeAllText();

    protected abstract void seeAllResults();

    public abstract void search(String text);

    public void setListener(OnResultSearchListener listener) {
        this.listener = listener;
    }

    public interface OnResultSearchListener{
        void onDataNotFound(View view);
        void onDataFound(View view);
    }

}
