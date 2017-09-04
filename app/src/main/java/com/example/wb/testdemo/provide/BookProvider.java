package com.example.wb.testdemo.provide;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Zhangchen on 2017/8/31.
 */

public class BookProvider extends ContentProvider{
    private Context context;
    private SQLiteDatabase mDb;

    private static final String TAG = "BookProvider";

    public static final String AUTHORITY = "com.android.provider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY,"book",BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY,"user",USER_URI_CODE);
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (sUriMatcher.match(uri)){
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TALBE_NAME;
                break;
        }
        return tableName;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG,"onCreate" + Thread.currentThread());
        context = getContext();

        //演示耗时的数据库创建操作
        mDb = new DbOpenHelper(context).getWritableDatabase();
        mDb.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        mDb.execSQL("delete from " + DbOpenHelper.USER_TALBE_NAME);
        mDb.execSQL("insert into book values(3,'Android');");
        mDb.execSQL("insert into book values(4,'Ios');");
        mDb.execSQL("insert into book values(5,'Html5');");
        mDb.execSQL("insert into user values(2,'Php',1);");
        mDb.execSQL("insert into user values(3,'c#',0);");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG,"query:" + Thread.currentThread());
        String tableName = getTableName(uri);
        if (tableName == null){
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        return mDb.query(tableName,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG,"getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG,"insert");
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        mDb.insert(table,null,values);
        context.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG,"delete");
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        int count = mDb.delete(table,selection,selectionArgs);
        if (count>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG,"update");
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        int row = mDb.update(table,values,selection,selectionArgs);
        if (row > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return row;
    }
}
