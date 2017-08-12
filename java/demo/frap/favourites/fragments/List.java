package demo.frap.favourites.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import demo.frap.favourites.R;
import demo.frap.favourites.adapters.RecyclerViewAdapter;
import demo.frap.favourites.interfaces.RecyclerViewClickListener;
import demo.frap.favourites.models.DataModel;

/**
 * Created by Ramandeep on 8/12/17.
 */

public class List extends Fragment implements RecyclerViewClickListener {
    View rootView;

    java.util.List<DataModel> DataModels;


    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;
    String Url="http://54.254.198.83:8080/favourite.json";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.list, container, false);

            DataModels = new ArrayList<>();
            recyclerView = (RecyclerView) rootView.findViewById(R.id.list_recyclerview);
            recyclerView.setHasFixedSize(true);


            recyclerViewadapter = new RecyclerViewAdapter(getActivity(),this);


            recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

            recyclerView.setLayoutManager(recyclerViewlayoutManager);
            getData(Url);




        }
        return rootView;

    }


    @Override
    public void recyclerViewListClicked(View v, int position)
    {
        Log.e("Clicked",position+"");

    }

     void getData(String url)
     {

         RequestQueue queue = Volley.newRequestQueue(getActivity());

         JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                 new Response.Listener<JSONArray>()
                 {
                     @Override
                     public void onResponse(JSONArray response) {
                         // display response
                         Log.d("Response", response.toString());


                         if ((response != null && !(response.equals("null"))) && !(response.equals("")))
                         {

                             for (int i=0;i<response.length();i++)
                             {
                                 DataModel DataModel = new DataModel();
                                 try {
                                     JSONObject jsonObject= response.getJSONObject(i);
                                     DataModel.setTitle(jsonObject.getString("title"));
                                     DataModel.setDescription(jsonObject.getString("desc"));
                                     DataModel.setViewCount(jsonObject.getString("view-count"));
                                     DataModel.setImage_url(jsonObject.getString("imageUrl"));
                                     DataModel.setType(jsonObject.getString("type"));
                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }

                                 DataModels.add(DataModel);


                             }

                             recyclerViewadapter = new RecyclerViewAdapter(DataModels, getActivity());


                             recyclerView.setAdapter(recyclerViewadapter);


                         }

                     }
                 },
                 new Response.ErrorListener()
                 {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Log.d("Error.Response", error.toString());
                         Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
                     }
                 }
         );



         // add it to the RequestQueue
         queue.add(getRequest);

     }




}
