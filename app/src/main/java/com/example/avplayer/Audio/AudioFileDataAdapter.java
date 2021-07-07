package com.example.avplayer.Audio;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avplayer.R;
import com.example.avplayer.Video.PlayVideo;

import java.io.IOException;
import java.util.ArrayList;

public class AudioFileDataAdapter extends RecyclerView.Adapter<AudioFileDataAdapter.viewholder>{
    ArrayList<AudioFileData> list=new ArrayList<>();
    Context con;
    public AudioFileDataAdapter(Context con){
        this.con=con;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.audiofilelayout,null);
        return new viewholder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.url.setText(list.get(position).getUri().toString());
        holder.song.setText(list.get(position).getArtist());
        try {
            Bitmap bp=con.getContentResolver().loadThumbnail(list.get(position).getUri(),new Size(640,480),null);
            holder.iv.setImageBitmap(bp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getList(ArrayList<AudioFileData> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView name,song,url;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    con.startActivity(new Intent(con, PlayAudio.class).putExtra("uri",url.getText()));
                }
            });
            url=(TextView)itemView.findViewById(R.id.audiourl);
            iv=(ImageView)itemView.findViewById(R.id.Iv_audio);
            name=(TextView)itemView.findViewById(R.id.Tv_audio);
            song=(TextView)itemView.findViewById(R.id.Tv_singer);
        }
    }
}
