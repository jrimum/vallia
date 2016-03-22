package br.com.nordestefomento.jrimum.vallia.digitoverificador;

public class NossoNumeroDV extends AbstractDigitoVerificador {
	
	/**
	 * Valor mínimo do código de compensação
	 */
	public static final int LIMITE_MINIMO = 2;
	
	/**
	 * Valor máximo do código de compensação
	 */
	public static final int LIMITE_MAXIMO = 9; 

	@Override
	public int calcule(String numero) throws IllegalArgumentException {

		int dv = 11 - Modulo.calculeSomaSequencialMod11(numero, LIMITE_MINIMO, LIMITE_MAXIMO);
		if (dv > 9) {
			dv = 0;
		}
		return dv;
	}

}
