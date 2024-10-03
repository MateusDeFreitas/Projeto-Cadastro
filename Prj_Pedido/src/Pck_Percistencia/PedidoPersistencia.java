package Pck_Percistencia;

import java.sql.CallableStatement;
import Pck_Dao.Conexao_Dao;
import Pck_Model.PedidoModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidoPersistencia {

    CallableStatement obj_Call;
    Conexao_Dao obj_Conectar = new Conexao_Dao();

    public void inserirPedido(PedidoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_InsPedido_AI(?)");
                obj_Call.setInt(1,obj_Model.getA01_id());
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
    
    public void atualizarPedido(PedidoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_AtualizarPedido_SD(?,?,?)");
                obj_Call.setInt(1,obj_Model.getA02_id());
                obj_Call.setDouble(2, obj_Model.getA02_valor_total());
                obj_Call.setInt(3,obj_Model.getA01_id());
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
    
    public void deletarPedido(PedidoModel obj_Model) {
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_DeletarPedido(?)");
                obj_Call.setInt(1,obj_Model.getA02_id());
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
    
    public void lerPedido(int id) {
        ResultSet rs = null;
        Connection conn = obj_Conectar.getConnection();
        try {
            if (conn != null) {
            obj_Call = conn.prepareCall("Call Proc_LerPedido(?)");
            obj_Call.setInt(1, id);
            rs = obj_Call.executeQuery();
            
            while(rs.next()) {
                int codigo_pedido = rs.getInt("A02_codigo");
                Date data_pedido = rs.getDate("A02_data");
                double valor_total = rs.getDouble("A02_valor_total");
                int codido_cliente = rs.getInt("A01_codigo");
            }
        }
        } catch (SQLException erro){
            System.out.println("Falha ao conectar ao banco de dados.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (obj_Call != null) obj_Call.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
