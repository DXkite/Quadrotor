package cn.dxkite.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import cn.dxkite.quadrotor.R;

public class NumberEdit extends LinearLayout {
    int number=50,max=100,min=0;
    String namespace ="http://schemas.android.com/apk/res/android";
    Button down;
    Button up;
    EditText editText;
    View layout;
    final static String TAG ="NumberEdit";
    OnNumberChangeListener numberChangeListener;

    public NumberEdit(Context context) {
        super(context);
        layout =  LayoutInflater.from(context).inflate(R.layout.number_edit,null);
        init();
    }

    public NumberEdit(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        layout =  LayoutInflater.from(context).inflate(R.layout.number_edit,null);
//        min = attrs.getAttributeIntValue(namespace,"min",min);
//        max = attrs.getAttributeIntValue(namespace,"max",max);
        init();
    }

    public NumberEdit(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        layout =  LayoutInflater.from(context).inflate(R.layout.number_edit,null);
//        min = attrs.getAttributeIntValue(namespace,"min",min);
//        max = attrs.getAttributeIntValue(namespace,"max",max);
        init();
    }

    public void setOnNumberChangeListener(OnNumberChangeListener numberChangeListener) {
        this.numberChangeListener = numberChangeListener;
    }

    public void init() {
        setOrientation(HORIZONTAL);
        editText = layout.findViewById(R.id.editText);
        down = layout.findViewById(R.id.down);
        up = layout.findViewById(R.id.up);
        editText.setText(String.valueOf(number));
        up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number < max) {
                    number += 1;
                    editText.setText(String.valueOf(number));
                }
            }
        });

        down.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > min) {
                    number -= 1;
                    editText.setText(String.valueOf(number));
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 if (s.length() > 0) {
                     number =Integer.valueOf(s.toString());
                     if (number > max) number = max;
                     if (number < min) number = min;
                     if (numberChangeListener != null) {
                         numberChangeListener.onNumberChange(NumberEdit.this,number);
                     }
                 }
            }
        });
        layout.setLayoutParams( new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT)
        );
        addView(layout);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        editText.setText(String.valueOf(this.number));
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public interface OnNumberChangeListener {
        void onNumberChange(NumberEdit numberEdit,int number);
    }
}
