package lab.Server;
import java.io.Serializable;

public  class Book implements Serializable {
    String bookname;
    String authtor;
    int year;
    int pages;

    public String getBookname() {
        return bookname;
    }

    public String getAuthtor() {
        return authtor;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setAuthtor(String authtor) {
        this.authtor = authtor;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookname='" + bookname + '\'' +
                ", authtor='" + authtor + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }
}