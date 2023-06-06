package naranco.proyecto.gimnasio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import naranco.proyecto.gimnasio.ui.ElegirEjercicioActivity;

public class MainActivity extends AppCompatActivity {
    private Button empezar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        empezar = findViewById(R.id.empezar);
    }

//  Pulsando el boton empezar, lanza la actividad para elegir el ejercicio a realizar
    public void empezar(View v){
        Intent intent = new Intent(this, ElegirEjercicioActivity.class);
        startActivity(intent);
    }
}