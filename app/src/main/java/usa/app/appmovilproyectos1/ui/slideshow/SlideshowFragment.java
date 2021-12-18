package usa.app.appmovilproyectos1.ui.slideshow;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.lang.reflect.Array;
import java.util.ArrayList;

import usa.app.appmovilproyectos1.BuildConfig;
import usa.app.appmovilproyectos1.R;
import usa.app.appmovilproyectos1.databinding.FragmentSlideshowBinding;
import usa.app.appmovilproyectos1.ui.model.Basededatos;
import usa.app.appmovilproyectos1.ui.model.Sucursal;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private Basededatos basededatos;
    private ArrayList<Sucursal> sucursals;
    private MapView myOpenMapView;
    private MapController myMapController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Context ctx = getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        basededatos = new Basededatos();
        sucursals = basededatos.getSucursals();

        TextView espacio = (TextView) binding.textViewespacio;
        espacio.setText("");
        espacio.setTextSize(21.f);

        TextView espacio2 = (TextView) binding.textViewespacio2;
        espacio2.setText("");
        espacio2.setTextSize(21.f);

        TextView titulo = (TextView) binding.textViewMap;
        titulo.setText("MARK HANDMADE SUCURSALES");
        titulo.setTextSize(TypedValue.COMPLEX_UNIT_DIP,21.f);
        titulo.setTextColor(Color.WHITE);
        titulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView titulo2 = (TextView) binding.textViewMap2;
        titulo2.setText("Conoce nuestros puntos de venta ubicados en la Ciudad de Bogotá D.C");
        titulo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15.f);
        titulo2.setTextColor(Color.WHITE);
        titulo2.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);


        myOpenMapView = (MapView) binding.openmapview;
        myOpenMapView.setTileSource(TileSourceFactory.MAPNIK);
        GeoPoint bogota = new GeoPoint(4.657034, -74.105018);
        GeoPoint puntof = new GeoPoint(0.00000,0.00000);
        GeoPoint puntof2 = new GeoPoint(0.00000,0.00000);
        GeoPoint puntof3 = new GeoPoint(0.00000,0.00000);
        myOpenMapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);

        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setCenter(bogota);
        myMapController.setZoom(13);
        myOpenMapView.setMultiTouchControls(true);
        Marker punto1 = new Marker(myOpenMapView);
        Marker punto2 = new Marker(myOpenMapView);
        Marker punto3 = new Marker(myOpenMapView);

            puntof.setCoords(sucursals.get(0).getLatitud(),sucursals.get(0).getLongitud());
            punto1.setPosition(puntof);
            punto1.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_CENTER);
            punto1.setTitle(sucursals.get(0).getNombre() + sucursals.get(0).getDireccion());
            myOpenMapView.getOverlays().add(punto1);

            puntof2.setCoords(sucursals.get(1).getLatitud(),sucursals.get(1).getLongitud());
            punto2.setPosition(puntof2);
            punto2.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_CENTER);
            punto2.setTitle(sucursals.get(1).getNombre() + sucursals.get(1).getDireccion());
            myOpenMapView.getOverlays().add(punto2);

            puntof3.setCoords(sucursals.get(2).getLatitud(),sucursals.get(2).getLongitud());
            punto3.setPosition(puntof3);
            punto3.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_CENTER);
            punto3.setTitle(sucursals.get(2).getNombre() + sucursals.get(2).getDireccion());
            myOpenMapView.getOverlays().add(punto3);




        /**final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}