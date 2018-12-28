package lab.Server;
import lab.Server.ORM.annotations.AttribType;
import lab.Server.ORM.annotations.DBTable;
import lab.Server.ORM.annotations.IgnoreField;
import lab.Server.ORM.annotations.SerialID;

import java.io.Serializable;
import java.time.LocalDateTime;
@DBTable(name="Книги")
@SerialID
public  class Book implements Serializable {
    @AttribType(typeName ="text")
    String bookname;
    @AttribType(typeName ="text")

    String authtor;
    @AttribType(typeName ="integer")

    int year;
    @AttribType(typeName ="integer")

    int pages;
    @AttribType(typeName ="Date")
    @IgnoreField
    LocalDateTime date ;
    public String getBookname() {
        return bookname;
    }

    public  LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
                ", date=" + date +
                '}';
    }
//    @Override
//    public String toString() {
//        return "Book{" +
//                "bookname='" + bookname + '\'' +
//                ", authtor='" + authtor + '\'' +
//                ", year=" + year +
//                ", pages=" + pages +
//                " time of creating= "+date+
//                '}';
//    }
}