package usa.app.appmovilproyectos1.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import usa.app.appmovilproyectos1.Carrocompras;
import usa.app.appmovilproyectos1.FavoritosApp;
import usa.app.appmovilproyectos1.R;
import usa.app.appmovilproyectos1.databinding.FragmentProductosBinding;
import usa.app.appmovilproyectos1.datos.ConsultaBd;
import usa.app.appmovilproyectos1.datos.DataBase;
import usa.app.appmovilproyectos1.ui.model.Basededatos;
import usa.app.appmovilproyectos1.ui.model.MySingleton;
import usa.app.appmovilproyectos1.ui.model.Producto;
import usa.app.appmovilproyectos1.ui.model.ProductoRef;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentProductosBinding binding;
    private LinearLayout layoutPadre;
    private LinearLayout layoutHorizontal;
    private LinearLayout layoutVertical;
    private LinearLayout layoutVertical2;
    private Basededatos basededatos;
    private ArrayList<ProductoRef> carrito;
    private DataBase datos;
    private SQLiteDatabase bDatosV;
    private ConsultaBd restBd;
    private ArrayList<ProductoRef> productosx;
    private ArrayList<ProductoRef> productosy;
    int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;
    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentProductosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        layoutPadre = (LinearLayout) binding.layPadre;

        /**
         * se instancia la base de datos y se instancia la variable carrito
         */

        datos = new DataBase(getContext());
        restBd = new ConsultaBd();

        getSucursalesApex();
        basededatos = new Basededatos();
        carrito = new ArrayList<ProductoRef>();


        /**
        final TextView textView = binding.textView2;

         final Button button = binding.button5;
        final Button button1 = binding.buttonc1;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });**/
        /**
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Próximamente se agrega función de compra", Toast.LENGTH_SHORT).show();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Próximamente se agrega función de compra", Toast.LENGTH_SHORT).show();
            }
        });**/

        return root;
    }

    public ArrayList<ProductoRef> getSucursalesApex(){
        String url = "https://g6b8ff7bc67d349-db202109302119.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/prod/prod";
        productosx = new ArrayList<>();

        ProgressDialog barra = new ProgressDialog(getContext());
        barra.setMessage("Cargando Información del servidor...");
        barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        barra.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ProductoRef producto = null;
                            JSONArray arrayProducto = response.getJSONArray("items");
                            JSONObject objeto = null;
                            Log.e("Array: ",""+arrayProducto.length());
                            for(int i = 0; i < arrayProducto.length();i++){
                                objeto = arrayProducto.getJSONObject(i);
                                int id = objeto.getInt("id");
                                String imagen = objeto.getString("imagen");
                                String nombre = objeto.getString("nombre");
                                int price = objeto.getInt("price");
                                String descripcion = objeto.getString("descripcion");
                                int cantidad = objeto.getInt("cantidad");

                                producto = new ProductoRef(id,imagen,nombre,price,descripcion,cantidad);
                                productosx.add(producto);
                                //Log.e("producto: ",""+productosx.size());
                            }
                            mostrarInformacion(productosx);
                            barra.cancel();
                            //Log.w("REST", "VOLLEY: " + arraySucursales.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //textView.setText("Response: " + response.toString());
                        //barra.cancel();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("REST", "VOLLEY: " + error.toString());
                        //barra.cancel();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
        return productosx;
    }

    private void mostrarInformacion(ArrayList<ProductoRef> prodxt){
        for(ProductoRef producto:prodxt){

            //Log.e("Evento",producto.getName());
            /**
             * creo el layout horizontal para agregar productos
             */
            layoutHorizontal = new LinearLayout(getContext());
            layoutHorizontal.setOrientation(LinearLayout.HORIZONTAL);
            layoutHorizontal.setLayoutParams(new LinearLayout.LayoutParams(matchParent,wrapContent));

            /**
             * creo un layout vertical para agregar los campos de descripción, nombre, etc
             */

            layoutVertical = new LinearLayout(getContext());
            layoutVertical.setOrientation(LinearLayout.VERTICAL);
            layoutVertical.setLayoutParams(new LinearLayout.LayoutParams(0,wrapContent,2));

            /**
             * creo un layout vertical para adicional el botón de favoritos.
             */

            layoutVertical2 = new LinearLayout(getContext());
            layoutVertical2.setOrientation(LinearLayout.VERTICAL);
            layoutVertical2.setLayoutParams(new LinearLayout.LayoutParams(0,wrapContent,1));

            /**
             * creo imagen para cada uno de los productos
             */
            ImageView image = new ImageView(getContext());
            /** ojo porque la imagen en la base de datos está como Integer */
            //image.setImageResource(producto.getImagen());
            Picasso.get().load(producto.getImagen()).into(image);
            image.setLayoutParams(new LinearLayout.LayoutParams(100,200,1));

            /**
             * Crear los textos para indicar los atributos de los productos
             */
            TextView espacio = new TextView(getContext());
            espacio.setLayoutParams(new LinearLayout.LayoutParams(300,50));
            espacio.setText("");


            TextView texto = new TextView(getContext());
            texto.setLayoutParams(new LinearLayout.LayoutParams(300,wrapContent));
            texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
            texto.setTextColor(Color.WHITE);
            texto.setText(producto.getName());

            TextView texto2 = new TextView(getContext());
            texto2.setLayoutParams(new LinearLayout.LayoutParams(300,wrapContent));
            texto2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
            texto2.setTextColor(Color.WHITE);
            texto2.setText("Precio: $" + producto.getPrice());

            TextView texto3 = new TextView(getContext());
            texto3.setLayoutParams(new LinearLayout.LayoutParams(350,wrapContent));
            texto3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
            texto3.setTextColor(Color.WHITE);
            texto3.setText(producto.getDescription());

            TextView texto4 = new TextView(getContext());
            texto4.setLayoutParams(new LinearLayout.LayoutParams(350,wrapContent));
            texto4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
            texto4.setTextColor(Color.WHITE);
            texto4.setText("Cant Carrito: 0");

            /**
             * se agregan los botones
             */
            Button button = new Button(getContext());
            button.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent,1));
            button.setText("+");
            button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            button.setBackgroundColor(getResources().getColor(R.color.purple_700));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "Próximamente se agrega función de compra", Toast.LENGTH_SHORT).show();
                    if(buscarProducto(carrito,producto)){
                        for(ProductoRef prod:carrito){
                            if(prod.getId() == producto.getId()){
                                prod.setCantidad(prod.getCantidad()+1);
                                texto4.setText("Cant Carrito: " + prod.getCantidad());
                                //Toast.makeText(getContext(), "Otro producto: " + prod.getName() + " Cant: " + prod.getCantidad() + " Agregado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        carrito.add(producto);
                        texto4.setText("Cant Carrito: " + 1);
                        Toast.makeText(getContext(), "Nuevo producto: " + producto.getName() + " Agregado", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Button button2 = new Button(getContext());
            button2.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent,1));
            button2.setText("-");
            button2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            button2.setBackgroundColor(getResources().getColor(R.color.purple_700));

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "Próximamente se agrega función de compra", Toast.LENGTH_SHORT).show();
                    if(buscarProducto(carrito,producto)){
                        for(ProductoRef prod:carrito){
                            if(prod.getId() == producto.getId() && prod.getCantidad()>0){
                                prod.setCantidad(prod.getCantidad()-1);
                                texto4.setText("Cant Carrito: " + prod.getCantidad());
                                //Toast.makeText(getContext(), "Otro producto: " + prod.getName() + " Cant: " + prod.getCantidad() + " Agregado", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getContext(), "Cantidad de " + producto.getName() + " en " + producto.getCantidad() , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(getContext(), "Cantidad de " + producto.getName() + " en 0", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Button button3 = new Button(getContext());
            button3.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent,1));
            //button3.setCompoundDrawables();
            button3.setText("Add Fav");
            button3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            button3.setBackgroundColor(getResources().getColor(R.color.purple_700));

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "Próximamente se agrega función de compra", Toast.LENGTH_SHORT).show();
                    if(datos.getFavoritos().getCount()!=0){
                        //Toast.makeText(getContext(), "Producto existe en favoritos: cant: "+ datos.getFavoritos().getCount(), Toast.LENGTH_SHORT).show();
                        //datos.deleteTodo();
                        Cursor cursor = datos.getFavoritos();
                        cursor.moveToFirst();
                        int a = 0;
                        int b = 0;
                        do {
                            int idc = cursor.getInt(1);
                            //Log.e("LOG",idc + " " + producto.getId());
                            if(idc == producto.getId()){
                                //Toast.makeText(getContext(), "Producto existe en favoritos: cant: "+ datos.getFavoritos().getCount(), Toast.LENGTH_SHORT).show();
                                a = 1;
                            }
                            else{
                                b=1;
                            }
                        }while(cursor.moveToNext());
                        if (a == 1){
                            Toast.makeText(getContext(), "Producto ya agregado a favoritos", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            datos.agregarFavoritos(producto.getId(),producto.getName(), producto.getDescription(), producto.getPrice(), producto.getImagen());
                            Toast.makeText(getContext(), "Producto: "+ producto.getName() + " Agregado a Favoritos " + datos.getFavoritos().getCount(), Toast.LENGTH_SHORT).show();
                        }

                        //Log.e("etiqueta: ",""+a+" "+b);
                    }
                    else{
                        datos.agregarFavoritos(producto.getId(),producto.getName(), producto.getDescription(), producto.getPrice(), producto.getImagen());
                        Toast.makeText(getContext(), "Producto: "+ producto.getName() + " Agregado a Favoritos " + datos.getFavoritos().getCount(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            /**
             * se muestran los componentes
             */
            //layoutVertical.addView(espacio);
            layoutVertical.addView(texto);
            layoutVertical.addView(texto2);
            layoutVertical.addView(texto3);
            layoutVertical.addView(texto4);

            layoutVertical2.addView(button);
            layoutVertical2.addView(button2);
            layoutVertical2.addView(button3);

            layoutHorizontal.addView(image);
            layoutHorizontal.addView(layoutVertical);
            layoutHorizontal.addView(layoutVertical2);
            //layoutHorizontal.addView(button);
            //layoutHorizontal.addView(button2);
            layoutHorizontal.setGravity(Gravity.CENTER);
            layoutPadre.addView(espacio);
            layoutPadre.addView(layoutHorizontal);
        }
    }

    private boolean buscarProducto(ArrayList<ProductoRef> carro, ProductoRef producto){
        for(ProductoRef p: carro){
            if(producto.getId() == p.getId()){
                return true;
            }
        }
        return false;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.compras){
            //Toast.makeText(getContext(), "Carrito de compras", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alerDialog = new AlertDialog.Builder(getContext());
            alerDialog.setTitle("¿Desea continuar?");
            alerDialog.setMessage("Presione Carrito para continuar o Mantener para seguir aquí").setPositiveButton("Carrito", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), Carrocompras.class);
                    Bundle bundle = new Bundle();
                    /**ArrayList<String> list = new ArrayList();
                     for(Producto producto:carrito){
                     list.add(producto.toString());
                     }**/
                    bundle.putParcelableArrayList("car",carrito);
                    intent.putExtras(bundle);
                    //Log.e("res",""+bundle);
                    startActivity(intent);
                }
            }).setNegativeButton("Mantener", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getContext(), "Productos", Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
        if (item.getItemId() == R.id.favoritos){
            //datos.deleteTodo();
            //Toast.makeText(getContext(), "En proceso cargando", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), FavoritosApp.class);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}