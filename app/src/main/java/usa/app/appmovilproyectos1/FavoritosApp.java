package usa.app.appmovilproyectos1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import usa.app.appmovilproyectos1.datos.DataBase;
import usa.app.appmovilproyectos1.ui.model.Producto;

public class FavoritosApp extends AppCompatActivity {

    private LinearLayout lyFavApp;
    private TextView textoFav;
    private LinearLayout layoutHorizontalFav;
    private LinearLayout layoutVerticalFav;
    private DataBase datosf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_app);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Favoritos");

        ArrayList<Producto> listaFavoritos = new ArrayList<Producto>();
        datosf = new DataBase(getApplicationContext());
        Cursor datosFav = datosf.getFavoritos();
        if (datosFav.getCount()!=0){
            datosFav.moveToFirst();
                do{
                    int a = datosFav.getInt(1);
                    String b = datosFav.getString(2);
                    String c = datosFav.getString(3);
                    int d = datosFav.getInt(4);
                    int e = datosFav.getInt(5);

                    Producto producto = new Producto(a,e,b,d,c,0);
                    listaFavoritos.add(producto);

                    }while(datosFav.moveToNext());}
        /**
        for(Producto producto:listaFavoritos) {
            if(producto.getCantidad()>0){

                layoutHorizontalFav = new LinearLayout(getApplicationContext());
                layoutHorizontalFav.setOrientation(LinearLayout.HORIZONTAL);
                layoutHorizontalFav.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

                layoutVerticalFav = new LinearLayout(getApplicationContext());
                layoutVerticalFav.setOrientation(LinearLayout.VERTICAL);
                layoutVerticalFav.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,2));

                ImageView image1 = new ImageView(getApplicationContext());
                image1.setImageResource(producto.getImagen());
                image1.setLayoutParams(new LinearLayout.LayoutParams(100,200,1));

                TextView espacio1 = new TextView(getApplicationContext());
                espacio1.setLayoutParams(new LinearLayout.LayoutParams(300,50));
                espacio1.setText("");


                TextView texto11 = new TextView(getApplicationContext());
                texto11.setLayoutParams(new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT));
                texto11.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
                texto11.setTextColor(Color.WHITE);
                texto11.setText(producto.getName());

                TextView texto22 = new TextView(getApplicationContext());
                texto22.setLayoutParams(new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT));
                texto22.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
                texto22.setTextColor(Color.WHITE);
                texto22.setText("Descripción. " + producto.getDescription());

                TextView texto33 = new TextView(getApplicationContext());
                texto33.setLayoutParams(new LinearLayout.LayoutParams(350, ViewGroup.LayoutParams.WRAP_CONTENT));
                texto33.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
                texto33.setTextColor(Color.WHITE);
                texto33.setText("Precio: $" + producto.getPrice());

                layoutVerticalFav.addView(texto11);
                layoutVerticalFav.addView(texto22);
                layoutVerticalFav.addView(texto33);

                layoutHorizontalFav.addView(image1);
                layoutHorizontalFav.addView(layoutVerticalFav);

                lyFavApp.addView(espacio1);
                lyFavApp.addView(layoutHorizontalFav);
            }

        }

        /**textoFav.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18.f);
        textoFav.setTextColor(Color.WHITE);
        textoFav.setText("Productos seleccionados: " + lista1.size());
        //Log.e("res",""+carritoDeCompras.size());
        TextView precioTotal = new TextView(getApplicationContext());
        precioTotal.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        precioTotal.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20.f);
        precioTotal.setTextColor(Color.WHITE);
        precioTotal.setBackgroundColor(getResources().getColor(R.color.purple_700));
        precioTotal.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        precioTotal.setText("Total: $" + prTotal);

        Button buttonComp = new Button(getApplicationContext());
        buttonComp.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonComp.setText("COMPRAR");
        buttonComp.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25.f);
        buttonComp.setTextColor(Color.WHITE);
        buttonComp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        buttonComp.setBackgroundColor(getResources().getColor(R.color.purple_700));
        buttonComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Esta función se agregará Proximamente", Toast.LENGTH_SHORT).show();
            }
        });**/

        /**
        //lyFavApp.addView(precioTotal);
        TextView espacio2 = new TextView(getApplicationContext());
        espacio2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,80));
        espacio2.setText("");
        lyFavApp.addView(espacio2);
        //lyFavApp.addView(buttonComp);
        //buttonComp.setGravity(Gravity.BOTTOM);**/


    }
}