package naranco.proyecto.gimnasio.ui;

import static naranco.proyecto.gimnasio.db.GimnasioDatabase.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.Ejercicio;
import naranco.proyecto.gimnasio.db.EjercicioDao;

public class AddEjercicioActivity extends AppCompatActivity {

    private EditText ejNombre;

    private Spinner mods, equipo;
    private Button fin;
    private EjercicioDao ejDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ejercicio);
        ejNombre = findViewById(R.id.etEjercicio);
        fin = findViewById(R.id.btnSiguiente);
    }

    public void fin(View view){
        String nombre = String.valueOf(ejNombre.getText());
        ejDao = db.ejDao();
        ejDao.insert(new Ejercicio(nombre));
        finish();
    }
}