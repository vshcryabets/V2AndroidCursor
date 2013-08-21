package com.v2soft.androidcursorsample;

import android.provider.ContactsContract;

import com.v2soft.androidcursor.CursorDataAnnotation;

/**
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 */
public class Contact {
	@CursorDataAnnotation(columnName=ContactsContract.Contacts._ID)
	private int mId;

    @CursorDataAnnotation(columnName=ContactsContract.Contacts.DISPLAY_NAME)
    private String mName;
	// ==========================================================================
	// Getters & setters
	// ==========================================================================
	public int getId() {
		return mId;
	}
	public void setId(int mId) {
		this.mId = mId;
	}
    public String getName() {
        return mName;
    }
}
