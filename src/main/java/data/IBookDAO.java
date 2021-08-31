package data;

import domain.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookDAO {

    public List<Book> select() throws SQLException;

    public int insert(Book book) throws SQLException;

    public int update(Book book)throws SQLException;

    public int delete(Book book)throws SQLException;
}
