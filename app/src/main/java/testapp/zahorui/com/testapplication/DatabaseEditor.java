package testapp.zahorui.com.testapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Roman Zahorui on 29.11.2016.
 */

public class DatabaseEditor {

    private DatabaseHelper dbHelper;

    public DatabaseEditor(Context context) {
        dbHelper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, 1);
    }

    public void addUser(String firstName, String LastName) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FIRST_NAME_COLUMN, firstName);
        values.put(DatabaseHelper.LAST_NAME_COLUMN, LastName);
        database.insert(DatabaseHelper.DATABASE_TABLE, null, values);
    }

    public ArrayList<UserObj> getUsersFromDB(int start, int amount) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, new String[]{DatabaseHelper.FIRST_NAME_COLUMN,
                        DatabaseHelper.LAST_NAME_COLUMN},
                null, null,
                null, null, null);

        ArrayList<UserObj> users = new ArrayList<>();
        cursor.move(start);
        while (cursor.moveToNext() && amount != 0) {
            String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIRST_NAME_COLUMN));
            String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.LAST_NAME_COLUMN));
            users.add(new UserObj(firstName, lastName));
            amount--;
        }
        cursor.close();
        return users;
    }

    public boolean isEmpty() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, new String[]{DatabaseHelper.FIRST_NAME_COLUMN,
                        DatabaseHelper.LAST_NAME_COLUMN},
                null, null,
                null, null, null);
        if (cursor.getCount() > 0) return false;
        else return true;
    }
}
