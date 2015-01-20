package es.javiergarbedo.tutorialmasterdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


/**
 * An activity representing a list of People. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PersonDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link PersonListFragment} and the item details
 * (if present) is a {@link PersonDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link PersonListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class PersonListActivity extends ActionBarActivity
        implements PersonListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        if (findViewById(R.id.person_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((PersonListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.person_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link PersonListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {

        this.id = id;

        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.

//            Bundle arguments = new Bundle();
//            arguments.putString(PersonDetailFragment.ARG_ITEM_ID, id);
//            PersonDetailFragment fragment = new PersonDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.person_detail_container, fragment)
//                    .commit();

            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.person_detail_container); //Cambiar
            mViewPager.setAdapter(mSectionsPagerAdapter);

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
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PersonDetailActivity.class);
            detailIntent.putExtra(PersonDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

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
//            arguments.putString(PersonDetailFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(PersonDetailFragment.ARG_ITEM_ID));
            arguments.putString(PersonDetailFragment.ARG_ITEM_ID, id);
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
            return 2;
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
