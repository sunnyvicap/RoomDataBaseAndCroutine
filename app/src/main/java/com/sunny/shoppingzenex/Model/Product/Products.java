package com.sunny.shoppingzenex.Model.Product;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Products implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "product_price")
    private long productPrice;

    @ColumnInfo(name = "product_description")
    private String productDescription;

    @ColumnInfo(name = "is_added_cart")
    private Boolean  isInCart = false;




    public Products( String productName, Long productPrice, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    protected Products(Parcel in) {
        id = in.readInt();
        productName = in.readString();
        productPrice = in.readLong();
        productDescription = in.readString();
        byte tmpIsInCart = in.readByte();
        isInCart = tmpIsInCart == 0 ? null : tmpIsInCart == 1;
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public Boolean getInCart() {
        return isInCart;
    }

    public void setInCart(Boolean inCart) {
        isInCart = inCart;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(productName);
        dest.writeLong(productPrice);
        dest.writeString(productDescription);
        dest.writeByte((byte) (isInCart == null ? 0 : isInCart ? 1 : 2));
    }
}
