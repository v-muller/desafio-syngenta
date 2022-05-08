package entities;

import enums.DaysOfWeek;
import enums.TipoDeCliente;

import java.util.List;

public abstract class Hotel {

    private Double taxaDiaDeSemanaClienteRegular;
    private Double getTaxaDiaDeSemanaClienteRewards;
    private Double taxaFinaisDeSemanaClienteRegular;
    private Double getTaxaFinaisDeSemanaClienteRewards;
    private Integer classificacao;

    public Double getTaxaDiaDeSemanaClienteRegular() {
        return taxaDiaDeSemanaClienteRegular;
    }

    public void setTaxaDiaDeSemanaClienteRegular(Double taxaDiaDeSemanaClienteRegular) {
        this.taxaDiaDeSemanaClienteRegular = taxaDiaDeSemanaClienteRegular;
    }

    public Double getGetTaxaDiaDeSemanaClienteRewards() {
        return getTaxaDiaDeSemanaClienteRewards;
    }

    public void setGetTaxaDiaDeSemanaClienteRewards(Double getTaxaDiaDeSemanaClienteRewards) {
        this.getTaxaDiaDeSemanaClienteRewards = getTaxaDiaDeSemanaClienteRewards;
    }

    public Double getTaxaFinaisDeSemanaClienteRegular() {
        return taxaFinaisDeSemanaClienteRegular;
    }

    public void setTaxaFinaisDeSemanaClienteRegular(Double taxaFinaisDeSemanaClienteRegular) {
        this.taxaFinaisDeSemanaClienteRegular = taxaFinaisDeSemanaClienteRegular;
    }

    public Double getGetTaxaFinaisDeSemanaClienteRewards() {
        return getTaxaFinaisDeSemanaClienteRewards;
    }

    public void setGetTaxaFinaisDeSemanaClienteRewards(Double getTaxaFinaisDeSemanaClienteRewards) {
        this.getTaxaFinaisDeSemanaClienteRewards = getTaxaFinaisDeSemanaClienteRewards;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    private boolean verificaFinalDeSemana(DaysOfWeek d){
        if(d.equals(DaysOfWeek.FRIDAY) || d.equals(DaysOfWeek.SATURDAY) || d.equals(DaysOfWeek.SUNDAY)){
            return true;
        }
        return false;
    }

    public Double calcularTotal(TipoDeCliente tipoDeCliente, List<DaysOfWeek> days){
        if(days.isEmpty()){
            throw new NullPointerException();
        }
        double total = 0.0;
        for(DaysOfWeek day : days){
            if(!verificaFinalDeSemana(day) && tipoDeCliente.equals(TipoDeCliente.REGULAR)){
                total += getTaxaDiaDeSemanaClienteRegular();
            }
            if(!verificaFinalDeSemana(day) && tipoDeCliente.equals(TipoDeCliente.REWARDS)){
                total += getGetTaxaDiaDeSemanaClienteRewards();
            }
            if(verificaFinalDeSemana(day) && tipoDeCliente.equals(TipoDeCliente.REGULAR)){
                total += getTaxaFinaisDeSemanaClienteRegular();
            }
            if(verificaFinalDeSemana(day) && tipoDeCliente.equals(TipoDeCliente.REWARDS)) {
                total += getGetTaxaFinaisDeSemanaClienteRewards();
            }
        }
        return total;
    }
}
