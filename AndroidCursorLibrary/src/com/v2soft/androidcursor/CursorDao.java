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
    public CursorDao(Class<T> typeClass, Factory<T> factory, int qwe) {
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
                        Class<?> dataType = field.getType();
                        if ( dataType.equals(String.class)) {
                            field.setAccessible(true);
                            field.set(item, value);
                        } else if ( dataType.equals(int.class)) {
                            field.setAccessible(true);
                            field.set(item, Integer.parseInt(value));
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
            result.add(item);
        }
        return result;
    }
    /**
     * Initialize object from content values.
     * @param values
     * @return
     */
    public T itemFromContentValues2(ContentValues values) {
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
                    String value= values.getAsString(columnName);
                    //cursor.getColumnIndex(columnName)
                    //cursor.getType(columnIndex)
                    Class<?> dataType = field.getType();
                    if ( dataType.equals(String.class)) {
                        field.setAccessible(true);
                        field.set(item, value);
                    } else if ( dataType.equals(int.class)) {
                        field.setAccessible(true);
                        field.set(item, Integer.parseInt(value));
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
        return item;
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
                    String value= field.get(item).toString();
                    result.put(columnName, value);
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
