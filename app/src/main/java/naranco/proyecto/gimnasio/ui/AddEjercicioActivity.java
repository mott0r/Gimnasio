package naranco.proyecto.gimnasio.ui;

import static naranco.proyecto.gimnasio.db.GimnasioDatabase.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.Ejercicio;
import naranco.proyecto.gimnasio.db.EjercicioDao;

public class AddEjercicioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText ejNombre;
    private Spinner movimiento, equipo;
    private Button fin;
    private EjercicioDao ejDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ejercicio);
        ejNombre = findViewById(R.id.etEjercicio);
        movimiento = findViewById(R.id.spinnerMovimiento);
        equipo = findViewById(R.id.spinnerEquipo);
        fillSpinners();

        fin = findViewById(R.id.btnFinalizar);
    }
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
        equipo.setAdapter(adapterMov);
        // Set listener to the spinner
        movimiento.setOnItemSelectedListener(this);
        equipo.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
//        String eoeo = (String) movimiento.getItemAtPosition(pos);
//        Toast.makeText(getApplicationContext(),eoeo,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    public void fin(View view){
        String nombre = String.valueOf(ejNombre.getText());
        String mov = (String) movimiento.getSelectedItem();
        String eq = (String) equipo.getSelectedItem();

        ejDao = db.ejDao();
        ejDao.insert(new Ejercicio(nombre, mov, eq));

        finish();
    }
}