package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {
    
    @Test
    void setNumeroR1a(){
        final Conta conta = new Conta();        
        assertThrows(IllegalArgumentException.class, () -> conta.setNumero("1234"));
    }
    
    @Test
    void setNumeroR1b(){
        final Conta conta = new Conta();
        final String invalido = "abcde-f";
        assertThrows(IllegalArgumentException.class, () -> conta.setNumero(invalido ));
        
        final String obtido = conta.getNumero();
        assertNotEquals(invalido, obtido);       
    }
    
    @Test
    void testInstanciaPadraoPoupancaR2() {
    	final Conta instance = new Conta();
    	assertFalse(instance.isPoupanca());
    }
    
    @Test
    void testSetLimiteContaEspecialR3a() {
    	final Conta instance = new Conta();
    	instance.setEspecial(true);
    	final double esperado = 1000;
    	instance.setLimite(esperado);
    	final double obtido = instance.getLimite();
    	assertEquals(esperado, obtido);
    }
    
    @Test
    void testSetLimiteContNaoEspecialR3b() {
    	final Conta instance = new Conta();
    	final double limite = 1000;
    	assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
    	
    }
    
    @Test
    void testHistoricoNotNullR4() {
    	final Conta instace = new Conta();
    	assertNotNull(instace.getMovimentacoes());
    }
    
    
    @Test
    void testAddMovimentacao() {
        //TODO: Você precisa implementar este teste
    }
    
    
}
