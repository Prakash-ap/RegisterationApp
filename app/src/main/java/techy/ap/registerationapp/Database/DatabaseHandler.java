package techy.ap.registerationapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import techy.ap.registerationapp.Modal.User;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Users.db";
    private static final int DATABASE_VERSION=2;

    private static final String TABLE_NAME="Users";

    private static final String KEY_ID="id";
    private static final String KEY_FIRSTNAME="fname";
    private static final String KEY_LASTNAME="lname";
    private static final String KEY_LOCATION="location";
    private static final String KEY_DOB="dob";
    private static final String KEY_GENDER="gender";

    private static final String CREATE_TABLE_USER="CREATE TABLE " + TABLE_NAME + "("+KEY_ID+ " INTEGER PRIMARY KEY," + KEY_FIRSTNAME + " TEXT,"
            + KEY_LASTNAME +" TEXT," + KEY_LOCATION +" TEXT,"
            + KEY_DOB +" TEXT,"
            + KEY_GENDER +" TEXT"+")";



    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public void insertdata(User user){

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_FIRSTNAME,user.getFname());
        contentValues.put(KEY_LASTNAME,user.getLname());
        contentValues.put(KEY_LOCATION,user.getLocation());
        contentValues.put(KEY_DOB,user.getDob());
        contentValues.put(KEY_GENDER,user.getGender());
        database.insert(TABLE_NAME,null,contentValues);
        database.close();
    }

    public ArrayList<User>getAllDocsDetail() {
        ArrayList<User> userArrayList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                user.setFname(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                user.setLname(cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
                user.setLocation(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
                user.setDob(cursor.getString(cursor.getColumnIndex(KEY_DOB)));
                user.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                userArrayList.add(user);
            } while (cursor.moveToNext());
        }
        db.close();
        return userArrayList;
    }


}
