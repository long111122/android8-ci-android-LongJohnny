package techkids.vn.fkinginstrument;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Thread loop;
    private ImageView imgView1;
    private ImageView imgView2;
    private ImageView imgView3;
    private ImageView imgView4;
    private ImageView imgView5;
    private ImageView imgView6;
    private ImageView imgView7;
    private ImageView imgView8;
    private ImageView imgView9;
    private ImageView imgView10;
    private ImageView imgView11;
    private ImageView imgView12;
    private ImageView imgView13;
    private ImageView imgView14;
    private ImageView imgView15;
    private MediaPlayer song1;
    private MediaPlayer song2;
    private MediaPlayer song3;

    public void setDefault(){
        imgView1 = (ImageView) findViewById(R.id.imageButton1);
        imgView2 = (ImageView) findViewById(R.id.imageButton2);
        imgView3 = (ImageView) findViewById(R.id.imageButton3);
        imgView4 = (ImageView) findViewById(R.id.imageButton4);
        imgView6 = (ImageView) findViewById(R.id.imageButton6);
        imgView7 = (ImageView) findViewById(R.id.imageButton7);
        imgView8 = (ImageView) findViewById(R.id.imageButton8);
        imgView9 = (ImageView) findViewById(R.id.imageButton9);
        imgView11 = (ImageView) findViewById(R.id.imageButton11);
        imgView12 = (ImageView) findViewById(R.id.imageButton12);
        imgView13 = (ImageView) findViewById(R.id.imageButton13);
        imgView14 = (ImageView) findViewById(R.id.imageButton14);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefault();

        imgView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song1 != null){
                    song1.stop();
                    song1.release();
                    song1 = null;
                    imgView1.setImageResource(R.drawable.btn2);
                }
                else {
                    song1 = MediaPlayer.create(MainActivity.this,  R.raw.toulouse);
                    imgView1.setImageResource(R.drawable.btn3);
                    song1.start();
                }
            }
        });


        imgView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song2 != null){
                    song2.stop();
                    song2.release();
                    song2 = null;
                    imgView2.setImageResource(R.drawable.btn2);
                }
                else {
                    song2 = MediaPlayer.create(MainActivity.this,  R.raw.mixbass);
                    imgView2.setImageResource(R.drawable.btn3);
                    song2.start();
                }
            }
        });

        imgView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song3 != null){
                    song3.stop();
                    song3.release();
                    song3 = null;
                    imgView3.setImageResource(R.drawable.btn2);
                }
                else {
                    song3 = MediaPlayer.create(MainActivity.this,  R.raw.mixbass);
                    imgView3.setImageResource(R.drawable.btn3);
                    song3.start();
                }
            }
        });
    }
}
