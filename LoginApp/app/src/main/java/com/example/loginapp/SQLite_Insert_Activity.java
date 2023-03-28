package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.zxing.pdf417.PDF417Writer;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class SQLite_Insert_Activity extends BaseActivity {

    EditText edtName,edtSurname,edtDesignation,edtDOB,edtJoiningDate,edtSalary,edtAddress,edtCity;
    Button btnInsert,btnInsertContact;
    SQLite_Database_Helper databaseHelper;

    private DatePicker datepicker;
    private Calendar calander;
    private int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        databaseHelper = new SQLite_Database_Helper(this);

        initView();
        ClickInsertButton();
        ClickInsertContactButton();


    }

    private void ClickInsertContactButton() {
        btnInsertContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(SQLite_Insert_Activity.this,SQLite_Insert_ContactData_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void ClickInsertButton() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString().trim();
                String surname =edtSurname.getText().toString().trim();
                String post =edtDesignation.getText().toString().trim();
                String DOB =edtDOB.getText().toString().trim();
                String JoinDate =edtJoiningDate.getText().toString().trim();
                String Salary =edtSalary.getText().toString().trim();
                String Address =edtAddress.getText().toString().trim();
                String City =edtCity.getText().toString().trim();

                if(name.length() > 0 && surname.length() > 0 && post.length() > 0 && DOB.length() > 0 && JoinDate.length() > 0 && Salary.length() > 0 && Address.length() > 0 && City.length() > 0)
                {
                    try {
                        Log.e("DATA : ","DATA "+ name+surname+post+DOB+JoinDate+Salary+Address+City);
                        boolean isInserted = databaseHelper.insertData(name,surname,post,DOB,JoinDate,Salary,Address,City);

                        if(isInserted == true)
                        {
                            try {
                                createPdf(name,surname,post,DOB,JoinDate,Salary,Address,City);
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                            showToast("Employee Details Added");
                            Intent i = new Intent(SQLite_Insert_Activity.this,SQLite_Select_Record.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            showToast("Employee Details Not Added");
                        }
                    }
                    catch (Exception ex)
                    {
                        showToast("Exception is : "+ex);
                    }
                }
                else
                {
                    showToast("Enter All Details");
                }
            }
        });
    }
    private void initView() {
        edtName = findViewById(R.id.editEmpName);
        edtSurname = findViewById(R.id.editEmpSurname);
        edtDesignation = findViewById(R.id.editEmpPost);
        edtDOB = findViewById(R.id.editEmpDOB);
        edtJoiningDate = findViewById(R.id.editEmpJoiningDate);
        edtSalary = findViewById(R.id.editEmpSalary);
        edtAddress = findViewById(R.id.editEmpAddress);
        edtCity = findViewById(R.id.editEmpCity);

        btnInsert=findViewById(R.id.InsertButton);
        btnInsertContact=findViewById(R.id.InsertContactButton);
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(SQLite_Insert_Activity.this, Welcome_Main.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        Intent i =new Intent(SQLite_Insert_Activity.this, Welcome_Main.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
    private void createPdf(String name,String surname,String post,String dob,String JoinDate,String salary,String address,String city) throws FileNotFoundException
    {
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file =new File(pdfpath,"MyDetails.pdf");
        OutputStream outputStream =new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(String.valueOf(file));
        PdfDocument pdfdocument =new PdfDocument(writer);
        Document document = new Document(pdfdocument);

        pdfdocument.setDefaultPageSize(PageSize.A4);
        document.setMargins(0,0,0,0);

        Drawable d = getDrawable(R.drawable.employeetmage);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bitmapData = stream.toByteArray();
        ImageData imagedata = ImageDataFactory.create(bitmapData);
        Image image = new Image(imagedata);

        Paragraph heading = new Paragraph("Employee Data").setBold().setFontSize(24).setTextAlignment(TextAlignment.CENTER);

        float[] width = {100f,100f};
        Table table = new Table(width);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        table.addCell(new Cell().add(new Paragraph("Name : ")));
        table.addCell(new Cell().add(new Paragraph(name)));

        table.addCell(new Cell().add(new Paragraph("Surname : ")));
        table.addCell(new Cell().add(new Paragraph(surname)));

        table.addCell(new Cell().add(new Paragraph("Designation : ")));
        table.addCell(new Cell().add(new Paragraph(post)));

        table.addCell(new Cell().add(new Paragraph("Date of Birth : ")));
        table.addCell(new Cell().add(new Paragraph(dob)));

        table.addCell(new Cell().add(new Paragraph("Joining Date : ")));
        table.addCell(new Cell().add(new Paragraph(JoinDate)));

        table.addCell(new Cell().add(new Paragraph("Salary : ")));
        table.addCell(new Cell().add(new Paragraph(salary)));

        table.addCell(new Cell().add(new Paragraph("Address : ")));
        table.addCell(new Cell().add(new Paragraph(address)));

        table.addCell(new Cell().add(new Paragraph("City : ")));
        table.addCell(new Cell().add(new Paragraph(city)));

        document.add(image);
        document.add(heading);
        document.add(table);
        document.close();
        showToast("Pdf Saved...");
    }
}