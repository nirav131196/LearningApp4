package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CustomInputLayout extends AppCompatActivity {

    TextView s1,s2,s3,s4,txtzero,txrone,txttwo,txtthree,txtfour,txtfile,txtsix,txtseven,txteight,txtnine,txtclear,txtback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_input_layout);

        initView();
        clickZero();
        clickOne();
        clickTwo();
        clickThree();
        clickFour();
        clickFive();
        clickSix();
        clickseven();
        clickeight();
        clicknine();
        clickBack();
        clickClear();
    }

    private void clickClear() {
        txtclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();

                s1.setText("");
                s2.setText("");
                s3.setText("");
                s4.setText("");

            }
        });
    }

    private void clickBack() {
        txtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();

                if(!t4.isEmpty())
                {
                    s4.setText("");
                }
                else if(!t3.isEmpty())
                {
                    s3.setText("");
                }
                else if(!t2.isEmpty())
                {
                    s2.setText("");
                }
                else
                {
                    s1.setText("");
                }
            }
        });
    }

    private void clicknine() {
        txtnine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("9");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("9");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("9");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("9");
                }
            }
        });
    }
    private void clickeight() {
        txteight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("8");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("8");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("8");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("8");
                }
            }
        });
    }

    private void clickseven() {
        txtseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("7");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("7");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("7");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("7");
                }
            }
        });
    }

    private void clickSix() {
        txtsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("6");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("6");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("6");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("6");
                }
            }
        });

    }
    private void clickFive() {
        txtfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("5");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("5");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("5");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("5");
                }
            }
        });
    }

    private void clickFour() {
        txtfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("4");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("4");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("4");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("4");
                }
            }
        });
    }

    private void clickThree() {

        txtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("3");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("3");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("3");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("3");
                }
            }
        });
    }

    private void clickTwo() {
        txttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("2");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("2");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("2");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("2");
                }
            }
        });
    }

    private void clickOne() {
        txrone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("1");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("1");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("1");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("1");
                }
            }
        });

    }
    private void clickZero() {

        txtzero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = s1.getText().toString();
                String t2 = s2.getText().toString();
                String t3 = s3.getText().toString();
                String t4 = s4.getText().toString();
                Log.e("Error","Error");
                if(t1.isEmpty())
                {
                    Log.e("Error","S1");
                    s1.setText("0");
                }
                else if (t2.isEmpty())
                {
                    Log.e("Error","S2");
                    s2.setText("0");
                }
                else if(t3.isEmpty())
                {
                    Log.e("Error","S3");
                    s3.setText("0");
                }
                else if(t4.isEmpty())
                {
                    Log.e("Error","S4");
                    s4.setText("0");
                }
            }
        });

    }

    private void initView() {
        s1 =(TextView) findViewById(R.id.showone);
        s2 =findViewById(R.id.showtwo);
        s3 =findViewById(R.id.showthree);
        s4 =findViewById(R.id.txtforth);

        txtzero = findViewById(R.id.txtzero);
        txrone = findViewById(R.id.txtone);
        txttwo = findViewById(R.id.txttwo);
        txtthree = findViewById(R.id.txtthree);
        txtfour = findViewById(R.id.txtfourth);
        txtfile = findViewById(R.id.txtfive);
        txtsix = findViewById(R.id.txtsixth);
        txtseven = findViewById(R.id.txtseven);
        txteight = findViewById(R.id.txteight);
        txtnine = findViewById(R.id.txtnine);
        txtclear = findViewById(R.id.txtclear);
        txtback = findViewById(R.id.txtback);
    }
}