package naranco.proyecto.gimnasio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import naranco.proyecto.gimnasio.R;

public class AnadirEjercicio extends AppCompatActivity {

    private EditText ejNombre;
    private Spinner mods, equipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ejercicio);
    }
}