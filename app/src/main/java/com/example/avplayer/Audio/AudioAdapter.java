package com.example.avplayer.Audio;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avplayer.R;

import java.util.ArrayList;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.viewholder>{
    ArrayList<String> list=new ArrayList<>();
    Context con;
    public AudioAdapter(Context con){
        this.con=con;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V= LayoutInflater.from(parent.getContext()).inflate(R.layout.audiofolders,null);
        return new viewholder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.t1.setText(list.get(position));
    }

    public void setList(ArrayList<String>List){
        this.list=List;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView t1;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setMinimumWidth(Resources.getSystem().getDisplayMetrics().widthPixels);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    con.startActivity(new Intent(con,AudioFiles.class).putExtra("Folder",t1.getText()));
                }
            });
            t1=(TextView)itemView.findViewById(R.id.tv_audiofoldername);
        }
    }
}
