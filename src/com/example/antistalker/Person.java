package com.example.antistalker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with IntelliJ IDEA.
 * User: cosmin
 * Date: 8/27/13
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Person implements Parcelable {
    String name;
    String telephone;

    public static final Parcelable.Creator<Person> CREATOR
            = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public Person(String name, String telephone){
        this.name = name;
        this.telephone = telephone;
    }

    public Person (Parcel source) {
        name = source.readString();
        telephone = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(telephone);
    }

    @Override
    public String toString() {
        return new String(name + "    " + telephone);
    }
}
