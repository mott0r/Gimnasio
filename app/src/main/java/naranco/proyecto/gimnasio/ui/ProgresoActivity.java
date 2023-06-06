package naranco.proyecto.gimnasio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import naranco.proyecto.gimnasio.R;
import naranco.proyecto.gimnasio.db.GimnasioDatabase;
import naranco.proyecto.gimnasio.db.Progreso;
import naranco.proyecto.gimnasio.db.ProgresoDao;

public class ProgresoActivity extends AppCompatActivity implements Serializable {

    public GimnasioDatabase db;
    private ProgresoDao progDao;
    public static Progreso progresoExtra;
    private LinearLayout linearLayout;
    private TextView tvEj;
    private List<Progreso> prog;
    private String nombreEj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progreso2);

        Intent intent = getIntent();
        nombreEj = intent.getExtras().getString("nombreEj");
        tvEj = findViewById(R.id.tvEj);
        tvEj.setText(nombreEj);

        cargarProgreso();
        listarProgreso();
    }
    public void cargarProgreso(){
        db = GimnasioDatabase.openDB(this);
        progDao = db.progDao();
        prog = progDao.getAllFromName(nombreEj);
    }
    public void listarProgreso(){
        linearLayout = findViewById(R.id.linLayoutProg);

        for(Progreso x : prog){
            TextView newTextView = new TextView(this);
            newTextView.setText(
                    x.getFecha() + " " + x.getSetNum() + " " +
                    x.getReps() + " @ " + x.getPeso() + "kg" +
                    " RPE: " + x.getRpe() + " " + x.getMods() +"\n"+
                    x.getNotas()+"\n");
            newTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progresoExtra = x;
                    editDelete();
                }
            });
            linearLayout.addView(newTextView);
        }
    }
    public void editDelete(){
        Intent i = new Intent(ProgresoActivity.this, UpdateDeleteActivity.class);
        i.putExtra("ejNombre", progresoExtra.getNombreEj());
        i.putExtra("setNum", progresoExtra.getSetNum());
        i.putExtra("reps", progresoExtra.getReps());
        i.putExtra("peso", progresoExtra.getPeso());
        i.putExtra("rpe", progresoExtra.getRpe());
        i.putExtra("mods", progresoExtra.getMods());
        i.putExtra("notas", progresoExtra.getNotas());
        i.putExtra("fecha", progresoExtra.getFecha());
        startActivity(i);
    }
}