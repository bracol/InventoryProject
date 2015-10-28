package com.example.c1284518.inventoryproject.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class Product implements Parcelable {

    private Long _id;
    private String name;
    private Double value;
    private String image;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (_id != null ? !_id.equals(product._id) : product._id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (value != null ? !value.equals(product.value) : product.value != null) return false;
        return !(image != null ? !image.equals(product.image) : product.image != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", image='" + image + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.name);
        dest.writeValue(this.value);
        dest.writeString(this.image);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.value = (Double) in.readValue(Double.class.getClassLoader());
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
