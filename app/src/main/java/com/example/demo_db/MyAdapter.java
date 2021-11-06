package com.example.demo_db;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    List<GridData> GridData= new ArrayList<>();



    public class GridData {
        int _id;
        String _name;
        String _empid;
        String _gender;
        int _salary;
        String _address;
    }
    public MyAdapter(Context context) {
        this.context = context;
        DatabaseHelper myDB= new DatabaseHelper(context);
        myDB.open();
        //String sql="select * from employe_data";
        Cursor c= myDB.getCompanies();
        c.moveToFirst();
        for (int i =0; i<c.getCount();i++)
        {
            GridData sd = new GridData();

            sd._id=c.getInt(c.getColumnIndex("ID"));
            sd._name=c.getString(c.getColumnIndex("Name"));
            sd._empid=c.getString(c.getColumnIndex("empid"));
            sd._gender=c.getString(c.getColumnIndex("gender"));
            sd._salary=c.getInt(c.getColumnIndex("salary"));
            sd._address=c.getString(c.getColumnIndex("address"));
            GridData.add(sd);
            c.moveToNext();
        }
        c.close();
        myDB.close();

    }







    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.recyclerview_display_support,parent,
                false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id= String.valueOf(GridData.get(position)._id);
        holder.id.setText(id);
        String name= String.valueOf(GridData.get(position)._name);
        holder.name.setText(name);
        String empid= String.valueOf(GridData.get(position)._empid);
        holder.empid.setText(empid);
        String gender= String.valueOf(GridData.get(position)._gender);
        holder.gender.setText(gender);
        String address= String.valueOf(GridData.get(position)._address);
        holder.address.setText(address);

       String salary= String.valueOf(GridData.get(position)._salary);
        holder.salary.setText(salary);


    }

    @Override
    public int getItemCount() {
        return GridData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id,name,empid,gender,address,salary;
        RelativeLayout recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.item_id);
            name=itemView.findViewById(R.id.item_name);
            empid=itemView.findViewById(R.id.item_emp);
            gender=itemView.findViewById(R.id.item_gender);
            address=itemView.findViewById(R.id.item_address);
            salary=itemView.findViewById(R.id.item_salary);
            recyclerView=itemView.findViewById(R.id.mylayout);
        }
    }

}
