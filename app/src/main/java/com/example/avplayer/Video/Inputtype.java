package com.example.avplayer.Video;

import android.net.Uri;

public class Inputtype {
        private final Uri uri;
        private final String name;
        private final int duration;
        private final int size;

        public Inputtype(Uri uri, String name, int duration, int size) {
            this.uri = uri;
            this.name = name;
            this.duration = duration;
            this.size = size;
        }

    public Uri getUri() {
        return uri;
    }

    public int getSize() {
        return size;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }
}
