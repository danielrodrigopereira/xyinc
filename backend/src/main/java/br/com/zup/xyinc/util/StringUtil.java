package br.com.zup.xyinc.util;

public class StringUtil {

	public static Boolean isEmpty(String value) {
		Boolean retorno = false;
		if (value == null) {
			retorno = true;
		}
		return retorno;
	}
}
