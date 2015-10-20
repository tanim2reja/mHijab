package com.mukramin.mhijab.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mukramin.mhijab.R;

import java.util.ArrayList;

/**
 * Created by Tanim reja on 10/20/2015.
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    ArrayList<String> imageList = new ArrayList();

    public ImageAdapter(Context context, ArrayList<String> imagePathList){
        this.context=context;
        this.imageList=imagePathList;
    }

    void add(String path){
        imageList.add(path);
    }


    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        ImageView imageView;
        if (convertView == null) {
            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.custom_image_view, null);



            imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
          //  imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
          //  imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }
        Bitmap bm = decodeSampledBitmapFromUri(imageList.get(position), 220, 220);
        imageView.setImageBitmap(bm);
        return imageView;
    }

    public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
        Bitmap bm = null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);
        return bm;
    }

    public int calculateInSampleSize(  BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }  return inSampleSize;
    }
}
