public class ClavesRSA{
	
	private BigInteger clave_P;
	private BigInteger clave_Q;
	private BigInteger clave_Phi;

	public ClavesRSA(BigInteger p, BigInteger q, BigInteger phi){
		this.clave_P = p;
		this.clave_Q = q;
		this.clave_Phi = phi;
	}
}