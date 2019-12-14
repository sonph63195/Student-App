package com.duatson.studentapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.duatson.studentapp.R;
import com.duatson.studentapp.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter {

    private Activity context;
    private List<Contact> contacts;

    public ContactAdapter(Activity context, List<Contact> contacts) {
        super(context, R.layout.contact_item_service_detail, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        @SuppressLint({"ViewHolder", "InflateParams"})
        View viewItem = inflater.inflate(R.layout.contact_item_service_detail, null, true);

        Contact contact = contacts.get(position);

        if (contact != null) {
            TextView title = viewItem.findViewById(R.id.contactTitle);
            title.setText(contact.getTitle());

            TextView caption = viewItem.findViewById(R.id.contactCaption);
            caption.setText(contact.getContent());

            ImageView icon = viewItem.findViewById(R.id.contactIcon);
            icon.setImageResource(contact.getIcon());
        }

        return viewItem;
        //return super.getView(position, convertView, parent);
    }
}
