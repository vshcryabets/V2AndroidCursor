package com.v2soft.androidcursor;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.v2soft.androidcursor.adapters.ContactsAdapter;
import com.v2soft.androidcursor.domain.Contact;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		ListView listView = (ListView) findViewById(android.R.id.list);
		ContactsAdapter adapter = new ContactsAdapter(this);
		listView.setAdapter(adapter);
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
				null, null, null, null);
		List<Contact> contacts = convertCursorToList(cursor, resolver);
		cursor.close();

		return true;
	}


	private List<Contact> convertCursorToList(Cursor cursor, ContentResolver resolver) {
		List<Contact> result = new ArrayList<Contact>();
		while (cursor.moveToNext()) {
			Contact contact = new Contact();
//			String id = cursor.getString(cursor.getColumnIndex());
//			contact.setId(Integer.parseInt(id));
//			String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//			if (Integer.parseInt(cursor.getString(
//					cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
//				Cursor pCur = cr.query(
//						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//						null,
//						ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
//								new String[]{id}, null);
//				while (pCur.moveToNext()) {
//					String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//				}
//				pCur.close();
//			}
			result.add(contact);
		}
		return result;
	}
}
