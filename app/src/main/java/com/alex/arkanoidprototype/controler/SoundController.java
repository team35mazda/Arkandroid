package com.alex.arkanoidprototype.controler;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.alex.arkanoidprototype.R;


public class SoundController {

    SoundPool sp;
    int soundIds[];
    AudioAttributes audioAttributes;
    public static int BLOCK_HIT = 0;
    public static int SLIDER_HIT = 1;

    public SoundController(Context context){
       this.audioAttributes = new AudioAttributes.Builder()
               .setUsage(AudioAttributes.USAGE_GAME)
               .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
               .build();
       this.sp = new SoundPool.Builder()
               .setMaxStreams(10)
               .setAudioAttributes(this.audioAttributes)
               .build();

       this.soundIds = new int[2];
       this.soundIds[BLOCK_HIT] = this.sp.load(context, R.raw.blockhit,1);
       this.soundIds[SLIDER_HIT] = this.sp.load(context,R.raw.sliderhit,1);
    }

    public void playBlockHitSound(){
        this.sp.play(soundIds[BLOCK_HIT],5,5,1,0, (float) 1.0);
    }

    public void playSliderHitSound(){
        this.sp.play(soundIds[SLIDER_HIT],5,5,1,0, (float) 1.0);
    }
}
