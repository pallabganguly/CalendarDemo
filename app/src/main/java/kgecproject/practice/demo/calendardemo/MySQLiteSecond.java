package kgecproject.practice.demo.calendardemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pallab on 10/2/17.
 */

public class MySQLiteSecond {
    Context context;
    MySQLiteSecond.MySQLiteOpenHelper mySQLiteOpenHelper;

    public MySQLiteSecond(Context context){
        this.context = context;
        mySQLiteOpenHelper = new MySQLiteSecond.MySQLiteOpenHelper(context);
    }

    public long InsertRecord(String date, int educ, int trans, int enter, int food) {
        //insert into Usr_table(Usr_Name,Usr_Password) values('amit','amit123');
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(mySQLiteOpenHelper.DATE, date);
        contentValues.put(mySQLiteOpenHelper.EDUCN, educ);
        contentValues.put(mySQLiteOpenHelper.TRANS, trans);
        contentValues.put(mySQLiteOpenHelper.ENTER, enter);
        contentValues.put(mySQLiteOpenHelper.FOOD, food);

        long id=db.insert(mySQLiteOpenHelper.TABLE_NAME, null, contentValues);
        return id;
    }

//    public String DisplayAllRecord() {
//        //select _id,Usr_Name,Usr_Password from Usr_table;
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        String[] columns={MySQLiteSecond.MySQLiteOpenHelper.UID, MySQLiteSecond.MySQLiteOpenHelper.NAME, MySQLiteSecond.MySQLiteOpenHelper.PASSWORD};
//        Cursor cursor=db.query(MySQLiteSecond.MySQLiteOpenHelper.TABLE_NAME, columns, null, null, null, null, null);
//        StringBuffer buffer=new StringBuffer();
//        while(cursor.moveToNext()) {
//            int index1=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.UID);
//            int index2=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.NAME);
//            int index3=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.PASSWORD);
//            int cid=cursor.getInt(index1);
//            String name=cursor.getString(index2);
//            String password=cursor.getString(index3);
//            buffer.append(cid+" "+name+" "+password+"\n");
//        }
//        return buffer.toString();
//    }

    public int[] SearchOneArgRecord(String date) {

        // Searches db for a record using 'date' as key
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        String[] columns={MySQLiteSecond.MySQLiteOpenHelper.UID, MySQLiteSecond.MySQLiteOpenHelper.EDUCN, MySQLiteSecond.MySQLiteOpenHelper.TRANS, MySQLiteSecond.MySQLiteOpenHelper.ENTER, MySQLiteSecond.MySQLiteOpenHelper.FOOD};
        Cursor cursor=db.query(MySQLiteSecond.MySQLiteOpenHelper.TABLE_NAME, columns, MySQLiteSecond.MySQLiteOpenHelper.DATE+" = '"+date+"'", null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        int values[] = new int[5];
        while(cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.UID);
            int index2 = cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.EDUCN);
            int index3 = cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.TRANS);
            int index4 = cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.ENTER);
            int index5 = cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.FOOD);
            values[0] = cursor.getInt(index1);
            values[1] = cursor.getInt(index2);
            values[2] = cursor.getInt(index3);
            values[3] = cursor.getInt(index4);
            values[4] = cursor.getInt(index5);
        }
        return values;
    }

//    public int DeleteOneArgRecord(String usrname) {
//        //delete from Usr_table where Usr_Name='amit'
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        int count=db.delete(MySQLiteSecond.MySQLiteOpenHelper.TABLE_NAME, MySQLiteSecond.MySQLiteOpenHelper.NAME+" = '"+usrname+"'", null);
//        return count;
//    }
//
//    public int DeleteVoidArgRecord(String usrname) {
//        ////delete from Usr_table where Usr_Name=?
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        String[] arguments={usrname};
//        int count=db.delete(MySQLiteSecond.MySQLiteOpenHelper.TABLE_NAME, MySQLiteSecond.MySQLiteOpenHelper.NAME+" = ? ", arguments);
//
//        return count;
//    }
//
//    public String SearchVoidArgRecord(String usrname,String usrpassword) {// checks both username and password
//        //select _id,Usr_Name,Usr_Password from Usr_table where Usr_Name='amit';
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        String[] columns={MySQLiteSecond.MySQLiteOpenHelper.UID, MySQLiteSecond.MySQLiteOpenHelper.NAME, MySQLiteSecond.MySQLiteOpenHelper.PASSWORD};
//        String[] selectionArgs={usrname,usrpassword};
//        Cursor cursor=db.query(MySQLiteSecond.MySQLiteOpenHelper.TABLE_NAME, columns, MySQLiteSecond.MySQLiteOpenHelper.NAME+" = ? and "+ MySQLiteSecond.MySQLiteOpenHelper.PASSWORD + "= ?"
//                , selectionArgs, null, null, null);
//        StringBuffer buffer=new StringBuffer();
//        while(cursor.moveToNext()) {
//            int index1=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.UID);
//            int index2=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.NAME);
//            int index3=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpenHelper.PASSWORD);
//            int cid=cursor.getInt(index1);
//            String name=cursor.getString(index2);
//            String password=cursor.getString(index3);
//            buffer.append(cid+" "+name+" "+password+"\n");
//        }
//        return buffer.toString();
//    }
//
//    public int UpdateOneArgRecord(String usrname,String newpassword) {
//        //update Usr_table set Usr_Password='amit123456' where Usr_Name='amit'
//        // MyToastMessage.myMessage(context, "Executing UpdateOneArgRecord");
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put(MySQLiteSecond.MySQLiteOpenHelper.PASSWORD, newpassword);
//        int count=db.update(MySQLiteSecond.MySQLiteOpenHelper.TABLE_NAME, contentValues, MySQLiteSecond.MySQLiteOpenHelper.NAME+" = '"+usrname+"'" , null);
//
//        return count;
//    }
//
//    public int UpdateVoidArgRecord(String usrname,String newpassword) {
//        //update Usr_table set Usr_Password=? where Usr_Name=?
//        // MyToastMessage.myMessage(context, "Executing UpdateVoidArgRecord");
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put(MySQLiteSecond.MySQLiteOpenHelper.PASSWORD, newpassword);
//        String[] arguments={usrname};
//        int count=db.update(MySQLiteSecond.MySQLiteOpenHelper.TABLE_NAME, contentValues, MySQLiteSecond.MySQLiteOpenHelper.NAME+" = ? ", arguments);
//
//        return count;
//    }




    public class MySQLiteOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "transaction.db";
        private static final String TABLE_NAME = "transaction_table";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
//        private static final String NAME = "Usr_Name";
//        private static final String PASSWORD = "Usr_Password";
        private static final String DATE = "date";
        private static final String EDUCN = "education";
        private static final String TRANS = "transport";
        private static final String ENTER = "entertainment";
        private static final String FOOD = "food";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE +
                " VARCHAR(255)," + EDUCN + "INTEGER "+ TRANS +" INTEGER "+ ENTER + " INTEGER "+ FOOD +" INTEGER "+");";
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
