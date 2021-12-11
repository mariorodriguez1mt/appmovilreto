package usa.app.appmovilproyectos1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import usa.app.appmovilproyectos1.databinding.ActivityMainBinding;

/**
 * @author MarioR
 * @version 1.1
 */
public class MainActivity extends AppCompatActivity {
    /**
     * variable para controlar la barra de configuracion
     */
    private AppBarConfiguration mAppBarConfiguration;
    /**
     * variable para controlar el layout activity main
     */
    private ActivityMainBinding binding;

    /**
     * metodo override para poner en línea la vista layout navigation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            /**
             * evento que se activa sobre click en el snackbar de la parte inferior de la app
             * @param view
             */
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hola esta es una nueva funcionalidad de prueba", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /**
         * configuracion de las vistas de layout navegacion
         */
        DrawerLayout drawer = binding.drawerLayout;
        /**
         * variable navigationview
         */
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.about)
                .setOpenableLayout(drawer)
                .build();
        /**
         * controlador de layout navegacion
         */
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    /**

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    /**
     * metodo para navegacion layout
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        /**
         * variable navController
         */
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.favoritos){
            Toast.makeText(getApplicationContext(), "Favoritos en construcción", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}