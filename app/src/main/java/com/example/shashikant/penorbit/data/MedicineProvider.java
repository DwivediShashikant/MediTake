package com.example.shashikant.penorbit.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.example.shashikant.penorbit.data.MedicineContract.MedicineEntry;
import android.support.annotation.Nullable;
/**
 * Created by Shashikant on 7/5/2017.
 */

public class MedicineProvider extends ContentProvider {
    public MedicineDbHelper mDbHelper;
    public static final int MEDICINES = 100;
    public static final int MEDICINE_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI( MedicineContract.CONTENT_AUTHORITY, MedicineContract.PATH_MEDICINE, MEDICINES);
        sUriMatcher.addURI( MedicineContract.CONTENT_AUTHORITY, MedicineContract.PATH_MEDICINE+"/#",MEDICINE_ID);
    }
    @Override
    public boolean onCreate() {
        mDbHelper = new MedicineDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch(match){
            case MEDICINES:
                return inserMedicine(uri, values);
            default:
                try {
                    throw new IllegalAccessException(" insertion is not supported for this " +uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
    public Uri inserMedicine( Uri uri, ContentValues values){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long medId = db.insert( MedicineEntry.TABLE_NAME, null, values);
        return ContentUris.withAppendedId(uri,medId);
    }
}
