package com.example.projectandroid;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Booking> bookingslist;

    public BookingListAdapter(Context context, int layout, ArrayList<Booking> bookingslist) {
        this.context = context;
        this.layout = layout;
        this.bookingslist = bookingslist;
    }

    @Override
    public int getCount() {
        return bookingslist.size();
    }

    @Override
    public Object getItem(int position) {
        return bookingslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder
    {
        TextView txtServiceName, txtDate, txtTime, txtServiceNameDummy, txtDateDummy, txtTimeDummy;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row=view;
        BookingListAdapter.ViewHolder holder=new BookingListAdapter.ViewHolder();

        if (row==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.txtServiceNameDummy=(TextView)row.findViewById(R.id.textView32);
            holder.txtDateDummy=(TextView)row.findViewById(R.id.textView33);
            holder.txtTimeDummy=(TextView)row.findViewById(R.id.textView34);
            holder.txtServiceName=(TextView)row.findViewById(R.id.textView26);
            holder.txtDate=(TextView)row.findViewById(R.id.textView27);
            holder.txtTime=(TextView)row.findViewById(R.id.textView31);
            row.setTag(holder);

        }
        else
        {
            holder=(BookingListAdapter.ViewHolder)row.getTag();
        }



        Booking booking=bookingslist.get(position);

        holder.txtServiceName.setText(booking.getServicename());
        holder.txtDate.setText(booking.getDate());
        holder.txtTime.setText(booking.getTime());

        return row;
    }
}
