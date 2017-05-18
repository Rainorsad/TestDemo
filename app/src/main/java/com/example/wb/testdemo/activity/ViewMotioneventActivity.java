package com.example.wb.testdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Zhangchen on 2017/3/20.
 */

public class ViewMotioneventActivity extends AppCompatActivity {

    @InjectView(R.id.viewmontion_oldx)
    TextView tvoldx;
    @InjectView(R.id.viewmontion_oldy)
    TextView tvoldy;
    @InjectView(R.id.viewmontion_x)
    TextView tvnewx;
    @InjectView(R.id.viewmontion_newy)
    TextView tvnewy;
    @InjectView(R.id.viewmontionview)
    View vi;
    @InjectView(R.id.viewmontionview_rela)
    LinearLayout viewlinmain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmotionevent);
        ButterKnife.inject(this);
        setMovienTion();
    }

    private void setMovienTion() {

    }

    @OnClick(R.id.viewmontionview)
    public void onClick() {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tvoldx.setText(x + "   ");
                tvoldy.setText(y + "   ");
                oldx = x;
                oldy = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int movex = x - oldx;
                int movey = y - oldy;
                if ((vi.getLeft() + movex) >= (viewlinmain.getLayoutParams().width - vi.getLayoutParams().width)) {
                    movex = viewlinmain.getLayoutParams().width - vi.getRight();
                }
                if ((vi.getTop() + movey) >= (viewlinmain.getLayoutParams().height - vi.getLayoutParams().height)) {
                    movey = viewlinmain.getLayoutParams().height - vi.getBottom();
                }

                //方法1 缺陷：view滑到父布局之外后会随机重新布值
//                vi.layout(vi.getLeft() + movex,vi.getTop() + movey,vi.getRight() + movex,vi.getBottom() + movey);

                //方法2
//                vi.offsetLeftAndRight(movex);
//                vi.offsetTopAndBottom(movey);

                //方法3
//                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) vi.getLayoutParams();
//                params.leftMargin = vi.getLeft() + movex;
//                params.topMargin = vi.getTop() + movey;
//                vi.setLayoutParams(params);

                //方法4 scrollto scrollby 移动的是view的content的内容
                ((View)vi.getParent()).scrollBy(-movex,-movey);
                oldx = x;
                oldy = y;
                break;
            case MotionEvent.ACTION_UP:
                tvnewx.setText(x + "   ");
                tvnewy.setText(y + "   ");
                int movex1 = 10;
                int movey2 = 10;
                break;
        }
        return true;
    }

    private int oldx;
    private int oldy;
    private int newx;
    private int newy;
}
