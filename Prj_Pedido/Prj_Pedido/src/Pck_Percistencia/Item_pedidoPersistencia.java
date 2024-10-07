package Pck_Percistencia;

import java.sql.CallableStatement;
import Pck_Dao.Conexao_Dao;
import Pck_Model.Item_pedidoModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

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
                JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
    
    public List<Item_pedidoModel> listarItem_pedidos() {
        List<Item_pedidoModel> lista = new ArrayList<>();
        Connection conn = obj_Conectar.getConnection();
        try {
            CallableStatement obj_Call = conn.prepareCall("CALL Proc_LerTodosItem()");
            ResultSet rs = obj_Call.executeQuery();
            while(rs.next()) {
                Item_pedidoModel obj_Item_pedido = new Item_pedidoModel();
                obj_Item_pedido.setA04_id(rs.getInt("A04_codigo"));
                obj_Item_pedido.setA02_id(rs.getInt("A02_codigo"));
                obj_Item_pedido.setA03_id(rs.getInt("A03_codigo"));
                obj_Item_pedido.setA04_quantidade(rs.getInt("A04_quantidade"));
                obj_Item_pedido.setA04_valorItem(rs.getDouble("A04_valor_item"));
                
                lista.add(obj_Item_pedido);
            } 
        } catch (SQLException erro) {
            System.out.println("Falha ao conectar ao banco de dados.");
            erro.printStackTrace();
        } finally {
            try {
                if (obj_Call != null) obj_Call.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
