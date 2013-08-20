package com.v2soft.androidcursorsample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by mrco on 8/20/13.
 *
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 */
public class ContactsAdapter extends BaseAdapter {
    private List<Contact> mItems;
    private Context mContext;

    public ContactsAdapter(Context context, List<Contact> items) {
        super();
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItemContact item = (ListItemContact) view;
        if ( item == null ) {
            item = new ListItemContact(mContext);
        }
        item.setData(mItems.get(i));
        return item;
    }
}

