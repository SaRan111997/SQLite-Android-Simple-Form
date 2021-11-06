package com.example.demo_db;
//SaRan 27-10-21
//https://www.youtube.com/channel/UC13QfHb2Dyncn1fwuavXq9A
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.demo_db.util.Utils;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    //Initializing fields
    DatabaseHelper myDB;
    EditText edit_name, edit_emp, edit_add,edit_salary;
    Button addData, viewData,excel,gridview,back;
    String name, emp, add,gender,salary;
    SQLiteToExcel sqliteToExcel;

    //int salary;
    RadioGroup radioGroup;
    String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Backup/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        //
        File file = new File(directory_path);
        if (!file.exists()) {
            Log.v("File Created", String.valueOf(file.mkdirs()));
        }
        //Initialize Database
        myDB = new DatabaseHelper( this );

        //Initialize EditText
        edit_name = findViewById( R.id.txtbox_name );
        radioGroup = findViewById( R.id.radio_gro );
        edit_emp = findViewById( R.id.txtbox_emp );
        edit_add = findViewById( R.id.PostalAddress );
        edit_salary = findViewById( R.id.txtbox_salary );
        //Excel Permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        //Excel Permission


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                gender = checkedRadioButton.getText().toString();
                Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT).show();
            }
        });
        //Initialize Button
        addData = findViewById( R.id.Submit );
        viewData = findViewById( R.id.List );
        excel=findViewById( R.id.Excel );
        gridview=findViewById(R.id.List);
        back=findViewById(R.id.btnback);
        AddData();
        gridview();
       // export();






        excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Export SQLite DB as EXCEL FILE
                sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DatabaseHelper.DATABASE_NAME, directory_path);
                sqliteToExcel.exportAllTables("users.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Utils.showSnackBar(view, "Successfully Exported");
                    }

                    @Override
                    public void onError(Exception e) {
                        Utils.showSnackBar(view, e.getMessage());
                    }
                });
            }
        });
    }



    //Adding or inserting data to database
    public void AddData(){

        addData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = edit_name.getText().toString();
                emp = edit_emp.getText().toString();
                salary = edit_salary.getText().toString();
                add=  edit_add.getText().toString();

                boolean isInserted = myDB.instertData(name,emp, gender,salary,add);

                if(isInserted == true){
                    Toast.makeText( MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
//Next Activity
    public void gridview() {
        viewData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), GridViewActivity.class);
               // Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(myIntent);

            }
        });
    }














            //Method for creating AlertDialog box
            private void showMessage(String title, String message) {

                AlertDialog.Builder builder = new AlertDialog.Builder( this );
                builder.setCancelable( true );
                builder.setTitle( title );
                builder.setMessage( message );
                builder.show();
            }
        }