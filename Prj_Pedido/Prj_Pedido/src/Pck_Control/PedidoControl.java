package Pck_Control;
import Pck_Model.PedidoModel;
import Pck_Percistencia.PedidoPersistencia;
import java.util.Date;

public class PedidoControl {
    PedidoModel obj_Model = new PedidoModel();
    PedidoPersistencia obj_Persistencia = new PedidoPersistencia();


    public void inserirPedido(int a01_id) {
        obj_Model.setA01_id(a01_id);

        obj_Persistencia.inserirPedido(obj_Model);
    }
    
    public void atualizarPedido(int a02_id, int a01_id, double a02_valor_total) {
        obj_Model.setA02_id(a02_id);
        obj_Model.setA01_id(a01_id);
        obj_Model.setA02_valor_total(a02_valor_total);

        obj_Persistencia.atualizarPedido(obj_Model);
    }
    
    public void deletarPedido(int a02_id) {
        obj_Model.setA02_id(a02_id);

        obj_Persistencia.deletarPedido(obj_Model);
    }
}
