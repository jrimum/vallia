package br.com.nordestefomento.jrimum.vallia;

import junit.framework.Test;
import junit.framework.TestSuite;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.TestDV4BoletoCodigoDeBarra;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.TestDV4BoletoLinhaDigitavel;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.TestDV4CNPJ;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.TestDV4CPF;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.TestModulo10;
import br.com.nordestefomento.jrimum.vallia.digitoverificador.TestModulo11;

public class TestSuiteVallia {

	public static Test suite() {
	    
		TestSuite suite= new TestSuite();
		
		suite.addTestSuite(TestAValidator4CadastroDePessoa.class);
		suite.addTestSuite(TestValidator4CPF.class);
		suite.addTestSuite(TestValidator4CNPJ.class);
		suite.addTestSuite(TestDV4BoletoCodigoDeBarra.class);
		suite.addTestSuite(TestDV4BoletoLinhaDigitavel.class);
		suite.addTestSuite(TestDV4CPF.class);
		suite.addTestSuite(TestDV4CNPJ.class);
		suite.addTestSuite(TestModulo10.class);
		suite.addTestSuite(TestModulo11.class);
	    
	    return suite;
	}
}
