package naranco.proyecto.gimnasio.ui;

import static naranco.proyecto.gimnasio.db.GimnasioDatabase.db;
import static naranco.proyecto.gimnasio.db.GimnasioDatabase.openDB;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.Progreso;
import naranco.proyecto.gimnasio.db.ProgresoDao;

public class EjercicioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView ejNombre, tiempo, set, lastDay;
    private EditText reps, kilos, notas;
    private int num_set;
    private double rpeDone = 7.0, pesoDone = 0;
    private String fecha, nombreEj, notes, set_mod;
    private int setNum, repsDone = 0;
    private Spinner mods;
    private SeekBar rpe;
    private Button fin;
    private ProgresoDao progDao;
    private MediaPlayer alerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
        openDB(this);
        progDao = db.progDao();

        Intent intent = getIntent();
        nombreEj = intent.getExtras().getString("ejercicio");

        ejNombre = findViewById(R.id.ejercicioNombre);
        ejNombre.setText(nombreEj);

        tiempo = findViewById(R.id.tiempo);
        startTemporizador();

        set = findViewById(R.id.tvSet);
        numSetDateEx();
        set.setText(String.valueOf(num_set));

        reps = findViewById(R.id.etReps);
        kilos = findViewById(R.id.etKilos);

        lastDay = findViewById(R.id.lastDay);

        getLastDay();
        onClickLastDay();
        mods = findViewById(R.id.spinnerMods);
        fillSpinnerMods();
        rpe = findViewById(R.id.seekBarRpe);
        setSeekBar();
        fin = findViewById(R.id.btnFinalizar);
        notas = findViewById(R.id.etNotas);

        alerta = MediaPlayer.create(this, R.raw.alerta);
    }

    // Crea y comienza el temporizador de descando por cada ejercicio
//    90s reduciendo 1s
    public void startTemporizador() {
        new CountDownTimer(90000, 1000) {
            // Lo que hace el temporizador con cada intervalo de cuenta atras (1s)
            //Muestra el contenido del temporizador en el EditText "tiempo"
            public void onTick(long millisUntilFinished) {
                tiempo.setText("descanso: " + millisUntilFinished / 1000);
            }
            // Cuando el temporizador termina, crea una alerta y reproduce un aviso sonoro
            public void onFinish() {
                tiempo.setText("Fin del descanso");
                crearAlerta("Fin del descanso", "");
                lanzarAlerta();
            }
        }.start();
    }
//    Si la alerta no esta sonando, lanza un aviso sonoro
    private void lanzarAlerta() {
        if (!alerta.isPlaying()) {
            alerta.start();
        }
    }
    // Metodo para para la alerta
//    Creado por prevencion, sin uso.
    private void pararAlerta() {
        /* Paramos la música sólo si está sonando */
        if (alerta.isPlaying()) {
            alerta.stop();
        }
    }
// Este metodo es un constructor de alertas
//    Creado al principio para pruebas, pero solo se usa una vez
    public void crearAlerta(String titulo, String mensaje){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();
    }
//    Llama al objeto de acceso a la base de datos y recoge el numero del ultimo set realizado
//    Usado para evitar tener sets duplicados ya que es una clave primaria
    public void numSetDateEx(){
        num_set = progDao.getNumSet(nombreEj) + 1;
    }
//    Metodo para cargar opciones en el spinner de los modificadores de set
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
//    Mediante una llamada al objecto de acceso, recoge la ultima instancia del ejercicio
//    para mostrarlo como referencia mientras se hace el mismo ejercicio mas adelante en el timepo
    public void getLastDay(){
        Progreso x = progDao.getLastDay(nombreEj);
        if(x != null) {
            lastDay.setText(
                    x.getFecha() + " " + x.getSetNum() +
                            x.getReps() + " @ " + x.getPeso() + "kg" +
                            " RPE: " + x.getRpe() + " " + x.getMods() + "\n" +
                            x.getNotas());
        } else {
            lastDay.setText("Sin datos.");
        }
    }

//    Cuando se hace click en el ultimo dia, recoge el nombre del ejercicio
//    y lo manda al metodo verProgreso
    public void onClickLastDay() {
        lastDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progresoactivity de ese ejercicio
                nombreEj = (String) ejNombre.getText();
                verProgreso(nombreEj);
            }
        });
    }
//    Recoge el modificador seleccionado por el usuario y lo guarda
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        set_mod = (String) mods.getItemAtPosition(pos);
//        Toast.makeText(getApplicationContext(),eoeo,Toast.LENGTH_LONG).show();
    }
//    Meteodo usado para hacer pruebas cuando no se selccionaba ningun elemento
//    sin uso
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

//    Este metodo inicia el seekbar para el RPE
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
                        case 0:
                            rpeDone = (double) 7;
                            break;

                        case 1:
                            rpeDone = (double) 7.5;
                            break;

                        case 2:
                            rpeDone = (double) 8;
                            break;

                        case 3:
                            rpeDone = (double) 8.5;
                            break;

                        case 4:
                            rpeDone = (double) 9;
                            break;

                        case 5:
                            rpeDone = (double) 9.5;
                            break;

                        case 6:
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
//    Recoge y formatea la fecha de "hoy" automaticamente
    public String getFecha(){
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yy"));
        return formattedDate;
    }
//    Lanza una actividad que muestra el progreso del ejercicio
    public void verProgreso(String ejName){
        Intent intent = new Intent(this, ProgresoActivity.class);
        intent.putExtra("nombreEj", ejName);
        startActivity(intent);
    }

//    Comprueba que las repeticiones y el peso no estan vacios
//    Si lo estan manda un aviso
//    Si no estan vacios da valor a las variables para crear un objeto e insertarlo en la BD
//    Despues limpiar los valores y reinicia el temporizador, para el siguiente set
    public void siguiente(View view){

        if (reps.getText().toString().isEmpty() || kilos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes introducir peso y reps",
                    Toast.LENGTH_SHORT).show();
        } else {
            fecha = getFecha();
            nombreEj = String.valueOf(ejNombre.getText());
            setNum = num_set;
            repsDone = Integer.parseInt(String.valueOf(reps.getText()));
            pesoDone = Double.parseDouble(String.valueOf(kilos.getText()));
            notes = String.valueOf(notas.getText()).trim();
            Progreso progreso = new Progreso(fecha, nombreEj, setNum, repsDone, rpeDone, pesoDone, notes, set_mod);

            progDao.insert(progreso);

            Toast.makeText(this, "Set agregado!", Toast.LENGTH_LONG).show();
            num_set++;
            set.setText(String.valueOf(num_set));
            clearAll();
            startTemporizador();
        }
    }
//    Pone a 0 el peso y las repeticiones para que no se sumen con las del siguiente set
    public void clearAll(){
        repsDone = 0;
        pesoDone = 0;
    }
}