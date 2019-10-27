package com.example.proyectoandroid;

import android.net.Uri;
import android.os.Bundle;

import com.example.proyectoandroid.Fragmentos.Frag1;
import com.example.proyectoandroid.Fragmentos.Frag2;
import com.example.proyectoandroid.Fragmentos.FragPrincipal;
import com.example.proyectoandroid.Fragmentos.FragSecundario;
import com.example.proyectoandroid.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FloatingActionButton fab = findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {
         //       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
         //               .setAction("Action", null).show();
         //   }
        //});
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.LEFT);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //mAppBarConfiguration = new AppBarConfiguration.Builder(
          //     R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
            //    R.id.nav_tools, R.id.nav_share, R.id.nav_send)
              //  .setDrawerLayout(drawer)
                //.build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);

        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle(this,drawer,toolbar,0,0);
        drawer.addDrawerListener(toogle);
        toogle.syncState();
        //navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main2,new Frag2()).commit();
        //se agrega

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        Toast.makeText(this,"Ingresando de prueba  12",Toast.LENGTH_SHORT).show();
        int id=menuItem.getItemId();
        Fragment mifrag=null;
        FragmentManager fragmentManager=getSupportFragmentManager();
        boolean fragmentseleccionado=false;
        //mi perfil
        if(id==R.id.nav_home){
            mifrag=new Frag1();
            Toast.makeText(this,"Mensaje de prueba  12",Toast.LENGTH_SHORT).show();
            //fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,mifrag).commit();
            fragmentseleccionado=true;
        }else if(id==R.id.nav_gallery){
            mifrag=new Frag2();
            Toast.makeText(this,"Mensaje de prueba",Toast.LENGTH_SHORT).show();
            fragmentManager.beginTransaction().replace(R.id.content_main2,mifrag).commit();
            fragmentseleccionado=true;
        }else if(id==R.id.nav_slideshow){

        }else if(id==R.id.nav_tools){
            Toast.makeText(this,"Mensaje de prueba  12",Toast.LENGTH_SHORT).show();
        }else if(id==R.id.nav_send){
            Toast.makeText(this,"Mensaje de prueba",Toast.LENGTH_SHORT).show();
        }
        //fragmentManager.beginTransaction().replace(R.id.content_main2,mifrag).commit();
        Toast.makeText(this,"entre los ifs de prueba  12",Toast.LENGTH_SHORT).show();
        if(fragmentseleccionado==true){

            fragmentManager.beginTransaction().replace(R.id.content_main2,mifrag).commit();
            Toast.makeText(this,"En el ultimo if de prueba  12",Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //StatementWithEmptyBody/


}
