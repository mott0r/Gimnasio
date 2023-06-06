package naranco.proyecto.gimnasio.ui;

import static naranco.proyecto.gimnasio.db.GimnasioDatabase.db;
import static naranco.proyecto.gimnasio.db.GimnasioDatabase.openDB;

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
import android.widget.Toast;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.Progreso;
import naranco.proyecto.gimnasio.db.ProgresoDao;

public class UpdateDeleteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Serializable {
    private TextView ejNombre, tiempo, set;
    private EditText sets, reps, kilos, notas;
    private int num_set, setNum;
    private double rpeDone,pesoDone;
    private String fecha, nombreEj, notes, set_mod;
    private int repsDone;
    private Spinner mods;
    private SeekBar rpe;
    private Button btnEdit, btnDel;
    private ProgresoDao progDao;
    private Progreso prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        openDB(this);
        progDao = db.progDao();
        Intent intent = getIntent();
        nombreEj = intent.getExtras().getString("ejNombre");
        num_set = intent.getExtras().getInt("setNum");
        repsDone = intent.getExtras().getInt("reps");
        pesoDone = intent.getExtras().getDouble("peso");
        rpeDone = intent.getExtras().getDouble("rpe");
        set_mod = intent.getExtras().getString("mods");
        notes = intent.getExtras().getString("notas");
        fecha = intent.getExtras().getString("fecha");

        prog = new Progreso(fecha, nombreEj, num_set, repsDone, rpeDone, pesoDone, notes, set_mod);

        ejNombre = findViewById(R.id.ejercicioNombre);
        set = findViewById(R.id.tvSet);
        reps = findViewById(R.id.etReps);
        kilos = findViewById(R.id.etKilos);
        mods = findViewById(R.id.spinnerMods);
        fillSpinnerMods();
        rpe = findViewById(R.id.seekBarRpe);
        setSeekBar();
        notas = findViewById(R.id.etNotas);

        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDel);

//        tiempo = findViewById(R.id.tiempo);

        ejNombre.setText(nombreEj);
        set.setText(Integer.toString(num_set));
        reps.setText(Integer.toString(repsDone));
        kilos.setText(String.valueOf(pesoDone));
//        rpe.set
//        mods.setConte
        notas.setText(notes);
    }
    //TODO
    // actualizar la lista despues de onclick
    public void editar(View view){
        // alert seguro?
        update();
    }

    public void borrar(View view){
        // alert seguro?
        remove();
    }

    public void update(){
        fecha = getFecha();
        nombreEj = String.valueOf(ejNombre.getText());
        setNum = num_set;
        repsDone = Integer.valueOf(String.valueOf(reps.getText()));
        pesoDone = Double.valueOf(String.valueOf(kilos.getText()));
        notes = String.valueOf(notas.getText()).trim();
        Progreso progreso = new Progreso(fecha, nombreEj, setNum, repsDone, rpeDone, pesoDone, notes, set_mod);
        progDao.update(progreso);
        Toast.makeText(this, "Actualizado!", Toast.LENGTH_LONG);
        finish();
    }

    public void remove(){
        progDao.delete(prog);
        Toast.makeText(this, "Eliminado!", Toast.LENGTH_LONG);
    }
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
        set_mod = (String) mods.getItemAtPosition(pos);
//        Toast.makeText(getApplicationContext(),eoeo,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
    public void setSeekBar(){
        rpe.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    // When the progress value has changed
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int prog, boolean fromUser){

                        // increment 1 in progress and
                        // with the value of progress
                        // 10, 9.5, 9, 8.5, 8, 7.5, 7 = 7
                        prog += 1;
//                    double progress = (double) prog;
                        switch(prog) {
                            case 1:
                                rpeDone = (double) 7;
                                break;

                            case 2:
                                rpeDone = (double) 7.5;
                                break;

                            case 3:
                                rpeDone = (double) 8;
                                break;

                            case 4:
                                rpeDone = (double) 8.5;
                                break;

                            case 5:
                                rpeDone = (double) 9;
                                break;

                            case 6:
                                rpeDone = (double) 9.5;
                                break;

                            case 7:
                                rpeDone = (double) 10;
                                break;
                        }
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
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    public String getFecha(){
//        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yy"));
        return formattedDate;
    }
}