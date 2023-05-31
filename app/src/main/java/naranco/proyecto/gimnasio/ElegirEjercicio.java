package naranco.proyecto.gimnasio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ElegirEjercicio extends AppCompatActivity {
    Button addEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_ejercicio);
        addEx = findViewById(R.id.btn_addEjercicio);
    }

    public void addEjercicio(View view){
        Intent intent = new Intent(this, AnadirEjercicio.class);
        startActivity(intent);
    }
}