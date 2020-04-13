package com.firstspring.testspring.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class TvSeries {
    int id;
    private String name;
    //最小值为1
    @DecimalMin("1") private int seasonCount;
    //@Valid表示要级联效验；@Size（min = ）表示这个列表至少要有2项内容，否则不能通过校验

    //如果想用long类型的时间戳表示日期，可用：@JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonFormat(timezone = "GMT + 8", pattern = "yyyy-MM-dd HH:mm:ss")
    //@Past表示只接受过去的时间
    @Past
    private Date originRelease;
    public TvSeries(int id, String name, int seasonCount, Date originRelease) {
        this.id = id;
        this.name = name;
        this.seasonCount = seasonCount;
        this.originRelease = originRelease;
    }
    public TvSeries() {

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
    }

    public Date getOriginRelease() {
        return originRelease;
    }

    public void setOriginRelease(Date originRelease) {
        this.originRelease = originRelease;
    }

    @Override
    public String toString() {
        return "TvSeries{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seasonCount=" + seasonCount +
                ", originRelease=" + originRelease +
                '}';
    }
}
