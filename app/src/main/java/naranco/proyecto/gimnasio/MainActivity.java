package naranco.proyecto.gimnasio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import naranco.proyecto.gimnasio.ui.ElegirEjercicio;

public class MainActivity extends AppCompatActivity {

    private Button empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicio();
    }

    public void inicio(){
        empezar = findViewById(R.id.btn_addEjercicio);
    }

    public void empezar(View v){
        Intent intent = new Intent(this, ElegirEjercicio.class);
        startActivity(intent);
    }
}