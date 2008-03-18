/*
 * Copyright 2007, JMatryx Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">
 * http://www.apache.org/licenses/LICENSE-2.0 </a>
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2007, Grupo JMatryx
 * 
 * Licenciado sob a licença da Apache, versão 2.0 (a “licença”); você não pode
 * usar este arquivo exceto na conformidade com a licença. Você pode obter uma
 * cópia da licença em
 * 
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">
 * http://www.apache.org/licenses/LICENSE-2.0 </a>
 * 
 * A menos que seja requerido pela aplicação da lei ou esteja de acordo com a
 * escrita, o software distribuído sob esta licença é distribuído “COMO É”
 * BASE,SEM AS GARANTIAS OU às CONDIÇÕES DO TIPO, expresso ou implicado. Veja a
 * licença para as permissões sobre a línguagem específica e limitações quando
 * sob licença.
 * 
 * 
 * Created at / Criado em : 17/03/2007 - 17:41:23
 * 
 */

package br.com.nordestefomento.jrimum.vallia;



/**
 * 
 * Descrição:
 * 
 * 
 * @author Gabriel Guimarães
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto 
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento Mercantil</a>
 * 
 * @since JMatryx 1.0
 * 
 * @version 1.0
 */
class Validator4CNPJ extends AValidator4CadastroDePessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7818892654534965062L;


	/**
	 * @see br.com.nordestefomento.jrimum.vallia.AValidator4CadastroDePessoa#isValido()
	 */
	@Override
	public boolean isValido() {
		
		boolean isValido = false;
		int dv = 0;
		int dvCalculado = -1;
		
		dv = Integer.parseInt(getCodigoDoCadastro().substring(12, 14));

		dvCalculado = digitoVerificador.calcular(getCodigoDoCadastro().substring(0, 12));
		
		isValido = (dv == dvCalculado);	
			
		return isValido;
	}
	
	
	/**
	 * @see br.com.nordestefomento.jrimum.vallia.AValidator4CadastroDePessoa#removeFormatacao()
	 */
	@Override
	protected void removeFormatacao() {
		
		String codigo = codigoDoCadastro.toString();
		
		codigo = codigo.replace(".", "");
		codigo = codigo.replace("/", "");
		codigo = codigo.replace("-", "");

		codigoDoCadastro.delete(0, codigoDoCadastro.length());
		
		codigoDoCadastro.append(codigo);
	}

}
