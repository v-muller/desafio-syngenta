import entities.BridgeWood;
import entities.Lakewood;
import entities.RidgeWood;
import enums.DaysOfWeek;
import enums.TipoDeCliente;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HotelReservation {
    private Lakewood lakewood = new Lakewood();
    private BridgeWood bridgeWood = new BridgeWood();
    private RidgeWood ridgeWood = new RidgeWood();


    public String getCheapestHotel (String input) {
        String[] fields = input.split("[:|,]");
        TipoDeCliente cliente = verificarTipoDeCliente(fields[0]);
        List<String> prefixos = retirarPrimeiroElemento(fields);
        List<DaysOfWeek> days = trasnformaStringEmDaysOfWeek(capturarPrefixosDaString(prefixos));

        double menorLake = lakewood.calcularTotal(cliente, days);
        double menorBridge = bridgeWood.calcularTotal(cliente, days);
        double menorRidge = ridgeWood.calcularTotal(cliente, days);
        if(menorLake < menorBridge && menorLake < menorRidge){
            return "Lakewood";
        }
        if(menorBridge < menorLake && menorBridge < menorRidge){
            return "Bridgewood";
        }
        if(menorRidge < menorLake && menorRidge < menorBridge){
            return "Ridgewood";
        }

        return "Não foi possivél realizar o cálculo.";
    }

    private List<String> retirarPrimeiroElemento(String[] listaInteira){
        if(listaInteira == null){
            throw new NullPointerException();
        }
        List<String> listaSemOprimeiro = new ArrayList<>();
        for(int i =1; i < listaInteira.length; i++){
            listaSemOprimeiro.add(listaInteira[i]);
        }
        return listaSemOprimeiro;
    }

    public TipoDeCliente verificarTipoDeCliente(String cliente){
        if(cliente.isEmpty()){
            throw new NullPointerException();
        }
        TipoDeCliente tipo;
        if(TipoDeCliente.REGULAR.name().equals(cliente.toUpperCase())){
            tipo = TipoDeCliente.REGULAR;
        }
        else{
            tipo = TipoDeCliente.REWARDS;
        }
        return tipo;
    }

    private List<String> capturarPrefixosDaString(List<String> frasesInteiras){
        if(frasesInteiras.isEmpty()){
            throw new NullPointerException();
        }
        List<String> prefixos = new ArrayList<>();
        for(String s : frasesInteiras){
            if(!s.contains("(") || !s.contains(")")){
                throw new StringIndexOutOfBoundsException();
            }
            int init = s.indexOf("(") + 1;
            int end = s.indexOf(")");
            prefixos.add(s.substring(init, end));
        }
        return prefixos;
    }
    public List<DaysOfWeek> trasnformaStringEmDaysOfWeek(List<String> prefixos){
        if(prefixos.isEmpty()){
            throw new NullPointerException();
        }
        List<DaysOfWeek> days = new ArrayList<>();
        for(String s : prefixos){
            if(DaysOfWeek.SUNDAY.name().startsWith(s.toUpperCase())){
                days.add(DaysOfWeek.SUNDAY);
            }
            if(DaysOfWeek.MONDAY.name().startsWith(s.toUpperCase())){
                days.add(DaysOfWeek.MONDAY);
            }
            if(DaysOfWeek.TUESDAY.name().startsWith(s.toUpperCase())){
                days.add(DaysOfWeek.TUESDAY);
            }
            if(DaysOfWeek.WEDNESDAY.name().startsWith(s.toUpperCase())){
                days.add(DaysOfWeek.WEDNESDAY);
            }
            if(DaysOfWeek.THURSDAY.name().startsWith(s.toUpperCase())){
                days.add(DaysOfWeek.THURSDAY);
            }
            if(DaysOfWeek.FRIDAY.name().startsWith(s.toUpperCase())){
                days.add(DaysOfWeek.FRIDAY);
            }
            if(DaysOfWeek.SATURDAY.name().startsWith(s.toUpperCase())){
                days.add(DaysOfWeek.SATURDAY);
            }
        }
        if(days.isEmpty()){
            throw new StringIndexOutOfBoundsException();
        }
        return days;
    }
    public void executa() {
        int in = 0;
        Scanner sc = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        char opcao = ' ';
        System.out.println("Sistema de busca do hotel mais barato. ");
        while (opcao != '0') {
            try {
                System.out.println("Opcoes: ");
                System.out.println("   [0] Sair.");
                System.out.println("   [1] Inserir dados.");
                System.out.print("Digite a opcao desejada: ");
                opcao = entrada.next().charAt(0);
                switch (opcao) {
                    case '1':
                        System.out.println("Digite os dados para fazer o cálculo: ");
                        String dado = sc.nextLine();
                        System.out.println(getCheapestHotel(dado));
                        break;
                    case '0':
                        break;
                    default:
                        System.out.println("Opcao invalida! Redigite.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Insira apenas números inteiros. ");
            } catch (NullPointerException e) {
                System.out.println("Insira dados válidos!");
            } catch (StringIndexOutOfBoundsException e){
                System.out.println( "Insira dados no formato correto!");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Até breve.");
    }
}
