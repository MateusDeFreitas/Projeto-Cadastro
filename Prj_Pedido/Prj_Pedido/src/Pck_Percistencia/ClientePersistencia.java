package Pck_Percistencia;

import java.sql.CallableStatement;
import Pck_Dao.Conexao_Dao;
import Pck_Model.ClienteModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List; 
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ClientePersistencia {
    
    CallableStatement obj_Call;
    Conexao_Dao obj_Conectar = new Conexao_Dao();

    public void inserirCliente(ClienteModel obj_Model) {
        Connection conn  = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_InsCliente_AI(?,?,?,?,?)");
                obj_Call.setString(1,obj_Model.getA01_nome());
                obj_Call.setString(2,obj_Model.getA01_endereco());
                obj_Call.setString(3,obj_Model.getA01_telefone());
                obj_Call.setString(4,obj_Model.getA01_cpf());
                obj_Call.setDouble(5,obj_Model.getA01_credito());
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
    
    public void atualizarCliente(ClienteModel obj_Model) {
        Connection conn  = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_AtualizarCliente(?,?,?,?,?,?)");
                obj_Call.setInt(1,obj_Model.getA01_id());
                obj_Call.setString(2, obj_Model.getA01_nome());
                obj_Call.setString(3, obj_Model.getA01_endereco());
                obj_Call.setString(4, obj_Model.getA01_telefone());
                obj_Call.setDouble(5, obj_Model.getA01_credito());
                obj_Call.setString(6, obj_Model.getA01_cpf());
                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
    
    public void deletarCliente(ClienteModel obj_Model) {
        Connection conn  = obj_Conectar.getConnection();
        try {
            if (conn != null) {
                obj_Call = conn.prepareCall("CALL Proc_DeletarCliente(?)");
                obj_Call.setInt(1,obj_Model.getA01_id());
                obj_Call.execute();
                JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
    
    public List<ClienteModel> listarClientes() {
        List<ClienteModel> lista = new ArrayList<>();
        Connection conn = obj_Conectar.getConnection();
        try {
            CallableStatement obj_Call = conn.prepareCall("CALL Proc_LerTodosCliente()");
            ResultSet rs = obj_Call.executeQuery();
            while(rs.next()) {
                ClienteModel obj_Cliente = new ClienteModel();
                obj_Cliente.setA01_id(rs.getInt("A01_CODIGO"));
                obj_Cliente.setA01_nome(rs.getString("A01_NOME"));
                obj_Cliente.setA01_endereco(rs.getString("A01_ENDERECO"));
                obj_Cliente.setA01_telefone(rs.getString("A01_TELEFONE"));
                obj_Cliente.setA01_credito(rs.getDouble("A01_CREDITO"));
                obj_Cliente.setA01_cpf(rs.getString("A01_CPF"));
                
                lista.add(obj_Cliente);
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
