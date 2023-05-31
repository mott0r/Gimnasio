package naranco.proyecto.gimnasio.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"fecha", "ejId", "setNum"})
public class Progreso {

    public String fecha;
    public int ejId;
    public int setNum;
    public int reps;
    public float rpe;
    public float peso;
    public String notas;
    public String mods;

    public Progreso(String fecha, int ejId, int setNum, int reps, float rpe, float peso, String notas, String mods) {
        this.fecha = fecha;
        this.ejId = ejId;
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

    public int getEjId() {
        return ejId;
    }

    public void setEjId(int ejId) {
        this.ejId = ejId;
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

    public float getRpe() {
        return rpe;
    }

    public void setRpe(float rpe) {
        this.rpe = rpe;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
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
