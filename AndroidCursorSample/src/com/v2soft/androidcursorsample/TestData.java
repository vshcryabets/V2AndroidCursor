package com.v2soft.androidcursorsample;

import com.v2soft.androidcursor.CursorDataAnnotation;

/**
 * 
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 */
public class TestData {
    public static final String FIELD_INT = "int";
    public static final String FIELD_BOOLEAN = "boolean";
    public static final String FIELD_STRING = "string";
    public static final String FIELD_FLOAT = "float";
    public static final String FIELD_DOUBLE = "double";
    public static final String FIELD_SHORT = "short";
    public static final String FIELD_LONG = "long";

    @CursorDataAnnotation(columnName=FIELD_INT)
    private int mIntValue;
    @CursorDataAnnotation(columnName=FIELD_BOOLEAN)
    private boolean mBooleanValue;
    @CursorDataAnnotation(columnName=FIELD_STRING)
    private String mStringValue;
    @CursorDataAnnotation(columnName=FIELD_FLOAT)
    private float mFloatValue;
    @CursorDataAnnotation(columnName=FIELD_DOUBLE)
    private double mDoubleValue;

    public int getIntValue() {
        return mIntValue;
    }
    public void setIntValue(int mIntValue) {
        this.mIntValue = mIntValue;
    }
    public boolean isBooleanValue() {
        return mBooleanValue;
    }
    public void setBooleanValue(boolean mBooleanValue) {
        this.mBooleanValue = mBooleanValue;
    }
    public String getStringValue() {
        return mStringValue;
    }
    public void setStringValue(String mStringValue) {
        this.mStringValue = mStringValue;
    }
    public float getFloatValue() {
        return mFloatValue;
    }
    public void setFloatValue(float mFloatValue) {
        this.mFloatValue = mFloatValue;
    }
    public double getDoubleValue() {
        return mDoubleValue;
    }
    public void setDoubleValue(double mDoubleValue) {
        this.mDoubleValue = mDoubleValue;
    }
    @Override
    public boolean equals(Object o) {
        if ( o == null ) {
            return false;
        }
        if( o instanceof TestData ) {
            TestData item = (TestData) o;
            return mBooleanValue == item.mBooleanValue &&
                    mIntValue == item.mIntValue &&
                    mStringValue.equals(item.mStringValue) &&
                    Math.abs(mDoubleValue-item.mDoubleValue)< 0.01 &&
                    Math.abs(mFloatValue- item.mFloatValue) < 0.01;
        }
        // TODO Auto-generated method stub
        return false;
    }
}
