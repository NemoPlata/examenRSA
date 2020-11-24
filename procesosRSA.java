import java.math.BigInteger;

public class procesosRSA{
	
	//Variables del cifrado RSA
	//Tamaño del primo
	private int tamanoPrimo;
	//Numeros Primos
	private BigInteger p_A, q_A, n_A, comprobador;
	//Indicador de Euler Phi
	private BigInteger phi_A;
	//Clave publica e , clave privada inversa d
	private BigInteger e_A, d_A  = 65537;

	public procesosRSA(int tamanoPrimo){
		this.tamanoPrimo = tamanoPrimo;
	}

	public void generarPrimos(){
		p_A = new BigInteger(tamanoPrimo, 10, new Random());
		do{
			q_A = new BigInteger(tamanoPrimo, 10, new Random());	
		}while(q_A.compareTo(p_A)==0);
	}

	public void generarClavePublicaPrivada(){
		//Obtenemos el numero primo n_A
		n_A = p_A.multiply(q_A);
		//Obtenemos el numero phi
		comprobador = p_A.subtract(BigInteger.valueOf(1));
		comprobador = comprobador.multiply(q_A.subtract(BigInteger.valueOf(1)));

		//Elegir el numero coPrimo menor que n 1 < e < Phi(n)
		do{
			e_A = new BigInteger(2*tamanoPrimo, new Random());
		}while((e_A.compareTo(comprobador) != -1) || (e_A.gcd(comprobador).compareTo(BigInteger.valueOf(1)) != 0));
		//realizar la operacion modulo d = e^(1mod phi)
		d_A = e_A.modInverse(comprobador);		
	}

	public BigInteger[] cifrarRSA(String mensaje){
		int i;
		byte[] temp = new byte[1];
		byte[] digitos = mensaje.getBytes();
		BigInteger[] bigdigitos = new BigInteger[digitos.length];
		BigInteger[] encriptado = new BigInteger[bigdigitos.length];

		for(i=0; i<bigdigitos.length; i++){
			temp[0] = digitos[i];
			bigdigitos[i] = new BigInteger(temp);
		}			
		for(i=0; i<bigdigitos.length; i++) {
			encriptado[i] = bigdigitos[i].modPow(e_A,n_A);
		}

		return encriptado;
	}

	public String descifrarRSA(BigInteger[] cifrado){
		BigInteger descifrar = new BigInteger[cifrado.length];
		char[] charArray = new char[descifrar.length];

		for(int i=0; i<descifrar.length; i++){
			descifrar[i] = encriptado[i].modPow(d_A,n_A);
		}		
		for(int i=0; i<charArray.length; i++){
			charArray[i] = new (char)(descifrar[i].intValue());
		}

		return (new String(charArray));
	}

	public BigInteger obtener_P(){
		return (p_A);
	}

	public BigInteger obtener_Q(){
		return (p_A);
	}

	public BigInteger obtenerComprobador(){
		return (p_A);
	}

	public BigInteger obtener_N(){
		return (p_A);
	}

	public BigInteger obtener_E(){
		return (p_A);
	}

	public BigInteger obtener_D(){
		return (p_A);
	}
}