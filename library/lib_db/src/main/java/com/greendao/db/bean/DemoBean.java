package com.greendao.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * PackageName  com.lib.db
 * ProjectName  TempleteProject
 * Author       chenxiaowu
 * Date         10/10/21.
 */
@Entity
public class DemoBean {
    @Id(autoincrement = true)
    public long _id;
    public String name;
    public int age;

    @Generated(hash = 27244436)
    public DemoBean(long _id, String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 2085635340)
    public DemoBean() {
    }

    public long get_id() {
        return this._id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
