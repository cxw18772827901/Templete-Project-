package com.templete.project.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * PackageName  com.templete.project.bean
 * ProjectName  TempleteProject-java
 * Date         2022/2/11.
 *
 * @author chenxiaowu
 */

public class PBean implements Parcelable {
    public String name;

    public PBean(String name) {
        this.name = name;
    }

    protected PBean(Parcel in) {
        name = in.readString();
    }

    public static final Creator<PBean> CREATOR = new Creator<PBean>() {
        @Override
        public PBean createFromParcel(Parcel in) {
            return new PBean(in);
        }

        @Override
        public PBean[] newArray(int size) {
            return new PBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @NonNull
    @Override
    public String toString() {
        return "PBean{" +
                "name='" + name + '\'' +
                '}';
    }
}

