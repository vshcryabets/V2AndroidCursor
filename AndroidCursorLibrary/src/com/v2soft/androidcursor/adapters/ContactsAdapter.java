package com.v2soft.androidcursor.adapters;

import android.content.Context;

import com.v2soft.AndLib.ui.Adapters.CustomViewAdapter;
import com.v2soft.AndLib.ui.views.IDataView;
import com.v2soft.androidcursor.domain.Contact;

public class ContactsAdapter extends CustomViewAdapter<Contact>{
	private CustomViewAdapter.CustomViewAdapterFactory<Contact, IDataView<Contact>> sFactory = 
			new CustomViewAdapterFactory<Contact, IDataView<Contact>>() {

		@Override
		public IDataView<Contact> createView(Context context, int viewType) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public ContactsAdapter(Context context) {
		super(context);
	}
}
