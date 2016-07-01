package kr.pe.burt.android.lib.keyboardedittext.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import kr.pe.burt.android.lib.keyboardedittext.KeyboardEditText;

public class MainActivity extends AppCompatActivity {

    private KeyboardEditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (KeyboardEditText)findViewById(R.id.editText);
        editText.setOnKeyboardListener(new KeyboardEditText.OnKeyboardListener() {
            @Override
            public void onKeyboardShown(EditText editText) {
                Log.d("MainActivity", "onKeyboardShown");
            }

            @Override
            public void onKeyboardHidden(EditText editText) {
                Log.d("MainActivity", "onKeyboardHidden");
            }
        });
    }
}
