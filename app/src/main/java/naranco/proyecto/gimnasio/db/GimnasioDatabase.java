package naranco.proyecto.gimnasio.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ejercicio.class, Progreso.class}, version = 1)
public abstract class GimnasioDatabase extends RoomDatabase {
    public abstract EjercicioDao ejDao();
    public abstract ProgresoDao progDao();
    public static GimnasioDatabase db;

    public static GimnasioDatabase openDB(Context context){
        // allowMainThreadQueries() not recommended
        db = Room.databaseBuilder(context,
                GimnasioDatabase.class, "GimnasioDb").allowMainThreadQueries().build();
        return db;
    }

}
