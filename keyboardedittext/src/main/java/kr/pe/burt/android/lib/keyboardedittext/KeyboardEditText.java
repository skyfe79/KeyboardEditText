package kr.pe.burt.android.lib.keyboardedittext;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by burt on 2016. 7. 1..
 */
public class KeyboardEditText extends EditText {

    public interface OnKeyboardListener {
        void onKeyboardShown(EditText editText);
        void onKeyboardHidden(EditText editText);
    }

    public KeyboardEditText(Context context) {
        super(context);
    }

    public KeyboardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private OnKeyboardListener onKeyboardListener = null;
    private boolean isKeyboardShown = false;

    public void setOnKeyboardListener(OnKeyboardListener onKeyboardListener) {
        this.onKeyboardListener = onKeyboardListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(onKeyboardListener != null) {
                onKeyboardListener.onKeyboardShown(this);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if(onKeyboardListener != null) {
                onKeyboardListener.onKeyboardHidden(this);
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
