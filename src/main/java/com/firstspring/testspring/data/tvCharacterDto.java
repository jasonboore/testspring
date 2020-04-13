package com.firstspring.testspring.data;

import com.sun.istack.NotNull;

public class tvCharacterDto {
    private int id;
    private int tvSeriesId;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTvSeriesId() {
        return tvSeriesId;
    }

    public void setTvSeriesId(int tvSeriesId) {
        this.tvSeriesId = tvSeriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "tvCharacterDto{" +
                "id=" + id +
                ", tvSeriesId=" + tvSeriesId +
                ", name='" + name + '\'' +
                '}';
    }
}
