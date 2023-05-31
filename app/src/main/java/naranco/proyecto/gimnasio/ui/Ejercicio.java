package naranco.proyecto.gimnasio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import naranco.proyecto.gimnasio.R;

public class Ejercicio extends AppCompatActivity {
    private TextView ejNombre, tiempo;
    private EditText sets, reps, kilos, notas;
    private Spinner mods;
    private SeekBar rpe;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
    }
}