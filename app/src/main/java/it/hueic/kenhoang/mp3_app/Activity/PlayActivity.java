package it.hueic.kenhoang.mp3_app.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import it.hueic.kenhoang.mp3_app.Common.Common;
import it.hueic.kenhoang.mp3_app.Model.Song;
import it.hueic.kenhoang.mp3_app.R;
import it.hueic.kenhoang.mp3_app.Util.Util;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, CircularSeekBar.OnCircularSeekBarChangeListener, MediaPlayer.OnCompletionListener {
    public static final String TAG = PlayActivity.class.getSimpleName();
    public static final int SELECT_SONG_REQUEST = 0;
    TextView title, subTitle, timeSong;
    CircleImageView imgSongCircle;
    CircularSeekBar seekBarProcess;
    ImageView btnShuffle, btnPrev, btnPlay, imgPlay, btnNext, btnRepeat;
    private int seekForwardTime = 5000;
    private int seekBackwardTime = 5000;
    private int currentSongIndex = 0;
    //MediaPlayer
    private MediaPlayer mp;
    //Handler to update UI timer, progress bar etc,...
    private Handler mHandler = new Handler();
    //List song
    ArrayList<Song> songs = Common.songslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_activity);
        setUpToolBar();
        if (getIntent() != null) currentSongIndex = getIntent().getIntExtra("id", 0);
        //Init View
        initView();
        //Init Event
        initEvents();
    }

    private void initEvents() {
        //On click next or prev music
        //On Long click forward or backward 5000
        btnPrev.setOnClickListener(this);
        btnPrev.setOnLongClickListener(this);
        btnNext.setOnClickListener(this);
        btnNext.setOnLongClickListener(this);
        //Play
        btnPlay.setOnClickListener(this);
        //Shuffle
        btnShuffle.setOnClickListener(this);
        //Loop
        btnRepeat.setOnClickListener(this);
        //Proccess Seekbar
        seekBarProcess.setOnSeekBarChangeListener(this);
        //MediaPlayer
        mp.setOnCompletionListener(this);
        playSong(currentSongIndex);
    }

    private void playSong(int currentSongIndex) {
        //Play song
        try {
            mp.reset();
            mp.setDataSource(songs.get(currentSongIndex).getUrl());
            mp.prepare();
            mp.start();
            //Update title of toolbar
            title.setText(songs.get(currentSongIndex).getName());
            subTitle.setText(songs.get(currentSongIndex).getUrl());
            //Changing Image to pause image
            imgPlay.setImageResource(R.drawable.pause);
            //set Progressbar values
            seekBarProcess.setProgress(0);
            seekBarProcess.setMax(100);
            //Updating progress bar
            updateProgressBar();
            //notification
            buildNotification();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildNotification() {
        Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getService(getApplicationContext(), 1, intent, 0);
        NotificationCompat.Builder  builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_queue_music_black_24dp)
                .setContentTitle("MP3-KenHoang")
                .setContentText(songs.get(currentSongIndex).getName())
                .setDeleteIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    /**
     * Update timer on seekbar
     */
    private void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runable thread
     * @param mUpdateTimeTask
     */
    private Runnable mUpdateTimeTask =  new Runnable() {
        @Override
        public void run() {
            try {
                long totalDuration = mp.getDuration();
                long currentDuration = mp.getCurrentPosition();
                //Displaying Total Duration time
                //tvTotalDuration.setText("" + Util.milisecondsToTimer(totalDuration));
                timeSong.setText("" + Util.milisecondsToTimer(currentDuration));
                //Updating progress bar
                int progress = Util.getProgressPercentage(currentDuration, totalDuration);
                seekBarProcess.setProgress(progress);
                //Running this thread after 100 milliseconds
                mHandler.postDelayed(this, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    private void initView() {
        imgSongCircle = findViewById(R.id.imgSongCircle);
        setAnimationRotate();
        //Media Player
        mp = new MediaPlayer();
        btnShuffle = findViewById(R.id.btnShuffle);
        btnPrev = findViewById(R.id.btnPrev);
        btnPlay = findViewById(R.id.btnPlay);
        btnNext = findViewById(R.id.btnNext);
        btnRepeat = findViewById(R.id.btnRepeat);
        imgPlay = findViewById(R.id.imgPlay);
        timeSong = findViewById(R.id.timeSong);
        seekBarProcess = findViewById(R.id.seekbarProcess);
    }

    private void setAnimationRotate() {
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotation.setFillAfter(true);
        imgSongCircle.startAnimation(rotation);
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subtitle);
        title.setText("Albums");
        subTitle.setText("6 Pop Music Albums");
        //toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handle late
            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                /**
                 * Play button click event
                 * plays a song and changes button to pause image
                 * pauses a song and changes button to play image
                 */
                if (mp.isPlaying()) {
                    if (mp != null) {
                        mp.pause();
                        //Changing button image to play Button
                        imgPlay.setImageResource(R.drawable.play);
                    }
                } else {
                    //Resume song
                    if (mp != null) {
                        mp.start();
                        imgPlay.setImageResource(R.drawable.pause);
                    }
                }
                break;
            case R.id.btnPrev:
                //get current song position
                int currentPositionRewind = mp.getCurrentPosition();
                if (currentPositionRewind - seekBackwardTime >= 0) {
                    mp.seekTo(currentPositionRewind - seekBackwardTime);
                } else {
                    mp.seekTo(0);
                }
                break;
            case R.id.btnNext:
                //get current song position
                int currentPosition = mp.getCurrentPosition();
                //check if seekForward time is lesser than song duration
                if (currentPosition + seekForwardTime <= mp.getDuration()) {
                    mp.seekTo(currentPosition + seekForwardTime);
                } else {
                    //forward to end position
                    mp.seekTo(mp.getDuration());
                }
                break;

        }
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.btnPrev:
                if (currentSongIndex > 0) {
                    playSong(currentSongIndex - 1);
                    currentSongIndex = currentSongIndex - 1;
                } else {
                    playSong(songs.size() - 1);
                    currentSongIndex = songs.size() - 1;
                }
                buildNotification();
                break;
            case R.id.btnNext:
                //checck if next song is there or not
                if (currentSongIndex < (songs.size() - 1)) {
                    playSong(currentSongIndex + 1);
                    currentSongIndex = currentSongIndex + 1;
                } else {
                    playSong(0);
                    currentSongIndex = 0;
                }
                buildNotification();
                break;
        }
        return true;
    }

    @Override
    public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {

    }

    @Override
    public void onStopTrackingTouch(CircularSeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(CircularSeekBar seekBar) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (currentSongIndex < (songs.size() - 1)) {
            playSong(currentSongIndex + 1);
            currentSongIndex = currentSongIndex + 1;
        } else {
            playSong(0);
            currentSongIndex = 0;
        }
        buildNotification();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_listsong:
                Intent intent = new Intent(this, ListSongsActivity.class);
                startActivityForResult(intent, SELECT_SONG_REQUEST);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_SONG_REQUEST && resultCode == RESULT_OK) {
            currentSongIndex = data.getExtras().getInt("id");
            playSong(currentSongIndex);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }
}
