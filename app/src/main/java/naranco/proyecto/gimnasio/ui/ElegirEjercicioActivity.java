package naranco.proyecto.gimnasio.ui;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.Ejercicio;
import naranco.proyecto.gimnasio.db.EjercicioDao;
import naranco.proyecto.gimnasio.db.GimnasioDatabase;
import naranco.proyecto.gimnasio.db.ProgresoDao;

public class ElegirEjercicioActivity extends AppCompatActivity {
    Button addEx;
    private String nombreEj;
    public GimnasioDatabase db;
    private EjercicioDao ejDao;
    private ProgresoDao progDao;
    private List<Ejercicio> ejs;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_ejercicio);
        addEx = findViewById(R.id.btn_addEjercicio);

        openDB();
        cargarEjercicios();
        listarEjercicios();
    }
//    Limpia el layout cuando se reinicia la actividad para evitar duplicados
    protected void onRestart() {
        super.onRestart();
        ejs.clear();
        limpiarLayout();
        cargarEjercicios();
        listarEjercicios();
    }
//    Inicia la base de datos y carga el objeto con el que nos comunicamos con ella
    public void openDB(){
        db = GimnasioDatabase.openDB(this);
        ejDao = db.ejDao();
        progDao = db.progDao();
    }

//  Mete ejercicios en la base de datos para comprobar funcionalidad
//    Irrelevante, pero se mantiene para futuras pruebas
//    public void fillDB(){
//        Ejercicio ej1 = new Ejercicio("BenchPress", "Push", "Barbell");
//        Ejercicio ej2 = new Ejercicio("Squat", "Push", "Barbell");
//        Ejercicio ej3 = new Ejercicio("OHP", "Push", "Barbell");
//        ejDao.insert(ej1);
//        ejDao.insert(ej2);
//        ejDao.insert(ej3);
//    }
//    Hace una llamada al objeto para contactar con la base de datos y
//    recoge una lista con todos los ejercicios
    public void cargarEjercicios(){
        ejs = ejDao.getAll();
    }

//    Con la lista de todos los ejercicios del metodo "cargarEjercicios",
//    crea un textView con cada fila
//    adjunta a cada textView un evento para detectar cuando el usuario lo toca
    public void listarEjercicios(){
        linearLayout = findViewById(R.id.linearLayout);

        for(Ejercicio x : ejs){
            TextView newTextView = new TextView(this);
            newTextView.setText(x.getNombre() + "    " +x.getMovimiento() + "    " + x.getEquipo());
            newTextView.setTextSize(16);

            Button buttonEdit = new Button(this);
            buttonEdit.setText("KAKAKAKA");
            newTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ejercicioactivity de ese ejercicio
                    nombreEj = x.getNombre();
                    rutina();
                }
            });
            linearLayout.addView(newTextView);
        }
    }
//  Abre la actividad "Ejercicio", donde el usuario mete sus progresos por ejercicio y set
    public void rutina(){
        Intent intent = new Intent(this, EjercicioActivity.class);
        intent.putExtra("ejercicio", nombreEj);
        startActivity(intent);
    }
// Abre una actividad para que el usuario pueda anadir ejercicios
    public void addEjercicio(View view){
        Intent intent = new Intent(this, AddEjercicioActivity.class);
        startActivity(intent);
    }
// Quita todos los textViews agregados en "listarEjercicios"
    public void limpiarLayout(){
        linearLayout.removeAllViewsInLayout();
    }
//    public void borrarTodo(View v){
//        crearAlertaBorrar();
//    }
//
//    public void removeAll(){
//        ejDao.deleteALlEjercicios();
//        progDao.deleteALlProgreso();
//    }
//
//    // Este metodo es un constructor de alertas para confirmar el borrado
//    public AlertDialog crearAlertaBorrar(){
//        AlertDialog alerta = new AlertDialog.Builder(this)
//                .setTitle("Se borraran todos los ejercicios y progreso")
//                .setMessage("Seguro que quieres borrar t odo?")
//                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        removeAll();
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .create();
//
//        return alerta;
//    }
}