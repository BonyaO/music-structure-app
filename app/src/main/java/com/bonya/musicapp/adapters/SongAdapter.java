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

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private Context context;
    private final ArrayList<Song> mSongs;
    private final OnItemClickListener listener;
    private Song mSong;

    public interface OnItemClickListener{
        void onItemClicked(Song song);
    }

    public SongAdapter(ArrayList<Song> songs, OnItemClickListener listener){
        this.mSongs = songs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.song_list_item, parent, false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        holder.bind(mSongs.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songTitleTV;
        TextView songArtistTV;
        TextView songAlbumTV;
        ImageView moreOptionsImgView;

        public SongViewHolder(@NonNull final View itemView) {
            super(itemView);
            songTitleTV = itemView.findViewById(R.id.song_title_tv);
            songAlbumTV = itemView.findViewById(R.id.song_time_tv);
            songArtistTV = itemView.findViewById(R.id.song_artist_tv);
            moreOptionsImgView = itemView.findViewById(R.id.more_options_btn);
            moreOptionsImgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context,view);
                    MenuInflater inflater = popupMenu.getMenuInflater();
                    inflater.inflate(R.menu.song_popup_menu, popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.play:
                                    Intent intent = new Intent(context, SongPlayingActivity.class);
                                    for(Song song: mSongs){
                                        if(songTitleTV.getText().toString().equalsIgnoreCase(song.getTitle())){
                                            mSong = song;
                                        }
                                    }
                                    intent.putExtra("SONG", mSong);
                                    context.startActivity(intent);
                                    Toast.makeText(context, "Play", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.add_to_favourites:
                                    Toast.makeText(context, "Favourites", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.remove_item:
                                    Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
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
        public void bind(final Song song, final OnItemClickListener listener){
            songTitleTV.setText(song.getTitle());
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
