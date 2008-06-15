/**
 * 
 */
package br.com.nordestefomento.jrimum.vallia.digitoverificador;


import org.apache.commons.lang.StringUtils;

/**
 * @author misael
 *
 */
public class DV4BoletoCodigoCompensacaoBanco extends ADigitoVerificador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Modulo modulo11 = new Modulo(EnumModulo.MODULO11); 

	@Override
	public int calcule(String numero) throws IllegalArgumentException {
		
		int dv = -1;
		int num = -1;
		boolean formatoValido = false;
		
		if (StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)) {
			num = Integer.valueOf(numero);		
			if ((num > 0) && (num <= 999)) {
				formatoValido = true;
			} 
		}
		
		if (formatoValido) {
			dv = modulo11.valor() - modulo11.calcule(num);
			
			if (dv == 10) {
				dv = 0;
			}
		}
		else {
			throw new IllegalArgumentException("O código de compensação do banco deve ser um número entre 1 e 999.");
		}
		
	
		return dv;
	}

	
	@Override
	public int calcule(long numero) {
		return calcule(String.valueOf(numero));
	}


}
