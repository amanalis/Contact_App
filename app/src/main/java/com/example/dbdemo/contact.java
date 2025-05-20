package com.example.dbdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class contact extends AppCompatActivity {

    TextView id;
    TextView name;
    TextView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int contactId = getIntent().getIntExtra("id", -1);
        String contactName = intent.getStringExtra("name");
        String contactNum = intent.getStringExtra("number");

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);

        id.setText(String.valueOf(contactId));
        name.setText(contactName);
        contact.setText(contactNum);

    }
}