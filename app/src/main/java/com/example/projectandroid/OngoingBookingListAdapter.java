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

public class OngoingBookingListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<OngoingBooking> ongoingBookings;

    public OngoingBookingListAdapter(Context context, int layout, ArrayList<OngoingBooking> ongoingBookings) {
        this.context = context;
        this.layout = layout;
        this.ongoingBookings = ongoingBookings;
    }

    @Override
    public int getCount() {
        return ongoingBookings.size();
    }

    @Override
    public Object getItem(int position) {
        return ongoingBookings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder
    {
        TextView txtservicename, txtdate, txttime, txtstatus, txtservicenamedummy, txtdatedummy, txttimedummy, txtstatusdummy;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row=view;
        ViewHolder holder=new ViewHolder();

        if (row==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.txtservicename=(TextView)row.findViewById(R.id.textView26);
            holder.txtdate=(TextView)row.findViewById(R.id.textView27);
            holder.txttime=(TextView)row.findViewById(R.id.textView31);
            holder.txtstatus=(TextView)row.findViewById(R.id.textView51);
            holder.txtservicenamedummy=(TextView)row.findViewById(R.id.textView32);
            holder.txtdatedummy=(TextView)row.findViewById(R.id.textView33);
            holder.txttimedummy=(TextView)row.findViewById(R.id.textView34);
            holder.txtstatusdummy=(TextView)row.findViewById(R.id.textView50);
            row.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)row.getTag();
        }
        OngoingBooking ongoingBooking=ongoingBookings.get(position);

        holder.txtservicename.setText(ongoingBooking.getServicename());
        holder.txtdate.setText(ongoingBooking.getDate());
        holder.txttime.setText(ongoingBooking.getTime());
        holder.txtstatus.setText(ongoingBooking.getStatus());

        return row;
    }
}
