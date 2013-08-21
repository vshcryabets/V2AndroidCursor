package com.v2soft.androidcursorsample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Vladimir Shcryabets <vshcryabets@gmail.com> on 8/20/13.
 */
public class ListItemContact extends LinearLayout {
    private Contact mData;
    private TextView mTxtContact;
    private ImageView mPhoto;

    public ListItemContact(Context context) {
        super(context);
        inflate(context, R.layout.listitem_contact, this);
        mTxtContact = (TextView) findViewById(R.id.txtContact);
        mPhoto = (ImageView) findViewById(R.id.imgContact);
    }

    public void setData(Contact data) {
        mData = data;
        mTxtContact.setText(data.getName());
        if ( data.getPhotoUri() != null ) {
            mPhoto.setImageURI(Uri.parse(data.getPhotoUri()));
        } else {
            mPhoto.setImageResource(android.R.drawable.ic_dialog_info);
        }
        PaintDrawable p = new PaintDrawable(Color.BLACK);
        p.setIntrinsicHeight(40);
        p.setIntrinsicWidth(40);
        p.setCornerRadius(6);
        mPhoto.setBackgroundDrawable(p);
    }

    public Contact getData() {
        return mData;
    }
}
