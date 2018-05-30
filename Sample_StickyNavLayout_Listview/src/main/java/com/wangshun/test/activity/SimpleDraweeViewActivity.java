package com.wangshun.test.activity;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangshun.test.R;

public class SimpleDraweeViewActivity extends Activity
{
    private SimpleDraweeView simpleDraweeView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Fresco.initialize(this);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_drawee_view);

        initView();
    }

    private void initView() {

        //创建SimpleDraweeView对象
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.main_sdv2);
        //创建将要下载的图片的URI
        Uri imageUri = Uri.parse("http://dynamic-image.yesky.com/740x-/uploadImages/2015/163/50/690V3VHW0P77.jpg");
        //开始下载
        simpleDraweeView.setImageURI(imageUri);

    }
}
