package com.lib.base.bean;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Parcel;
import android.os.Parcelable;

import com.lib.base.ui.activity.VideoPlayActivity;

import java.io.File;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.bean
 *@author      xwchen
 * Date         2022/1/14.
 */

public class VideoBuilder implements Parcelable {
    public static final String INTENT_KEY_PARAMETERS = "parameters";
    /**
     * 视频源
     */
    private String videoSource;
    /**
     * 视频标题
     */
    private String videoTitle;

    /**
     * 播放进度
     */
    private int playProgress;
    /**
     * 手势开关
     */
    private boolean gestureEnabled = true;
    /**
     * 循环播放
     */
    private boolean loopPlay = false;
    /**
     * 自动播放
     */
    private boolean autoPlay = true;
    /**
     * 播放完关闭
     */
    private boolean autoOver = true;

    /**
     * 播放方向
     */
    private int activityOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

    public VideoBuilder() {
    }

    public static VideoBuilder newBuilder() {
        return new VideoBuilder();
    }

    protected VideoBuilder(Parcel in) {
        videoSource = in.readString();
        videoTitle = in.readString();
        activityOrientation = in.readInt();

        playProgress = in.readInt();
        gestureEnabled = in.readByte() != 0;
        loopPlay = in.readByte() != 0;
        autoPlay = in.readByte() != 0;
        autoOver = in.readByte() != 0;
    }

    public VideoBuilder setVideoSource(File file) {
        videoSource = file.getPath();
        if (videoTitle == null) {
            videoTitle = file.getName();
        }
        return this;
    }

    public VideoBuilder setVideoSource(String url) {
        videoSource = url;
        return this;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public VideoBuilder setVideoTitle(String title) {
        videoTitle = title;
        return this;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public VideoBuilder setPlayProgress(int progress) {
        playProgress = progress;
        return this;
    }

    public int getPlayProgress() {
        return playProgress;
    }

    public VideoBuilder setGestureEnabled(boolean enabled) {
        gestureEnabled = enabled;
        return this;
    }

    public boolean isGestureEnabled() {
        return gestureEnabled;
    }

    public VideoBuilder setLoopPlay(boolean enabled) {
        loopPlay = enabled;
        return this;
    }

    public boolean isLoopPlay() {
        return loopPlay;
    }

    public VideoBuilder setAutoPlay(boolean enabled) {
        autoPlay = enabled;
        return this;
    }

    public boolean isAutoPlay() {
        return autoPlay;
    }

    public VideoBuilder setAutoOver(boolean enabled) {
        autoOver = enabled;
        return this;
    }

    public boolean isAutoOver() {
        return autoOver;
    }

    public VideoBuilder setActivityOrientation(int orientation) {
        activityOrientation = orientation;
        return this;
    }

    public void start(Context context) {
        Intent intent = new Intent();
        switch (activityOrientation) {
            case ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
                intent.setClass(context, VideoPlayActivity.Landscape.class);
                break;
            case ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                intent.setClass(context, VideoPlayActivity.Portrait.class);
                break;
            default:
                intent.setClass(context, VideoPlayActivity.class);
                break;
        }

        intent.putExtra(INTENT_KEY_PARAMETERS, this);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoSource);
        dest.writeString(videoTitle);
        dest.writeInt(activityOrientation);
        dest.writeInt(playProgress);
        dest.writeByte(gestureEnabled ? (byte) 1 : (byte) 0);
        dest.writeByte(loopPlay ? (byte) 1 : (byte) 0);
        dest.writeByte(autoPlay ? (byte) 1 : (byte) 0);
        dest.writeByte(autoOver ? (byte) 1 : (byte) 0);
    }

    public static final Creator<VideoBuilder> CREATOR = new Creator<VideoBuilder>() {
        @Override
        public VideoBuilder createFromParcel(Parcel source) {
            return new VideoBuilder(source);
        }

        @Override
        public VideoBuilder[] newArray(int size) {
            return new VideoBuilder[size];
        }
    };
}
