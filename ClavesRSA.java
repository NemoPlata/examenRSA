import java.io.Serializable;
import java.math.BigInteger;

public class ClavesRSA implements Serializable{
	
	private BigInteger clave_P;
	private BigInteger clave_Q;
	private BigInteger clave_Phi;
	private BigInteger[] msjCifrado;

	public ClavesRSA(){

	}
	
	public ClavesRSA(BigInteger p, BigInteger q, BigInteger phi, BigInteger[] msj){
		this.clave_P = p;
		this.clave_Q = q;
		this.clave_Phi = phi;
		this.msjCifrado = msj;
	}

	public BigInteger getClave_P(){
		return clave_P;
	}

	public void setClave_P(BigInteger clave_P){
		this.clave_P = clave_P;
	}

	public BigInteger getClave_Q(){
		return clave_Q;
	}

	public void setClave_Q(BigInteger clave_Q){
		this.clave_Q = clave_Q;
	}

	public BigInteger getClave_Phi(){
		return clave_Phi;
	}

	public void setClave_Phi(BigInteger clave_Phi){
		this.clave_Phi = clave_Phi;
	}

	public BigInteger[] getMsjCifrado(){
		return msjCifrado;
	}

	public void setMsjCifrado(BigInteger[] msjCifrado){
		this.clave_Phi = clave_Phi;
	}
}