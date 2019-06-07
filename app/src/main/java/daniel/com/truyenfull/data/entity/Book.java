package daniel.com.truyenfull.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Book {
    private static final String TAG = Book.class.getSimpleName();

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "author_name")
    public String authorName;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "description")
    public String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", authorName='" + authorName + '\'' +
            ", status='" + status + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
