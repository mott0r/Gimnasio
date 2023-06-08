package naranco.proyecto.gimnasio.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ejercicios")
public class Ejercicio {
    @PrimaryKey
    @NonNull
    public String nombre;
    public String movimiento;

    public String equipo;

    public Ejercicio(String nombre, String movimiento, String equipo) {
        this.nombre = nombre;
        this.movimiento = movimiento;
        this.equipo = equipo;
    }
//    Ignora este constructor usado para pruebas
//    se mantiene para futuro
    @Ignore
    public Ejercicio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}
