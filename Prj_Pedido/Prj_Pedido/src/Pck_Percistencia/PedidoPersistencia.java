package Pck_Percistencia;

import java.sql.CallableStatement;
import Pck_Dao.Conexao_Dao;
import Pck_Model.PedidoModel;
import java.util.List; 
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class PedidoPersistencia {

    CallableStatement obj_Call;
    Conexao_Dao obj_Conectar = new Conexao_Dao();

    public void inserirPedido(PedidoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_InsPedido_AI(?)");
                obj_Call.setInt(1, obj_Model.getA01_id());
                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
    }

    public void atualizarPedido(PedidoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_AtualizarPedido_SD(?,?,?)");
                obj_Call.setInt(1, obj_Model.getA02_id());
                obj_Call.setDouble(2, obj_Model.getA02_valor_total());
                obj_Call.setInt(3, obj_Model.getA01_id());
                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
    }

    public void deletarPedido(PedidoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_DeletarPedido(?)");
                obj_Call.setInt(1, obj_Model.getA02_id());
                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
    }

    public List<PedidoModel> listarPedidos() {
        List<PedidoModel> lista = new ArrayList<>();
        Connection conn = obj_Conectar.getConnection();

        try {
            CallableStatement obj_Call = conn.prepareCall("CALL Proc_LerTodosPedido()");
            ResultSet rs = obj_Call.executeQuery();
            while (rs.next()) {
                PedidoModel obj_Pedido = new PedidoModel();
                obj_Pedido.setA02_id(rs.getInt("A02_CODIGO"));
                obj_Pedido.setA01_id(rs.getInt("A01_codigo"));
                obj_Pedido.setA02_valor_total(rs.getDouble("A02_valor_total"));
                obj_Pedido.setA02_dataDate(rs.getDate("A02_data"));

                lista.add(obj_Pedido);
            }
            rs.close(); // Fechar ResultSet após uso
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
