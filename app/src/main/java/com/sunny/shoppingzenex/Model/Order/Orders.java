package com.sunny.shoppingzenex.Model.Order;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.sunny.shoppingzenex.Model.Product.ProductTypeConverters;
import com.sunny.shoppingzenex.Model.Product.Products;

import java.util.List;

@Entity(tableName = "order")
public class Orders implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters(ProductTypeConverters.class)
    private List<Products> productsList;

    @ColumnInfo(name = "total")
    private Long total;

    @ColumnInfo(name = "gst")
    private Integer gst;

    @ColumnInfo(name = "coupon")
    private Integer coupon;

    @ColumnInfo(name = "items")
    private Integer totalItem;



    public Orders() {
    }


    protected Orders(Parcel in) {
        id = in.readInt();
        productsList = in.createTypedArrayList(Products.CREATOR);
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readLong();
        }
        if (in.readByte() == 0) {
            gst = null;
        } else {
            gst = in.readInt();
        }
        if (in.readByte() == 0) {
            coupon = null;
        } else {
            coupon = in.readInt();
        }
        if (in.readByte() == 0) {
            totalItem = null;
        } else {
            totalItem = in.readInt();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getGst() {
        return gst;
    }

    public void setGst(Integer gst) {
        this.gst = gst;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public static Creator<Orders> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeTypedList(productsList);
        if (total == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(total);
        }
        if (gst == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(gst);
        }
        if (coupon == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(coupon);
        }
        if (totalItem == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalItem);
        }
    }
}
