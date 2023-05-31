package naranco.proyecto.gimnasio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import naranco.proyecto.gimnasio.R;

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
       }
/*
        val food = listOf("apple", "pie", "banana", "cookie")
        val linearLayout = findViewById<LinearLayout>(R.id.myList)

        food.forEach {
            TextView(this).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                text = it
                linearLayout.addView(this)
            }
        }
* */

    /*
    private void BuildTable() {

    sqlcon.open();
    Cursor c = sqlcon.readEntry();

    int rows = c.getCount();
    int cols = c.getColumnCount();
    String[] array;
    c.moveToFirst();

    // outer for loop
    for (int i = 0; i < rows; i++) {

        TableRow row = new TableRow(this);
        row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        // inner for loop
        for (int j = 0; j < cols; j++) {

            TextView tv = new TextView(this);
            tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
        //    tv.setBackgroundResource(R.drawable.cell_shape);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(0, 5, 0, 5);
            array = c.getString(1).split(",");
            for (int k = 0; k < array.length; k++) {
                tv.setText(array[k]);

           }

               row.addView(tv);
        }

        c.moveToNext();

        table_layout.addView(row);

    }
    sqlcon.close();
    }
    * */
}