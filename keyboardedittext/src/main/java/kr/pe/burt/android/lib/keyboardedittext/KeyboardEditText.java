package kr.pe.burt.android.lib.keyboardedittext;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by burt on 2016. 7. 1..
 */
public class KeyboardEditText extends EditText {

    public interface OnKeyboardListener {
        void onKeyboardShown(EditText editText);
        void onKeyboardHidden(EditText editText);
    }

    public OnEditorActionListener editorActionListener;


    public KeyboardEditText(Context context) {
        super(context);
        init();
    }

    public KeyboardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOnEditorActionListener(actionListener);
    }



    private OnKeyboardListener onKeyboardListener = null;
    private boolean isKeyboardShown = false;

    public void setOnKeyboardListener(OnKeyboardListener onKeyboardListener) {
        this.onKeyboardListener = onKeyboardListener;
    }

    @Override
    public void setOnEditorActionListener(OnEditorActionListener listener) {
        super.setOnEditorActionListener(actionListener);
        if(listener != actionListener) {
            editorActionListener = listener;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(isKeyboardShown)
                return super.onTouchEvent(event);

            isKeyboardShown = true;

            if(onKeyboardListener != null) {
                onKeyboardListener.onKeyboardShown(this);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            isKeyboardShown = false;
            if(onKeyboardListener != null) {
                onKeyboardListener.onKeyboardHidden(this);
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    private OnEditorActionListener actionListener = new OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

            if(actionId != EditorInfo.IME_ACTION_NONE && actionId != EditorInfo.IME_ACTION_UNSPECIFIED) {
                isKeyboardShown = false;
                if(onKeyboardListener != null) {
                    onKeyboardListener.onKeyboardHidden(KeyboardEditText.this);
                }
                if(editorActionListener == null)
                    return false;
            }

            if(editorActionListener != null) {
                return editorActionListener.onEditorAction(textView, actionId, keyEvent);
            }
            return true;
        }
    };
}
