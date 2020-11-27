package com.huoli.bbl.focusdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends Activity {

    FocusLayout mFocusLayout;//焦点层 实现OnGlobalFocusChangeListener接口
    View mContainerView;
    Button btn1,btn2,btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn1.setOnKeyListener(listener);
        btn2.setOnKeyListener(listener);
        btn3.setOnKeyListener(listener);
        btn4.setOnKeyListener(listener);
        btn5.setOnKeyListener(listener);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mFocusLayout = new FocusLayout(this);
        bindListener();//绑定焦点变化事件
        addContentView(mFocusLayout,
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));//添加焦点层
    }

    private void bindListener() {
        //获取根元素,.findViewById(android.R.id.content);
        mContainerView = this.getWindow().getDecorView();
        //将焦点放在全局
        mContainerView.setFocusable(true);
        //得到整个view树的viewTreeObserver
        ViewTreeObserver viewTreeObserver = mContainerView.getViewTreeObserver();
        //给观察者设置焦点变化监听
        viewTreeObserver.addOnGlobalFocusChangeListener(mFocusLayout);
    }

    private View.OnKeyListener listener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if( event.KEYCODE_ENTER == keyCode && event.getAction() == KeyEvent.ACTION_UP){
                //
                Log.i("demoKey","真按钮按键监听");
                keyActionListener(v);
            }
            return false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        View  focusView = mFocusLayout.getFocusView();
        Log.i("demoKey","按下Code:"+keyCode);
        View  focusView = mContainerView.findFocus();
        Log.i("demoKey","focusView = "+ (focusView instanceof Button));
        if( event.KEYCODE_DPAD_UP == keyCode ){  //如果按下的是上键
            Log.i("demoKey","按下UP");
        }
        if( event.KEYCODE_DPAD_DOWN == keyCode ){  //如果按下的是下键
            Log.i("demoKey","按下DOWN");
        }
        if( event.KEYCODE_DPAD_LEFT == keyCode ){  //如果按下的是左键
            Log.i("demoKey","按下LEFT");
        }
        if( event.KEYCODE_DPAD_RIGHT == keyCode ){  //如果按下的是右键
            Log.i("demoKey","按下RIGHT");
        }
        if( event.KEYCODE_ENTER == keyCode ){  //如果按下的是右键
            Log.i("demoKey","按下enter");
            if(!(focusView instanceof Button)){
                //属于button 键盘失效
                keyActionListener(mContainerView.findFocus());
            }
        }
        if( event.KEYCODE_DEL == keyCode ) {  //如果按下的是右键
            Log.i("demoKey", "按下del");
        }
        if( event.KEYCODE_BACK == keyCode ) {  //如果按下的是右键
            Log.i("demoKey", "按下back");
            Toast.makeText(this,"点击返回",Toast.LENGTH_SHORT).show();
        }
        if( event.KEYCODE_MENU== keyCode ) {  //如果按下的是右键
            Log.i("demoKey", "按下menu");
            Toast.makeText(this,"点击菜单",Toast.LENGTH_SHORT).show();
        }

        return super.onKeyDown(keyCode, event);
    }

    private void keyActionListener(View  focusView){
        Log.i("demoKey","点击确认");
        switch (focusView.getId()){
            case R.id.btn1:
                Toast.makeText(this,"当前点击按钮1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(this,"当前点击按钮2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                Toast.makeText(this,"当前点击按钮3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn4:
                Toast.makeText(this,"当前点击按钮4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn5:
                Toast.makeText(this,"当前点击按钮5",Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt1:
                Toast.makeText(this,"当前点击文本1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt2:
                Toast.makeText(this,"当前点击文本2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img1:
                Toast.makeText(this,"当前点击图片1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relayout:
                Toast.makeText(this,"当前点击组合框",Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
    }
}
