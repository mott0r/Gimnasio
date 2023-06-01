package naranco.proyecto.gimnasio.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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

public class ElegirEjercicioActivity extends AppCompatActivity {
    Button addEx;
    private String nombreEj;
    public GimnasioDatabase db;
    private EjercicioDao ejDao;
    private TextView ejList;
    private List<Ejercicio> ejs;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_ejercicio);
        addEx = findViewById(R.id.btn_addEjercicio);
        ejList = findViewById(R.id.ejsList);

        openDB();
        fillDB();
        cargarEjercicios();
        listarEjercicios();
    }
    protected void onRestart() {
        super.onRestart();
        ejs.clear();
        cargarEjercicios();
        listarEjercicios();
    }

    public void openDB(){
        db = GimnasioDatabase.openDB(this);
        ejDao = db.ejDao();
    }

    public void fillDB(){
//        Ejercicio ej1 = new Ejercicio("BenchPress", "Push", "Barbell");
//        Ejercicio ej2 = new Ejercicio("Squat", "Push", "Barbell");
//        Ejercicio ej3 = new Ejercicio("OHP", "Push", "Barbell");
//        ejDao.insert(ej1);
//        ejDao.insert(ej2);
//        ejDao.insert(ej3);
    }
    public void cargarEjercicios(){
        ejs = ejDao.getAll();
    }

    public void listarEjercicios(){
//        ejList.setText(ejs.get(ejs.size()-1).getNombre());
        linearLayout = findViewById(R.id.linearLayout);

        for(Ejercicio x : ejs){
            TextView newTextView = new TextView(this);
            newTextView.setText(x.getNombre());
            newTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    nombreEj = (String) newTextView.getText();
                    rutina();
                }
            });
            linearLayout.addView(newTextView);
        }
    }

    public void rutina(){
        Intent intent = new Intent(this, EjercicioActivity.class);
        intent.putExtra("ejercicio", nombreEj);
        startActivity(intent);
    }

    public void addEjercicio(View view){
        Intent intent = new Intent(this, AddEjercicioActivity.class);
        startActivity(intent);
    }
}