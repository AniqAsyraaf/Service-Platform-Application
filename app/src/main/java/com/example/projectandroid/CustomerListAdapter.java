package com.example.projectandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Customer> customerlist;

    public CustomerListAdapter(Context context, int layout, ArrayList<Customer> customerlist) {
        this.context = context;
        this.layout = layout;
        this.customerlist = customerlist;
    }

    @Override
    public int getCount() {
        return customerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return customerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder
    {
        ImageView imageView;
        TextView txtbusinessname, txtrating;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row=view;
        ViewHolder holder=new ViewHolder();

        if (row==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.txtbusinessname=(TextView)row.findViewById(R.id.businessname);
            holder.imageView=(ImageView)row.findViewById(R.id.imageLogo);
            holder.txtrating=(TextView)row.findViewById(R.id.textView65);
            row.setTag(holder);

        }
        else
        {
            holder=(ViewHolder)row.getTag();
        }
        Customer customer=customerlist.get(position);

        holder.txtbusinessname.setText(customer.getBusinessname());

        byte[] customerImage=customer.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(customerImage,0,customerImage.length);
        holder.imageView.setImageBitmap(bitmap);

        String temprate = new Float(customer.getRating()).toString();
        String temprate1 = temprate+" out of 5.0";

        holder.txtrating.setText(temprate1);

        return row;
    }
}
