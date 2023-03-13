package com.example.loginapp;



import static android.Manifest.permission.CAMERA;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class WelcomeActivity extends BaseActivity {

    private ImageView IVQRCodeGenerator;
    private EditText edtQRText;
    private Button btnQRGenerator,btnQRScanner,btnCamera,btnStore;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int pic_id = 123;
    private static final int STORAGE_PERMISSION_CODE = 101;

    FileOutputStream outputStream;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        ClickEventQRGenerator();
        ClickEventQRScanner();
        ClickEventCameraButton();
        ClickEventSaveButton();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(WelcomeActivity.this, Welcome_Main.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    private void ClickEventSaveButton() {

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                    try
                    {
                        if(ContextCompat.checkSelfPermission(getApplicationContext(),STORAGE_PERMISSION) == PackageManager.PERMISSION_GRANTED)  // GRANTED
                        {

                            // IMAGE STORAGE METHOD
                            saveImage();

                        }
                        else  // NOT GRANTED
                        {
                            ActivityCompat.requestPermissions(WelcomeActivity.this,new String[] {STORAGE_PERMISSION},STORAGE_PERMISSION_CODE);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        });
    }
    public void saveImage()
    {
        BitmapDrawable drawable =(BitmapDrawable) IVQRCodeGenerator.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        if(bitmap == null)
        {
            File filepath = Environment.getExternalStorageDirectory();
            File dir = new File(filepath.getAbsolutePath()+"/Demo/");
            dir.mkdir();
            File file = new File(dir,System.currentTimeMillis()+".jpg");
            try
            {
                outputStream = new FileOutputStream(file);
            }
            catch (FileNotFoundException fe)
            {
                fe.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            showToast("Image Successfully Saved");
            IVQRCodeGenerator.setImageResource(R.drawable.editext_design4);
        }
        else
        {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i =new Intent(WelcomeActivity.this,Welcome_Main.class);
        startActivity(i);
        finish();
    }

    private void ClickEventCameraButton()
    {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    checkPermission(CAMERA,CAMERA_PERMISSION_CODE);
                }
                catch (Exception ex)
                {
                    showToast("Exception : "+ex);
                }
            }
        });
    }
    // CAMERA PERMISSION CHCEKING OR REQUESTING
    private void  checkPermission(String permission,int requestCode)
    {
        if(ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED)  // GRANTED
        {
            // FOR CAMERA
         /*   Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera,pic_id);*/
            // FOR GALLERY
            Intent pickphoto = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             startActivityForResult(pickphoto,pic_id);
        }
        else  // NOT GRANTED
        {
            ActivityCompat.requestPermissions(this,new String[] {permission},requestCode);
        }
    }
    private void ClickEventQRScanner() {
        btnQRScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
    }
    private void ClickEventQRGenerator() {
        btnQRGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String qr_text2 = edtQRText.getText().toString().trim();
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
                    edtQRText.setText("");
                    qrgEncoder = new QRGEncoder(qr_text2,null, QRGContents.Type.TEXT,dimen);
                    try
                    {
                        bitmap  = qrgEncoder.getBitmap();
                        IVQRCodeGenerator.setImageBitmap(bitmap);
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    private void initView()
    {
        IVQRCodeGenerator =(ImageView) findViewById(R.id.QR_CODE_GENERATOR);
        edtQRText = (EditText)findViewById(R.id.edittext_for_qrcode);
        btnQRGenerator = (Button)findViewById(R.id.QRCODE_GENERATOR_BUTTON);
        btnQRScanner = (Button)findViewById(R.id.QRCODE_SCANNER_BUTTON);
        btnCamera = (Button)findViewById(R.id.OPEN_CAMERA);
        btnStore = (Button)findViewById(R.id.STORE_IMAGE);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == pic_id)
        {
            // FOR CAMERA
          /*  Bitmap photo = (Bitmap) data.getExtras().get("data");
            IVQRCodeGenerator.setImageBitmap(photo);*/

            // FOR GALLREY
            Uri selectedImage = data.getData();
            IVQRCodeGenerator.setImageURI(selectedImage);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode ==  CAMERA_PERMISSION_CODE)
        {
            if(grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                showToast("Camera Permission Granted");
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,pic_id);
            }
            else
            {
                showToast("Camera Permission Denied");
            }
        }
        else if(requestCode == STORAGE_PERMISSION_CODE)
        {
            if(grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                saveImage();
            }
            else
            {
                showToast("Camera Permission Denied");
            }
        }
    }
}