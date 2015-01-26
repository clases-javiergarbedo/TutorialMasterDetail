package es.javiergarbedo.tutorialmasterdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;

/*
Modificaciones realizadas en la Activity encargada de mostrar el detalle en una aplicación de tipo
master-detail para que se puedan utilizar páginas de desplazamiento lateral o pestañas.

Más información sobre la distribución en páginas laterales (Swipe Views) o pestañas (Tabs) en:
http://developer.android.com/training/implementing-navigation/lateral.html
 */

public class PersonDetailActivity extends ActionBarActivity {

    /* TODO: Añadir las 2 líneas siguientes
    La clase SectionsPagerAdapter no se encontrará inicialmente, porque se va a crear después
    dentro de esta misma clase.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    final LatLng YEREVAN = new LatLng(40.181, 44.513);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO: Añadir estas líneas cambiando el ID del Layout al correspondiente a
        // la Activity de detalle. En ese layout debe cambiarse el elemento raíz,
        // en la vista XML, cambiando FrameLayout por android.support.v4.view.ViewPager
        // Lo que hacen estas líneas es adaptar el layout de la Activity a un objeto
        // de tipo ViewPager para que se pueda mostrar la Activity por páginas o pestañas
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.person_detail_container); //Cambiar
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //TODO: Eliminar todo este bloque que se colocará parecido posteriormente
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(PersonDetailFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(PersonDetailFragment.ARG_ITEM_ID));
//            PersonDetailFragment fragment = new PersonDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.person_detail_container, fragment)
//                    .commit();
//        }

        /*
        Las siguientes líneas de código muestran pestañas en la Activity para poder pasar de una
        página a otra usando las pestañas, además de que se puedan pasar arrastrándo las páginas
        lateralmente como se ha hecho en el código anterior.
        Para las clases FragmentTransaction y ActionBar añadir los import:
            import android.support.v4.app.FragmentTransaction;
            import android.support.v7.app.ActionBar;
        en lugar de la otra posibilidad que sería:
            import android.app.FragmentTransaction;
            import android.app.ActionBar;
        Usando los paquetes support se ofrece la posibilidad de usar las clases FragmentTransaction
        y ActionBar en dispositivos con API antigua que no soportaba esas clases.
        */

        //Obtener una referencia a la barra de acciones de la Activity porque en ella se situarán
        //  las pestañas, y se especifica que se va a usar el modo de navegación con pestañas (tabs)
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Se debe crear un TabListener para que se puedan añadir acciones a los cambios de
        //  selección que realice el usuario en las pestañas.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            //Se ha seleccionado una pestaña
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                //Cambiar a la página correspondiente a la pestaña seleccionada
                mViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }

        };

        //Indica aquí los títulos que van a tener las pestañas. Se crearán tantas pestañas como
        //  títulos se indiquen en este array.
        String titles[] = {"Tab1", "Tab2", "Tab3"};
        for (int i = 0; i < titles.length; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(titles[i])
                            .setTabListener(tabListener));
        }

        //Cuando el usuario cambie de página usando el arrastre lateral en lugar de las pestañas,
        //  también se debe cambiar de pestaña seleccionada
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, PersonListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /* TODO: Copiar toda esta clase, que se utiliza para poder crear objetos que permitan adaptar
    varios Fragment que se incluyan en una Activity de modo que se puedan mostrar a modo de páginas
    o pestañas.
    Observa que esta clase SectionsPagerAdapter se encuentra dentro de la anterior xxxDetailActivity
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /*
        Retorna el Fragment que corresponda en función de la posición (position) que corresponda,
        que es la página o pestaña que deberá mostrarse dentro de la Activity
         */
        @Override
        public Fragment getItem(int position) {
            //TODO: Meter aquí el siguiente código que es similar al que había en el método
            // onCreate original de esta Activity
            Bundle arguments = new Bundle();
            //Se pasa al fragment el id de la posición seleccionada por el usuario en la lista
            arguments.putString(PersonDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PersonDetailFragment.ARG_ITEM_ID));
            switch (position) {
                case 0:
                    //Se indica el Fragment que se cargará para la position 0
                    PersonDetailFragment fragment1 = new PersonDetailFragment();
                    fragment1.setArguments(arguments);
                    return fragment1;
                case 1:
                    //Se indica el Fragment que se cargará para la position 1
                    //TODO: Hay que crear una clase diferente para cada contenido de las pestañas
                    PersonDetailFragment2 fragment2 = new PersonDetailFragment2();
                    fragment2.setArguments(arguments);
                    return fragment2;
                case 2:
//                    return MyMapFragment.newInstance(YEREVAN);
                    return new MyMapFragment();
//                    PersonDetailFragment3 fragment3 = new PersonDetailFragment3();
//                    fragment3.setArguments(arguments);
//                    return fragment3;
            }
            return null;
        }

        /*
        Retorna el número de páginas o pestañas (Fragments) que se incluirán en la Activity.
        Debe coincidir con el número de casos incluidos en el método getItem anterior.
         */
        @Override
        public int getCount() {
            // TODO: Indicar aquí el número de pestañas que se van a tener
            return 3;
        }

        /*
        Retorna el título que se mostrará en cada página o pestaña
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    //TODO: Crear un recurso String para cada título de las pestañas
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section2);
            }
            return null;
        }
    }

}
