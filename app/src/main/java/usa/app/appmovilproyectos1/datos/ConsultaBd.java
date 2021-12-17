package usa.app.appmovilproyectos1.datos;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import usa.app.appmovilproyectos1.ui.model.MySingleton;
import usa.app.appmovilproyectos1.ui.model.Producto;
import usa.app.appmovilproyectos1.ui.model.ProductoRef;
import usa.app.appmovilproyectos1.ui.model.Sucursal;

public class ConsultaBd {

    private ArrayList<ProductoRef> productos;
    public ConsultaBd() {
    }

    public ArrayList<ProductoRef> getSucursalesApex(Context context){
        String url = "https://g6b8ff7bc67d349-db202109302119.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/prod/prod";

        productos = new ArrayList<>();
        //ProgressDialog barra = new ProgressDialog(context);
        //barra.setMessage("Cargando Información del servidor...");
        //barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //barra.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ProductoRef producto = null;
                            JSONArray arrayProducto = response.getJSONArray("items");
                            JSONObject objeto = null;
                            for(int i = 0; i < arrayProducto.length();i++){
                                objeto = arrayProducto.getJSONObject(i);
                                int id = objeto.getInt("Id");
                                String imagen = objeto.getString("imagen");
                                String nombre = objeto.getString("nombre");
                                int price = objeto.getInt("price");
                                String descripcion = objeto.getString("descripcion");
                                int cantidad = objeto.getInt("cantidad");

                                producto = new ProductoRef(id,imagen,nombre,price,descripcion,cantidad);
                                productos.add(producto);
                            }
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
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        //Log.e("EN CLASE: ",""+productos.size());
        return productos;
    }
}
