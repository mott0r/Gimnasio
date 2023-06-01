package naranco.proyecto.gimnasio.ui;

import static naranco.proyecto.gimnasio.db.GimnasioDatabase.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.Ejercicio;
import naranco.proyecto.gimnasio.db.Progreso;
import naranco.proyecto.gimnasio.db.ProgresoDao;

public class EjercicioActivity extends AppCompatActivity {
    private TextView ejNombre, tiempo;
    private EditText sets, reps, kilos, notas;
    private Spinner mods;
    private SeekBar rpe;
    private Button siguiente;
    private String nombreEj;
    private ProgresoDao progDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
        Intent intent = getIntent();
        nombreEj = intent.getExtras().getString("ejercicio");
        ejNombre = findViewById(R.id.ejercicioNombre);
        ejNombre.setText(nombreEj);
//        tiempo = findViewById(R.id.tiempo);
        sets = findViewById(R.id.etSets);
        reps = findViewById(R.id.etReps);
        kilos = findViewById(R.id.etKilos);
        mods = findViewById(R.id.spinnerMods);
        rpe = findViewById(R.id.seekBarRpe);
        siguiente = findViewById(R.id.btnSiguiente);
        notas = findViewById(R.id.etNotas);
    }


//click on nombre -> lista de ejercicios
//    en lugar de boton siguiente, finalizar




    public void siguiente(){
        String nombre = String.valueOf(ejNombre.getText());
        progDao = db.progDao();
//        progDao.insert(new Progreso());
        finish();
    }



}