package com.example.avplayer.Video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Size;
import android.util.TimeUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avplayer.R;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class InsideFolderAdapter extends RecyclerView.Adapter<InsideFolderAdapter.viewholder>{

    ArrayList<VideoList> list=new ArrayList<>();
    ArrayList<String> urilist=new ArrayList<>();
    Context con;
    int pos=0;
    public InsideFolderAdapter(Context con){
        this.con=con;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.videofiles_layout,null);
        return new viewholder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.videoname.setText(list.get(position).getName());
        int time1=list.get(position).getDuration();
        urilist.add(list.get(position).getUri().toString());
        pos=position;
        String time=""+time1;
        holder.time.setText(time);
        holder.Duration.setText(Integer.toString(list.get(position).getDuration()));
        holder.uri.setText(list.get(position).getUri().toString());
        holder.time.setTextColor(R.color.black);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            try {
                Bitmap bt=con.getContentResolver().loadThumbnail(list.get(position).getUri(),new Size(640,480),null);
                holder.thumbnail.setImageBitmap(bt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getList(ArrayList<VideoList> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView videoname,time,Duration;
        TextView uri;
        CardView cardView;
        ImageView thumbnail;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundel=new Bundle();
                    bundel.putSerializable("Array",urilist);
                    for(int i=0;i<list.size();i++){
                        if(uri.getText().toString().equals(urilist.get(i))){
                            pos=i;
                            break;
                        }
                    }
                    bundel.putInt("Pos",pos);
                    con.startActivity(new Intent(con,PlayVideo.class).putExtra("uri",uri.getText()).putExtra("Duration",Duration.getText()).putExtra("name",videoname.getText()).putExtra("Bundel",bundel));
                }
            });
            Duration=(TextView)itemView.findViewById(R.id.duration);
            cardView =(CardView) itemView.findViewById(R.id.Card1);
            cardView.setMinimumWidth(Resources.getSystem().getDisplayMetrics().widthPixels);
            uri=(TextView)itemView.findViewById(R.id.uri);
            time=(TextView)itemView.findViewById(R.id.tv_time);
            videoname=(TextView)itemView.findViewById(R.id.tv_videofilename);
            thumbnail=(ImageView)itemView.findViewById(R.id.Iv_thumbnail);
        }
    }
}
