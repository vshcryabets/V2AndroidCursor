package com.v2soft.androidcursorsample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.widget.ListView;

import com.v2soft.androidcursor.CursorDao;

import java.util.List;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cursor != null) {
            CursorDao<Contact> dao = new CursorDao<Contact>(Contact.class, new CursorDao.Factory<Contact>(){
                @Override
                public Contact newItem() {
                    return new Contact();
                }
            }, 1);
            List<Contact> contacts = dao.getListFromCursor(cursor);
            cursor.close();
            ContactsAdapter adapter = new ContactsAdapter(this, contacts);
            ListView list = (ListView) findViewById(android.R.id.list);
            list.setAdapter(adapter);
        }
    }
}
