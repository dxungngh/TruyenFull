package daniel.com.truyenfull.data.remote;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import daniel.com.truyenfull.data.entity.Book;

public class BooksParser {
    public List<Book> parseBookList(Document document) {
        List<Book> bookList = new ArrayList<>();
        Element newListElement = document.selectFirst("div.list-new");
        Elements rowBookElements = newListElement.select("div.row");
        for (Element rowBookElement : rowBookElements) {
            if (rowBookElement.hasClass("row")) {
                Element bookElement = rowBookElement.selectFirst("h3").selectFirst("a");
                String bookLink = bookElement.attr("href");
                String bookName = bookElement.attr("title");
                Book book = new Book();
                book.setLink(bookLink);
                book.setName(bookName);
                bookList.add(book);
            }
        }
        return bookList;
    }
}
