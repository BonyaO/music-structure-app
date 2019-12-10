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
import com.bonya.musicapp.adapters.GenreAdapter;
import com.bonya.musicapp.models.Song;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenresFragment extends Fragment {

    RecyclerView songsRecView;
    ArrayList<Song> allSongs = new ArrayList<>();
    public GenresFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_genres, container, false);
        songsRecView = rootView.findViewById(R.id.song_rec_view);
        songsRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Dummy data
        allSongs.add(new Song("Find your way back","Beyonce","The Lion King: The Gift", "Pop",165));
        allSongs.add(new Song("Another in the Fire","Hillsong United","People", "Gospel",502));
        allSongs.add(new Song("My Love","Sia","The Twilight Saga: Eclipse", "Dance",311));
        allSongs.add(new Song("Untouchable","Eminem","Revial", "Pop",370));
        allSongs.add(new Song("River (feat. Ed Sheeran)","Eminem","Revival", "Hip Hop",221));
        allSongs.add(new Song("Slave Ships","Jovi","16 Wives", "Mboko",217));
        allSongs.add(new Song("ANy Body","Burna Boy","African Giant", "African Fusion",188));
        allSongs.add(new Song("White is in the Winter Night","Enya","And Winter Came", "New Age",180));

        GenreAdapter songAdapter = new GenreAdapter(allSongs, new GenreAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Song song) {
                Intent intent = new Intent(getContext(), SongPlayingActivity.class);
                intent.putExtra("SONG_GENRE", song);
                startActivity(intent);
            }
        });
        songsRecView.setAdapter(songAdapter);
        songsRecView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }
}
