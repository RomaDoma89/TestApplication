package testapp.zahorui.com.testapplication;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Roman on 29.11.2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    public static final String DATABASE_NAME = "users_database.db";
    public static final String DATABASE_TABLE = "users_table";
    public static final String FIRST_NAME_COLUMN = "first_name_col";
    public static final String LAST_NAME_COLUMN = "last_name_col";

    private static final String DATABASE_CREATE_SCRIPT = "CREATE TABLE "
            + DATABASE_TABLE + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + FIRST_NAME_COLUMN + " TEXT, "
            + LAST_NAME_COLUMN + " TEXT);";
    //    "/mnt/sdcard/"+
    private static final int DATABASE_VERSION = 1;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Remove old table and add a new one.
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // Create new table
        onCreate(db);
    }
}
