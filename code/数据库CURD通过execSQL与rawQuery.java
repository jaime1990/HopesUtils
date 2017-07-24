package com.itheima.sqlite.dao;

import java.util.ArrayList;
import java.util.List;

import com.itheima.sqlite.bean.Person;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDaoClassic {
	private MyOpenHelper helper;

	public PersonDaoClassic(Context context) {
		helper = new MyOpenHelper(context);
	}

	public void insert(Person p) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("INSERT INTO person(name, balance) VALUES(?,?)", new Object[] { p.getName(), p.getBalance() });
		db.close();
	}

	public void delete(int id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("DELETE FROM person WHERE id=?", new Object[] { id });
		db.close();
	}

	public void update(Person p) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("UPDATE person SET name=?, balance=? WHERE id=?", new Object[]{p.getName(), p.getBalance(), p.getId()});
		db.close();
	}

	public Person query(int id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT name, balance FROM person WHERE id=?", new String[] { id + "" });	// ��ѯ, �õ��α�(�����)	
		Person p = null;
		if (c.moveToNext()) {	// ���α�����ƶ�һλ, �ж��Ƿ��������
			String name = c.getString(c.getColumnIndex("name"));	// �ȸ���������ȡ����, �ٸ���������ȡ����
			int balance = c.getInt(1);								// ֱ�Ӹ���������ȡ����
			p = new Person(id, name, balance);
		}
		c.close();
		db.close();
		return p;
	}
	
	public List<Person> queryAll() {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT id, name, balance FROM person", null);
		while (c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex("id"));
			String name = c.getString(c.getColumnIndex("name"));
			int balance = c.getInt(c.getColumnIndex("balance"));	
			persons.add(new Person(id, name, balance));
		}
		c.close();
		db.close();
		return persons;
	}
	
	public List<Person> queryPage(int pageNum, int pageSize) {
		int offset = (pageNum - 1) * pageSize;
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT id, name, balance FROM person LIMIT ?,?", new String[] {offset + "", pageSize + ""});
		while (c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex("id"));
			String name = c.getString(c.getColumnIndex("name"));
			int balance = c.getInt(c.getColumnIndex("balance"));	
			persons.add(new Person(id, name, balance));
		}
		c.close();
		db.close();
		return persons;
	}
	
	public void remit(int from, int to, int amount) {
		SQLiteDatabase db = helper.getWritableDatabase();
		try {
			db.beginTransaction(); 			// ��������
			db.execSQL("UPDATE person SET balance=balance+? WHERE id=?", new Object[] { amount, to });
			db.execSQL("UPDATE person SET balance=balance-? WHERE id=?", new Object[] { amount, from });
			db.setTransactionSuccessful(); 	// ��������ɹ�
		} finally {
			db.endTransaction();			// ��������, �����һ�γɹ���֮ǰ���ύ
			db.close();
		}
	}

}
