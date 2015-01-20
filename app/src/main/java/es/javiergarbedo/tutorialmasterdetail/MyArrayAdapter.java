package es.javiergarbedo.tutorialmasterdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.javiergarbedo.tutorialmasterdetail.person.Person;

/* Se crea esta clase como una extensión (hija) de ArrayAdapter (extends ArrayAdapter),
 que es la clase que se utiliza normalmente para cargar valores en un ListView. De esta manera
 conseguiremos tener las mismas funcionalidades que la clase ArrayAdapater pero haciendo la
 personalización que deseemos */
public class MyArrayAdapter extends ArrayAdapter {

    private final Context context;
    //TODO: Cambiar el tipo de elementos contenidos en la lista
    private final ArrayList<Person> valuesList; //Lista de objetos que va a contener la lista

    /**
     * Constructor que permite cargar las propiedades de esta clase, llamando también al constructor
     * original de ArrayAdapter
     *
     * @param context    Contexto (p.e. getActivity())
     * @param valuesList Lista de objetos que va a contener la lista
     */
    public MyArrayAdapter(Context context, ArrayList valuesList) {
        //TODO: Comprobar el nombre del layout que contiene estructura de elemento de la lista
        super(context, R.layout.rowlayout, valuesList);
        this.context = context;
        this.valuesList = valuesList;
    }

    /* Sobrecargar el método getView para rellenar el elemento de la lista con los valores
    almacenados en la propiedad values de esta clase
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //TODO: Comprobar el nombre del layout que contiene estructura de elemento de la lista
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        //Obtener el objeto correspondiente a la posición de la lista
        //TODO: Cambiar el tipo de objeto
        Person person = valuesList.get(position);

        //TODO: Rellenar los componentes (Views) del layout con el valor correspondiente del array
        // values, obteniendo previamente la variable que haga referencia a cada componente

        //Cargar en el TextView de letra grande el alias del contacto
        TextView textViewAlias = (TextView) rowView.findViewById(R.id.textViewAlias);
        textViewAlias.setText(person.getAlias());

        //Cargar en el TextView de letra pequeña el nombre completo del contacto
        TextView textViewFullName = (TextView) rowView.findViewById(R.id.textViewFullName);
        textViewFullName.setText(person.getSurnames() + ", " + person.getName());

        //Cargar la imagen con la foto del contacto
        ImageView imageViewPhoto = (ImageView) rowView.findViewById(R.id.imageViewPhoto);
        int id = context.getResources().getIdentifier(
                "drawable/" + person.getPhotoFileName(),
                "drawable",
                context.getPackageName());
        imageViewPhoto.setImageDrawable(context.getResources().getDrawable(id));

        return rowView;
    }
}
