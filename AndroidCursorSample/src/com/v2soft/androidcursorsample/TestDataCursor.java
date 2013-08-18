package com.v2soft.androidcursorsample;

import java.util.List;

import android.database.AbstractCursor;

/**
 * 
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 */
public class TestDataCursor extends AbstractCursor {
    private static final String[] sColumns = new String[]{"_id",TestData.FIELD_INT, TestData.FIELD_BOOLEAN,
        TestData.FIELD_STRING, TestData.FIELD_FLOAT, TestData.FIELD_DOUBLE};
    private List<TestData> mData;
    public TestDataCursor(List<TestData> data) {
        mData = data;
    }
    @Override
    public String[] getColumnNames() {
        // TODO Auto-generated method stub
        return sColumns;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public double getDouble(int column) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getFloat(int column) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getInt(int column) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getLong(int column) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public short getShort(int column) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getString(int column) {
        switch(column){
        case 0:
            if((this.mPos < 0) || (this.mPos >= mData.size())) {return null;}
            else {return String.valueOf(this.mPos);}
        case 1:
            return String.valueOf(mData.get(this.mPos).getIntValue());
        case 2:
            return String.valueOf(mData.get(this.mPos).isBooleanValue());
        case 3:
            return String.valueOf(mData.get(this.mPos).getStringValue());
        case 4:
            return String.valueOf(mData.get(this.mPos).getFloatValue());
        case 5:
            return String.valueOf(mData.get(this.mPos).getDoubleValue());
        default: return null;
        }
    }

    @Override
    public boolean isNull(int column) {
        return false;
    }

}
