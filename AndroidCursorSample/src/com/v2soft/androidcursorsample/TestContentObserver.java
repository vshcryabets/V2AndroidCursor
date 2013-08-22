package com.v2soft.androidcursorsample;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

/**
 * 
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 *
 */
public class TestContentObserver extends ContentObserver {
    private static final String LOG_TAG = TestContentObserver.class.getSimpleName();

    public TestContentObserver(Handler handler) {
        super(handler);
    }
    
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.d(LOG_TAG, "onChange called "+ selfChange);
    }
}
