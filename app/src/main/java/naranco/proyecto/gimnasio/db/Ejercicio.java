package naranco.proyecto.gimnasio.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ejercicio {
    @PrimaryKey(autoGenerate = true)
    public int ejercicioId;

    public String nombre;

    public String movimiento;

    public String equipo;

    public Ejercicio(String nombre, String movimiento, String equipo) {
        this.nombre = nombre;
        this.movimiento = movimiento;
        this.equipo = equipo;
    }

    public int getEjercicioId() {
        return ejercicioId;
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
