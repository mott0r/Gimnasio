package naranco.proyecto.gimnasio.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//Relaciona la base de datos con las clases (tablas)
@Database(entities = {Ejercicio.class, Progreso.class}, version = 4)
public abstract class GimnasioDatabase extends RoomDatabase {
    public abstract EjercicioDao ejDao();
    public abstract ProgresoDao progDao();
    public static GimnasioDatabase db;
//  Construye la base de datos
    public static GimnasioDatabase openDB(Context context){
        // allowMainThreadQueries() not recommended
        db = Room.databaseBuilder(context,
                GimnasioDatabase.class, "GimnasioDb").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        return db;
    }

}
