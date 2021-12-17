package usa.app.appmovilproyectos1.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductoRef implements Parcelable {

    private Integer id;
    private String imagen;
    private String name;
    private Integer price;
    private String description;
    private Integer cantidad;

    public ProductoRef(Integer id, String imagen, String name, Integer price, String description, Integer cantidad) {
        this.id = id;
        this.imagen = imagen;
        this.name = name;
        this.price = price;
        this.description = description;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imagen);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(description);
        dest.writeInt(cantidad);

        /**private Integer id;
        private String imagen;
        private String name;
        private Integer price;
        private String description;
        private Integer cantidad;**/
    }

    public ProductoRef(Parcel argum1){
        id = argum1.readInt();
        imagen = argum1.readString();
        name = argum1.readString();
        price = argum1.readInt();
        description = argum1.readString();
        cantidad =  argum1.readInt();
    }

    public static final Parcelable.Creator<ProductoRef> CREATOR = new Parcelable.Creator<ProductoRef>() {
        public ProductoRef createFromParcel(Parcel in) {
            return new ProductoRef(in);
        }

        public ProductoRef[] newArray(int size) {
            return new ProductoRef[size];
        }
    };

}
