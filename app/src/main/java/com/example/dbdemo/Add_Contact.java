package com.example.dbdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dbdemo.data.MyDBHandler;
import com.example.dbdemo.model.Contact;

public class Add_Contact extends AppCompatActivity {
    EditText editName;
    EditText editContact;
    ImageButton addBtn;

    MyDBHandler db = new MyDBHandler(Add_Contact.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editName = findViewById(R.id.inputName);
        editContact = findViewById(R.id.inputContact);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact addContact = new Contact();

                addContact.setName(editName.getText().toString());
                addContact.setPhoneNumber(editContact.getText().toString());

                db.addContact(addContact);
                Toast.makeText(Add_Contact.this, "New Contact Added!", Toast.LENGTH_SHORT).show();

            }
            public void onLongClicked(int position) {
                Toast.makeText(Add_Contact.this, "Add Contact!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}