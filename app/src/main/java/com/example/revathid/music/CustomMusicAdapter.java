package com.example.revathid.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomMusicAdapter extends BaseAdapter {
    private Context context;
    private int layout;

    private ArrayList<Music> arrayList;
    private MediaPlayer mediaPlayer;
    private Boolean flag = true;

    public CustomMusicAdapter(Context context, int layout, ArrayList<Music> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        TextView tName;
        ImageView iPlay, iStop;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            viewHolder.tName = (TextView) convertView.findViewById(R.id.txName);
            viewHolder.iPlay = convertView.findViewById(R.id.iPlay);
            viewHolder.iStop = convertView.findViewById(R.id.iStop);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        final Music music = arrayList.get(position);
        viewHolder.tName.setText(music.getName());
//play music
        viewHolder.iPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    mediaPlayer = MediaPlayer.create(context, music.getSong());
                    flag = false;
                }
                if (mediaPlayer.isPlaying()) {

                    mediaPlayer.pause();
                    viewHolder.iPlay.setImageResource(R.drawable.ic_play);
                } else {
                    mediaPlayer.start();
                    viewHolder.iPlay.setImageResource(R.drawable.ic_pause);
                }
            }
        });
//stop music
        viewHolder.iStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag= true;
                }    viewHolder.iPlay.setImageResource(R.drawable.ic_play);
            }
        });
        return convertView;
    }
}
