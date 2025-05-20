package com.example.dbdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dbdemo.data.MyDBHandler;
import com.example.dbdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    MyDBHandler db;
    ArrayList<String> contacts;
    List<Contact> allContacts;
    ArrayAdapter<String> arrayAdapter;

    /*int contactId;
    String contactName;
    String contactNum;*/

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

        db = new MyDBHandler(MainActivity.this);

/*
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

        //contact update
        aman2.setId(19);
        aman2.setName("Akram");
        aman2.setPhoneNumber("6969696969969");
        int affectedRows = db.updateContact(aman2);
        Log.d("dbaman", "affectedRows are: " + affectedRows);

        db.deleteContactById(12);
        db.deleteContactById(5);
*/

        contacts = new ArrayList<>();
        listView = findViewById(R.id.listView);

        //get all contacts
        allContacts = db.getAllContacts();
        for (Contact contact : allContacts) {
            Log.d("dbaman", "Id: " + contact.getId() +
                    " Name: " + contact.getName() +
                    " Contact: " + contact.getPhoneNumber() + "\n");
            contacts.add(contact.getName() + " (" + contact.getPhoneNumber() + ")");

            /*contactId = contact.getId();
            contactName=contact.getName();
            contactNum=contact.getPhoneNumber();*/

        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String Contact = "Contact" + position + " " + ((TextView) view).getText().toString();
                Toast.makeText(MainActivity.this, Contact, Toast.LENGTH_SHORT).show();

                Contact selectedContact = allContacts.get(position);

                Intent intent = new Intent(MainActivity.this, contact.class);
                intent.putExtra("id", selectedContact.getId());
                intent.putExtra("name", selectedContact.getName());
                intent.putExtra("number", selectedContact.getPhoneNumber());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
    }

    private void loadContacts(){
        contacts.clear();
        allContacts = db.getAllContacts();

        for (Contact contact : allContacts){
            contacts.add(contact.getName() + " (" + contact.getPhoneNumber() + ")");
        }
        if(arrayAdapter == null){
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
            listView.setAdapter(arrayAdapter);
        }else {
            arrayAdapter.notifyDataSetChanged();
        }

    }
}