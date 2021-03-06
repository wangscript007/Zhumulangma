package com.gykj.zhumulangma.home.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gykj.zhumulangma.common.Constants;
import com.gykj.zhumulangma.common.event.KeyCode;
import com.gykj.zhumulangma.common.util.RouteHelper;
import com.gykj.zhumulangma.home.R;


/**
 * Created by 10719
 * on 2019/6/11
 */
public class AlbumCategoryItem extends ConstraintLayout {

    private int icon;
    private String title = "分类一";
    private boolean centerLine;

    public AlbumCategoryItem(@NonNull Context context) {
        this(context, null);
    }

    public AlbumCategoryItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlbumCategoryItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlbumCategoryItem);
        getAttrs(typedArray);
        typedArray.recycle();

        LayoutInflater.from(context).inflate(R.layout.home_widget_category_item, this);

        TextView tvTitle = findViewById(R.id.tv_title);
        if (centerLine) {
            tvTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }

        ImageView ivIcon = findViewById(R.id.iv_icon);
        tvTitle.setText(title);
        ivIcon.setImageResource(icon);
        if (!centerLine) {
            this.setOnClickListener(view ->
                    RouteHelper.navigateTo(ARouter.getInstance().build(Constants.Router.Home.F_ALBUM_LIST)
                            .withInt(KeyCode.Home.CATEGORY, getTag() == null ? 3 : Integer.parseInt(getTag().toString()))
                            .withString(KeyCode.Home.TITLE, title)));
        }
    }

    private void getAttrs(TypedArray typedArray) {
        icon = typedArray.getResourceId(R.styleable.AlbumCategoryItem_aci_icon, icon);
        title = typedArray.getString(R.styleable.AlbumCategoryItem_aci_title);
        centerLine = typedArray.getBoolean(R.styleable.AlbumCategoryItem_aci_center_line, false);
    }

}
