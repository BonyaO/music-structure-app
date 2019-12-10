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
import com.bonya.musicapp.adapters.SongAdapter;
import com.bonya.musicapp.models.Song;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TracksFragment extends Fragment {
    ArrayList<Song> allSongs = new ArrayList<>();
    RecyclerView songsRecView;

    public TracksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tracks, container, false);

        songsRecView = rootView.findViewById(R.id.song_rec_view);
        songsRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = rootView.findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SongPlayingActivity.class);
                intent.putParcelableArrayListExtra("Songs", allSongs);
                startActivity(intent);

            }
        });
        //Dummy data
        allSongs.add(new Song("Find your way back","Beyonce","The Lion King: The Gift", "Pop",165));
        allSongs.add(new Song("Another in the Fire","Hillsong United","People", "Gospel",502));
        allSongs.add(new Song("As you find me","Hillsong United","People", "Gospel",518));
        allSongs.add(new Song("It's Time","LSD","LSD", "Pop",311));
        allSongs.add(new Song("Mountains","LSD","LSD", "Pop",191));
        allSongs.add(new Song("My Love","Sia","The Twilight Saga: Eclipse", "Dance",311));
        allSongs.add(new Song("Titanium","David Guetta","Single", "Dance",245));
        allSongs.add(new Song("Untouchable","Eminem","Revial", "Pop",370));
        allSongs.add(new Song("River (feat. Ed Sheeran)","Eminem","Revival", "Hip Hop",221));
        allSongs.add(new Song("Tragic Endings (feat. Skyler Grey)","Eminem","Revival", "Hip Hop",252));
        allSongs.add(new Song("You Have Been Loved","Sia","Some People Have Real Problems", "Pop",263));
        allSongs.add(new Song("Soon We'll Be Found","Sia","Some People Have Real Problems", "Pop",165));
        allSongs.add(new Song("This is America","Childis Gambino","Awaken! My Love", "Pop",244));
        allSongs.add(new Song("Free Music","Jovi","16 Wives", "Mboko",300));
        allSongs.add(new Song("On Est Bien","Jovi","16 Wives", "Mboko",192));
        allSongs.add(new Song("Slave Ships","Jovi","16 Wives", "Mboko",217));
        allSongs.add(new Song("Dangote","Burna Boy","African Giant", "African Fusion",225));
        allSongs.add(new Song("ANy Body","Burna Boy","African Giant", "African Fusion",188));
        allSongs.add(new Song("White is in the Winter Night","Enya","And Winter Came", "New Age",180));
        allSongs.add(new Song("Carribean Blue","Enya","Shepard Moons", "New Age",238));
        allSongs.add(new Song("Walk On Water","Eminem, Beyonce","Revival", "Hip Hop",303));

        SongAdapter songAdapter = new SongAdapter(allSongs, new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Song song) {
                Intent intent = new Intent(getContext(), SongPlayingActivity.class);
                intent.putExtra("SONG", song);
                startActivity(intent);
            }
        });
        songsRecView.setAdapter(songAdapter);
        songsRecView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }
}
