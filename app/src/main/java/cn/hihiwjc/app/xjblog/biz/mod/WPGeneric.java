package cn.hihiwjc.app.xjblog.biz.mod;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Generic class for holding "rendered" fields returned by the JSON API
 *
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public class WPGeneric implements Parcelable {

    @SerializedName("rendered")
    private String mRendered;

    public String getRendered() {
        return mRendered;
    }

    public void setRendered(String rendered) {
        this.mRendered = rendered;
    }

    public WPGeneric withRendered(String rendered) {
        mRendered = rendered;
        return this;
    }

    @SerializedName("raw")
    private String mRaw;

    public String getRaw() {
        return mRaw;
    }

    public void setRaw(String raw) {
        mRaw = raw;
    }

    public WPGeneric withRaw(String raw) {
        setRaw(raw);
        return this;
    }

    public WPGeneric() {
    }

    public WPGeneric(Parcel in) {
        mRendered = in.readString();
        mRaw = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mRendered);
        dest.writeString(mRaw);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WPGeneric> CREATOR = new Creator<WPGeneric>() {
        @Override
        public WPGeneric createFromParcel(Parcel source) {
            return new WPGeneric(source);
        }

        @Override
        public WPGeneric[] newArray(int size) {
            return new WPGeneric[size];
        }
    };

    @Override
    public String toString() {
        return "WPGeneric{" +
                "mRendered='" + mRendered + '\'' +
                ", mRaw='" + mRaw + '\'' +
                '}';
    }
}
