package Pck_Model;

import java.sql.Date;

public class PedidoModel {
    private int a01_id, a02_id; 
    private String a02_data;
    private double a02_valor_total;
    private Date a02_dataDate;
    

    public int getA01_id() {
        return a01_id;
    }

    public Date getA02_dataDate() {
        return a02_dataDate;
    }

    public void setA02_dataDate(Date a02_dataDate) {
        this.a02_dataDate = a02_dataDate;
    }

    public void setA01_id(int a01_id) {
        this.a01_id = a01_id;
    }

    public int getA02_id() {
        return a02_id;
    }

    public void setA02_id(int a02_id) {
        this.a02_id = a02_id;
    }

    public String getA02_data() {
        return a02_data;
    }

    public void setA02_data(String a02_data) {
        this.a02_data = a02_data;
    }

    public double getA02_valor_total() {
        return a02_valor_total;
    }

    public void setA02_valor_total(double a02_valor_total) {
        this.a02_valor_total = a02_valor_total;
    }



}
