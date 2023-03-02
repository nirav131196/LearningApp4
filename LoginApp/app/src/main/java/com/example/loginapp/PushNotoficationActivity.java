package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PushNotoficationActivity extends BaseActivity {

    EditText edtName,edtTitle,edtDescription;
    Button btnEnter;
    String name,title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notofication);

        initView();
        ClickEventButton();
    }
    private void ClickEventButton()
    {
      name = edtName.getText().toString().trim();
        title = edtTitle.getText().toString().trim();
        description = edtDescription.getText().toString().trim();
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.isEmpty() || title.isEmpty() || description.isEmpty())
                {
                    showToast("Please Enter All Details");
                }
                else
                {

                }

            }
        });
    }
    private void initView()
    {
        edtName = findViewById(R.id.editTextName);
        edtTitle = findViewById(R.id.editTextTitle);
        edtDescription = findViewById(R.id.editTextDescription);
        btnEnter = findViewById(R.id.EnterButton);
    }
}