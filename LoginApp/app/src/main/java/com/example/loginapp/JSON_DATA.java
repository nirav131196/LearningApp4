package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSON_DATA extends AppCompatActivity {

    public static final String JSON_STRING = "{\"employee\":{\"name\":\"Sachin\",\"salary\":90000}}";
    String strJson="{ \"Employee\" :[{\"id\":\"101\",\"name\":\"Sonoo Jaiswal\",\"salary\":\"50000\"},{\"id\":\"102\",\"name\":\"Vimal Jaiswal\",\"salary\":\"60000\"}] }";


    TextView txtJSONARRAY , txtJSONDATA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data);

        txtJSONARRAY = findViewById(R.id.txtJSONARRAY);
        txtJSONDATA = findViewById(R.id.txtJSONDATA);

        try{
            JSONObject emp = (new JSONObject(JSON_STRING).getJSONObject("employee"));
            String empname = emp.getString("name");
            int empsalary = emp.getInt("salary");

            String str = "Employee name : "+empname+"\n"+"Employee Salary : "+empsalary;
            txtJSONDATA.setText(str);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Error of JSON DATA "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        String data = "";

        try
        {
            JSONObject jsonObject = new JSONObject(strJson);
            JSONArray jsonArray = jsonObject.getJSONArray("Employee");

            for(int i = 0; i<jsonArray.length();i++)
            {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonObject1.getString("id").toString());
                String name = jsonObject1.getString("name").toString();
                float salary = Float.parseFloat(jsonObject1.getString("salary").toString());

                data += "Node : "+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
            }
            txtJSONARRAY.setText(data);
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Error of JSON array",Toast.LENGTH_SHORT).show();
        }
    }
}