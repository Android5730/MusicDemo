package com.tutuit.a1.data.bean;

import java.io.Serializable;

public class LocalMusicBean implements Serializable {
    // 当前行指定列的值
    private String number;
    // 歌名
    private String name;
    // 歌手
    private String singer;
    // 歌的时长
    private long duration;
    // 歌的大小
    private long size;
    // 专辑
    private String album;
    // 歌曲绝对路径
    private String url;

    public LocalMusicBean(String name, String singer, long duration, String album, String url) {
        this.name = name;
        this.singer = singer;
        this.duration = duration;
        this.album = album;
        this.url = url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
