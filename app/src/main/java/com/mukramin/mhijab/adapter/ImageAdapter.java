package com.mukramin.mhijab.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.mukramin.mhijab.Item;
import com.mukramin.mhijab.L2R;
import com.mukramin.mhijab.R;

import java.util.ArrayList;

/**
 * Created by Tanim reja on 10/20/2015.
 */
public class ImageAdapter extends ArrayAdapter<Item> {

    Context context;
    int layoutResourceId;
    ArrayList<Item> gridItemList = new ArrayList<Item>();

    public ImageAdapter(Context context,int layoutResourceId, ArrayList<Item> gridItemList){
        super(context, layoutResourceId, gridItemList);
        this.context=context;
        this.layoutResourceId=layoutResourceId;
        this.gridItemList=gridItemList;

    }

    @Override
    public int getCount() {
        return gridItemList.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecordHolder();
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Item item = gridItemList.get(position);
        holder.imageItem.setImageBitmap(item.getBm());



        return row;



    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
    }

}
