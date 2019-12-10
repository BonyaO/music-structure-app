package com.bonya.musicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bonya.musicapp.R;
import com.bonya.musicapp.SongPlayingActivity;
import com.bonya.musicapp.models.Song;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private final ArrayList<Song> mSongs;
    private final OnItemClickListener listener;
    private Context mContext;
    private Song mSong;

    public interface OnItemClickListener{
        void onItemClicked(Song song);
    }

    public AlbumAdapter(ArrayList<Song> songs, OnItemClickListener listener){
        this.mSongs = songs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.album_list_item, parent, false);
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
    //Custom ViewHolder to display a list of Albums on the recycler view appropriately.
    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView songArtistTV;
        TextView songAlbumTV;
        ImageView moreOptionsImgView;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            songAlbumTV = itemView.findViewById(R.id.song_time_tv);
            songArtistTV = itemView.findViewById(R.id.song_artist_tv);
            moreOptionsImgView = itemView.findViewById(R.id.more_options_btn);
            moreOptionsImgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(mContext,view);
                    MenuInflater inflater = popupMenu.getMenuInflater();
                    inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.play:
                                    Intent intent = new Intent(mContext, SongPlayingActivity.class);
                                    //A better way to achieve this will be to get the item position.
                                    //At the time of writing this, I was unaware of how I can get the item position
                                    //because the viewholders seems to have a different index to ArrayList containing the songs
                                    for(Song song: mSongs){
                                        if(songAlbumTV.getText().toString().equalsIgnoreCase(song.getAlbum())){
                                            mSong = song;
                                        }
                                    }
                                    intent.putExtra("SONG_ALBUM", mSong);
                                    mContext.startActivity(intent);
                                    Toast.makeText(mContext, "Play", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.add_to_favourites:
                                    //With a favourites activity created, clicking this popup menu option will add a list of songs to a playlist
                                    Toast.makeText(mContext, "Added to Favourites", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                }
            });
        }
        public void bind( final Song song, final OnItemClickListener listener){
            songArtistTV.setText(song.getArtist());
            songAlbumTV.setText(song.getAlbum());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(song);
                }
            });
        }


    }
}
