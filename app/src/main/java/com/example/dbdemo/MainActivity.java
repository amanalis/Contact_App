package com.example.dbdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dbdemo.data.MyDBHandler;
import com.example.dbdemo.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MyDBHandler db = new MyDBHandler(MainActivity.this);

        //Creating contact
        Contact aman = new Contact();
        aman.setPhoneNumber("123456789012");
        aman.setName("Aman");

        //adding contact
        db.addContact(aman);

        //Creating contact
        Contact aman1 = new Contact();
        aman1.setPhoneNumber("123456789012");
        aman1.setName("Aman22");

        //adding contact
        db.addContact(aman1);

        //Creating contact
        Contact aman2 = new Contact();
        aman2.setPhoneNumber("123456789012");
        aman2.setName("Aman11");

        //adding contact
        db.addContact(aman2);

        Log.d("dbaman", "Id's are: " + aman.getId() + aman1.getId() + aman2.getId());

        //get all contacts
        List<Contact> allContacts = db.getAllContacts();
        for (Contact contact : allContacts) {
            Log.d("dbaman", "Id: " + contact.getId() +
                    " Name: " + contact.getName() +
                    " Contact: " + contact.getPhoneNumber() + "\n");
        }
    }
}