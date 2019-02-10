package app.arxapp.jonathan.arxap;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class activity_administracion_ciudadela extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabLista;
    TabItem tabNoticias;
    //TabItem tab..;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_ciudadela);






        /*
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Arxapp");
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabLista = findViewById(R.id.tabLista);
        tabNoticias = findViewById(R.id.tabNoticias);
        //TabItem tabCalls = findViewById(R.id.tabCalls);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        */



    }





}
