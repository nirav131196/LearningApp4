package com.example.myapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    Button button;
    ArrayList<Uri> list;
    RecyclerAdaptor adaptor;

    String colum[]={
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        adaptor=new RecyclerAdaptor(list);
        recyclerView.setLayoutManager(new GridLayoutManager(HomeActivity.this,3));
        recyclerView.setAdapter(adaptor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalley();
            }
        });

        if((ActivityCompat.checkSelfPermission(this,colum[0])!= PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this,colum[1])!= PackageManager.PERMISSION_GRANTED))
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(colum,123);
            }
        }
    }
    private void openGalley() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 123 && resultCode == RESULT_OK)
        {
            if(data.getClipData() != null)
            {
                int x = data.getClipData().getItemCount();
                for (int i  = 0;i < x;i++)
                {
                    list.add(data.getClipData().getItemAt(i).getUri());
                }
                adaptor.notifyDataSetChanged();
                textView.setText("Selected Images : "+list.size());
            }
            else if(data.getData() != null)
            {
                String imageuri = data.getData().getPath();
                list.add(Uri.parse(imageuri));
            }
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            Toast.makeText(this, "App is closed", Toast.LENGTH_SHORT).show();
            finish();

        });

             builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
             dialog.cancel();
                 Toast.makeText(this, "You can select more images", Toast.LENGTH_SHORT).show();

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}