package usa.app.appmovilproyectos1.ui.model;

import java.util.ArrayList;
import java.util.List;

import usa.app.appmovilproyectos1.R;

public class Basededatos {

    private ArrayList<Producto> base;

    public Basededatos() {
        base = new ArrayList<Producto>();
        base.add(new Producto(1,R.drawable.producto2,"USB Madera", 10000,"Memoría USB de 60Gb U1 Diseñada para trabajos de alta velocidad",1));
        base.add(new Producto(2,R.drawable.producto1,"Llavero Arbol", 3000,"Llavero personalizado con arbol de navidad",1));
        base.add(new Producto(3,R.drawable.producto3,"Arete mujer", 2000,"Arete para mujer, adorno para su oreja",1));
        base.add(new Producto(4,R.drawable.producto4,"Llavero Pray Jerusalen", 4000,"Llavero alusivo a la situación de Jerusalen - Israel",1));
        //base.add(new Producto(5,R.drawable.producto5,"Llavero Elefante", 5000,"Llavero personalizado con figura de elefante",1));
    }

    public ArrayList<Producto> getProductos(){
        return base;
    }
}
