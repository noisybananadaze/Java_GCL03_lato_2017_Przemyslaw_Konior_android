package com.example.lenovo.bazydanych;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getIntent().getStringExtra("Username");

        TextView tv= (TextView)findViewById(R.id.textViewUsername);
        tv.setText("Welcome "+username);


       // db.deleteAll();
        show();
    }

    public void show(){

        TextView tvID= (TextView)findViewById(R.id.tvID);
        TextView tvName= (TextView)findViewById(R.id.tvName);
        TextView tvMail= (TextView)findViewById(R.id.tvMail);
        TextView tvPass= (TextView)findViewById(R.id.tvPass);


        db.insertContact(new Contact("q","q","q"));
        db.insertContact(new Contact("a","a","a"));
        db.insertContact(new Contact("b","b","b"));
        db.insertContact(new Contact("c","c","c"));
        db.insertContact(new Contact("d","d","d"));
        db.insertContact(new Contact("e","e","e"));
        db.insertContact(new Contact("f","f","f"));
        db.insertContact(new Contact("g","g","g"));
        db.insertContact(new Contact("h","h","h"));
        db.insertContact(new Contact("dasd","ads@sad.pl","4rwr"));
        db.insertContact(new Contact("alex","a@2.com","333"));
        db.insertContact(new Contact("adasd","dasdas","dsadawe"));


        String id, name, email, pass;

        Cursor cursor = db.giveAllRows();
        while(cursor.moveToNext()){
            id = cursor.getString(0);
            name = cursor.getString(1);
            email = cursor.getString(2);
            pass = cursor.getString(3);
            tvID.setText(tvID.getText()+"\n"+id);
            tvName.setText(tvName.getText()+"\n"+name);
            tvMail.setText(tvMail.getText()+"\n"+email);
            tvPass.setText(tvPass.getText()+"\n"+pass);


        }


    }

    public void onDeleteButtonClick(View view) {
        Log.d("delete", "clicked");
        EditText et = (EditText) findViewById(R.id.eTtoDel);
        String id = et.getText().toString();
        Log.d("delete", "id readed: "+id);
        db.deleteContatct(id);

        Toast newToast = Toast.makeText(MainActivity.this, "Member with "+id+" ID was deleted, refresh activity", Toast.LENGTH_LONG);
        newToast.show();

    }


}
