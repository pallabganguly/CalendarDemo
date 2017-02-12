package kgecproject.practice.demo.calendardemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pallab on 10/2/17.
 */

public class MySQLiteSecond {
    Context context;
    MySQLiteOpHelperTwo newSQLiteOpenHelper;

    public MySQLiteSecond(Context context){
        this.context = context;
        newSQLiteOpenHelper = new MySQLiteOpHelperTwo(context);
    }

    public long InsertRecord(String date, int educ, int trans, int enter, int food) {
        //insert into Usr_table(Usr_Name,Usr_Password) values('amit','amit123');
        SQLiteDatabase db=newSQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(newSQLiteOpenHelper.DATE, date);
        contentValues.put(newSQLiteOpenHelper.EDUCN, educ);
        contentValues.put(newSQLiteOpenHelper.TRANS, trans);
        contentValues.put(newSQLiteOpenHelper.ENTER, enter);
        contentValues.put(newSQLiteOpenHelper.FOOD, food);

        long id=db.insert(newSQLiteOpenHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String DisplayAllRecord() {
        //select _id,Usr_Name,Usr_Password from Usr_table;
        SQLiteDatabase db=newSQLiteOpenHelper.getWritableDatabase();
        String coloumns[] = {MySQLiteOpHelperTwo.UID, MySQLiteOpHelperTwo.EDUCN, MySQLiteOpHelperTwo.TRANS, MySQLiteOpHelperTwo.ENTER, MySQLiteOpHelperTwo.FOOD};
        Cursor cursor = db.query(MySQLiteOpHelperTwo.TABLE_NAME, coloumns, null, null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MySQLiteOpHelperTwo.UID);
            int index2 = cursor.getColumnIndex(MySQLiteOpHelperTwo.EDUCN);
            int index3 = cursor.getColumnIndex(MySQLiteOpHelperTwo.TRANS);
            int index4 = cursor.getColumnIndex(MySQLiteOpHelperTwo.ENTER);
            int index5 = cursor.getColumnIndex(MySQLiteOpHelperTwo.FOOD);
            String cat1=cursor.getString(index2);
            String cat2=cursor.getString(index3);
            String cat3=cursor.getString(index4);
            String cat4=cursor.getString(index5);
            buffer.append(cat1+" "+cat2+" "+cat3+" "+cat4+" "+ "\n");
        }
        return buffer.toString();
    }

    public String DisplayByDate(String date) { // display record using date as key date, return string
        SQLiteDatabase db = newSQLiteOpenHelper.getWritableDatabase();
        String coloumns[] = {MySQLiteOpHelperTwo.UID, MySQLiteOpHelperTwo.EDUCN, MySQLiteOpHelperTwo.TRANS, MySQLiteOpHelperTwo.ENTER, MySQLiteOpHelperTwo.FOOD};
        Cursor cursor = db.query(MySQLiteOpHelperTwo.TABLE_NAME, coloumns, MySQLiteSecond.MySQLiteOpHelperTwo.DATE+" = '"+date+"'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MySQLiteOpHelperTwo.UID);
            int index2 = cursor.getColumnIndex(MySQLiteOpHelperTwo.EDUCN);
            int index3 = cursor.getColumnIndex(MySQLiteOpHelperTwo.TRANS);
            int index4 = cursor.getColumnIndex(MySQLiteOpHelperTwo.ENTER);
            int index5 = cursor.getColumnIndex(MySQLiteOpHelperTwo.FOOD);
            String cat1=cursor.getString(index2);
            String cat2=cursor.getString(index3);
            String cat3=cursor.getString(index4);
            String cat4=cursor.getString(index5);
            buffer.append(cat1+" "+cat2+" "+cat3+" "+cat4+" "+ "\n");
        }
        return buffer.toString();
    }

    public int[] SearchOneArgRecord(String date) { // same as above, but return array instead

        // Searches db for a record using 'date' as key
        SQLiteDatabase db=newSQLiteOpenHelper.getWritableDatabase();
        String[] columns={MySQLiteOpHelperTwo.UID, MySQLiteOpHelperTwo.EDUCN, MySQLiteOpHelperTwo.TRANS, MySQLiteOpHelperTwo.ENTER, MySQLiteOpHelperTwo.FOOD};
        Cursor cursor=db.query(MySQLiteOpHelperTwo.TABLE_NAME, columns, MySQLiteSecond.MySQLiteOpHelperTwo.DATE+" = '"+date+"'", null, null, null, null);
        int values[] = new int[5];
        while(cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MySQLiteOpHelperTwo.UID);
            int index2 = cursor.getColumnIndex(MySQLiteOpHelperTwo.EDUCN);
            int index3 = cursor.getColumnIndex(MySQLiteOpHelperTwo.TRANS);
            int index4 = cursor.getColumnIndex(MySQLiteOpHelperTwo.ENTER);
            int index5 = cursor.getColumnIndex(MySQLiteOpHelperTwo.FOOD);
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
//        SQLiteDatabase db=newSQLiteOpenHelper.getWritableDatabase();
//        int count=db.delete(MySQLiteSecond.MySQLiteOpHelperTwo.TABLE_NAME, MySQLiteSecond.MySQLiteOpHelperTwo.NAME+" = '"+usrname+"'", null);
//        return count;
//    }
//
//    public int DeleteVoidArgRecord(String usrname) {
//        ////delete from Usr_table where Usr_Name=?
//        SQLiteDatabase db=newSQLiteOpenHelper.getWritableDatabase();
//        String[] arguments={usrname};
//        int count=db.delete(MySQLiteSecond.MySQLiteOpHelperTwo.TABLE_NAME, MySQLiteSecond.MySQLiteOpHelperTwo.NAME+" = ? ", arguments);
//
//        return count;
//    }
//
//    public String SearchVoidArgRecord(String usrname,String usrpassword) {// checks both username and password
//        //select _id,Usr_Name,Usr_Password from Usr_table where Usr_Name='amit';
//        SQLiteDatabase db=newSQLiteOpenHelper.getWritableDatabase();
//        String[] columns={MySQLiteSecond.MySQLiteOpHelperTwo.UID, MySQLiteSecond.MySQLiteOpHelperTwo.NAME, MySQLiteSecond.MySQLiteOpHelperTwo.PASSWORD};
//        String[] selectionArgs={usrname,usrpassword};
//        Cursor cursor=db.query(MySQLiteSecond.MySQLiteOpHelperTwo.TABLE_NAME, columns, MySQLiteSecond.MySQLiteOpHelperTwo.NAME+" = ? and "+ MySQLiteSecond.MySQLiteOpHelperTwo.PASSWORD + "= ?"
//                , selectionArgs, null, null, null);
//        StringBuffer buffer=new StringBuffer();
//        while(cursor.moveToNext()) {
//            int index1=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpHelperTwo.UID);
//            int index2=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpHelperTwo.NAME);
//            int index3=cursor.getColumnIndex(MySQLiteSecond.MySQLiteOpHelperTwo.PASSWORD);
//            int cid=cursor.getInt(index1);
//            String name=cursor.getString(index2);
//            String password=cursor.getString(index3);
//            buffer.append(cid+" "+name+" "+password+"\n");
//        }
//        return buffer.toString();
//    }
//
    public int UpdateOneArgRecord(String date, int educ, int trans, int enter, int food) {  //update record by date
        //update Usr_table set Usr_Password='amit123456' where Usr_Name='amit'
        // MyToastMessage.myMessage(context, "Executing UpdateOneArgRecord");
        SQLiteDatabase db=newSQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MySQLiteOpHelperTwo.EDUCN, educ);
        contentValues.put(MySQLiteOpHelperTwo.TRANS, trans);
        contentValues.put(MySQLiteOpHelperTwo.ENTER, enter);
        contentValues.put(MySQLiteOpHelperTwo.FOOD, food);
        int count=db.update(MySQLiteOpHelperTwo.TABLE_NAME, contentValues, MySQLiteOpHelperTwo.DATE+" = '"+date+"'" , null);

        return count;
    }
//
//    public int UpdateVoidArgRecord(String usrname,String newpassword) {
//        //update Usr_table set Usr_Password=? where Usr_Name=?
//        // MyToastMessage.myMessage(context, "Executing UpdateVoidArgRecord");
//        SQLiteDatabase db=newSQLiteOpenHelper.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put(MySQLiteSecond.MySQLiteOpHelperTwo.PASSWORD, newpassword);
//        String[] arguments={usrname};
//        int count=db.update(MySQLiteSecond.MySQLiteOpHelperTwo.TABLE_NAME, contentValues, MySQLiteSecond.MySQLiteOpHelperTwo.NAME+" = ? ", arguments);
//
//        return count;
//    }




    public class MySQLiteOpHelperTwo extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "transaction.db";
        private static final String TABLE_NAME = "transaction_table";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String DATE = "date";
        private static final String EDUCN = "education";
        private static final String TRANS = "transport";
        private static final String ENTER = "entertainment";
        private static final String FOOD = "food";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE +
                " VARCHAR(255)," + EDUCN + " INTEGER,"+ TRANS +" INTEGER,"+ ENTER + " INTEGER,"+ FOOD +" INTEGER);";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        private Context context;

        public MySQLiteOpHelperTwo(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
//                Toast.makeText(context, "Table Created!", Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, CREATE_TABLE, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, e+"", Toast.LENGTH_SHORT).show();
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
