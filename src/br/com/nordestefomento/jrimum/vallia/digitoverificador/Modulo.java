/* 
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created at: 20/04/2008 - 18:11:05
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode 
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma 
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que 
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta 
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam 
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões 
 * e limitações sob esta LICENÇA.
 * 
 * Criado em: 20/04/2008 - 18:11:05
 * 
 */

package br.com.nordestefomento.jrimum.vallia.digitoverificador;

import org.apache.commons.lang.StringUtils;

import br.com.nordestefomento.jrimum.ACurbitaObject;

/**
 * 
 * <p>
 * DEFINIÇÃO DA CLASSE
 * </p>
 * 
 * <p>
 * OBJETIVO/PROPÓSITO
 * </p>
 * 
 * <p>
 * EXEMPLO:
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since
 * 
 * @version
 */

public class Modulo extends ACurbitaObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3612368544258125201L;

	/**
	 * Mensagem da exceção lançada no método calcular.
	 */
	private static final String O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS = "O argumento deve conter apenas números !";
	
	private EnumModulo mod;
	
	private int limiteMaximo;
	
	private int limiteMinimo;

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private Modulo() {
		super();
	}

	/**
	 * @param mod
	 */
	public Modulo(EnumModulo mod) {
		super();
		
		if(isNotNull(mod,"modulo")){
			
			this.mod = mod;
			initDefault();
		}
	}

	/**
	 * @param limiteMaximo
	 * @param limiteMinimo
	 * @param mod
	 */
	public Modulo(EnumModulo mod, int limiteMaximo, int limiteMinimo) {
		super();
		
		if(isNotNull(mod,"modulo")){
			
			this.limiteMaximo = limiteMaximo;
			this.limiteMinimo = limiteMinimo;
			this.mod = mod;
			initDefault();
		}	
	}

	public static int calcularMod11(long numero, int limiteMin, int limiteMax) {

		return calcularMod11(String.valueOf(numero), limiteMin, limiteMax);
	}

	public static int calcularMod11(String numero, int limiteMin, int limiteMax)
			throws IllegalArgumentException {

		return (calcularSomaSequencialMod11(numero, limiteMin, limiteMax) % 11);
	}

	public static int calcularSomaSequencialMod11(String numero, int limiteMin,
			int limiteMax) throws IllegalArgumentException{

		int peso = 0;
		int soma = 0;

		if (StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)) {

			StringBuilder sb = new StringBuilder(numero);
			sb.reverse();

			peso = limiteMin;

			for (char c : sb.toString().toCharArray()) {
				soma += peso * Character.getNumericValue(c);
				peso++;

				if (peso > limiteMax)
					peso = limiteMin;
			}

		} else
			throw new IllegalArgumentException(
					O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS);

		return soma;
	}

	public static int calcularMod10(long numero, int limiteMin, int limiteMax) {

		return calcularMod10(String.valueOf(numero), limiteMin, limiteMax);
	}

	public static int calcularMod10(String numero, int limiteMin, int limiteMax)
			throws IllegalArgumentException {

		return (calcularSomaSequencialMod10(numero, limiteMin, limiteMax) % 10);
	}

	public static int calcularSomaSequencialMod10(String numero,
			int limiteMin, int limiteMax) throws IllegalArgumentException{

		int produto = 0;
		int peso = 0;
		int soma = 0;
		
		if(StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)){
			
			StringBuilder sb = new StringBuilder(numero);
			sb.reverse();
			
			peso = limiteMax;
			
			for(char c : sb.toString().toCharArray()){
				
				produto = peso * Character.getNumericValue(c);
				
				if(produto > 9){
					
					soma += produto / 10;
					soma += produto % 10;
				}
				else
					soma += produto;
				
				peso = ( peso == limiteMax ) ? limiteMin : limiteMax;
			}
			
		}else
			throw new IllegalArgumentException(O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS);
		
		return soma;
	}

	public int calcular(String numero) throws IllegalArgumentException {

		int modulo = 0;
		
		switch (mod) {

		case MODULO10:
			
			modulo = calcularMod10(numero, getLimiteMinimo(), getLimiteMaximo());
			
			break;

		case MODULO11:

			modulo = calcularMod11(numero, getLimiteMinimo(), getLimiteMaximo());
			
			break;
		}
		
		return modulo;
	}

	public int calcular(long numero) {
		
		return calcular(String.valueOf(numero));
	}
	
	private void initDefault(){
		
		switch (mod) {

		case MODULO10:
			
			setLimiteMinimo(1);
			setLimiteMaximo(2);

			break;

		case MODULO11:
			
			setLimiteMinimo(2);
			setLimiteMaximo(9);

			break;
		}
	}
	
	/**
	 * @return the limiteMaximo
	 */
	public int getLimiteMaximo() {
		return limiteMaximo;
	}

	/**
	 * @param limiteMaximo the limiteMaximo to set
	 */
	public void setLimiteMaximo(int limiteMaximo) {
		this.limiteMaximo = limiteMaximo;
	}

	/**
	 * @return the limiteMinimo
	 */
	public int getLimiteMinimo() {
		return limiteMinimo;
	}

	/**
	 * @param limiteMinimo the limiteMinimo to set
	 */
	public void setLimiteMinimo(int limiteMinimo) {
		this.limiteMinimo = limiteMinimo;
	}

	/**
	 * @return the mod
	 */
	public EnumModulo getMod() {
		return mod;
	}

	/**
	 * @param mod the mod to set
	 */
	public void setMod(EnumModulo mod) {
		this.mod = mod;
	}
}
