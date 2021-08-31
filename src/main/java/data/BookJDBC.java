package data;

import domain.Book;

import java.sql.*;
import java.util.*;

import static data.ConexionMariaDB.close;

public class BookJDBC implements IBookDAO {

    private static final String SQL_SELECT = "SELECT isbn, author, title, category, year FROM classics";
    private static final String SQL_INSERT = "INSERT INTO classics VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE classics SET author= ?, title= ?, category= ?, year= ? WHERE isbn= ?";
    private static final String SQL_DELETE = "DELETE FROM classics WHERE isbn= ?";

    @Override
    public List<Book> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();
        Book book;
        try{
            conn = ConexionMariaDB.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                String isbn = rs.getString("isbn");
                String author = rs.getString("author");
                String title = rs.getString("title");
                String category = rs.getString("category");
                Short year = rs.getShort("year");
                book = new Book(isbn, author, title, category, year);
                books.add(book);
            }

        }catch(SQLException Ex){
            Ex.printStackTrace(System.out);
        }finally{
            try{
                close(rs);
                close(stmt);
                close(conn);
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
        }
        return books;
    }

    @Override
    public int insert(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int counter = 0;
        try{
            conn = ConexionMariaDB.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, book.getId());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getTitle());
            stmt.setString(4, book.getCategory());
            stmt.setShort(5, book.getYear());
            counter = stmt.executeUpdate();
            System.out.println("Rows affected: "+counter);
        }catch(SQLException Ex){
            Ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
        }
        return counter;
    }

    @Override
    public int update(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int counter = 0;
        try{
            conn = ConexionMariaDB.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, book.getId());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getTitle());
            stmt.setString(4, book.getCategory());
            stmt.setShort(5, book.getYear());
            counter = stmt.executeUpdate();
            System.out.println("Rows affected: "+counter);
        }catch(SQLException Ex){
            Ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
        }
        return counter;
    }

    @Override
    public int delete(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int counter = 0;
        try{
            conn = ConexionMariaDB.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, book.getId());
            counter = stmt.executeUpdate();
            System.out.println("Rows affected: "+counter);
        }catch(SQLException Ex){
            Ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
        }
        return counter;
    }
}
