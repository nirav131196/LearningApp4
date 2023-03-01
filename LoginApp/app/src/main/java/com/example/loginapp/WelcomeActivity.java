package com.example.loginapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;

import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

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
                scanCode();

              /*  Intent i =new Intent(WelcomeActivity.this,QRScanner_Activity.class);
                startActivity(i);
                finish();*/
            }
        });
    }
    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result->
    {
       if (result.getContents() != null)
       {
           AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
           builder.setTitle("Result");
           builder.setMessage(result.getContents());
           builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
               }
           }).show();

       }
    });
}