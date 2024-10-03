package Pck_Percistencia;

import java.sql.CallableStatement;
import Pck_Dao.Conexao_Dao;
import Pck_Model.Item_pedidoModel;
import java.sql.Connection;
import java.sql.SQLException;

public class Item_pedidoPersistencia {
    
    CallableStatement obj_Call;
    Conexao_Dao obj_Conectar = new Conexao_Dao();
    
    public void inserirItem_pedido(Item_pedidoModel obj_Model) {
        Connection conn = null;
        try {
            conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_InsItem_AI(?,?,?)");
                obj_Call.setInt(1, obj_Model.getA02_id());
                obj_Call.setInt(2, obj_Model.getA03_id());
                obj_Call.setInt(3, obj_Model.getA04_quantidade());
                obj_Call.execute();
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException erro) {
            erro.printStackTrace(); // Exibir erro
        } finally {
            try {
                if (obj_Call != null) obj_Call.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void atualizarItem_pedido(Item_pedidoModel obj_Model) {
        Connection conn = null;
        try {
            conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("Call Proc_AtualizarItem(?,?,?,?,?)");
                obj_Call.setInt(1, obj_Model.getA04_id());
                obj_Call.setInt(2, obj_Model.getA02_id());
                obj_Call.setInt(3, obj_Model.getA03_id());
                obj_Call.setInt(4, obj_Model.getA04_quantidade());
                obj_Call.setDouble(5, obj_Model.getA04_valorItem());
                obj_Call.execute();
            }
            else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException erro) {
            erro.printStackTrace(); // Exibir erro
        } finally {
            try {
                if (obj_Call != null) obj_Call.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deletarItem_pedido(Item_pedidoModel obj_Model) {
        Connection conn = null;
        try {
            conn = obj_Conectar.getConnection();
            if (conn != null) {
                obj_Call = conn.prepareCall("Call Proc_DeletarItem(?)");
                obj_Call.setInt(1, obj_Model.getA04_id());
                obj_Call.execute();
            }
            else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException erro) {
            erro.printStackTrace(); // Exibir erro
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
