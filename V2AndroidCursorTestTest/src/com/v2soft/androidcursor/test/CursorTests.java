package com.v2soft.androidcursor.test;

import java.util.List;

import com.v2soft.androidcursor.CursorDao;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.test.AndroidTestCase;

/**
 * Tests for CursorDao class.
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 */
public class CursorTests extends AndroidTestCase {

    public void testContacts() {
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        CursorDao<Contact> dao = new CursorDao<Contact>(Contact.class, new CursorDao.Factory<Contact>(){
            @Override
            public Contact newItem() {
                return new Contact();
            }
        });
        List<Contact> contacts = dao.getListFromCursor(cursor);
        cursor.close();
        assertNotNull("No contacts", contacts);
        assertTrue("No contacts", contacts.size() > 0);
    }
    
    /**
     * Test different data types.
     * @author V.Shcryabets<vshcryabets@gmail.com>
     */
    public void testDataTypes() {
        
    }
}
