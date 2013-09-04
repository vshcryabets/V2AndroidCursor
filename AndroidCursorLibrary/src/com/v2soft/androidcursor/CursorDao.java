/*
 * Copyright (C) 2013 V.Shcryabets (vshcryabets@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.v2soft.androidcursor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * 
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 * @param <T> data type
 */
public class CursorDao<T> {
    /**
     * Construction factory.
     * @author V.Shcryabets<vshcryabets@gmail.com>
     *
     * @param <T>
     */
    public interface Factory<T> {
        T newItem();
    }
    private Class<T> mTypeClass;
    private Factory<T> mFactory;
    /**
     * 
     * @param typeClass
     * @param factory construction factory implementation.
     */
    public CursorDao(Class<T> typeClass, Factory<T> factory) {
        mTypeClass = typeClass;
        mFactory = factory;
    }
    /**
     * Get items list from cursor
     * @param cursor
     * @return
     */
    public List<T> getListFromCursor(Cursor cursor) {
        List<T> result = new ArrayList<T>();
        while (cursor.moveToNext()) {
            final T item = mFactory.newItem();
            // process fields
            final Field[] fields = mTypeClass.getDeclaredFields();
            try {
                for (int i = 0; i < fields.length; i++ ) {
                    final Field field = fields[i];
                    if ( field.isAnnotationPresent(CursorDataAnnotation.class)) {
                        final CursorDataAnnotation annotation = 
                                field.getAnnotation(CursorDataAnnotation.class);
                        String columnName = annotation.columnName();
                        String value = cursor.getString(cursor.getColumnIndex(columnName));
                        //cursor.getColumnIndex(columnName)
                        //cursor.getType(columnIndex)
                        setFieldValue(field, value, item);
                    }
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            result.add(item);
        }
        return result;
    }
    /**
     * Initialize object from content values.
     * @param values
     * @return
     */
    public T itemFromContentValues(ContentValues values) {
        final T item = mFactory.newItem();
        // process fields
        final Field[] fields = mTypeClass.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++ ) {
                final Field field = fields[i];
                if ( field.isAnnotationPresent(CursorDataAnnotation.class)) {
                    final CursorDataAnnotation annotation = 
                            field.getAnnotation(CursorDataAnnotation.class);
                    String columnName = annotation.columnName();
                    setFieldValueFromContentValues(field, columnName, item, values);
                }
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return item;
    }
    private void setFieldValue(Field field, String value, T item) throws IllegalArgumentException, IllegalAccessException {
        Class<?> dataType = field.getType();
        if ( dataType.equals(String.class)) {
            field.setAccessible(true);
            field.set(item, value);
        } else if ( dataType.equals(int.class)) {
            field.setAccessible(true);
            field.set(item, Integer.parseInt(value));
        } else if ( dataType.equals(boolean.class)) {
            field.setAccessible(true);
            field.set(item, Boolean.parseBoolean(value));
        } else if ( dataType.equals(double.class)) {
            field.setAccessible(true);
            field.set(item, Double.parseDouble(value));
        } else if ( dataType.equals(float.class)) {
            field.setAccessible(true);
            field.set(item, Float.parseFloat(value));
        }
    }
    private void setFieldValueFromContentValues(Field field, String columnName, T item, ContentValues values) throws IllegalArgumentException, IllegalAccessException {
        Class<?> dataType = field.getType();
        if ( dataType.equals(String.class)) {
            field.setAccessible(true);
            field.set(item, values.getAsString(columnName));
        } else if ( dataType.equals(int.class)) {
            field.setAccessible(true);
            field.set(item, values.getAsInteger(columnName));
        } else if ( dataType.equals(boolean.class)) {
            field.setAccessible(true);
            field.set(item, values.getAsBoolean(columnName));
        } else if ( dataType.equals(double.class)) {
            field.setAccessible(true);
            field.set(item, values.getAsDouble(columnName));
        } else if ( dataType.equals(float.class)) {
            field.setAccessible(true);
            field.set(item, values.getAsFloat(columnName));
        }
    }
    /**
     * Convert item to ContentValue object.
     * @param item
     * @return
     */
    public ContentValues itemToContentValues(T item) {
        ContentValues result = new ContentValues();
        // process fields
        final Field[] fields = mTypeClass.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++ ) {
                final Field field = fields[i];
                if ( field.isAnnotationPresent(CursorDataAnnotation.class)) {
                    final CursorDataAnnotation annotation = 
                            field.getAnnotation(CursorDataAnnotation.class);
                    String columnName = annotation.columnName();
                    field.setAccessible(true);
                    Object fieldValue = field.get(item);
                    if ( fieldValue == null ) {
//                        result.putNull(columnName);
                    } else {
                        String value = fieldValue.toString();
                        result.put(columnName, value);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
