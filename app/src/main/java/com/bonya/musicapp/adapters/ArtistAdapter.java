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

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private final ArrayList<Song> mSongs;
    private final OnItemClickedListener listener;
    private Context mContext;
    private Song mSong;

    public interface OnItemClickedListener{
        void onItemClicked(Song song);
    }

    public ArtistAdapter(ArrayList<Song> songs, OnItemClickedListener listener){
        this.mSongs = songs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.artist_list_item, parent, false);
        return new ArtistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        holder.bind(mSongs.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder  {
        TextView songArtistTV;
        ImageView moreOptionsImgView;
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
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
                                    for(Song song: mSongs){
                                        if(songArtistTV.getText().toString().equalsIgnoreCase(song.getArtist())){
                                            mSong = song;
                                        }
                                    }
                                    intent.putExtra("SONG_ARTIST", mSong);
                                    mContext.startActivity(intent);
                                    Toast.makeText(mContext, "Play", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.add_to_favourites:
                                    Toast.makeText(mContext, "Favourites", Toast.LENGTH_SHORT).show();
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
        public void bind(final Song song, final OnItemClickedListener listener){
            songArtistTV.setText(song.getArtist());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(song);
                }
            });
        }
    }
}
