package Pck_Control;

import Pck_Model.ClienteModel;
import Pck_Percistencia.ClientePersistencia;

public class ClienteControl {
    
    ClienteModel obj_Model = new ClienteModel();
    ClientePersistencia obj_Persistencia = new ClientePersistencia();

    public void inserirCliente(String a01_nome, String a01_endereco, String a01_telefone, String a01_cpf, double a01_credito) {
        obj_Model.setA01_nome(a01_nome);
        obj_Model.setA01_endereco(a01_endereco);
        obj_Model.setA01_telefone(a01_telefone);
        obj_Model.setA01_cpf(a01_cpf);
        obj_Model.setA01_credito(a01_credito);

        obj_Persistencia.inserirCliente(obj_Model);
    }
    
    public void atualizarCliente( int a01_id ,String a01_nome, String a01_endereco, String a01_telefone, String a01_cpf, double a01_credito) {
        obj_Model.setA01_id(a01_id);
        obj_Model.setA01_nome(a01_nome);
        obj_Model.setA01_endereco(a01_endereco);
        obj_Model.setA01_telefone(a01_telefone);
        obj_Model.setA01_cpf(a01_cpf);
        obj_Model.setA01_credito(a01_credito);

        obj_Persistencia.atualizarCliente(obj_Model);
    }
    
    public void deletarCliente( int a01_id) {
        obj_Model.setA01_id(a01_id);

        obj_Persistencia.deletarCliente(obj_Model);
    }
}
