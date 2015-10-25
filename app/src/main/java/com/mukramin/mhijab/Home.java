package com.mukramin.mhijab;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.mukramin.mhijab.adapter.ImageAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Home extends Activity {
    private ImageAdapter imageAdapter;
    ArrayList<String> imageList;
    ArrayList<Item> gridItemList = new ArrayList<Item>();
    GridView gridview;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        gridview= (GridView) findViewById(R.id.gridView);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);


        /*PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.mukramin.mhijab", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }*/

    }

    @Override
    public void onResume(){
        super.onResume();


        loadImageOldStyle();
    }


    public void loadImageOldStyle(){
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);


        String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory() .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera";


        File targetDirector = new File(targetPath);
        File[] files = targetDirector.listFiles();


        for (File file : files){
            // imageList.add(file.getAbsolutePath());
            //Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.home);
            Bitmap bm = decodeSampledBitmapFromUri(file.getAbsolutePath(), 220, 220);
            // bm=createSquaredBitmap(bm);
            // bm= ThumbnailUtils.extractThumbnail(bm, bm.getHeight(), bm.getWidth());
            //  bm = Bitmap.createScaledBitmap(bm, 200, 200, true);
            gridItemList.add(new Item(bm,"Tanim"));
        }


        imageAdapter = new ImageAdapter(Home.this,R.layout.custom_image_view,gridItemList);
        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ImageAdapter adapter = (ImageAdapter)adapterView.getAdapter();
                Item item =adapter.getItem(i);



                Bitmap image =item.getBm(); //((BitmapDrawable)imageView.getDrawable()).getBitmap();
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();



                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    /*ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Hello Facebook")
                            .setContentDescription(
                                    "The 'Hello Facebook' sample  showcases simple Facebook integration")
                            .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                            .build();*/

                    shareDialog.show(content);
                }


            }
        });

    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
