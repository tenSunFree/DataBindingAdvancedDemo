package com.example.administrator.databindingadvanceddemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.databindingadvanceddemo.bean.Content2;
import com.example.administrator.databindingadvanceddemo.component.BottomButtonView;
import com.example.administrator.databindingadvanceddemo.databinding.ActivityMainBinding;
import com.example.administrator.databindingadvanceddemo.fragment.FavoriteFragment;
import com.example.administrator.databindingadvanceddemo.fragment.HomeFragment;
import com.example.administrator.databindingadvanceddemo.fragment.MemberFragment;
import com.example.administrator.databindingadvanceddemo.fragment.MoreFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private FavoriteFragment favoriteFragment;
    private MemberFragment memberFragment;
    private MoreFragment moreFragment;
    private Content2 content2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializationBinding();
        initializationBottomButtonView(true, false, false, false);
        initializationOnClickListener();
        initializationOnBottomButtonViewClickListener(activityMainBinding.homeBottomButtonView, 1);
        initializationOnBottomButtonViewClickListener(activityMainBinding.favoriteBottomButtonView, 2);
        initializationOnBottomButtonViewClickListener(activityMainBinding.memberBottomButtonView, 3);
        initializationOnBottomButtonViewClickListener(activityMainBinding.moreBottomButtonView, 4);
    }

    private void initializationBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        content2 = new Content2(getResources().getString(R.string.titleBarAppName));
        activityMainBinding.setContent2(content2);
    }

    private void initializationOnClickListener() {
        activityMainBinding.searchImageView.setOnClickListener(this);
        activityMainBinding.otherImageView.setOnClickListener(this);
    }

    private void initializationBottomButtonView(
            boolean home, boolean favorite, boolean member, boolean more) {
        activityMainBinding.homeBottomButtonView.setImageResource(home);
        activityMainBinding.favoriteBottomButtonView.setImageResource(favorite);
        activityMainBinding.memberBottomButtonView.setImageResource(member);
        activityMainBinding.moreBottomButtonView.setImageResource(more);
    }

    private void initializationOnBottomButtonViewClickListener(
            final BottomButtonView bottomButtonView, final int showFragment) {
        bottomButtonView.setOnLinearLayoutClickListener(
                new BottomButtonView.OnLinearLayoutClickListener() {
                    @Override
                    public void OnLinearLayoutClick() {
                        switch (bottomButtonView.getNameString()) {
                            case "首頁":
                                CustomApplication.isHomeSelected = true;
                                CustomApplication.isFavoriteSelected = false;
                                CustomApplication.isMemberSelected = false;
                                CustomApplication.isMoreSelected = false;
                                break;
                            case "最愛":
                                CustomApplication.isHomeSelected = false;
                                CustomApplication.isFavoriteSelected = true;
                                CustomApplication.isMemberSelected = false;
                                CustomApplication.isMoreSelected = false;
                                break;
                            case "會員":
                                CustomApplication.isHomeSelected = false;
                                CustomApplication.isFavoriteSelected = false;
                                CustomApplication.isMemberSelected = true;
                                CustomApplication.isMoreSelected = false;
                                break;
                            case "更多":
                                CustomApplication.isHomeSelected = false;
                                CustomApplication.isFavoriteSelected = false;
                                CustomApplication.isMemberSelected = false;
                                CustomApplication.isMoreSelected = true;
                                break;
                        }
                        changePictureBottomButtonView();
                        showFragment(showFragment);
                    }
                });
    }

    private void initializationFragmentManager() {
        fragmentManager = getSupportFragmentManager();                                                // 获取到外层fragment的子fragment的manager
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    public void showFragment(int index) {
        initializationFragmentManager();
        hideFragments(fragmentTransaction);                                                         // Want to display a fragment, first hide all fragments, prevent overlap
        switch (index) {
            case 1:
                if (homeFragment != null) {                                                         // If fragment already exists, display it
                    fragmentTransaction.show(homeFragment);
                } else {                                                                            // Otherwise, the first time you switch, add fragment. Note that it will be displayed after adding.
                    homeFragment = new HomeFragment().newInstance();
                    fragmentTransaction.add(R.id.mainLinearLayout, homeFragment);
                }
                break;
            case 2:
                if (favoriteFragment != null) {                                                     // If fragment already exists, display it
                    fragmentTransaction.show(favoriteFragment);
                } else {                                                                            // Otherwise, the first time you switch, add fragment. Note that it will be displayed after adding.
                    favoriteFragment = new FavoriteFragment().newInstance("favorite");
                    fragmentTransaction.add(R.id.mainLinearLayout, favoriteFragment);
                }
                break;
            case 3:
                if (memberFragment != null) {                                                       // If fragment already exists, display it
                    fragmentTransaction.show(memberFragment);
                } else {                                                                            // Otherwise, the first time you switch, add fragment. Note that it will be displayed after adding.
                    memberFragment = new MemberFragment().newInstance("member");
                    fragmentTransaction.add(R.id.mainLinearLayout, memberFragment);
                }
                break;
            case 4:
                if (moreFragment != null) {                                                         // If fragment already exists, display it
                    fragmentTransaction.show(moreFragment);
                } else {                                                                            // Otherwise, the first time you switch, add fragment. Note that it will be displayed after adding.
                    moreFragment = new MoreFragment().newInstance("more");
                    fragmentTransaction.add(R.id.mainLinearLayout, moreFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * When the fragment has been instantiated, it is hidden
     */
    public void hideFragments(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null)
            fragmentTransaction.hide(homeFragment);
        if (favoriteFragment != null)
            fragmentTransaction.hide(favoriteFragment);
        if (memberFragment != null)
            fragmentTransaction.hide(memberFragment);
        if (moreFragment != null)
            fragmentTransaction.hide(moreFragment);
    }

    public void changePictureBottomButtonView() {
        initializationBottomButtonView(
                CustomApplication.isHomeSelected, CustomApplication.isFavoriteSelected,
                CustomApplication.isMemberSelected, CustomApplication.isMoreSelected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        changePictureBottomButtonView();
        if (CustomApplication.isHomeSelected) {
            showFragment(1);
        }
        if (CustomApplication.isFavoriteSelected) {
            showFragment(2);
        }
        if (CustomApplication.isMemberSelected) {
            showFragment(3);
        }
        if (CustomApplication.isMoreSelected) {
            showFragment(4);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchImageView:
                Toast.makeText(view.getContext(), "onClickSearch", Toast.LENGTH_SHORT).show();
                break;
            case R.id.otherImageView:
                Toast.makeText(view.getContext(), "onClickOther", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
