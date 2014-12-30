package group.ripple.dhamaschool;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseManager extends SQLiteOpenHelper {

	private static String DB_NAME = "dhamaschool.sqlite";
	public static Context mContext;
	private SQLiteDatabase db;
	private static DatabaseManager helper;

	public static synchronized DatabaseManager getInstance(Context context) {
		if (helper == null) {
			helper = new DatabaseManager(context);
		}

		return helper;
	}

	public DatabaseManager(Context context) {
		super(context, DB_NAME, null, 1);
		DatabaseManager.mContext = context;
		try {
			boolean dbexist = checkdatabase();
			if (dbexist) {
				// System.out.println("Database exists");
				opendatabase();
			} else {
				System.out.println("Database doesn't exist");
				createdatabase();
				System.out.println(" Database Copied");
				opendatabase();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void createdatabase() throws IOException {
		boolean dbexist = checkdatabase();
		if (dbexist) {
			System.out.println(" Database exists.");
		} else {
			this.getReadableDatabase();
			try {
				System.out.println(" Database Copying");
				copydatabase();
			} catch (IOException e) {
				// throw new Error("Error copying database");
				e.printStackTrace();
			}
		}
	}

	private boolean checkdatabase() {
		boolean checkdb = false;
		try {
			String myPath = mContext.getFilesDir().getPath() + DB_NAME;
			File dbfile = new File(myPath);
			checkdb = dbfile.exists();
		} catch (SQLiteException e) {
			e.printStackTrace();
			System.out.println("Database doesn't exist");
		}

		return checkdb;
	}

	private void copydatabase() throws IOException {

		InputStream myinput = mContext.getAssets().open(DB_NAME);
		String outfilename = mContext.getFilesDir().getPath() + DB_NAME;
		System.out.println(outfilename);
		OutputStream myoutput = new FileOutputStream(outfilename);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myinput.read(buffer)) > 0) {
			myoutput.write(buffer, 0, length);
		}
		myoutput.flush();
		myoutput.close();
		myinput.close();

	}

	public void opendatabase() throws SQLException {
		// Open the database
		String mypath = mContext.getFilesDir().getPath() + DB_NAME;
		db = SQLiteDatabase.openDatabase(mypath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	public SQLiteDatabase getDB() {
		return db;
	}

	public boolean CheckIfExist(String table, String field, String value) {
		String query = "SELECT * FROM " + table + " WHERE " + field + " = '"
				+ value + "'";

		catchLog("CheckQuery ==> " + query);

		Cursor c = this.db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				if (c.getInt(0) != 0)
					return true;
				else
					return false;
			} while (c.moveToNext());
		} else {
			return false;
		}
	}

	public boolean CheckIfExist2(String table, String field, String value) {

		String query = "SELECT * FROM " + table + " WHERE " + field + " = '"
				+ value + "'";

		catchLog("CheckQuery ==> " + query);

		Cursor c = this.db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				if (c.getString(0).equalsIgnoreCase(value))
					return true;
				else
					return false;
			} while (c.moveToNext());
		} else {
			return false;
		}
	}

	public boolean CheckIfExist(String table, String firstField,
			String firstValue, String secondField, String secondValue) {

		String query = "SELECT * FROM " + table + " WHERE " + firstField
				+ " = '" + firstValue + "'" + " AND " + secondField + " = '"
				+ secondValue + "'";

		catchLog("CheckQuery ==> " + query);

		Cursor c = this.db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				if (c.getString(1).equalsIgnoreCase(firstValue)
						&& c.getString(2).equalsIgnoreCase(secondValue))
					return true;
				else
					return false;
			} while (c.moveToNext());
		} else {
			return false;
		}
	}

	public boolean CheckIfExist(String table, String field, int value) {
		return CheckIfExist(table, field, value + "");
	}

	public void deleteDatabase() throws IOException {
		try {
			String myPath = mContext.getFilesDir().getPath() + DB_NAME;
			File dbfile = new File(myPath);
			if (dbfile.exists())
				dbfile.delete();
		} catch (SQLException e) {

		}
	}

	private void catchLog(String log) {
		Log.i(getClass().getSimpleName(), log);
	}

	public synchronized void close() {
		if (db != null) {
			db.close();
		}
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}