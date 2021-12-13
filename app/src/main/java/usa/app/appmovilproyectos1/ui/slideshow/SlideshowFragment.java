package usa.app.appmovilproyectos1.ui.slideshow;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

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

        //basededatos = new Basededatos();
        myOpenMapView = (MapView) binding.openmapview;
        GeoPoint bogota = new GeoPoint(4.657034, -74.105018);
        myOpenMapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setCenter(bogota);
        myMapController.setZoom(12);

        myOpenMapView.setMultiTouchControls(true);
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