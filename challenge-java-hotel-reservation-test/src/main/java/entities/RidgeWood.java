package entities;

import enums.DaysOfWeek;
import enums.TipoDeCliente;

import java.util.List;

public class RidgeWood extends Hotel{

    public RidgeWood(){
        super.setTaxaDiaDeSemanaClienteRegular(220.0);
        super.setGetTaxaDiaDeSemanaClienteRewards(100.0);
        super.setTaxaFinaisDeSemanaClienteRegular(150.0);
        super.setGetTaxaFinaisDeSemanaClienteRewards(40.0);
        super.setClassificacao(5);
    }
}
