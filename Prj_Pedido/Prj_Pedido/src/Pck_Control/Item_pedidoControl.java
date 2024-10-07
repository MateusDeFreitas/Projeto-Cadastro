package Pck_Control;

import Pck_Model.Item_pedidoModel;
import Pck_Percistencia.Item_pedidoPersistencia;

public class Item_pedidoControl {
    
    Item_pedidoModel obj_Model = new Item_pedidoModel();
    Item_pedidoPersistencia obj_Persistencia = new Item_pedidoPersistencia();
    
    public void inserirItem_pedido(int a02_id, int a03_id, int a04_quantidade) {
        obj_Model.setA02_id(a02_id);
        obj_Model.setA03_id(a03_id);
        obj_Model.setA04_quantidade(a04_quantidade);

        obj_Persistencia.inserirItem_pedido(obj_Model);
    }
    
    public void atualizarItem_pedido(int a04_id, int a02_id, int a03_id, int a04_quantidade, double a04_valorItem) {
        obj_Model.setA04_id(a04_id);
        obj_Model.setA02_id(a02_id);
        obj_Model.setA03_id(a03_id);
        obj_Model.setA04_quantidade(a04_quantidade);
        obj_Model.setA04_valorItem(a04_valorItem);

        obj_Persistencia.atualizarItem_pedido(obj_Model);
    }
    
    public void deletarItem_pedido(int a04_id) {
        obj_Model.setA04_id(a04_id);

        obj_Persistencia.deletarItem_pedido(obj_Model);
    }
}
