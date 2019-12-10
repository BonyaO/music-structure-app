package com.bonya.musicapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonya.musicapp.R;
import com.bonya.musicapp.SongPlayingActivity;
import com.bonya.musicapp.adapters.AlbumAdapter;
import com.bonya.musicapp.models.Song;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {

    RecyclerView albumsRecView;
    ArrayList<Song> allSongs = new ArrayList<>();

    public AlbumsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_albums, container, false);
        albumsRecView = rootView.findViewById(R.id.album_rec_view);
        //Dummy data
        allSongs.add(new Song("Find your way back","Beyonce","The Lion King: The Gift", "Pop",165));
        allSongs.add(new Song("Another in the Fire","Hillsong United","People", "Gospel",502));
        allSongs.add(new Song("It's Time","LSD","LSD", "Pop",311));
        allSongs.add(new Song("My Love","Sia","The Twilight Saga: Eclipse", "Dance",311));
        allSongs.add(new Song("Titanium","David Guetta","Single", "Dance",245));
        allSongs.add(new Song("Untouchable","Eminem","Revial", "Pop",370));
        allSongs.add(new Song("You Have Been Loved","Sia","Some People Have Real Problems", "Pop",263));
        allSongs.add(new Song("This is America","Childis Gambino","Awaken! My Love", "Pop",244));
        allSongs.add(new Song("On Est Bien","Jovi","16 Wives", "Mboko",192));
        allSongs.add(new Song("Dangote","Burna Boy","African Giant", "African Fusion",225));
        allSongs.add(new Song("White is in the Winter Night","Enya","And Winter Came", "New Age",180));
        allSongs.add(new Song("Carribean Blue","Enya","Shepard Moons", "New Age",238));

        AlbumAdapter albumAdapter = new AlbumAdapter(allSongs, new AlbumAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Song song) {
                Intent intent = new Intent(getContext(), SongPlayingActivity.class);
                intent.putExtra("SONG_ALBUM", song);
                startActivity(intent);
            }
        });
        albumsRecView.setItemAnimator(new DefaultItemAnimator());
        albumsRecView.setAdapter(albumAdapter);

        return rootView;

    }



}
