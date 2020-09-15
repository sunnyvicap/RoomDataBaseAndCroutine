package com.sunny.shoppingzenex.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notification")
public class Notification {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "order_id")
    int order_id;


    @ColumnInfo(name = "order_status")
    String order_status;


    @ColumnInfo(name = "available_products")
    String avaialbleProducts;

    @ColumnInfo(name = "unavailable_products")
    String unavailableProduct;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "invoice")
    private byte[] invoice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getAvaialbleProducts() {
        return avaialbleProducts;
    }

    public void setAvaialbleProducts(String avaialbleProducts) {
        this.avaialbleProducts = avaialbleProducts;
    }

    public String getUnavailableProduct() {
        return unavailableProduct;
    }

    public void setUnavailableProduct(String unavailableProduct) {
        this.unavailableProduct = unavailableProduct;
    }

    public byte[] getInvoice() {
        return invoice;
    }

    public void setInvoice(byte[] invoice) {
        this.invoice = invoice;
    }
}
