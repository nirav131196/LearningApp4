package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.pdf417.PDF417Writer;

import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SQLite_Insert_Activity extends BaseActivity {

    EditText edtName,edtSurname,edtDOB,edtJoiningDate,edtSalary,edtAddress,edtCity;
    Spinner spDesignation;
    Button btnInsert,btnInsertContact;
    SQLite_Database_Helper databaseHelper;
    String[] items2 = {"Select Designation","IOS","Angular","REACT NATIVE","ANDROID","FLUTTER","PYTHON","C#","JAVA","UI DESIGNER","PHP","LARAVEL"};
    int pageWidth = 1200;
    private DatePicker datepicker;
    private Calendar calander;
    private int year,month,day;
    Bitmap bmp,scalebmp;
    Date dateObj;
    DateFormat dateFormat;
    float[] prices = new float[]{0,200,300,450,325,500};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert);


      // For Screenshot and recording disable
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        calander =Calendar.getInstance();
        year = calander.get(Calendar.YEAR);
        month = calander.get(Calendar.MONTH);
        day = calander.get(Calendar.DAY_OF_MONTH);

        databaseHelper = new SQLite_Database_Helper(this);

        initView();
        ClickInsertButton();
        ClickInsertContactButton();
        ClickDobEdittext();
        ClickJoiningDate();

        spDesignation = findViewById(R.id.editEmpPost);
        spDesignation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        break;
                    case 1:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 10:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;
                    case 11:
                        Toast.makeText(SQLite_Insert_Activity.this, "Iteam Name : "+items2[position], Toast.LENGTH_SHORT).show();
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      /*  spDesignation.setOnItemSelectedListener(this);*/
        //For Spinner

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items2);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spDesignation.setAdapter(ad);
    }
    private void ClickJoiningDate() {
        edtJoiningDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(990);
            }
        });
    }
    private void ClickDobEdittext() {
        edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });
    }
    protected Dialog onCreateDialog(int id)
    {
        if(id==999)
        {
            return new DatePickerDialog(this,myDatelistener,year,month,day);
        }
        else if(id==990)
        {
            return new DatePickerDialog(this,myDatelistener2,year,month,day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDatelistener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            showDate2(year,month+1,dayOfMonth);
        }
    };
    private DatePickerDialog.OnDateSetListener myDatelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year,month+1,dayOfMonth);
        }
    };
    private void showDate(int year, int month,int day)
    {
        edtDOB.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }
    private void showDate2(int year, int month,int day)
    {
        edtJoiningDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
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
                String post =spDesignation.getSelectedItem().toString();
                String DOB =edtDOB.getText().toString().trim();
                String JoinDate =edtJoiningDate.getText().toString().trim();
                String Salary =edtSalary.getText().toString().trim();
                String Address =edtAddress.getText().toString().trim();
                String City =edtCity.getText().toString().trim();

                if(name.length() > 0 && surname.length() > 0  && !post.equals("Select Designation") && DOB.length() > 0 && JoinDate.length() > 0 && Salary.length() > 0 && Address.length() > 0 && City.length() > 0)
                {
                    try {
                        Log.e("DATA : ","DATA "+ name+surname+post+DOB+JoinDate+Salary+Address+City);
                        boolean isInserted = databaseHelper.insertData(name,surname,post,DOB,JoinDate,Salary,Address,City);

                        if(isInserted == true)
                        {
                            try {
                               // createPdf(name,surname,post,DOB,JoinDate,Salary,Address,City);
                                createPDF();
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
    private void createPDF() {
        Log.e("POINT","POINT 1");
        dateObj  =new Date();
        Log.e("POINT","POINT 2");
        String name = edtName.getText().toString().trim();
        String surname =edtSurname.getText().toString().trim();

        Log.e("POINT","POINT 3");
        PdfDocument pdfDocument = new PdfDocument();
        Paint myPaint = new Paint();
        Paint titlepaint = new Paint();
        Log.e("POINT","POINT 4");

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1200,2010,1).create();
        PdfDocument.Page myPage1 = pdfDocument.startPage(myPageInfo);
        Canvas canvas = myPage1.getCanvas();

        canvas.drawBitmap(scalebmp,0,0,myPaint);

        Log.e("POINT","POINT 5");
        titlepaint.setTextAlign(Paint.Align.CENTER);
        titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        titlepaint.setTextSize(70);
        Log.e("POINT","POINT  6");
        canvas.drawText("Diamond Pizaa",pageWidth/2,270,titlepaint);

        myPaint.setColor(Color.rgb(0,113,188));
        myPaint.setTextSize(30f);

        Log.e("POINT","POINT 7");
        myPaint.setTextAlign(Paint.Align.RIGHT);
        Log.e("POINT","POINT 7.1");
        canvas.drawText("Call : 022-41414141",1160,40,myPaint);
        Log.e("POINT","POINT 7.2");
        canvas.drawText("022-8686868686",1160,80,myPaint);
        Log.e("POINT","POINT 8");
        titlepaint.setTextAlign(Paint.Align.CENTER);
        titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
        titlepaint.setTextSize(70);
        canvas.drawText("Invoice",pageWidth/2,500,titlepaint);
        Log.e("POINT","POINT 9");
        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(35f);
        myPaint.setColor(Color.BLACK);
        canvas.drawText("Name : "+name,20,590,myPaint);
        canvas.drawText("Surname : "+surname,20,640,myPaint);
        Log.e("POINT","POINT 10");
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Invoice no : "+ "911819",pageWidth-20,590,myPaint );
        Log.e("POINT","POINT 11");
        dateFormat = new SimpleDateFormat("dd/MM/yy");
        canvas.drawText("Date: "+dateFormat.format(dateObj),pageWidth-20,640,myPaint);
        Log.e("POINT","POINT 12");
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        canvas.drawText("Time: "+dateFormat.format(dateObj),pageWidth-20,690,myPaint);
        Log.e("POINT","POINT 13");
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        canvas.drawRect(20,780,pageWidth-20,860,myPaint);
        Log.e("POINT","POINT 14");
        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("Sr No ",40,830,myPaint);
        canvas.drawText("Item Description",200,830,myPaint);
        canvas.drawText("Price",700,830,myPaint);
        canvas.drawText("Qty.",900,830,myPaint);
        canvas.drawText("Total",1050,830,myPaint);
        Log.e("POINT","POINT 15");
        canvas.drawLine(180,790,180,840,myPaint);
        canvas.drawLine(680,790,680,840,myPaint);
        canvas.drawLine(880,790,880,840,myPaint);
        canvas.drawLine(1030,790,1030,840,myPaint);
        Log.e("POINT","POINT 16");
        float total1 = 0;
        if(edtName.getText().toString() != null && edtSurname.getText().toString() != null)
        {
              canvas.drawText("1. ",40,950,myPaint);
              canvas.drawText("CAKE",200,950,myPaint);
              canvas.drawText("200",700,950,myPaint);
              canvas.drawText("1",900,950,myPaint);
              //total1 = Float.parseFloat()
              total1 = 400;
              myPaint.setTextAlign(Paint.Align.RIGHT);
              canvas.drawText(String.valueOf(total1),pageWidth-40,950,myPaint);
              myPaint.setTextAlign(Paint.Align.LEFT);
            Log.e("POINT","POINT 17");
        }
        float sub_total = total1 + 100;
        canvas.drawLine(680,1200,pageWidth-20,1200,myPaint);
        canvas.drawText("Sub total ",700,1250,myPaint);
        canvas.drawText(":",900,1250,myPaint);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(sub_total),pageWidth-40,1250,myPaint);
        Log.e("POINT","POINT 18");
        myPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Tax (12%)",700,1300,myPaint);
        canvas.drawText(":",900,1300,myPaint);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(sub_total*12/100),pageWidth-40,1300,myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);
        Log.e("POINT","POINT 19");
        myPaint.setColor(Color.rgb(247,147,30));
        canvas.drawRect(680,1350,pageWidth-20,1450,myPaint);
        Log.e("POINT","POINT 20");
        myPaint.setColor(Color.BLACK);
        myPaint.setTextSize(50f);
        myPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Total",700,1415,myPaint);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(sub_total+(sub_total*12/100)),pageWidth-40,1415,myPaint);

        pdfDocument.finishPage(myPage1);

        Log.e("POINT","POINT 21");

        File file = new File(Environment.getExternalStorageDirectory(),"/Hello.pdf");

        try {
            Log.e("POINT","POINT 22");
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(getApplicationContext(),"Pdf saved...",Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.e("POINT","POINT 23");
        }
        pdfDocument.close();
        Log.e("POINT","POINT 24");
    }
    private void initView() {

        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.employeetmage);
        scalebmp = Bitmap.createScaledBitmap(bmp,710,315,false);

        edtName = findViewById(R.id.editEmpName);
        edtSurname = findViewById(R.id.editEmpSurname);

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
    /*private void createPdf(String name,String surname,String post,String dob,String JoinDate,String salary,String address,String city) throws FileNotFoundException
    {
        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File (pdfpath,"MyDetails.pdf");
        OutputStream outputStream =new FileOutputStream(file);


        PdfWriter pdfWriter = new PdfWriter(file);
        PdfDocument pdfdocument =new PdfDocument(pdfWriter);
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
        showToast("Pdf file Saved...");
    }*/
}