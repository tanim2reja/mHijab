package com.mukramin.mhijab;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.mukramin.mhijab.adapter.ImageAdapter;

import java.io.File;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private ImageAdapter imageAdapter;
    ArrayList<String> imageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);



    }

    @Override
    public void onResume(){
        super.onResume();

        String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory() .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera";

        File targetDirector = new File(targetPath);
        File[] files = targetDirector.listFiles();
        imageList = new ArrayList();
        for (File file : files){
            imageList.add(file.getAbsolutePath());
        }

        GridView gridview = (GridView) findViewById(R.id.gridView);
        imageAdapter = new ImageAdapter(Home.this,imageList);
        gridview.setAdapter(imageAdapter);

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
