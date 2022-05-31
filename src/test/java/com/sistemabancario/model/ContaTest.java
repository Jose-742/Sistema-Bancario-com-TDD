package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {
    private final Conta conta = new Conta();
    
    @Test
    void setNumeroR1a(){              
        assertThrows(IllegalArgumentException.class, () -> conta.setNumero("1234"));
    }
    
    @Test
    void setNumeroR1b(){
        final String invalido = "abcde-f";
        assertThrows(IllegalArgumentException.class, () -> conta.setNumero(invalido ));
        
        final String obtido = conta.getNumero();
        assertNotEquals(invalido, obtido);       
    }
    
    @Test
    void setNumeroR1c(){   
        final String invalido = "12345-f";
        assertThrows(IllegalArgumentException.class, () -> conta.setNumero(invalido ));
    }
    
    @Test
    void testInstanciaPadraoPoupancaR2() {
    	assertFalse(conta.isPoupanca());
    }
    
    @Test
    void setLimiteContaColumR3(){
        final double limite = 1000;
        conta.setEspecial(false);
        assertThrows(IllegalStateException.class, () -> conta.setLimite(limite));
    }
    
    @Test
    void testSetLimiteContaEspecialR3a() {
    	conta.setEspecial(true);
    	final double esperado = 1000;
    	conta.setLimite(esperado);
    	final double obtido = conta.getLimite();
    	assertEquals(esperado, obtido);
    }
    
    @Test
    void testHistoricoNotNullR4() {
    	assertNotNull(conta.getMovimentacoes());
    }
    
    @Test
    void getSaldoTotalR6(){
        conta.setEspecial(true);
    	final double limite= 1000, saldo = 200, saldoTotal = 1200;
    	conta.setLimite(limite);
        conta.setSaldo(saldo);
    	assertEquals( saldoTotal,conta.getSaldoTotal());
    }
    
    @Test
    void getSaldoTotalR6a(){
        conta.setEspecial(true);
    	final double limite= 0.2, saldo = 0.1, saldoTotalEsperado = 0.3;
    	conta.setLimite(limite);
        conta.setSaldo(saldo);
        final double obtido = conta.getSaldoTotal();
    	assertEquals( saldoTotalEsperado,obtido, 0.001);
    }
      /****************************/
    @Test
    void testDepositoDinheiro() {
    	final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
    	conta.setEspecial(true);
    	conta.setLimite(limite);
    	conta.depositoDinheiro(deposito);
    	
    	final double obtido = conta.getSaldoTotal();
    	assertEquals(esperado, obtido, 0.001);
    }
    
    @Test
    void testTipoDefinidoCredito() {//verificar se o tipo definido é como Credito
    	final double deposito = 500;
    	conta.setEspecial(true);
    	conta.depositoDinheiro(deposito);   	
    	char tipoEsperado = 'C';
    	char tipoObtido = conta.getMovimentacoes().get(0).getTipo();	
    	assertEquals(tipoEsperado, tipoObtido);
    }
    
    @Test
    void testMovimentacaoFoiConfirmada() {//A movimentacao foi confirmada
    	final double deposito = 500;
    	conta.setEspecial(true);
    	conta.depositoDinheiro(deposito);
    	boolean esperado = true;
    	boolean obtido = conta.getMovimentacoes().get(0).isConfirmada();
    	assertEquals(esperado, obtido);
    }
    
    @Test
    void testValorFoiAtribuidoMovimentacao() { // O valor foi atribuido a movimentação
    	final double deposito = 500;
    	conta.setEspecial(true);
    	conta.depositoDinheiro(deposito);
    	double esperado = deposito;
    	double obtido = conta.getMovimentacoes().get(0).getValor();
    	assertEquals(esperado, obtido);
    }

    @Test
    void testMovimentacaoFoiAdicionadaNoFinal() { // verificar se a movimentação foi adicionada no final da lista de movimentações
    	final double deposito = 500;
    	conta.setEspecial(true);
    	conta.depositoDinheiro(deposito);
    	int esperado = 1;
    	int obtido = conta.getMovimentacoes().size();
    	assertEquals(esperado, obtido);
    }
}
