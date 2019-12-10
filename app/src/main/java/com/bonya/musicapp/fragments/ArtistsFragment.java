package com.bonya.musicapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonya.musicapp.R;
import com.bonya.musicapp.SongPlayingActivity;
import com.bonya.musicapp.adapters.ArtistAdapter;
import com.bonya.musicapp.adapters.SongAdapter;
import com.bonya.musicapp.models.Song;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistsFragment extends Fragment {

    RecyclerView songsRecView;
    ArrayList<Song> songs = new ArrayList<>();

    public ArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_artists, container, false);
        songsRecView = rootView.findViewById(R.id.song_rec_view);
        songsRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Dummy data
        songs.add(new Song("Find your way back","Beyonce","The Lion King: The Gift", "Pop",165));
        songs.add(new Song("Another in the Fire","Hillsong United","People", "Gospel",502));
        songs.add(new Song("It's Time","LSD","LSD", "Pop",311));
        songs.add(new Song("My Love","Sia","The Twilight Saga: Eclipse", "Dance",311));
        songs.add(new Song("Titanium","David Guetta","Single", "Dance",245));
        songs.add(new Song("Untouchable","Eminem","Revial", "Pop",370));
        songs.add(new Song("You Have Been Loved","Sia","Some People Have Real Problems", "Pop",263));
        songs.add(new Song("This is America","Childis Gambino","Awaken! My Love", "Pop",244));
        songs.add(new Song("On Est Bien","Jovi","16 Wives", "Mboko",192));
        songs.add(new Song("Dangote","Burna Boy","African Giant", "African Fusion",225));
        songs.add(new Song("White is in the Winter Night","Enya","And Winter Came", "New Age",180));
        songs.add(new Song("Walk On Water","Eminem, Beyonce","Revival", "Hip Hop",303));
        ArtistAdapter songAdapter = new ArtistAdapter(songs, new ArtistAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(Song song) {
                Intent intent = new Intent(getContext(), SongPlayingActivity.class);
                intent.putExtra("SONG_ARTIST", song);
                startActivity(intent);
            }
        });
        songsRecView.setAdapter(songAdapter);
        songsRecView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }
}
