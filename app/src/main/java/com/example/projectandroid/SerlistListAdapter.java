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

public class SerlistListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Serlist> serlistlist;


    public SerlistListAdapter(Context context, int layout, ArrayList<Serlist> serlistlist) {
        this.context = context;
        this.layout = layout;
        this.serlistlist = serlistlist;
    }





    @Override
    public int getCount() {
        return serlistlist.size();
    }

    @Override
    public Object getItem(int position) {
        return serlistlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder
    {
        TextView serviceview;
        TextView description;
        TextView pricemin;
        TextView pricemax;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row=view;
        ViewHolder holder=new ViewHolder();

        if (row==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.serviceview=(TextView)row.findViewById(R.id.servicename);
            holder.description=(TextView) row.findViewById(R.id.description);
            holder.pricemin=(TextView)row.findViewById(R.id.pricemin);
            holder.pricemax=(TextView) row.findViewById(R.id.pricemax);
            row.setTag(holder);

        }
        else
        {
            holder=(ViewHolder)row.getTag();
        }
        Serlist serlist=serlistlist.get(position);

        holder.serviceview.setText(serlist.getServicename());
        holder.description.setText(serlist.getDescription());
        holder.pricemin.setText(Double.toString(serlist.getPrice_min()));
        holder.pricemax.setText(Double.toString(serlist.getPrice_max()));

        return row;
    }
}
