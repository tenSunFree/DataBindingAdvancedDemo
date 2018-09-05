package com.example.administrator.databindingadvanceddemo.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.databindingadvanceddemo.R;

public class BottomButtonView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private TypedArray attributesTypedArray;
    private ImageView imageView;
    private TextView textView;
    private int imageResourcePressed;
    private int imageResourceSelectable;
    private String nameString;
    private LinearLayout linearLayout;
    private OnLinearLayoutClickListener onLinearLayoutClickListener;

    public BottomButtonView(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public BottomButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
        attributesSettings(context, attrs);
        initializationOnClickListener();
    }

    public BottomButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.layout_bottom_button, this);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        linearLayout = findViewById(R.id.linearLayout);
    }

    private void attributesSettings(Context context, AttributeSet attrs) {
        attributesTypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.BottomButtonView);
        nameString = attributesTypedArray.getString(R.styleable.BottomButtonView_nameText);
        if (nameString != null && !nameString.equals("")) {
            textView.setText(nameString);
        }
        imageResourcePressed =
                attributesTypedArray.getResourceId(R.styleable.BottomButtonView_imageResourcePressed, -1);
        imageResourceSelectable =
                attributesTypedArray.getResourceId(R.styleable.BottomButtonView_imageResourceSelectable, -1);
    }

    private void initializationOnClickListener() {
        linearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onLinearLayoutClickListener.OnLinearLayoutClick();
    }

    public void setImageResource(boolean isPressed) {
        if (isPressed) {
            if (imageResourcePressed != -1) {
                imageView.setImageResource(imageResourcePressed);
            }
        } else {
            if (imageResourceSelectable != -1) {
                imageView.setImageResource(imageResourceSelectable);
            }
        }
    }

    public interface OnLinearLayoutClickListener {
        void OnLinearLayoutClick();
    }

    public void setOnLinearLayoutClickListener(OnLinearLayoutClickListener listener) {
        this.onLinearLayoutClickListener = listener;
    }

    public String getNameString() {
        return nameString;
    }
}
