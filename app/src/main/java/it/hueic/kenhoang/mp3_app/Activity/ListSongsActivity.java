package it.hueic.kenhoang.mp3_app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.hueic.kenhoang.mp3_app.Adapter.ListSongAdapter;
import it.hueic.kenhoang.mp3_app.Common.Common;
import it.hueic.kenhoang.mp3_app.Model.Song;
import it.hueic.kenhoang.mp3_app.R;

public class ListSongsActivity extends AppCompatActivity {
    TextView title, subTitle;
    RecyclerView recycler_songs;
    RecyclerView.LayoutManager layoutManager;
    ListSongAdapter adapter;
    List<Song> songs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);
        //Setup Toolbar
        setupToolbar();
        //InitView
        initView();
        //Data fake
        //fakeData();
        songs = Common.songslist;
        adapter = new ListSongAdapter(songs, this);
        recycler_songs.setAdapter(adapter);
    }

    private void fakeData() {
        songs.add(new Song("1", "Shape of you", "Ed Sheeran", ""));
        songs.add(new Song("2", "Overground", "James Blake", ""));
        songs.add(new Song("3", "Hello", "Adele", ""));
        songs.add(new Song("4", "I am sold", "James Blake", ""));
        songs.add(new Song("5", "You're my best friend", "The Once", ""));
        songs.add(new Song("6", "Our Loves Come Back", "James Blake", ""));
        songs.add(new Song("7", "Cross My Mind", "Twin Forks", ""));
        songs.add(new Song("8", "Rosana (Single Ver.)", "Toto", ""));
        songs.add(new Song("9", "1974", "Mondo Grosso", ""));
        songs.add(new Song("10", "I DO", "911 Band", ""));
        songs.add(new Song("11", "I Wanna Grow Old With You", "Westlife", ""));
    }

    private void initView() {
        recycler_songs = findViewById(R.id.recycler_songs);
        layoutManager = new LinearLayoutManager(this);
        recycler_songs.setLayoutManager(layoutManager);
        recycler_songs.setHasFixedSize(true);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subtitle);
        title.setText("Lists song");
        subTitle.setText("My list");
        //toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handle late
            }
        });
        setSupportActionBar(toolbar);
    }
}
