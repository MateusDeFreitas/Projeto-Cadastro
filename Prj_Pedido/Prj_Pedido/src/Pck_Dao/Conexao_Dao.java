package Pck_Dao;

import com.sun.security.jgss.GSSUtil;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexao_Dao {
    public Connection obj_Connection = null;
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/db_pedido2";
    private final String LOGIN = "root";
    private final String SENHA = "";

    public Conexao_Dao() {

    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            obj_Connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectou");
            return obj_Connection;
        } catch (ClassNotFoundException erro) {
            System.out.println("DRIVER não encontrado" + erro.toString());
            return obj_Connection;
        } catch (SQLException erro) {
            System.out.println("Falha ao conectar" + erro.toString());
            return obj_Connection;
        }
    }
}
