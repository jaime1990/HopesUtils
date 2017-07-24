package com.itheima.sqlite.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.itheima.sqlite.dao.MyOpenHelper;

public class SQLiteProvider extends ContentProvider {
	private MyOpenHelper helper;
	private UriMatcher matcher;
	private static final int PERSON_ID = 0;
	private static final int PERSON = 1;
	private static final int USER = 2;
	
	@Override
	public boolean onCreate() {		// ������ʱ���Զ�ִ��. ��һ�ΰ�װ���ֻ���ʱ����. �ֻ��������һ�α�����ʱ����.
		System.out.println("onCreate");
		helper = new MyOpenHelper(getContext());
		matcher = new UriMatcher(UriMatcher.NO_MATCH);					// ����һ��Uriƥ����, ����ʶ�����Uri, �ֱ����
		matcher.addURI("com.itheima.SQLiteProvider", "person", PERSON);	// ��ƥ�������һ��Uri, �Ա�����ɾ�Ĳ���ʶ��
		matcher.addURI("com.itheima.SQLiteProvider", "user", USER);
		matcher.addURI("com.itheima.SQLiteProvider", "person/#", PERSON_ID);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = helper.getReadableDatabase();
		switch (matcher.match(uri)) {
			case PERSON_ID:
				selection = "id=" + ContentUris.parseId(uri);	// ��ȡUri�е�id, ��Ϊ��ѯ����
			case PERSON:
				return db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
			case USER:
				return db.query("user", projection, selection, selectionArgs, null, null, sortOrder);
			default:
				throw new IllegalArgumentException("����ʶ���Uri: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = helper.getReadableDatabase();
		switch (matcher.match(uri)) {
			case PERSON:
				long id = db.insert("person", "id", values);
				db.close();
				return ContentUris.withAppendedId(uri, id);		// ��idƴ��Uri����
			default:
				throw new IllegalArgumentException("����ʶ���Uri: " + uri);
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getReadableDatabase();
		switch (matcher.match(uri)) {
			case PERSON_ID:
				selection = "id=" + ContentUris.parseId(uri);
			case PERSON:
				int count = db.delete("person", selection, selectionArgs);
				db.close();
				return count;
			default:
				throw new IllegalArgumentException("����ʶ���Uri: " + uri);
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getReadableDatabase();
		switch (matcher.match(uri)) {
			case PERSON_ID:
				selection = "id=" + ContentUris.parseId(uri);
			case PERSON:
				int count = db.update("person", values, selection, selectionArgs);
				db.close();
				return count;
			default:
				throw new IllegalArgumentException("����ʶ���Uri: " + uri);
		}
	}
	
	@Override
	public String getType(Uri uri) {
		switch (matcher.match(uri)) {
			case PERSON_ID:
				return "vnd.android.cursor.item/person";	// mimetype, ����person
			case PERSON:
				return "vnd.android.cursor.dir/person";		// mimetype, ����person
			default:
				throw new IllegalArgumentException("����ʶ���Uri: " + uri);
		}
	}

}
