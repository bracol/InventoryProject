package com.example.c1284518.inventoryproject.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class Generico implements Parcelable {

    Long _id;
    String valor;
    Long product_id;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Generico generico = (Generico) o;

        if (_id != null ? !_id.equals(generico._id) : generico._id != null) return false;
        if (valor != null ? !valor.equals(generico.valor) : generico.valor != null) return false;
        return !(product_id != null ? !product_id.equals(generico.product_id) : generico.product_id != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        result = 31 * result + (product_id != null ? product_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Generico{" +
                "_id=" + _id +
                ", valor='" + valor + '\'' +
                ", product_id=" + product_id +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.valor);
        dest.writeValue(this.product_id);
    }

    public Generico() {
    }

    protected Generico(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.valor = in.readString();
        this.product_id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Generico> CREATOR = new Parcelable.Creator<Generico>() {
        public Generico createFromParcel(Parcel source) {
            return new Generico(source);
        }

        public Generico[] newArray(int size) {
            return new Generico[size];
        }
    };
}
