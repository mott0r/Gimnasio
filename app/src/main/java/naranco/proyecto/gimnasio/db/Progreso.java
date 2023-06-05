package naranco.proyecto.gimnasio.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"fecha", "nombreEj", "setNum"}, tableName = "progreso")
public class Progreso {
    @NonNull
    public String fecha;
    @NonNull
    public String nombreEj;
    @NonNull
    public int setNum;
    public int reps;
    public double rpe;
    public double peso;
    public String notas;
    public String mods;

    public Progreso(String fecha, String nombreEj, int setNum, int reps, double rpe, double peso, String notas, String mods) {
        this.fecha = fecha;
        this.nombreEj = nombreEj;
        this.setNum = setNum;
        this.reps = reps;
        this.rpe = rpe;
        this.peso = peso;
        this.notas = notas;
        this.mods = mods;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreEj() {
        return nombreEj;
    }

    public void setNombreEj(String nombreEj) {
        this.nombreEj = nombreEj;
    }

    public int getSetNum() {
        return setNum;
    }

    public void setSetNum(int setNum) {
        this.setNum = setNum;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getRpe() {
        return rpe;
    }

    public void setRpe(double rpe) {
        this.rpe = rpe;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getMods() {
        return mods;
    }

    public void setMods(String mods) {
        this.mods = mods;
    }
}
