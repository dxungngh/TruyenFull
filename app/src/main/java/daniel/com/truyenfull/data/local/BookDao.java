package daniel.com.truyenfull.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import daniel.com.truyenfull.data.entity.Book;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book")
    List<Book> getBooks();

    @Insert
    void insertBooks(Book... books);

    @Delete
    void delete(Book book);

    @Query("DELETE FROM book")
    void deleteAllBooks();
}
