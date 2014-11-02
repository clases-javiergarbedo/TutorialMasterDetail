package es.javiergarbedo.tutorialmasterdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import es.javiergarbedo.tutorialmasterdetail.person.Person;
import es.javiergarbedo.tutorialmasterdetail.person.PersonContent;

public class PersonDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

//    private DummyContent.DummyItem mItem;
    private Person person;

    public PersonDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            //Obtener el número de orden en la lista del elemento seleccionado por el usuario
            int index = Integer.valueOf(getArguments().getString(ARG_ITEM_ID));
            //Obtener la persona que se se encuentra en esa posición de la lista
            person = (Person)PersonContent.getPersonList().get(index);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_detail, container, false);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.person_detail)).setText(mItem.content);
//        }
        if(person != null) {
            //Rellenar los elementos de la pantalla de detalle
            ((TextView) rootView.findViewById(R.id.textViewName)).setText(person.getName());
            ((TextView) rootView.findViewById(R.id.textViewSurnames)).setText(person.getSurnames());
            ((TextView) rootView.findViewById(R.id.textViewMobileNumber)).setText(person.getMobileNumber());
            ((TextView) rootView.findViewById(R.id.textViewEmail)).setText(person.getEmail());
        }

        return rootView;
    }
}
