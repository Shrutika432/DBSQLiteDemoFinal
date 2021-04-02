package com.example.dbmysqli;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button  b1,b2,b3,b4;
    EditText et1,et2,et3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        b1=findViewById(R.id.btnView);
        b2=findViewById(R.id.btnins);
        b3=findViewById(R.id.btnupdate);
        b4=findViewById(R.id.btnDel);
        //insert method
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1=et1.getText().toString();
                String username1=et2.getText().toString();
                String password1=et3.getText().toString();
                if(name1.equals("") || username1.equals("") || password1.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean i=dbHelper.insert(name1,username1,password1);
                    if(i==true){
                        Toast.makeText(MainActivity.this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Record not inserted", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //view method
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c= dbHelper.getInfo();
                if(c.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Record Found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer sb= new StringBuffer();
                while (c.moveToNext()){
                    sb.append("Name :" + c.getString(1)+"\n");
                    sb.append("username :" + c.getString(2)+"\n");
                    sb.append("password :" + c.getString(3)+"\n\n\n");

                    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Sighup user dada");
                    builder.setMessage(sb.toString());
                }
            }
        });
        //delete
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname1= et2.getText().toString();
                Boolean i= dbHelper.delete(uname1);
                if(i==true)
                    Toast.makeText(MainActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Record not deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}