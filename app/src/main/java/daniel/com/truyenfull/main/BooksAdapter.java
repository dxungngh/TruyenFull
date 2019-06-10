package daniel.com.truyenfull.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniel.com.truyenfull.R;
import daniel.com.truyenfull.data.entity.Book;

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = BooksAdapter.class.getSimpleName();
    private List<Book> bookList;

    public BooksAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Book book = this.bookList.get(position);
        BookViewHolder bookViewHolder = (BookViewHolder) holder;
        bookViewHolder.drawBook(book);
    }

    @Override
    public int getItemCount() {
        if (this.bookList == null) {
            return 0;
        }
        return this.bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_book_image)
        protected ImageView bookImage;
        @BindView(R.id.row_book_name)
        protected TextView bookName;

        public BookViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void drawBook(Book book) {
            this.bookName.setText(book.getName());
        }
    }
}
