package Pck_Control;

import Pck_Model.ProdutoModel;
import Pck_Percistencia.ProdutoPersistencia;

public class ProdutoControl {
    
    ProdutoModel obj_Model = new ProdutoModel();
    ProdutoPersistencia obj_Persistencia = new ProdutoPersistencia();
    
    public void inserirProduto(int a03_estoque, String a03_descricao, double a03_valorUnitario) {
        obj_Model.setA03_estoque(a03_estoque);
        obj_Model.setA03_descricao(a03_descricao);
        obj_Model.setA03_valorUnitario(a03_valorUnitario);

        obj_Persistencia.inserirProduto(obj_Model);
    }
    
    public void atualizarProduto(int a03_id ,int a03_estoque, String a03_descricao, double a03_valorUnitario) {
        obj_Model.setA03_id(a03_id);
        obj_Model.setA03_estoque(a03_estoque);
        obj_Model.setA03_descricao(a03_descricao);
        obj_Model.setA03_valorUnitario(a03_valorUnitario);

        obj_Persistencia.atualizarProduto(obj_Model);
    }
    
    public void deletarProduto(int a03_id) {
        obj_Model.setA03_id(a03_id);

        obj_Persistencia.deletarProduto(obj_Model);
    }
    
    public void criarProduto(int a03_id ,int a03_estoque, String a03_descricao, double a03_valorUnitario) {
        obj_Model.setA03_id(a03_id);
        obj_Model.setA03_estoque(a03_estoque);
        obj_Model.setA03_descricao(a03_descricao);
        obj_Model.setA03_valorUnitario(a03_valorUnitario);
    }
}
