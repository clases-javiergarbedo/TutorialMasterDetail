package es.javiergarbedo.tutorialmasterdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.javiergarbedo.tutorialmasterdetail.person.Person;
import es.javiergarbedo.tutorialmasterdetail.person.PersonContent;

public class PersonDetailFragment2 extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private Person person;

    public PersonDetailFragment2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            int index = Integer.valueOf(getArguments().getString(ARG_ITEM_ID));
            person = (Person)PersonContent.getPersonList().get(index);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_detail_2, container, false);

        if(person != null) {
            ((TextView) rootView.findViewById(R.id.textViewMobileNumber)).setText(person.getMobileNumber());
            ((TextView) rootView.findViewById(R.id.textViewEmail)).setText(person.getEmail());
        }

        return rootView;
    }
}
