package demo.frap.favourites.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;

import demo.frap.favourites.R;
import demo.frap.favourites.adapters.RecyclerViewAdapterFav;
import demo.frap.favourites.models.Common;
import demo.frap.favourites.models.DataModelfav;

/**
 * Created by Ramandeep on 8/12/17.
 */

public class Favourites extends Fragment  {



        View rootView;
        java.util.List<DataModelfav> dataModelfavs;
        RecyclerView recyclerView;

        private SwipeRefreshLayout mRefreshLayout;


        RecyclerView.Adapter recyclerViewadapterFav;

        RecyclerView.LayoutManager recyclerViewlayoutManager;


        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {

            if (rootView == null) {
                rootView = inflater.inflate(R.layout.favourites, container, false);
                dataModelfavs = new ArrayList<>();
                recyclerView = (RecyclerView) rootView.findViewById(R.id.fav_recyclerview);
                mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
                recyclerView.setHasFixedSize(true);

                mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setData();



                                mRefreshLayout.setRefreshing(false);
                            }
                        }, 2500);


                    }
                });


                recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(recyclerViewlayoutManager);
                 setData();

            }
            return rootView;

        }



    void setData()
    {
        dataModelfavs.clear();
        if (!Common.fav_desc.isEmpty()) {


            Iterator<String> itr_title = Common.fav_title.iterator();
            Iterator<String> itr_des = Common.fav_desc.iterator();
            Iterator<String> itr_image = Common.fav_image.iterator();
            Iterator<String> itr_viewCount = Common.fav_viewcount.iterator();
            Iterator<String> itr_type = Common.fav_type.iterator();

            while (itr_title.hasNext() && itr_des.hasNext() && itr_type.hasNext() && itr_viewCount.hasNext() && itr_image.hasNext()) {
                DataModelfav dataModelfavorit = new DataModelfav();
                dataModelfavorit.setTitle(itr_title.next());
                dataModelfavorit.setDescription(itr_des.next());
                dataModelfavorit.setType(itr_type.next());
                dataModelfavorit.setImage_url(itr_image.next());
                dataModelfavorit.setViewCount(itr_viewCount.next());
                dataModelfavs.add(dataModelfavorit);


            }
            recyclerViewadapterFav = new RecyclerViewAdapterFav(dataModelfavs, getActivity());
            recyclerView.setAdapter(recyclerViewadapterFav);
        }
    }


}
