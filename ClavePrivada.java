import java.io.Serializable;
import java.math.BigInteger;

public class ClavePrivada implements Serializable{
	
	private BigInteger clavePrivada;

	public ClavePrivada(){
	}
	
	public ClavePrivada(BigInteger clave){
		this.clavePrivada = clave;
	}

	public BigInteger getClavePrivada(){
		return clavePrivada;
	}

	public void setClavePrivada(BigInteger clave){
		this.clavePrivada = clave;
	}
}