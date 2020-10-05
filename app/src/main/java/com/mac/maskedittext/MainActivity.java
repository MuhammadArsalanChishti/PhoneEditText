package com.mac.maskedittext;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.textEdit);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER |
                        InputType.TYPE_CLASS_PHONE);
    }


}