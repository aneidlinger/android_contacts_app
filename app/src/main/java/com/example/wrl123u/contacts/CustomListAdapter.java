package com.example.wrl123u.contacts;


import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] phone;
    private final Integer[] imgid;

    public CustomListAdapter(Activity context, String[] itemname, String[] phone, Integer[] imgid ) {
        super(context, R.layout.mylist, itemname);

        this.context = context;
        this.itemname = itemname;
        this.phone = phone;
        this.imgid = imgid;

    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = rowView.findViewById(R.id.item);
        ImageView imageView = rowView.findViewById(R.id.icon);
        TextView extratxt = rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText(phone[position]);
        return rowView;

    }

}
