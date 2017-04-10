package com.chuangfeng.pdnl.user.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chuangfeng.pdnl.R;
import com.chuangfeng.pdnl.util.PhotoUtils;
import com.chuangfeng.pdnl.util.SPHelper;
import com.chuangfeng.pdnl.widget.fragment.LazyFragment;

import org.joda.time.DateTime;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chuangfeng on 2017/4/8.
 */

public class UserFragment extends LazyFragment {

    private static final int REQUEST_CAMERA = 100;
    private static final int REQUEST_GALLERY = REQUEST_CAMERA + 1;

    private String savePath;

    @BindView(R.id.user_iv_appbar)
    ImageView iv_appbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.user_btn_photo)
    FloatingActionButton btn_photo;

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void initLazyView(Bundle savedInstanceState) {
        initToolBar();
    }

    private void initToolBar() {
        toolbar.setTitle(getString(R.string.title_user));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        String filePath = SPHelper.with(this.getContext()).getString(SPHelper.STRING_USER);//查询保存的背景图片路径
        if (!TextUtils.isEmpty(filePath)) {
            Glide.with(this).load(filePath).into(iv_appbar);
        } else {
            Glide.with(this).load(R.drawable.background_user).into(iv_appbar);
        }
    }

    @OnClick({R.id.user_btn_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_btn_photo:
                final Context context = view.getContext();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Tips:")
                        .setMessage("如何获取图片？")
                        .setPositiveButton(getString(R.string.user_gallery), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                PhotoUtils.startGallery(UserFragment.this, REQUEST_GALLERY);
                            }
                        })
                        .setNegativeButton(getString(R.string.user_carema), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                String fileName = "PDNL_" + new DateTime(System.currentTimeMillis()).toString("yyyyMMddHHmmss") + ".png";
                                savePath = Environment.getExternalStorageDirectory() + "/DCIM/Camera/" + fileName;
                                PhotoUtils.startTakePhoto(UserFragment.this, REQUEST_CAMERA, fileName);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.color_primary));
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.color_primary));
                dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(context.getResources().getColor(R.color.color_secondary_text));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CAMERA: // 调用相机拍照
                if (resultCode == RESULT_OK) {
                    //如果调用相机时指定了savePath的Uri，则不会返回data，data == null
                    //我们可以直接从之前设置savePath得到相片File
                    File temp = new File(savePath);
                    PhotoUtils.updateGallery(this.getContext(), savePath);
                    String filePath = temp.getAbsolutePath();
                    Glide.with(this).load(filePath).into(iv_appbar);
                    SPHelper.with(this.getContext()).setString(SPHelper.STRING_USER, filePath);//保存图片路径
                }
                break;
            case REQUEST_GALLERY: // 相册获取
                if (requestCode == RESULT_OK) {
                    try {
                        String filePath = PhotoUtils.getRealPathFromURI(this.getContext(), data.getData());
                        Glide.with(this).load(filePath).into(iv_appbar);
                        SPHelper.with(this.getContext()).setString(SPHelper.STRING_USER, filePath);//保存图片路径
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
