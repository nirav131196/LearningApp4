package com.example.loginapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class WelcomeActivity extends BaseActivity {

    private ImageView QRCodeGenerator;
    private EditText qr_Text;
    private Button qr_button,QR_Scanner_button;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        QRCodeGenerator =(ImageView) findViewById(R.id.QR_CODE_GENERATOR);
        qr_Text = (EditText)findViewById(R.id.edittext_for_qrcode);
        qr_button = (Button)findViewById(R.id.QRCODE_GENERATOR_BUTTON);
        QR_Scanner_button = (Button)findViewById(R.id.QRCODE_SCANNER_BUTTON);


        qr_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v)
            {
                String qr_text2 = qr_Text.getText().toString().trim();
                if(qr_text2.isEmpty())
                {
                    showToast("Please Enter Text");
                }
                else
                {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display =manager.getDefaultDisplay();
                    Point point =new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int dimen = width<height ? width:height;
                    dimen = dimen  * 3/4;
                    qr_Text.setText("");
                    qrgEncoder = new QRGEncoder(qr_text2,null, QRGContents.Type.TEXT,dimen);

                    try
                    {
                        bitmap  = qrgEncoder.getBitmap();
                        QRCodeGenerator.setImageBitmap(bitmap);
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });
        QR_Scanner_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
     /*   user_name.setText(appPreferences.getString(AppPreferences.USER_NAME));
        user_id.setText(appPreferences.getString(AppPreferences.USER_ID));

        Picasso.get().load(appPreferences.getString(AppPreferences.PROFILE_PIC)).into(profilephoto);*/
    }
   /* public void onTokenReceived(String auth_token) {

        if (auth_token == null)
            return;
        appPreferences.putString(AppPreferences.TOKEN, auth_token);
        token = auth_token;
        getUserInfoByAccessToken(token);
    }*/
}