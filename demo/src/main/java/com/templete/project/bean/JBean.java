package com.templete.project.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.templete.project
 * Author       Administrator
 * Date         2021/12/24.
 */

public class JBean extends BaseBean implements Parcelable {

    //public int version;
    //public String abc;
    public List<Integer> version;
    public int abc;

    protected JBean(Parcel in) {
        abc = in.readInt();
    }

    public static final Creator<JBean> CREATOR = new Creator<JBean>() {
        @Override
        public JBean createFromParcel(Parcel in) {
            return new JBean(in);
        }

        @Override
        public JBean[] newArray(int size) {
            return new JBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(abc);
    }
}
