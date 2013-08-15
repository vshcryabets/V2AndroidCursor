package com.v2soft.androidcursor.views;

import com.v2soft.AndLib.ui.views.IDataView;
import com.v2soft.androidcursor.domain.Contact;

import android.content.Context;
import android.widget.LinearLayout;

public class ListItemContact extends LinearLayout implements IDataView<Contact>{
	private Contact mData;

	public ListItemContact(Context context) {
		super(context);
	}

	@Override
	public void setData(Contact data) {
		mData = data;
	}

	@Override
	public Contact getData() {
		return mData;
	}
}
