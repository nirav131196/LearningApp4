package com.example.loginapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImageAPI_Adapter extends RecyclerView.Adapter{


    Context context;
    ArrayList<Model_ImageAPI> model;

    String defaultImage;


    public ImageAPI_Adapter(ArrayList<Model_ImageAPI> model) {

        this.model = model;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_image_api,null);
        return new ImageAPI_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder =(ViewHolder) holder;

            Model_ImageAPI model2 = model.get(position);
            viewHolder.progressBar.setVisibility(View.VISIBLE);
            viewHolder.txtName.setText(model2.getTitle());
            viewHolder.txtDetails.setText(model2.getDescription1());
            defaultImage = model2.getDefault_image();

            if(model2.getImage() != null)
            {
                Picasso.get()
                        .load(model2.getImage())
                        .into(viewHolder.image, new Callback() {
                            @Override
                            public void onSuccess() {
                                //   Log.e("Hi","inSuccess");
                                // Hide the ProgressBar on success
                                viewHolder.progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {

                                Log.e("Hi","inError");
                                MyAsync obj = new MyAsync() {

                                    @Override
                                    protected void onPostExecute(Bitmap bmp) {
                                        super.onPostExecute(bmp);

                                        Bitmap bm = bmp;
                                        viewHolder.image.setImageBitmap(bm);
                                    }
                                };
                                obj.execute();

                                // Hide the ProgressBar on error
                                viewHolder.progressBar.setVisibility(View.GONE);
                            }
                        });
            }
            else if(model2.getImage() == null)
            {
                Picasso.get()
                        .load(model2.getDefault_image())
                        .into(viewHolder.image, new Callback() {
                            @Override
                            public void onSuccess() {
                                viewHolder.progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                viewHolder.progressBar.setVisibility(View.GONE);
                            }
                        });
            }
            if(!model2.getImageLogo().equals(""))
            {
                Picasso.get()
                        .load(model2.getImageLogo())
                        .into(viewHolder.image1, new Callback() {
                            @Override
                            public void onSuccess() {
                                viewHolder.progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                             //   viewHolder.image1.setBackgroundResource(R.drawable.ic_slogo_white);
                                // Hide the ProgressBar on error
                                viewHolder.progressBar.setVisibility(View.GONE);
                            }
                        });
            }
            else if (model2.getImageLogo().equals(""))
            {
                Picasso.get()
                        .load(R.drawable.backgroudimage)
                        .into(viewHolder.image1, new Callback() {
                            @Override
                            public void onSuccess() {
                                viewHolder.progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                viewHolder.progressBar.setVisibility(View.GONE);
                            }
                        });
            }

    }

    @Override
    public int getItemCount() {

        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardMain,cardCircle;
        ImageView image,image1;

        TextView txtDetails,txtName;

        ProgressBar progressBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardMain = itemView.findViewById(R.id.cardview_event);
            image = itemView.findViewById(R.id.imgEventNew);
            image1 = itemView.findViewById(R.id.circle_image1);
            txtName = itemView.findViewById(R.id.txt_name);
            txtDetails = itemView.findViewById(R.id.txt_details);
            cardCircle= itemView.findViewById(R.id.cardCircle);

            progressBar = itemView.findViewById(R.id.progress_bar_img);
        }
    }
public class MyAsync extends AsyncTask<Void,Void,Bitmap>
{

    @Override
    protected Bitmap doInBackground(Void... voids) {

        try{
                URL url = new URL(defaultImage);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream =connection.getInputStream();
            Bitmap mybitmap = BitmapFactory.decodeStream(inputStream);
            return mybitmap;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
}
