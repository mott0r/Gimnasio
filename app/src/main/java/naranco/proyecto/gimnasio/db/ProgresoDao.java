package naranco.proyecto.gimnasio.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProgresoDao {
    @Query("SELECT * FROM progreso")
    List<Progreso> getAll();

//    @Query("SELECT * FROM progreso WHERE fecha IN (:dia)")
//    List<ProgresoActivity> loadAllByFecha(String[] dia);
//
//    @Query("SELECT * FROM progreso WHERE fecha LIKE :dia LIMIT 1")
//    ProgresoActivity findByDia(String dia);

    @Insert
    void insert(Progreso prog);

    @Update
    void update(Progreso prog);

    @Delete
    void delete(Progreso prog);
}
