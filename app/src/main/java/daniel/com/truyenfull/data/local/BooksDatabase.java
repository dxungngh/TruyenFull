package daniel.com.truyenfull.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import daniel.com.truyenfull.data.entity.Book;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class BooksDatabase extends RoomDatabase {
    private static BooksDatabase INSTANCE;

    public abstract BookDao bookDao();

    private static final Object sLock = new Object();

    public static BooksDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BooksDatabase.class,
                    "Books.db"
                ).build();
            }
            return INSTANCE;
        }
    }
}
