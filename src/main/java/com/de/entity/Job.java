package com.de.entity;

import org.springframework.stereotype.Component;

/**
 * @编写人:de
 * @时间:2019/10/27
 * @描述:岗位表
 */
@Component
public class Job {
    private int id;
    private String name;


    public Job() {
    }

    public Job(int id, String name) {
        this.id = id;
        this.name = name;
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



    @Override
    public String toString() {
        return "job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
