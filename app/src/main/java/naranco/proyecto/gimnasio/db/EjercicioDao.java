package naranco.proyecto.gimnasio.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EjercicioDao {
    @Query("SELECT * FROM ejercicios")
    List<Ejercicio> getAll();

//    @Query("SELECT * FROM progreso WHERE fecha IN (:dia)")
//    List<ProgresoActivity> loadAllByFecha(String[] dia);
//
//    @Query("SELECT * FROM progreso WHERE fecha LIKE :dia LIMIT 1")
//    ProgresoActivity findByDia(String dia);
//    @Query("SELECT nombre FROM ejercicios WHERE ejercicioId LIKE :ejId")
//    String getNameById(int ejId);

    @Insert
    void insert(Ejercicio ej);

    @Update
    void update(Ejercicio ej);

    @Delete
    void delete(Ejercicio ej);

}
