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

public class BookingHistoryListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<BookingHistory> bookingHistories;

    public BookingHistoryListAdapter(Context context, int layout, ArrayList<BookingHistory> bookingHistories) {
        this.context = context;
        this.layout = layout;
        this.bookingHistories = bookingHistories;
    }

    @Override
    public int getCount() {
        return bookingHistories.size();
    }

    @Override
    public Object getItem(int position) {
        return bookingHistories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder
    {
        TextView txtServiceName, txtDate, txtTime, txtCname, txtStatus, txtServiceNameDummy, txtDateDummy, txtTimeDummy, txtCnameDummy, txtStatusDummy;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row=view;
        BookingHistoryListAdapter.ViewHolder holder=new BookingHistoryListAdapter.ViewHolder();

        if (row==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.txtServiceNameDummy=(TextView)row.findViewById(R.id.textView52);
            holder.txtDateDummy=(TextView)row.findViewById(R.id.textView54);
            holder.txtTimeDummy=(TextView)row.findViewById(R.id.textView56);
            holder.txtCnameDummy=(TextView)row.findViewById(R.id.textView58);
            holder.txtStatusDummy=(TextView)row.findViewById(R.id.textView60);
            holder.txtServiceName=(TextView)row.findViewById(R.id.textView53);
            holder.txtDate=(TextView)row.findViewById(R.id.textView55);
            holder.txtTime=(TextView)row.findViewById(R.id.textView57);
            holder.txtCname=(TextView)row.findViewById(R.id.textView59);
            holder.txtStatus=(TextView)row.findViewById(R.id.textView61);
            row.setTag(holder);

        }
        else
        {
            holder=(BookingHistoryListAdapter.ViewHolder)row.getTag();
        }



        BookingHistory bookingHistory=bookingHistories.get(position);

        holder.txtServiceName.setText(bookingHistory.getServicename());
        holder.txtDate.setText(bookingHistory.getDate());
        holder.txtTime.setText(bookingHistory.getTime());
        holder.txtCname.setText(bookingHistory.getCname());
        holder.txtStatus.setText(bookingHistory.getStatus());

        return row;
    }
}
