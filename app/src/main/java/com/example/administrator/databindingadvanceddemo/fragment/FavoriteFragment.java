package com.example.administrator.databindingadvanceddemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.databindingadvanceddemo.R;

public class FavoriteFragment extends Fragment {

    private String testString;
    private TextView textView;

    public static FavoriteFragment newInstance(String test) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("test", test);
        fragment.setArguments(bundle);
        return fragment;
    }

    public FavoriteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        initializationView(view);
        Bundle bundle = getArguments();
        testString = bundle.getString("test");
        textView.setText(testString);
        return view;
    }

    private void initializationView(View view) {
        textView = view.findViewById(R.id.textView);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
