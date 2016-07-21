package kr.pe.burt.android.lib.keyboardedittext.app;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import kr.pe.burt.android.lib.keyboardedittext.KeyboardEditText;

public class MainActivity extends AppCompatActivity {

    private KeyboardEditText editText = null;
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText = (KeyboardEditText)findViewById(R.id.editText);
        editText.setOnKeyboardListener(new KeyboardEditText.OnKeyboardListener() {
            @Override
            public void onKeyboardShown(EditText editText) {
                ObjectAnimator animator = ObjectAnimator.ofInt(editText, "bottom",   editText.getBottom(), editText.getBottom() + 300);
                animator.setDuration(1000);
                animator.setInterpolator(new BounceInterpolator());
                animator.start();
            }

            @Override
            public void onKeyboardHidden(EditText editText) {
                textView.setText(editText.getText());

                ObjectAnimator animator = ObjectAnimator.ofInt(textView, "top",   textView.getTop(), textView.getTop() - 200);
                animator.setDuration(1000);
                animator.setInterpolator(new BounceInterpolator());
                animator.start();
            }
        });
    }
}
