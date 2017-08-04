package com.example.wb.testdemo.compressimg;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wb.testdemo.R;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Zhangchen on 2017/7/20.
 */

public class CompressimgActivity extends AppCompatActivity {

    @InjectView(R.id.photo)
    Button photo;
    @InjectView(R.id.img)
    Button img;
    @InjectView(R.id.img_old)
    ImageView imgOld;
    @InjectView(R.id.img_one)
    ImageView imgOne;

    private static final int REQUEST_CAMERA_CODE = 11;
    private static final int REQUEST_PREVIEW_CODE = 22;
    @InjectView(R.id.compress)
    Button compress;
    @InjectView(R.id.tv_old)
    TextView tvOld;
    @InjectView(R.id.tv_one)
    TextView tvOne;
    private String imagepath; //压缩后图片路径
    private Context context;
    private ImageCaptureManager captureManager; // 相机拍照处理类
    private ArrayList<String> imagePaths;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compressimg);
        ButterKnife.inject(this);
        context = this;
    }

    @OnClick({R.id.photo, R.id.img, R.id.img_old, R.id.img_one, R.id.compress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo:
                openCamera();
                break;
            case R.id.img:
                openPhoto();
                break;
            case R.id.compress:
                compress();
                break;
            case R.id.img_old:
                dialogshow(imagePaths.get(0));
                break;
            case R.id.img_one:
                dialogshow(imagepath);
                break;
        }
    }

    private void dialogshow(String s) {
        Log.d("压缩测试",s);
        final Dialog dialog = new Dialog(context, R.style.LogDialog);
        View v = LayoutInflater.from(context).inflate(R.layout.item_photoview, null);
        PhotoView img = (PhotoView) v.findViewById(R.id.item_login_img);
        Glide.with(context).load(s).fitCenter().into(img);
        img.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        dialog.setContentView(v, layoutParams);
        dialog.show();
    }

    private File tempFile = new File(Environment.getExternalStorageDirectory()+"/","test.png");
    /**
     * 压缩
     */
    private void compress() {
        String s = imagePaths.get(0);
        final File file = new File(s);
        Luban.with(CompressimgActivity.this).load(file).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
                Log.e("文件大小start", " "+file.getAbsolutePath());
            }

            @Override
            public void onSuccess(File file) {
                imagepath = file.getAbsolutePath();
                Log.d("文件大小success",file.length()/1024+"  "+file.getAbsolutePath());
//                file.renameTo(tempFile);
                Glide.with(CompressimgActivity.this).load(file).error(R.drawable.one).into(imgOne);
//                tvOne.setText(file.getAbsolutePath()+"  "+getFileSize(tempFile.getName()));
//                Log.e("文件大小success",file.length()/1024+"  "+file.toString()+"  "+file.getAbsolutePath()+"  "+tempFile.getAbsoluteFile());
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
    }

    /**
     * 打开相机
     */
    public void openCamera() {
        if (ContextCompat.checkSelfPermission(CompressimgActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(CompressimgActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 1);//1 can be another integer
        }
        try {
            if (captureManager == null) {
                captureManager = new ImageCaptureManager(CompressimgActivity.this);
            }
            Intent intent = captureManager.dispatchTakePictureIntent();
            startActivityForResult(intent, ImageCaptureManager.REQUEST_TAKE_PHOTO);
        } catch (IOException e) {
            Toast.makeText(CompressimgActivity.this, com.foamtrace.photopicker.R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 打开相册
     */
    public void openPhoto() {
        PhotoPickerIntent intent = new PhotoPickerIntent(CompressimgActivity.this);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCarema(true); // 是否显示拍照
        intent.setMaxTotal(9); // 最多选择照片数量，默认为9
        intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT), 1);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT), 3);
                    break;
                // 调用相机拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                    if (captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        ArrayList<String> paths = new ArrayList<>();
                        paths.add(captureManager.getCurrentPhotoPath());
                        loadAdpater(paths, 2);
                    }
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths, int index) {
        if (imagePaths == null) {
            imagePaths = new ArrayList<>();
        }
        imagePaths.clear();
        if (index == 1 || index == 3) {
            imagePaths.clear();
            imagePaths.addAll(paths);
        } else if (index == 2) {
            imagePaths.addAll(paths);
        }
        Glide.with(CompressimgActivity.this).load(imagePaths.get(0)).into(imgOld);
        tvOld.setText(imagePaths.get(0)+"  "+getFileSize(imagePaths.get(0)));
    }

    /**
     * 获取指定文件大小
     * @param srcPath
     * @return
     * @throws Exception
     */
    public static long getFileSize(String srcPath)
    {
        long size = 0;
        try {
            File sfile = new File(srcPath);

            if (sfile.exists()){
                FileInputStream fis = null;
                fis = new FileInputStream(sfile);
                size = fis.available();
            }
            else{
                sfile.createNewFile();
                Log.e("获取文件大小","文件不存在!");
            }
            return size;
        } catch (Exception e) {
            return size;
        }
    }
}
