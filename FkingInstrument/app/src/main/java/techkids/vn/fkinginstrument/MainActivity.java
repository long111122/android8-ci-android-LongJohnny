package techkids.vn.fkinginstrument;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import techkids.vn.fkinginstrument.sound.SoundManager;
import techkids.vn.fkinginstrument.touches.Touch;
import techkids.vn.fkinginstrument.touches.TouchManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "abc";
    private Thread loop;
    private ImageView imgView1;
    private ImageView imgView2;
    private ImageView imgView3;
    private ImageView imgView4;
    private ImageView imgView5;
    private Button btnRecord;
    private String FILE;
    private MediaRecorder record;
    private TextView txtView;
    private List<PressKeyInfo> pressKeyInfoList;
    private List<PressKeyInfo> ivViewListEven;
    private List<PressKeyInfo> ivViewListOdd;
    private MediaPlayer song1;
    private MediaPlayer song2;
    private MediaPlayer song3;
    private MediaPlayer play;

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

        btnRecord = (Button) findViewById(R.id.record);
        FILE = Environment.getExternalStorageDirectory() + "/tempRecord.3gp";
        txtView = (TextView) findViewById(R.id.txtView);

        ivViewListEven = new ArrayList<>();
        ivViewListOdd = new ArrayList<>();
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton6), MediaPlayer.create(MainActivity.this, R.raw.sound_1)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton7), MediaPlayer.create(MainActivity.this, R.raw.sound_2)));
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton8), MediaPlayer.create(MainActivity.this, R.raw.sound_4)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton9), MediaPlayer.create(MainActivity.this, R.raw.sound_5)));
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton10), MediaPlayer.create(MainActivity.this, R.raw.sound_6)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton11), MediaPlayer.create(MainActivity.this, R.raw.sound_6)));
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton12), MediaPlayer.create(MainActivity.this, R.raw.sound_7)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton13), MediaPlayer.create(MainActivity.this, R.raw.sound_8)));
        ivViewListEven.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton14), MediaPlayer.create(MainActivity.this, R.raw.sound_9)));
        ivViewListOdd.add(new PressKeyInfo((ImageView) findViewById(R.id.imageButton15), MediaPlayer.create(MainActivity.this, R.raw.sound_10)));

        pressKeyInfoList = new ArrayList<>();
        SoundManager.loadSoundIntoList(this);
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

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnRecord.getText().toString().equals("Record")){
                    try {
                        startRecord();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    txtView.setText("Recording...");
                    btnRecord.setText("End");
                } else if(btnRecord.getText().toString().equals("End")){
                    stopRecord();
                    btnRecord.setText("Play");
                } else if(btnRecord.getText().toString().equals("Play")){
                    try {
                        startPlayback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    btnRecord.setText("Stop");
                } else {
                    stopPlayback();
                    btnRecord.setText("Record");
                }
            }
        });
    }
    //old onTouch
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        for (int pointerIndex = 0; pointerIndex < event.getPointerCount(); pointerIndex++) {
//            int pointerId = event.getPointerId(pointerIndex);
//            float pointerX = event.getX(pointerIndex);
//            float pointerY = event.getY(pointerIndex);
//            int pointerAction = event.getActionMasked();
//            if (pointerAction == MotionEvent.ACTION_MOVE) {
//                for (int i = 0; i < pressKeyInfoList.size(); i++) {
//                    PressKeyInfo pressKeyInfo = pressKeyInfoList.get(i);
//                    if (pressKeyInfo.getPressKeyId() == pointerId && !isInside(pointerX, pointerY, pressKeyInfo.getIvView())) {
//                        pressKeyInfoList.remove(i);
//                        setPress(pressKeyInfo, false);
//                    }
//                }
////                for (PressKeyInfo pressKeyInfo : pressKeyInfoList){
////                    if(!containsKeyInfoWith(pressKeyInfo.getIvView())){
////                        new PressKeyInfo(pointerId, pressKeyInfo.getIvView());
////                        setPress(pressKeyInfo, true);
////                    }
////                }
//            }
//
//            PressKeyInfo pressKey = findPressKey(pointerX, pointerY);
//            if (pressKey != null) {
//                if (pointerAction == MotionEvent.ACTION_DOWN || pointerAction == MotionEvent.ACTION_POINTER_DOWN || pointerAction == MotionEvent.ACTION_MOVE) {
//                    if (!containsKeyInfoWith(pressKey.getIvView())) {
//                        pressKeyInfoList.add(new PressKeyInfo(pointerId, pressKey.getIvView()));
//                    }
//                    setPress(pressKey, true);
//                }
//                if (pointerAction == MotionEvent.ACTION_UP || pointerAction == MotionEvent.ACTION_POINTER_UP) {
//                    for (int i = 0; i < pressKeyInfoList.size(); i++) {
//                        PressKeyInfo pressKeyInfo = pressKeyInfoList.get(i);
//                        if (pressKeyInfo.getPressKeyId() == pointerId) {
//                            pressKeyInfoList.remove(i);
//                        }
//                        setPress(pressKeyInfo, false);
//                    }
//                }
//            }
//        }
//        return super.onTouchEvent(event);
//    }

    //New Touch

    private void startRecord() throws Exception {
        if(record != null){
            record.release();
        }
        File fileOut = new File(FILE);
        if(fileOut.exists()){
            fileOut.delete();
        }
        record = new MediaRecorder();
        record.setAudioSource(MediaRecorder.AudioSource.MIC);
        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        record.setAudioEncodingBitRate(16);
        record.setAudioSamplingRate(44100);
        record.setOutputFile(FILE);
        record.prepare();
        record.start();
    }

    private void stopRecord()  {
        if(record != null) {
            try {
                record.stop();

            } catch(RuntimeException stopException){

            }
        }
    }

    private void startPlayback() throws Exception {
        if(play != null){
            play.stop();
            play.release();
        }
        play = new MediaPlayer();
        play.setDataSource(FILE);
        play.prepare();
        play.start();
        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                play.release();
            }
        });
    }

    private void stopPlayback(){
        if(play != null){
            play.stop();
        }
    }

    private void playSong(PressKeyInfo pressKey){
        play = pressKey.getSong();
        play.start();
        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                play.release();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        List<Touch> touches = TouchManager.toTouches(event);
        if(touches.size() > 0){
            Touch firstTouch = touches.get(0);
            if(firstTouch.getAction() == MotionEvent.ACTION_DOWN || firstTouch.getAction() == MotionEvent.ACTION_POINTER_DOWN){
                PressKeyInfo pressKey = findPressKey(firstTouch);
                if(pressKey != null) {
                    if (!containsKeyInfoWith(pressKey.getIvView())) {
                        pressKeyInfoList.add(new PressKeyInfo(firstTouch.getId() ,pressKey.getIvView()));
//                        SoundManager.PlaySound(pressKey.getIvView().getTag().toString());
                        playSong(pressKey);
//                        pressKey.getSong().start();
                        setPress(pressKey, true);

                    }
                }
            } else if(firstTouch.getAction() == MotionEvent.ACTION_UP || firstTouch.getAction() == MotionEvent.ACTION_POINTER_UP){
                Iterator<PressKeyInfo> infoIterator = pressKeyInfoList.iterator();
                while(infoIterator.hasNext()){
                    PressKeyInfo pressKeyInfo = infoIterator.next();
                    if(firstTouch.getId() == pressKeyInfo.getPressKeyId()){
                        setPress(pressKeyInfo, false);
                        infoIterator.remove();
                    }
                }
            } else if(firstTouch.getAction() == MotionEvent.ACTION_MOVE){
                for (Touch touch : touches){
                    PressKeyInfo pressKey = findPressKey(touch);
                    if(pressKey != null && !containsKeyInfoWith(pressKey.getIvView())){
                        pressKeyInfoList.add(new PressKeyInfo(touch.getId(), pressKey.getIvView()));
                        SoundManager.PlaySound(pressKey.getIvView().getTag().toString());
                        setPress(pressKey, true);
                    }

                    Iterator<PressKeyInfo> infoIterator = pressKeyInfoList.iterator();
                    while(infoIterator.hasNext()){
                        PressKeyInfo pressKeyInfo = infoIterator.next();
                        if(pressKeyInfo != null) {
                            if (touch.getId() == pressKeyInfo.getPressKeyId() && !touch.isInside(pressKeyInfo.getIvView())) {
                                infoIterator.remove();
                                setPress(pressKeyInfo, false);
                            }
                        }
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
//            if(!pressKeyInfo.getSong().isPlaying()){
//                pressKeyInfo.getSong().start();
//            }
        }
        else{
            pressKeyInfo.getIvView().setImageResource(R.drawable.btn2);
        }
    }

    private boolean containsKeyInfoWith(ImageView ivKey){
        for (PressKeyInfo pressKey : pressKeyInfoList){
            if(pressKey.getIvView() == ivKey){
                return true;
            }
        }
        return false;
    }

    private PressKeyInfo findPressKey(Touch touch){
        for (PressKeyInfo pressKeyInfo : ivViewListEven){
            if(touch.isInside(pressKeyInfo.getIvView())){
                return pressKeyInfo;
            }
        }
        for (PressKeyInfo pressKeyInfo : ivViewListOdd){
            if(touch.isInside(pressKeyInfo.getIvView())){
                return pressKeyInfo;
            }
        }
        return null;
    }
}
