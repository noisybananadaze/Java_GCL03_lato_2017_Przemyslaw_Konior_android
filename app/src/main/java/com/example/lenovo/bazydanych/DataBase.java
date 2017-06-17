package com.example.lenovo.bazydanych;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DataBase extends SQLiteOpenHelper {

    private static final int DATEBASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_MAIL = "email";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;
    static final String id="0";

    public DataBase(Context context){
        super(context, DATABASE_NAME,null,DATEBASE_VERSION);
    }



    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null autoincrement , "+
            "name text not null, email text not null, pass text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS contacts";
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertContact(Contact contact){

        db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        //Cursor cursor = db.rawQuery(query,null);
        //cursor.moveToLast();
        //int count = cursor.getCount();
        //count++;

        //int count = Integer.parseInt(id);
        //count++;
        //String c = Integer.toString(count);


        //values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,contact.getName() );
        values.put(COLUMN_MAIL,contact.getEmail() );
        values.put(COLUMN_PASS,contact.getPassword() );

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public String searchPass(String name, String pass){

        db = this.getReadableDatabase();
        //String query = "select * from "+TABLE_NAME;
        //Cursor cursor = db.rawQuery(query,null);
        Cursor cursor = db.rawQuery("select * from contacts where name=? and pass=?", new String[]{name,pass});

        String a,b = "not found";

        if(cursor.moveToFirst()){

            do{
                a= cursor.getString(1);
                //b=cursor.getString(1);

                if(a.equals(name)){
                    b=cursor.getString(3);
                    Log.d("password", cursor.getString(3));
                    break;
                }

            }while(cursor.moveToNext());

        }
        return b;
    }

    public Cursor giveAllRows(){

        String[] rows = {"id","name","email","pass"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,rows,null,null,null,null,null);
        return cursor;

    }

    public void deleteContatct(String id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {""+id};
        db.delete(TABLE_NAME,"id=?",args);


    }

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,null,null);

    }

}

