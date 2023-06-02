package naranco.proyecto.gimnasio.ui;

import static naranco.proyecto.gimnasio.db.GimnasioDatabase.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.ProgresoDao;

public class EjercicioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView ejNombre, tiempo, set;
    private EditText sets, reps, kilos, notas;
    private int num_set;
    private Spinner mods;
    private SeekBar rpe;
    private Button fin;
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
        set = findViewById(R.id.tvSet);
        num_set = 1;
        set.setText(num_set);

        reps = findViewById(R.id.etReps);
        kilos = findViewById(R.id.etKilos);
        mods = findViewById(R.id.spinnerMods);
        fillSpinnerMods();
        rpe = findViewById(R.id.seekBarRpe);
        setSeekBar();
        fin = findViewById(R.id.btnFinalizar);
        notas = findViewById(R.id.etNotas);

    }


//  click on nombre -> lista de ejercicios
//    en lugar de boton siguiente, finalizar

    public void fillSpinnerMods() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mods, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mods.setAdapter(adapter);
        mods.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        String eoeo = (String) mods.getItemAtPosition(pos);
//        Toast.makeText(getApplicationContext(),eoeo,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    public void setSeekBar(){
        rpe.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                // When the progress value has changed
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

                    // increment 1 in progress and
                    // increase the textsize
                    // with the value of progress
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar){

                    // This method will automatically
                    // called when the user touches the SeekBar
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar){

                    // This method will automatically
                    // called when the user
                    // stops touching the SeekBar
                }
            }
        );
    }

    public void siguiente(){
        num_set++;
        String nombre = String.valueOf(ejNombre.getText());
        progDao = db.progDao();
//        progDao.insert(new Progreso(nombre));
        finish();
    }
}