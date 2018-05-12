package kh.battambang.ava.ChristChurch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kh.battambang.ava.ChristChurch.Data;
import kh.battambang.ava.ChristChurch.R;
import kh.battambang.ava.ChristChurch.RecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private String url = "http://pembuatanprogram.000webhostapp.com/";
    private String urlEndPoint = "";
    private String sanaURL = "http://sanacinema.000webhostapp.com/";
    private String sanaEndPoint = "christ_church_anndroid_app/get_json_data.php";
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<Data> listData;
    private GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        listData = new ArrayList<>();
        initData();
        recyclerAdapter = new RecyclerAdapter(getActivity(), listData);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

        return view;
    }

    private void initData() {

        Response.Listener<JSONObject> resJSonJbj = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray home_navi = response.getJSONArray("home_navi");
                    if(home_navi.length() > 0){
                        for (int j=0; j < home_navi.length(); j++){
                            JSONObject dataObj = home_navi.getJSONObject(j);
                            Data item = new Data();
                            item.setId(dataObj.getString("id"));
                            item.setTitle(dataObj.getString("title"));
                            item.setDescription(dataObj.getString("description"));
                            item.setThubnail("http://img.youtube.com/vi/"+dataObj.getString("video_id")+"/0.jpg");
                            listData.add(item);
                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET,sanaURL+sanaEndPoint,null,resJSonJbj,errorListener);
        Volley.newRequestQueue(getContext()).add(objRequest);
    }

}
