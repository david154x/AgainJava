package test;

import bussines.BookController;
import domain.Book;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException {
        Book bookOne = new Book("9786071567890", "Luis Joyanes Aguilar", "Programacion en Java 6: Algoritmos, programacion orientada a objetos e interfaces graficas de usuario", "Programming", (short) 2011);
        Book bookTwo = new Book("9786071567890");
        BookController.insertarRegistro(bookOne);
        System.out.println("");
        //BookController.modificarRefistro(bookOne);
        System.out.println("List before of removed the register");
        System.out.println("");

        BookController.imprimirLista();
        System.out.println("");
        System.out.println("List after of removed the register");
        System.out.println("");
        BookController.eliminarRegistro(bookTwo);
        System.out.println("");
        BookController.imprimirLista();
    }
}
