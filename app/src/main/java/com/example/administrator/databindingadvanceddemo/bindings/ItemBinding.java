package com.example.administrator.databindingadvanceddemo.bindings;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ItemBinding {

    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
