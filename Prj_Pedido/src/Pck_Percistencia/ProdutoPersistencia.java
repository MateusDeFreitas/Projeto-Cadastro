package Pck_Percistencia;

import java.sql.CallableStatement;
import Pck_Dao.Conexao_Dao;
import Pck_Model.ProdutoModel;
import java.sql.Connection;
import java.sql.SQLException;

public class ProdutoPersistencia {
    CallableStatement obj_Call;
    Conexao_Dao obj_Conectar = new Conexao_Dao();

    public void inserirProduto(ProdutoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_InsProduto_AI(?,?,?)");
                obj_Call.setString(1,obj_Model.getA03_descricao());
                obj_Call.setDouble(2,obj_Model.getA03_valorUnitario());
                obj_Call.setInt(3,obj_Model.getA03_estoque());
                obj_Call.execute();
            }
        } catch (SQLException erro) {
            System.out.println("Falha ao conectar ao banco de dados.");
        } finally {
            try {
                if (obj_Call != null) obj_Call.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void atualizarProduto(ProdutoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_AtualizarProduto(?,?,?,?)");
                obj_Call.setInt(1,obj_Model.getA03_id());
                obj_Call.setString(2,obj_Model.getA03_descricao());
                obj_Call.setDouble(3,obj_Model.getA03_valorUnitario());
                obj_Call.setInt(4,obj_Model.getA03_estoque());
                obj_Call.execute();
            }
        } catch (SQLException erro) {
            System.out.println("Falha ao conectar ao banco de dados.");
        } finally {
            try {
                if (obj_Call != null) obj_Call.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deletarProduto(ProdutoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_DeletarProduto(?)");
                obj_Call.setInt(1,obj_Model.getA03_id());
                obj_Call.execute();
            }
        } catch (SQLException erro) {
            System.out.println("Falha ao conectar ao banco de dados.");
        } finally {
            try {
                if (obj_Call != null) obj_Call.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
