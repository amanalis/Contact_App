package com.example.dbdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dbdemo.data.MyDBHandler;
import com.example.dbdemo.model.Contact;

public class contact extends AppCompatActivity {

    EditText editid;
    EditText editname;
    EditText editcontact;
    Button button;

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

        editid = findViewById(R.id.editId);
        editname = findViewById(R.id.editName);
        editcontact = findViewById(R.id.editContact);
        button = findViewById(R.id.button);

        editid.setText(String.valueOf(contactId));
        editname.setText(contactName);
        editcontact.setText(contactNum);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler db = new MyDBHandler(contact.this);
                Contact updateContact = new Contact();

                updateContact.setId(contactId);
                updateContact.setName(editname.getText().toString());
                updateContact.setPhoneNumber(editcontact.getText().toString());

                int affectedRows = db.updateContact(updateContact);
                Toast.makeText(contact.this, "Contact updated!", Toast.LENGTH_SHORT).show();
                Log.d("dbaman", "affectedRows are: " + affectedRows);

                Intent intent = new Intent(contact.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}