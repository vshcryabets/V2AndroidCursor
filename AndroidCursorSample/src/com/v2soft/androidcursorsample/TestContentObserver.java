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
    private boolean isOnChangeCalled;
    private int mChangeCount;

    public TestContentObserver(Handler handler) {
        super(handler);
        setOnChangeCalled(false);
        mChangeCount = 0;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        setOnChangeCalled(true);
        mChangeCount ++;
        Log.d(LOG_TAG, "onChange called "+ selfChange);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return true;
    }

    public boolean isOnChangeCalled() {
        return isOnChangeCalled;
    }

    public void setOnChangeCalled(boolean isOnChangeCalled) {
        this.isOnChangeCalled = isOnChangeCalled;
    }

    public int getChangeCount() {
        return mChangeCount;
    }

    public void setChangeCount(int mChangeCount) {
        this.mChangeCount = mChangeCount;
    }
}
