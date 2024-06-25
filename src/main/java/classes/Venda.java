/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author pedro
 */
public class Venda {
    private long codigo;
    private Timestamp horario;
    private double valorTotal;
    private long funcionario;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public long getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(long funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
