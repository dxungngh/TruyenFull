package daniel.com.truyenfull.data.remote;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import daniel.com.truyenfull.data.entity.Book;

public class BooksParser {
    private static final String TAG = BooksParser.class.getSimpleName();

    public List<Book> parseBookList(Document document) {
        List<Book> bookList = new ArrayList<>();
        Element newListElement = document.selectFirst("div.list-truyen");
        Elements rowBookElements = newListElement.select("div.row");
        for (Element rowBookElement : rowBookElements) {
            if (rowBookElement.hasClass("row")) {
                try {
                    Element bookImageElement = rowBookElement.selectFirst("div.col-xs-3").selectFirst("div.lazyimg");
                    String bookImage = bookImageElement.attr("data-desk-image");
                    Element bookNameElement = rowBookElement.selectFirst("h3").selectFirst("a");
                    String bookLink = bookNameElement.attr("href");
                    String bookName = bookNameElement.attr("title");
                    Element authorNameElement = rowBookElement.selectFirst("span.author");
                    String authorName = authorNameElement.text();
                    Element newChapterElement = rowBookElement.selectFirst("div.col-xs-2").selectFirst("a");
                    String newChapter = newChapterElement.text();
                    Book book = new Book();
                    book.setImageUrl(bookImage);
                    book.setLink(bookLink);
                    book.setName(bookName);
                    book.setNewChapter(newChapter);
                    book.setAuthorName(authorName);
                    bookList.add(book);
                    Log.i(TAG, "book: " + book);
                } catch (Exception e) {
                    Log.e(TAG, "parseBookList", e);
                }
            }
        }
        return bookList;
    }
}
