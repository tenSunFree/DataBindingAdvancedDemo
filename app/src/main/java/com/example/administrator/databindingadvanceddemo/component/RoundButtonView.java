package com.example.administrator.databindingadvanceddemo.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.databindingadvanceddemo.R;

public class RoundButtonView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private TypedArray attributesTypedArray;
    private ImageView imageView;
    private TextView textView;
    private int imageResourceSelectable;
    private String nameString;
    private LinearLayout linearLayout;
    private OnLinearLayoutClickListener onLinearLayoutClickListener;

    public RoundButtonView(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public RoundButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
        attributesSettings(context, attrs);
        initializationOnClickListener();
    }

    public RoundButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.layout_round_button, this);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        linearLayout = findViewById(R.id.linearLayout);
    }

    private void attributesSettings(Context context, AttributeSet attrs) {
        attributesTypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.RoundButtonView);
        nameString = attributesTypedArray.getString(R.styleable.RoundButtonView_RoundButtonNameText);
        if (nameString != null && !nameString.equals("")) {
            textView.setText(nameString);
        }
        imageResourceSelectable =
                attributesTypedArray.getResourceId(R.styleable.RoundButtonView_RoundButtonImageResourceSelectable, -1);
        if (imageResourceSelectable != -1) {
            imageView.setImageResource(imageResourceSelectable);
        }
    }

    private void initializationOnClickListener() {
        linearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onLinearLayoutClickListener.OnLinearLayoutClick();
    }

    public interface OnLinearLayoutClickListener {
        void OnLinearLayoutClick();
    }

    public void setOnLinearLayoutClickListener(OnLinearLayoutClickListener listener) {
        this.onLinearLayoutClickListener = listener;
    }
}
