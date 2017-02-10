package kgecproject.practice.demo.calendardemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pallab on 8/2/17.
 */

public class MySQLiteAdapter {
    Context context;
    MySQLiteOpenHelper mySQLiteOpenHelper;

    public MySQLiteAdapter(Context context){
        this.context = context;
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }

    public long InsertRecord(String name, String password) {
        //insert into Usr_table(Usr_Name,Usr_Password) values('amit','amit123');
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(mySQLiteOpenHelper.NAME, name);
        contentValues.put(mySQLiteOpenHelper.PASSWORD, password);
        long id=db.insert(mySQLiteOpenHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String DisplayAllRecord() {
        //select _id,Usr_Name,Usr_Password from Usr_table;
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        String[] columns={MySQLiteOpenHelper.UID,MySQLiteOpenHelper.NAME,MySQLiteOpenHelper.PASSWORD};
        Cursor cursor=db.query(MySQLiteOpenHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(MySQLiteOpenHelper.UID);
            int index2=cursor.getColumnIndex(MySQLiteOpenHelper.NAME);
            int index3=cursor.getColumnIndex(MySQLiteOpenHelper.PASSWORD);
            int cid=cursor.getInt(index1);
            String name=cursor.getString(index2);
            String password=cursor.getString(index3);
            buffer.append(cid+" "+name+" "+password+"\n");
        }
        return buffer.toString();
    }

    public String SearchOneArgRecord(String usrname) {
        //select _id,Usr_Name,Usr_Password from Usr_table where Usr_Name='amit' and Usr_Password='abc123';
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        String[] columns={MySQLiteOpenHelper.UID,MySQLiteOpenHelper.NAME,MySQLiteOpenHelper.PASSWORD};
        Cursor cursor=db.query(MySQLiteOpenHelper.TABLE_NAME, columns, MySQLiteOpenHelper.NAME+" = '"+usrname+"'", null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(MySQLiteOpenHelper.UID);
            int index2=cursor.getColumnIndex(MySQLiteOpenHelper.NAME);
            int index3=cursor.getColumnIndex(MySQLiteOpenHelper.PASSWORD);
            int cid=cursor.getInt(index1);
            String name=cursor.getString(index2);
            String password=cursor.getString(index3);
            buffer.append(cid+" "+name+" "+password+"\n");
        }
        return buffer.toString();
    }

    public int DeleteOneArgRecord(String usrname) {
        //delete from Usr_table where Usr_Name='amit'
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        int count=db.delete(MySQLiteOpenHelper.TABLE_NAME, MySQLiteOpenHelper.NAME+" = '"+usrname+"'", null);
        return count;
    }

    public int DeleteVoidArgRecord(String usrname) {
        ////delete from Usr_table where Usr_Name=?
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        String[] arguments={usrname};
        int count=db.delete(MySQLiteOpenHelper.TABLE_NAME, MySQLiteOpenHelper.NAME+" = ? ", arguments);

        return count;
    }

    public String SearchVoidArgRecord(String usrname,String usrpassword) {// checks both username and password
        //select _id,Usr_Name,Usr_Password from Usr_table where Usr_Name='amit';
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        String[] columns={MySQLiteOpenHelper.UID,MySQLiteOpenHelper.NAME,MySQLiteOpenHelper.PASSWORD};
        String[] selectionArgs={usrname,usrpassword};
        Cursor cursor=db.query(MySQLiteOpenHelper.TABLE_NAME, columns, MySQLiteOpenHelper.NAME+" = ? and "+MySQLiteOpenHelper.PASSWORD + "= ?"
                , selectionArgs, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(MySQLiteOpenHelper.UID);
            int index2=cursor.getColumnIndex(MySQLiteOpenHelper.NAME);
            int index3=cursor.getColumnIndex(MySQLiteOpenHelper.PASSWORD);
            int cid=cursor.getInt(index1);
            String name=cursor.getString(index2);
            String password=cursor.getString(index3);
            buffer.append(cid+" "+name+" "+password+"\n");
        }
        return buffer.toString();
    }

    public int UpdateOneArgRecord(String usrname,String newpassword) {
        //update Usr_table set Usr_Password='amit123456' where Usr_Name='amit'
        // MyToastMessage.myMessage(context, "Executing UpdateOneArgRecord");
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MySQLiteOpenHelper.PASSWORD, newpassword);
        int count=db.update(MySQLiteOpenHelper.TABLE_NAME, contentValues, MySQLiteOpenHelper.NAME+" = '"+usrname+"'" , null);

        return count;
    }

    public int UpdateVoidArgRecord(String usrname,String newpassword) {
        //update Usr_table set Usr_Password=? where Usr_Name=?
        // MyToastMessage.myMessage(context, "Executing UpdateVoidArgRecord");
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MySQLiteOpenHelper.PASSWORD, newpassword);
        String[] arguments={usrname};
        int count=db.update(MySQLiteOpenHelper.TABLE_NAME, contentValues, MySQLiteOpenHelper.NAME+" = ? ", arguments);

        return count;
    }




    public class MySQLiteOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "users.db";
        private static final String TABLE_NAME = "usr_table";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "Usr_Name";
        private static final String PASSWORD = "Usr_Password";

        //		private static final String AGE="Usr_Age";
        //		private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255),"+AGE+" INTEGER);";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME +
                " VARCHAR(255)," + PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        private Context context;

        public MySQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            // MyToastMessage.myMessage(context, "Constructor called...");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                // e.printStackTrace();
                // MyToastMessage.myMessage(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                // MyToastMessage.myMessage(context, "onUpgrade called...");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                //e.printStackTrace();
                // MyToastMessage.myMessage(context, "" + e);
                Log.d("TEST", "" + e);
            }
        }
    }
}
