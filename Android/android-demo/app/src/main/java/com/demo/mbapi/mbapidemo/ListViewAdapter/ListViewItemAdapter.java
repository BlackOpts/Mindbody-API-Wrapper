package com.demo.mbapi.mbapidemo.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.mbapi.mbapidemo.R;

import java.util.ArrayList;

public class ListViewItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ListViewEntryItem> items;
    private LayoutInflater inflater;

    public ListViewItemAdapter(Context context, ArrayList<ListViewEntryItem> items)
    {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        final ListViewEntryItem i = items.get(position); if(i!=null) {

            if(i.isSectionHeader()) {
                v = inflater.inflate(R.layout.list_header, null);
                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);
                final TextView sectionView = (TextView)v.findViewById(R.id.tv_header);
                sectionView.setText(i.Header);
            }
            else if(i.isMoreContentAction()) {
                v = inflater.inflate(R.layout.list_entry_more, null);
            }
            else {
                v= inflater.inflate(R.layout.list_entry_detail, null);
                final TextView title = (TextView)v.findViewById(R.id.tv_title);
                final TextView subtitle = (TextView)v.findViewById(R.id.tv_detail);
                if(title != null) title.setText(i.Title);
                if(subtitle != null) subtitle.setText(i.Description);
            }
        }
        return v;
    }
}
