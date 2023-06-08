package naranco.proyecto.gimnasio.ui;

import static naranco.proyecto.gimnasio.db.GimnasioDatabase.db;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.Ejercicio;
import naranco.proyecto.gimnasio.db.EjercicioDao;

public class AddEjercicioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText ejNombre;
    private Spinner movimiento, equipo;
    private Button fin;
    private EjercicioDao ejDao;
    private List nombres;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ejercicio);
        ejNombre = findViewById(R.id.etEjercicio);
        movimiento = findViewById(R.id.spinnerMovimiento);
        equipo = findViewById(R.id.spinnerEquipo);
        fillSpinners();
        ejDao = db.ejDao();
        fin = findViewById(R.id.btnFinalizar);
    }



//    Mete los valores que van a usar los dos spinners de la actividad para
//            que el usuario pueda personalizar los ejercicios que cree
    public void fillSpinners() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterMov = ArrayAdapter.createFromResource(this,
                R.array.movimientos, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterEq = ArrayAdapter.createFromResource(this,
                R.array.equipo, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterMov.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterEq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        movimiento.setAdapter(adapterMov);
        equipo.setAdapter(adapterEq);
        // Set listener to the spinner
        movimiento.setOnItemSelectedListener(this);
        equipo.setOnItemSelectedListener(this);
    }
//  Metodo que recoge el elemento seleccionado de un spinner
//    Deprecado pero se mantiene para futuras pruebas
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
//        String eoeo = (String) movimiento.getItemAtPosition(pos);
//        Toast.makeText(getApplicationContext(),eoeo,Toast.LENGTH_LONG).show();
    }
    // Comportamiento del spinner cuando no se selecciona ningun elemento
    //    Deprecado pero se mantiene para futuras pruebas

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    // Recoge los valores que metio el usuario y crea e inserta el nuevo
//    ejercicio en la base de datos
//    cierra la actividad cuando se pulsa el boton "Finalizar"
    public void fin(View view){
        nombre = String.valueOf(ejNombre.getText());
        String mov = (String) movimiento.getSelectedItem();
        String eq = (String) equipo.getSelectedItem();
        if(checkNombre(nombre)){
            ejDao.insert(new Ejercicio(nombre, mov, eq));
            finish();
        }
    }
    //    Comprueba que el nombre del ejercicio no ha sido utilizado previamente
    public boolean checkNombre(String name){
        nombres = ejDao.getAllNombres();

        if(nombres.contains(name)){
           crearAlerta("Error", "Ese nombre ya existe, introduce otro.");
           return false;
       }
       return true;
    }

    public void crearAlerta(String titulo, String mensaje){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();
    }
}