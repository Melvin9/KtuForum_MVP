package com.ktu.dev.melvin.ktu_forum.MainScreen.Profile.View;

/**
 * Created by Melvin on 12/27/2017.
 */

public class items_rank {
    private String name,rank,point;

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }

    public String getPoint() {
        return point;
    }

    items_rank(String name, String rank, String point) {
        this.name = name;
        this.rank = rank;
        this.point = point;
    }
}
