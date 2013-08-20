package com.v2soft.androidcursorsample;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Vladimir Shcryabets <vshcryabets@gmail.com> on 8/20/13.
 */
public class ListItemContact extends LinearLayout {
    private Contact mData;
    private TextView mTxtContact;

    public ListItemContact(Context context) {
        super(context);
        inflate(context, R.layout.listitem_contact, this);
        mTxtContact = (TextView) findViewById(R.id.txtContact);
    }

    public void setData(Contact data) {
        mData = data;
        mTxtContact.setText(data.getName());
    }

    public Contact getData() {
        return mData;
    }
}
