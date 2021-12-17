package usa.app.appmovilproyectos1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import usa.app.appmovilproyectos1.ui.model.Producto;
import usa.app.appmovilproyectos1.ui.model.ProductoRef;

public class Carrocompras extends AppCompatActivity {
    private LinearLayout lyCompras;
    private TextView texto1;
    private LinearLayout layoutHorizontalCamp;
    private LinearLayout layoutVerticalCamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrocompras);

        int prTotal = 0;
        texto1 = (TextView) findViewById(R.id.textView2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Carro de compras");


        lyCompras = (LinearLayout) findViewById(R.id.layoutCompras);
        ArrayList lista1 = new ArrayList();

        Intent intentCarrito = getIntent();
        ArrayList<ProductoRef> carritoDeCompras = (ArrayList<ProductoRef>) intentCarrito.getSerializableExtra("car");

        for(ProductoRef producto:carritoDeCompras) {
            if(producto.getCantidad()>0){
                lista1.add(producto.getCantidad());
                prTotal = prTotal+producto.getCantidad()*producto.getPrice();
                layoutHorizontalCamp = new LinearLayout(getApplicationContext());
                layoutHorizontalCamp.setOrientation(LinearLayout.HORIZONTAL);
                layoutHorizontalCamp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

                layoutVerticalCamp = new LinearLayout(getApplicationContext());
                layoutVerticalCamp.setOrientation(LinearLayout.VERTICAL);
                layoutVerticalCamp.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,2));

                ImageView image1 = new ImageView(getApplicationContext());
                //image1.setImageResource(producto.getImagen());
                Picasso.get().load(producto.getImagen()).into(image1);
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
                texto22.setText("Cantidad: " + producto.getCantidad());

                TextView texto33 = new TextView(getApplicationContext());
                texto33.setLayoutParams(new LinearLayout.LayoutParams(350, ViewGroup.LayoutParams.WRAP_CONTENT));
                texto33.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
                texto33.setTextColor(Color.WHITE);
                texto33.setText("Precio Total: $" + producto.getCantidad()*producto.getPrice());

                layoutVerticalCamp.addView(texto11);
                layoutVerticalCamp.addView(texto22);

                layoutHorizontalCamp.addView(image1);
                layoutHorizontalCamp.addView(layoutVerticalCamp);
                layoutHorizontalCamp.addView(texto33);

                lyCompras.addView(espacio1);
                lyCompras.addView(layoutHorizontalCamp);
            }

        }

        texto1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18.f);
        texto1.setTextColor(Color.WHITE);
        texto1.setText("Productos seleccionados: " + lista1.size());
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
        });


        lyCompras.addView(precioTotal);
        TextView espacio2 = new TextView(getApplicationContext());
        espacio2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,80));
        espacio2.setText("");
        lyCompras.addView(espacio2);
        lyCompras.addView(buttonComp);
        buttonComp.setGravity(Gravity.BOTTOM);



    }
}