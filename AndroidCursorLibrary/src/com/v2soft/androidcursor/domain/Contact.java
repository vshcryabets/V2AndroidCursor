package com.v2soft.androidcursor.domain;

import android.provider.ContactsContract;

import com.v2soft.androidcursor.CursorDataAnnotation;

public class Contact {
	@CursorDataAnnotation(columnName=ContactsContract.Contacts._ID)
	private int mId;
	// ==========================================================================
	// Getters & setters
	// ==========================================================================
	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}
}
