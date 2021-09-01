package bussines;

import data.BookJDBC;
import data.IBookDAO;
import domain.Book;

import java.sql.SQLException;
import java.util.List;

public class BookController {

    public static List<Book> imprimirLista() throws SQLException {
        IBookDAO bookDAOJDBC = new BookJDBC();
        List<Book> books = bookDAOJDBC.select();
        for (Book book: books) {
            System.out.println("book = " + book);
        }
        books= bookDAOJDBC.select();
        return books;
    }

    public static void insertarRegistro(Book book) throws SQLException {
        IBookDAO bookDAOJDBC = new BookJDBC();
        bookDAOJDBC.insert(book);
    }

    public static void modificarRefistro(Book book) throws SQLException {
        IBookDAO bookDAOJDBC = new BookJDBC();
        bookDAOJDBC.update(book);
    }

    public static void eliminarRegistro(Book book) throws SQLException {
        IBookDAO bookDAOJDBC = new BookJDBC();
        bookDAOJDBC.delete(book);
    }

}
