package com.example.administrator.databindingadvanceddemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.databindingadvanceddemo.R;
import com.example.administrator.databindingadvanceddemo.adapter.RecyclerViewAdapter;
import com.example.administrator.databindingadvanceddemo.bean.Content;
import com.example.administrator.databindingadvanceddemo.component.RoundButtonView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RoundButtonView nearbyRoundButtonView, recommendedRoundButtonView, latestRoundButtonView,
            nightRoundButtonView, discountRoundButtonView, iRoundButtonView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Content> contentList;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializationView(view);
        initializationOnLinearLayoutClickListener();
        initializationRecyclerView();
        return view;
    }

    private void initializationView(View view) {
        nearbyRoundButtonView = view.findViewById(R.id.nearbyRoundButtonView);
        recommendedRoundButtonView = view.findViewById(R.id.recommendedRoundButtonView);
        latestRoundButtonView = view.findViewById(R.id.latestRoundButtonView);
        nightRoundButtonView = view.findViewById(R.id.nightRoundButtonView);
        discountRoundButtonView = view.findViewById(R.id.discountRoundButtonView);
        iRoundButtonView = view.findViewById(R.id.iRoundButtonView);
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void initializationOnLinearLayoutClickListener() {
        nearbyRoundButtonView.setOnLinearLayoutClickListener(setListener("Nearby"));
        recommendedRoundButtonView.setOnLinearLayoutClickListener(setListener("Recommended"));
        latestRoundButtonView.setOnLinearLayoutClickListener(setListener("Latest"));
        nightRoundButtonView.setOnLinearLayoutClickListener(setListener("Night"));
        discountRoundButtonView.setOnLinearLayoutClickListener(setListener("Discount"));
        iRoundButtonView.setOnLinearLayoutClickListener(setListener("I"));
    }

    @NonNull
    private RoundButtonView.OnLinearLayoutClickListener setListener(final String name) {
        return new RoundButtonView.OnLinearLayoutClickListener() {
            @Override
            public void OnLinearLayoutClick() {
                Toast.makeText(getContext(), "onClick" + name, Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void initializationRecyclerView() {
        contentList = new ArrayList<>();
        contentList.add(new Content("紫艷中餐廳", "中式料理, 亞洲料理",
                "https://media-cdn.tripadvisor.com/media/photo-s/0c/01/2a/5b/photo2jpg.jpg",
                "1012m"));
        contentList.add(new Content("琺蘭綺瑥朵茶餐館", "義式料理, 歐式料理",
                "https://media-cdn.tripadvisor.com/media/photo-f/12/5c/da/70/world-luxury-restaurant.jpg",
                "318m"));
        contentList.add(new Content("大腕燒肉專門店", "日式料理, 燒烤",
                "https://media-cdn.tripadvisor.com/media/photo-f/08/f5/06/fd/photo1jpg.jpg",
                "262m"));
        contentList.add(new Content("燈燈庵日式料理餐廳", "日式料理",
                "https://media-cdn.tripadvisor.com/media/photo-i/12/d4/d4/5e/caption.jpg",
                "373m"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), contentList);
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "onClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
