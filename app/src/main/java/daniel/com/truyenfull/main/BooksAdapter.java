package daniel.com.truyenfull.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniel.com.truyenfull.R;
import daniel.com.truyenfull.data.entity.Book;

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = BooksAdapter.class.getSimpleName();

    private Context context;
    private List<Book> bookList;

    public BooksAdapter(Context context, List<Book> bookList) {
        this.context = context;
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
        protected SimpleDraweeView bookImage;
        @BindView(R.id.row_book_name)
        protected TextView bookName;
        @BindView(R.id.row_book_new_chapter)
        protected TextView newChapterName;

        public BookViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void drawBook(Book book) {
            this.bookName.setText(book.getName());
            this.newChapterName.setText(book.getNewChapter());
            this.drawBookImage(book.getImageUrl());
        }

        private void drawBookImage(String imageUrl) {
            if (!TextUtils.isEmpty(imageUrl)) {
                this.bookImage.setImageURI(imageUrl);
            } else {
                this.bookImage.setImageResource(R.mipmap.ic_book);
            }
        }
    }
}
