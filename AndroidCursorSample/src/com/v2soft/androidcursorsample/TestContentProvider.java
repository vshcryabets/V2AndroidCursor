package com.v2soft.androidcursorsample;

import java.util.ArrayList;
import java.util.List;

import com.v2soft.androidcursor.CursorDao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Test data provider.
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 */
public class TestContentProvider extends ContentProvider {
    public static final String PROVIDER_NAME = "com.v2soft.androidcursor.test.TestContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://"+ PROVIDER_NAME + "/minerals");
    private UriMatcher uriMatcher;
    private static final int MINERALS = 1;
    private static final int MINERAL_ID = 2;    
    private List<TestData> mData;
    private CursorDao<TestData> mDao;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
        case MINERALS:
            return "vnd.android.cursor.dir/com.v2soft.androidcursor.test.minerals";
        case MINERAL_ID: 
            return "vnd.android.cursor.item/com.v2soft.androidcursor.test.minerals";
        default:
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        TestData item = mDao.itemFromContentValues2(values);
        mData.add(item);
        Uri newRow = Uri.parse(CONTENT_URI.toString() + "/" + (mData.size()-1));
        return newRow;
    }

    @Override
    public boolean onCreate() {
        mDao = new CursorDao<TestData>(TestData.class, new CursorDao.Factory<TestData>() {
            @Override
            public TestData newItem() {
                return new TestData();
            }
        }, 1);
        mData = new ArrayList<TestData>();
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "minerals", MINERALS);
        uriMatcher.addURI(PROVIDER_NAME, "minerals/#", MINERAL_ID);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
            switch (uriMatcher.match(uri)){
            case MINERALS:
                return new TestDataCursor(mData);
            case MINERAL_ID:                
                int i = Integer.parseInt(uri.getLastPathSegment());
                return new TestDataCursor(new ArrayList<TestData>(mData.subList(i, i)));
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection,
                String[] selectionArgs) {
            // TODO Auto-generated method stub
            return 0;
        }

    }
