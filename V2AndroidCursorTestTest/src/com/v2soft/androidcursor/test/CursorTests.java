package com.v2soft.androidcursor.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.test.AndroidTestCase;

import com.v2soft.androidcursor.CursorDao;
import com.v2soft.androidcursorsample.Contact;
import com.v2soft.androidcursorsample.TestContentObserver;
import com.v2soft.androidcursorsample.TestContentProvider;
import com.v2soft.androidcursorsample.TestData;

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
        ContentResolver resolver = mContext.getContentResolver();
        CursorDao<TestData> dao = new CursorDao<TestData>(TestData.class, new CursorDao.Factory<TestData>(){
            @Override
            public TestData newItem() {
                return new TestData();
            }
        });
        deleteAllItems(resolver);

        // prepare items
        List<TestData> origin = new ArrayList<TestData>();
        for ( int i = 0; i< 10; i++ ) {
            TestData item = new TestData();
            item.setBooleanValue(i%2==0);
            item.setDoubleValue(((double)i)/10.0);
            item.setFloatValue(((float)i)/20.0f);
            item.setIntValue(i*3);
            if ( i == 0 ) {
                item.setStringValue(null);
            } else {
                item.setStringValue("String "+i);
            }
            resolver.insert(TestContentProvider.CONTENT_URI, dao.itemToContentValues(item));
            origin.add(item);
        }

        // read and check list
        Cursor cursor = resolver.query(TestContentProvider.CONTENT_URI,
                null, null, null, null);
        List<TestData> items = dao.getListFromCursor(cursor);
        cursor.close();
        assertNotNull("No items", items);
        assertTrue("Wrong items count", items.size() == 10);
        // test items
        for (TestData item : origin) {
            assertTrue("Can't find item "+item.getIntValue(), items.contains(item));
        }
        // test update
        int position = items.size()/2;
        TestData modifiedItem = items.get(position);
        modifiedItem.setBooleanValue(false);
        modifiedItem.setDoubleValue(123.456);
        modifiedItem.setFloatValue(894.345f);
        modifiedItem.setIntValue(232434234);
        modifiedItem.setStringValue(UUID.randomUUID().toString());
        resolver.update(TestContentProvider.CONTENT_URI, dao.itemToContentValues(modifiedItem), String.valueOf(position), null);
        origin.set(position, modifiedItem);
        cursor = resolver.query(TestContentProvider.CONTENT_URI,
                null, null, null, null);
        items = dao.getListFromCursor(cursor);
        cursor.close();
        for (TestData item : origin) {
            assertTrue("Can't find item "+item.getIntValue(), items.contains(item));
        }
        deleteAllItems(resolver);
        // read and check list
        cursor = resolver.query(TestContentProvider.CONTENT_URI,
                null, null, null, null);
        items = dao.getListFromCursor(cursor);
        cursor.close();
        assertNotNull("No items", items);
        assertTrue("List should be empty", items.size() == 0);
    }

    private void deleteAllItems(ContentResolver resolver) {
        Cursor cursor = resolver.query(TestContentProvider.CONTENT_URI,
                null, null, null, null);
        int size = cursor.getCount();
        cursor.close();
        // delete all items
        for ( int i = size; i > 0 ; i -- ) {
            resolver.delete(TestContentProvider.CONTENT_URI, String.valueOf(i-1), null);
        }
    }

    /**
     * Test content observer functionality.
     * @author V.Shcryabets<vshcryabets@gmail.com>
     */
    public void testContentObserver() {
        ContentResolver resolver = mContext.getContentResolver();
        CursorDao<TestData> dao = new CursorDao<TestData>(TestData.class, new CursorDao.Factory<TestData>(){
            @Override
            public TestData newItem() {
                return new TestData();
            }
        });

        deleteAllItems(resolver);
        TestContentObserver observer = new TestContentObserver(null);
        resolver.registerContentObserver(TestContentProvider.CONTENT_URI, true, observer);
        int testRecordsCount = new Random().nextInt(100);
        for ( int i = 0; i< testRecordsCount; i++ ) {
            TestData item = new TestData();
            item.setDoubleValue(((double)i)/10.0);
            item.setStringValue("String "+i);
            resolver.insert(TestContentProvider.CONTENT_URI, dao.itemToContentValues(item));
        }
        assertTrue("Content observer wasn't notified", testRecordsCount == observer.getChangeCount());
        deleteAllItems(resolver);

        resolver.unregisterContentObserver(observer);
        assertTrue("Content observer wasn't notified", observer.isOnChangeCalled());
    }
}
