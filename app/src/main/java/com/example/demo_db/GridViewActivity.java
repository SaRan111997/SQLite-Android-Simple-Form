package com.example.demo_db;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    Button btnAdd, btnClear,fetch;
    GridView lstView;
    DatabaseHelper myDB;
    ListView views;
    private Context context;
    private RecyclerView myrecylerview;
    List<MyAdapter.GridData> griddetail = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);
        fetch=(Button) findViewById(R.id.fecth);
        views= (ListView) findViewById(R.id.list_view);
        myrecylerview=findViewById(R.id.myrecycler);
        myDB = new DatabaseHelper( this );
        viewData();

      //
       MyAdapter recycleadapter = new MyAdapter(this);
        myrecylerview.setAdapter(recycleadapter);
        myrecylerview.setLayoutManager(
                new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this,
                        DividerItemDecoration.VERTICAL);
        myrecylerview.addItemDecoration(dividerItemDecoration);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5,GridLayoutManager.VERTICAL,false);
//        myrecylerview.setLayoutManager(gridLayoutManager);
//        myrecylerview.setAdapter(recycleadapter);





    }





///////
    public void goback(View v) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }

    public void viewData(){

        fetch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getData();

                if (res.getCount() == 0){
                    showMessage("Error", "Data not found!");
                }

                else{
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append( "ID: " + res.getString( 0 ) + "\n" );
                        buffer.append( "Name: " + res.getString( 1 ) + "\n" );
                        buffer.append( "EmpID: " + res.getString( 2 ) + "\n" );
                        buffer.append( "Gender: " + res.getString( 3 ) + "\n" );
                        buffer.append( "Salary: " + res.getString( 4 ) + "\n" );
                        buffer.append( "Address: " + res.getString( 5 ) + "\n" );


                    }

                    showMessage( "Data", buffer.toString() );

                }
            }
        } );
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
