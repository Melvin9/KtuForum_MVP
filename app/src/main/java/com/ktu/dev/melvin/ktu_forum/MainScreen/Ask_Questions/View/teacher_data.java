package com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View;

/**
 * Created by Melvin on 12/22/2017.
 */

public class teacher_data {
    private String tid,tname;

    teacher_data(String tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public String getTid() {
        return tid;
    }
    public String getTname() {
        return tname;
    }
}
