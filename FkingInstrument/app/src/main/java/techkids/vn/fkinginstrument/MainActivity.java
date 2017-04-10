package techkids.vn.fkinginstrument;

import android.media.Image;
import android.media.MediaPlayer;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "abc";
    private Thread loop;
    private ImageView imgView1;
    private ImageView imgView2;
    private ImageView imgView3;
    private ImageView imgView4;
    private ImageView imgView5;
    private List<PressKeyInfo> pressKeyInfoList;
    private List<PressKeyInfo> ivViewListEven;
    private List<PressKeyInfo> ivViewListOdd;
    private MediaPlayer song1;
    private MediaPlayer song2;
    private MediaPlayer song3;

    class PressKeyInfo {
        private ImageView ivView;
        private int pressKeyId;
        private MediaPlayer song;

        public PressKeyInfo(ImageView ivView, MediaPlayer song) {
            this.ivView = ivView;
            this.song = song;
        }

        public PressKeyInfo(int pressKeyId, ImageView ivView) {
            this.pressKeyId = pressKeyId;
            this.ivView = ivView;
        }

        public ImageView getIvView() {
            return ivView;
        }

        public int getPressKeyId() {
            return pressKeyId;
        }

        public MediaPlayer getSong() {
            return song;
        }
    }

    public void setDefault() {
        imgView1 = (ImageView) findViewById(R.id.imageButton1);
        imgView2 = (ImageView) findViewById(R.id.imageButton2);
        imgView3 = (ImageView) findViewById(R.id.imageButton3);
        imgView4 = (ImageView) findViewById(R.id.imageButton4);
        imgView5 = (ImageView) findViewById(R.id.imageButton5);

        ivViewListEven = new ArrayList<>();
        ivViewListOdd = new ArrayList<>();
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton6), MediaPlayer.create(MainActivity.this, R.raw.fa)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton7), MediaPlayer.create(MainActivity.this, R.raw.sol)));
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton8), MediaPlayer.create(MainActivity.this, R.raw.animalsolt)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton9), MediaPlayer.create(MainActivity.this, R.raw.animallat)));
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton10), MediaPlayer.create(MainActivity.this, R.raw.doo)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton11), MediaPlayer.create(MainActivity.this, R.raw.mit)));
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton12), MediaPlayer.create(MainActivity.this, R.raw.animalsolt)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton13), MediaPlayer.create(MainActivity.this, R.raw.animalsolt)));
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton14), MediaPlayer.create(MainActivity.this, R.raw.animalsolt)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton15), MediaPlayer.create(MainActivity.this, R.raw.animalsolt)));

        pressKeyInfoList = new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefault();

        imgView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song1 != null) {
                    song1.stop();
                    song1.release();
                    song1 = null;
                    imgView1.setImageResource(R.drawable.btn2);
                } else {
                    song1 = MediaPlayer.create(MainActivity.this, R.raw.toulouse);
                    imgView1.setImageResource(R.drawable.btn3);
                    song1.start();
                }
            }
        });


        imgView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song2 != null) {
                    song2.stop();
                    song2.release();
                    song2 = null;
                    imgView2.setImageResource(R.drawable.btn2);
                } else {
                    song2 = MediaPlayer.create(MainActivity.this, R.raw.mixbass);
                    imgView2.setImageResource(R.drawable.btn3);
                    song2.start();
                }
            }
        });

        imgView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song3 != null) {
                    song3.stop();
                    song3.release();
                    song3 = null;
                    imgView3.setImageResource(R.drawable.btn2);
                } else {
                    song3 = MediaPlayer.create(MainActivity.this, R.raw.bass2);
                    imgView3.setImageResource(R.drawable.btn3);
                    song3.start();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (int pointerIndex = 0; pointerIndex < event.getPointerCount(); pointerIndex++) {
            int pointerId = event.getPointerId(pointerIndex);
            float pointerX = event.getX(pointerIndex);
            float pointerY = event.getY(pointerIndex);
            int pointerAction = event.getActionMasked();
            if (pointerAction == MotionEvent.ACTION_MOVE) {
                for (int i = 0; i < pressKeyInfoList.size(); i++) {
                    PressKeyInfo pressKeyInfo = pressKeyInfoList.get(i);
                    if (pressKeyInfo.getPressKeyId() == pointerId && !isInside(pointerX, pointerY, pressKeyInfo.getIvView())) {
                        pressKeyInfoList.remove(i);
                        setPress(pressKeyInfo, false);
                    }
                }
            }

            PressKeyInfo pressKey = findPressKey(pointerX, pointerY);
            if (pressKey != null) {
                if (pointerAction == MotionEvent.ACTION_DOWN || pointerAction == MotionEvent.ACTION_POINTER_DOWN || pointerAction == MotionEvent.ACTION_MOVE) {
                    if (!containsKeyInfoWith(pressKey.getIvView())) {
                        pressKeyInfoList.add(new PressKeyInfo(pointerId, pressKey.getIvView()));
                    }
                    setPress(pressKey, true);
                }
                if (pointerAction == MotionEvent.ACTION_UP || pointerAction == MotionEvent.ACTION_POINTER_UP) {
                    for (int i = 0; i < pressKeyInfoList.size(); i++) {
                        PressKeyInfo pressKeyInfo = pressKeyInfoList.get(i);
                        if (pressKeyInfo.getPressKeyId() == pointerId) {
                            pressKeyInfoList.remove(i);
                        }
                        setPress(pressKeyInfo, false);
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private void setPress(PressKeyInfo pressKeyInfo, boolean isPress){
        if(isPress){
            if(ivViewListOdd.contains(pressKeyInfo)){
                pressKeyInfo.getIvView().setImageResource(R.drawable.btn1);
            }
            if(ivViewListEven.contains(pressKeyInfo)){
                pressKeyInfo.getIvView().setImageResource(R.drawable.btn4);
            }
            if(!pressKeyInfo.getSong().isPlaying()){
                pressKeyInfo.getSong().start();
            }
        }
        else{
            pressKeyInfo.getIvView().setImageResource(R.drawable.btn2);
        }
    }

    private boolean containsKeyInfoWith(ImageView imageView){
        for (PressKeyInfo pressKeyInfo : pressKeyInfoList){
            if(pressKeyInfo.getIvView() == imageView){
                return true;
            }
        }
        return false;
    }

    private boolean isInside(float x, float y, View v){
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int left = location[0];
        int right = left + v.getWidth();
        int top = location[1];
        int bottom = top + v.getHeight();
        return (x > left && x < right && y > top && y < bottom);
    }

    private PressKeyInfo findPressKey(float pointerX, float pointerY){
        for (PressKeyInfo pressKeyInfo : ivViewListEven){
            if(isInside(pointerX, pointerY, pressKeyInfo.getIvView())){
                return pressKeyInfo;
            }
        }
        for (PressKeyInfo pressKeyInfo : ivViewListOdd){
            if(isInside(pointerX, pointerY, pressKeyInfo.getIvView())){
                return pressKeyInfo;
            }
        }
        return null;
    }
}
