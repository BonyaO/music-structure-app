package com.bonya.musicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bonya.musicapp.adapters.SongPlayingAdapter;
import com.bonya.musicapp.models.Song;

import java.util.ArrayList;

public class SongPlayingActivity extends AppCompatActivity {

    ArrayList<Song> songsPlaying = new ArrayList<>();
    ArrayList<Song> allSongs = new ArrayList<>();
    TextView timeElapseTV;
    TextView songTimeTV;
    ImageButton shuffleBtn;
    ImageButton previousBtn;
    ImageButton playBtn;
    ImageButton nextBtn;
    ImageButton repeatBtn;
    private Song mCurrentSong;
    private int currentPosition;
    private boolean shuffleIsOn = false;
    private boolean isPlaying = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_playing);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        initializeViews();

        //Dummy data
        allSongs.add(new Song("Find your way back", "Beyonce", "The Lion King: The Gift", "Pop", 165));
        allSongs.add(new Song("Another in the Fire", "Hillsong United", "People", "Gospel", 502));
        allSongs.add(new Song("As you find me", "Hillsong United", "People", "Gospel", 518));
        allSongs.add(new Song("It's Time", "LSD", "LSD", "Pop", 311));
        allSongs.add(new Song("Mountains", "LSD", "LSD", "Pop", 191));
        allSongs.add(new Song("My Love", "Sia", "The Twilight Saga: Eclipse", "Dance", 311));
        allSongs.add(new Song("Titanium", "David Guetta", "Single", "Dance", 245));
        allSongs.add(new Song("Untouchable", "Eminem", "Revial", "Pop", 370));
        allSongs.add(new Song("River (feat. Ed Sheeran)", "Eminem", "Revival", "Hip Hop", 221));
        allSongs.add(new Song("Tragic Endings (feat. Skyler Grey)", "Eminem", "Revival", "Hip Hop", 252));
        allSongs.add(new Song("You Have Been Loved", "Sia", "Some People Have Real Problems", "Pop", 263));
        allSongs.add(new Song("Soon We'll Be Found", "Sia", "Some People Have Real Problems", "Pop", 165));
        allSongs.add(new Song("This is America", "Childis Gambino", "Awaken! My Love", "Pop", 244));
        allSongs.add(new Song("Free Music", "Jovi", "16 Wives", "Mboko", 300));
        allSongs.add(new Song("On Est Bien", "Jovi", "16 Wives", "Mboko", 192));
        allSongs.add(new Song("Slave Ships", "Jovi", "16 Wives", "Mboko", 217));
        allSongs.add(new Song("Dangote", "Burna Boy", "African Giant", "African Fusion", 225));
        allSongs.add(new Song("ANy Body", "Burna Boy", "African Giant", "African Fusion", 188));
        allSongs.add(new Song("White is in the Winter Night", "Enya", "And Winter Came", "New Age", 180));
        allSongs.add(new Song("Carribean Blue", "Enya", "Shepard Moons", "New Age", 238));
        allSongs.add(new Song("Walk On Water", "Eminem, Beyonce", "Revival", "Hip Hop", 303));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();

        //The following lines handles data passed to this activity.
        //It checks if the intent extras are not null and then performs the appropriate actions
        if (intent.getParcelableArrayListExtra("Songs") != null) {
            songsPlaying.addAll(intent.<Song>getParcelableArrayListExtra("Songs"));
            mCurrentSong = songsPlaying.get(0);
        } else if (intent.getParcelableExtra("SONG") != null) {
            mCurrentSong = intent.getParcelableExtra("SONG");
            songsPlaying.add(mCurrentSong);
        } else if (intent.getParcelableExtra("SONG_ALBUM") != null) {
            mCurrentSong = intent.getParcelableExtra("SONG_ALBUM");
            for (Song song : allSongs) {
                if (mCurrentSong.getAlbum().equalsIgnoreCase(song.getAlbum())) {
                    songsPlaying.add(song);
                }
            }
        } else if (intent.getParcelableExtra("SONG_GENRE") != null) {
            mCurrentSong = intent.getParcelableExtra("SONG_GENRE");
            for (Song song : allSongs) {
                if (mCurrentSong.getGenre().equalsIgnoreCase(song.getGenre())) {
                    songsPlaying.add(song);
                }
            }
        } else if (intent.getParcelableExtra("SONG_ARTIST") != null) {
            mCurrentSong = intent.getParcelableExtra("SONG_ARTIST");
            for (Song song : allSongs) {
                if (mCurrentSong.getArtist().equalsIgnoreCase(song.getArtist())) {
                    songsPlaying.add(song);
                }
            }
        }
        final SongPlayingAdapter songPlayingAdapter = new SongPlayingAdapter(songsPlaying, new SongPlayingAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Song song) {
                currentPosition = songsPlaying.indexOf(song);
                mCurrentSong = song;
                updateActionBarTitle();

            }
        });
        currentPosition = songsPlaying.indexOf(mCurrentSong);
        updateActionBarTitle();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(songPlayingAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Applies appropriate actions depending on the cutton clicked
       View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.shuffle_button:
                        if(!shuffleIsOn){
                            Toast.makeText(getApplicationContext(), "Shuffle On", Toast.LENGTH_SHORT).show();
                            shuffleIsOn = true;
                        }else {
                            Toast.makeText(getApplicationContext(), "Shuffle off", Toast.LENGTH_SHORT).show();
                            shuffleIsOn = false;
                        }
                        break;
                    case R.id.previous_button:
                        if(currentPosition > 0){
                            mCurrentSong = songsPlaying.get(currentPosition - 1);
                            currentPosition--;
                            updateActionBarTitle();
                        }
                        break;
                    case R.id.play_pause_button:
                        if(isPlaying){
                            ((ImageButton) view).setImageResource(R.drawable.ic_play);
                            isPlaying = false;
                        } else{
                            ((ImageButton) view).setImageResource(R.drawable.ic_pause);
                            isPlaying = true;
                        }
                        break;
                    case R.id.next_button:
                        if(currentPosition < songsPlaying.size() - 1){
                            mCurrentSong = songsPlaying.get(currentPosition + 1);
                            currentPosition++;
                            updateActionBarTitle();
                        }
                        break;
                    case R.id.repeat_button:
                        Toast.makeText(getApplicationContext(), "Repeat On", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        shuffleBtn.setOnClickListener(listener);
        previousBtn.setOnClickListener(listener);
        playBtn.setOnClickListener(listener);
        nextBtn.setOnClickListener(listener);
        repeatBtn.setOnClickListener(listener);
    }

    private void updateActionBarTitle() {
        setTitle(mCurrentSong.getTitle());
        songTimeTV.setText(parseSongTimeToString(mCurrentSong.getPlaybackTimeInSeconds()));
    }

    private void initializeViews() {
        timeElapseTV = findViewById(R.id.play_time_elapsed);
        songTimeTV = findViewById(R.id.play_time);
        shuffleBtn = findViewById(R.id.shuffle_button);
        previousBtn = findViewById(R.id.previous_button);
        playBtn = findViewById(R.id.play_pause_button);
        nextBtn = findViewById(R.id.next_button);
        repeatBtn = findViewById(R.id.repeat_button);
    }

    public String parseSongTimeToString(int songTime){
        String result;
        result = (songTime - (songTime%60))/60 + ":" + songTime%60;
        return result;
    }

}
