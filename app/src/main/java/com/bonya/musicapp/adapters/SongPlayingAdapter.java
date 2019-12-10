package com.bonya.musicapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bonya.musicapp.R;
import com.bonya.musicapp.models.Song;

import java.util.ArrayList;

public class SongPlayingAdapter extends RecyclerView.Adapter<SongPlayingAdapter.AlbumViewHolder> {
    private final ArrayList<Song> mSongs;
    private final OnItemClickListener listener;

    //Custom ClickListener
    public interface OnItemClickListener{
        void onItemClicked(Song song);
    }

    public SongPlayingAdapter(ArrayList<Song> songs, OnItemClickListener listener){
        this.mSongs = songs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.song_playing_item, parent, false);
        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bind(mSongs.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView songArtistTV;
        TextView songTimeTV;
        TextView songTV;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            songTV = itemView.findViewById(R.id.song_title_tv);
            songTimeTV = itemView.findViewById(R.id.song_time_tv);
            songArtistTV = itemView.findViewById(R.id.song_artist_tv);
        }
        public void bind(final Song song, final OnItemClickListener listener){
            songArtistTV.setText(song.getArtist());
            songTV.setText(song.getTitle());
            songTimeTV.setText(parseSongTimeToString(song.getPlaybackTimeInSeconds()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(song);
                }
            });
        }
    }

    /**
     * This method takes an integer and formats it into a String that represents the playback time of a song
     * @param songTime
     * @return String with time format
     */
    public String parseSongTimeToString(int songTime){
        String result;
        result = (songTime - (songTime%60))/60 + ":" + songTime%60;
        return result;
    }
}
