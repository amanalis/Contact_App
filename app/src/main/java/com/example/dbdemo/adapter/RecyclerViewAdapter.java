package com.example.dbdemo.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbdemo.MainActivity;
import com.example.dbdemo.R;
import com.example.dbdemo.data.MyDBHandler;
import com.example.dbdemo.displayContact;
import com.example.dbdemo.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;
    private MyDBHandler dbHandler;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        this.dbHandler = new MyDBHandler(context);
    }

    //where to get the single card as viewholder object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new ViewHolder(view);
    }

    // what will happen after we create the viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posi = holder.getAdapterPosition();
                Contact contact = contactList.get(posi);

                /*int id = contactList.get(posi).getId(); // Get actual DB ID
                dbHandler.deleteContactById(id);*/

                dbHandler.deleteContact(contact);

                contactList.remove(posi);
                notifyItemRemoved(posi);
                notifyItemRangeChanged(posi, contactList.size());

                Toast.makeText(context, "Deleted: " + contact.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //how many items
    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView contactName;
        public TextView phoneNumber;
        public ImageView iconButton;
        public ImageButton delBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            contactName = itemView.findViewById(R.id.rowName);
            phoneNumber = itemView.findViewById(R.id.rowContact);
            iconButton = itemView.findViewById(R.id.iconButton);
            delBtn = itemView.findViewById(R.id.btnDelete);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            Contact dbContact = contactList.get(position);

            Intent intent = new Intent(context, displayContact.class);
            intent.putExtra("id", dbContact.getId());
            intent.putExtra("name", dbContact.getName());
            intent.putExtra("number", dbContact.getPhoneNumber());
            context.startActivity(intent);

            Toast.makeText(context, "The position is " + String.valueOf(position), Toast.LENGTH_SHORT).show();
        }
    }
}
