package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDV4BoletoCodigoCompensacaoBanco {

	private final int CODIGO_COMPENSACAO_LIMITE_MINIMO = 1; 
	private final int CODIGO_COMPENSACAO_LIMITE_MAXIMO = 999; 
	
	private DV4BoletoCodigoCompensacaoBanco dv4CodigoCompensacaoBanco = null;
	
	@Before
	public void setUp() throws Exception {
		dv4CodigoCompensacaoBanco = new DV4BoletoCodigoCompensacaoBanco();
	}	
		
	@After
	public void tearDown() throws Exception {
		dv4CodigoCompensacaoBanco = null;
	}	
		
	@Test
	public final void testCalculeString() {
		assertEquals(9, dv4CodigoCompensacaoBanco.calcule("001"));
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule("104"));
		assertEquals(2, dv4CodigoCompensacaoBanco.calcule("237"));
		assertEquals(5, dv4CodigoCompensacaoBanco.calcule("356"));
	}

	@Test
	public final void testCalculeLong() {
		assertEquals(9, dv4CodigoCompensacaoBanco.calcule(1));
		assertEquals(0, dv4CodigoCompensacaoBanco.calcule(104));
		assertEquals(2, dv4CodigoCompensacaoBanco.calcule(237));
		assertEquals(5, dv4CodigoCompensacaoBanco.calcule(356));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testCalculeCodigoDeCompensacaoMenorQueLimiteMinimo() {
		dv4CodigoCompensacaoBanco.calcule(CODIGO_COMPENSACAO_LIMITE_MINIMO - 1);
	}	

	@Test(expected=IllegalArgumentException.class)
	public final void testCalculeCodigoDeCompensacaoMaiorQueLimiteMaximo() {
		dv4CodigoCompensacaoBanco.calcule(CODIGO_COMPENSACAO_LIMITE_MAXIMO + 1);
	}	

}
