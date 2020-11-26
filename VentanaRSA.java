import java.math.BigInteger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class VentanaRSA extends JFrame{
	
	private JPanel panelRSA = new JPanel();
	private GuardarClaves guardar_claves = new GuardarClaves();
	private ProcesosRSA procesos_RSA = new ProcesosRSA();
	private JTextArea txtMensaje = new JTextArea();
	private BigInteger claveP, claveQ, clavePhi, clavePrivada;
	private BigInteger[] msjCifrado;
	private BigInteger[] msjObtenido;
	private String msjDescifrado;


	public VentanaRSA(){
		setSize(500, 580);
        setTitle("Cifrado RSA");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500, 580));
        setMaximumSize(new Dimension(500, 580));                
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        componentesRSA();
	}	

	private void componentesRSA(){
		panelRSA();
		etiquetasRSA();
        botonesRSA();        
        areaTextoRSA();
	}

	private void panelRSA(){
        panelRSA = new JPanel();
        panelRSA.setLayout(null);
        panelRSA.setBackground(new Color(98, 198, 199));
        this.getContentPane().add(panelRSA);
    }

    private void etiquetasRSA(){
        //Etiqueta instrucciones
        JLabel et0 = new JLabel("Examen RSA");
        et0.setBounds(85,40,400,50);       
        panelRSA.add(et0);

        //Etqueta de la grafica General
        JLabel et1 = new JLabel("generar_Claves Gráfica General de Autoestima");
        et1.setBounds(120,130,300,25);        
        panelRSA.add(et1);

        //Etiqueta generar_Claves todos lo Alumnos y la suma de sus preguntas
        JLabel et2 = new JLabel("generar_Claves Resultados Generales de Preguntas");
        et2.setBounds(110,230,300,25);        
        panelRSA.add(et2);

        //Etiqueta generar_Claves Grafica de Alumno especifico y sus preguntas
        JLabel et3 = new JLabel("generar_Claves Lista de Incisos");
        et3.setBounds(170,330,400,25);        
        panelRSA.add(et3);        

        //Etiqueta generar_Claves Listado de Preguntas
        JLabel et4 = new JLabel("generar_Claves Datos de un Alumno Específico");
        et4.setBounds(130,430,400,25);        
        panelRSA.add(et4);    
    }
    
    private void areaTextoRSA(){
    	txtMensaje = new JTextArea();
    	txtMensaje.setBounds(0,0,100,50);
    	txtMensaje.setEditable(true);
    	txtMensaje.setLineWrap(true);
    	panelRSA.add(txtMensaje);
    }
    
    private void botonesRSA(){
        JButton generar_Claves = new JButton("Generar Claves");
        generar_Claves.setBounds(210,180,80,35);
        generar_Claves.setEnabled(true);
        panelRSA.add(generar_Claves);
        
        generar_Claves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	procesos_RSA.generarPrimos();		
				procesos_RSA.generarClavePublicaPrivadaPhi();
				JOptionPane.showMessageDialog(null, "Claves Generadas con Éxito.");          	
            }            
        });
        
        JButton btn_Cifrar = new JButton("Cifrar");
        btn_Cifrar.setBounds(210,280,80,35);
        btn_Cifrar.setEnabled(true);
        panelRSA.add(btn_Cifrar);
        
        btn_Cifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
            	String msjPlano = txtMensaje.getText();  
            	try{
            		msjCifrado = procesos_RSA.cifrarRSA_ClavePublica(msjPlano);  
            	}catch(IOException ioe){
            		ioe.printStackTrace();
            	}catch(ClassNotFoundException cnfe){
            		cnfe.printStackTrace();
            	}         	            	
            	JOptionPane.showMessageDialog(null, "Mensaje Cifrado Correctamente.");          	
            }            
        });
        
        JButton btn_Descifrar = new JButton("Descifrar");
        btn_Descifrar.setBounds(210,380,80,35);
        btn_Descifrar.setEnabled(true);
        panelRSA.add(btn_Descifrar);
        
        btn_Descifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
            		guardar_claves.obtenerClaves();		
	            	for(int i=0; i<guardar_claves.getListaClavesRSA().size(); i++) {
						claveP = guardar_claves.getListaClavesRSA().get(i).getClave_P();
						claveQ = guardar_claves.getListaClavesRSA().get(i).getClave_Q();
						clavePhi = guardar_claves.getListaClavesRSA().get(i).getClave_Phi();
						msjObtenido = guardar_claves.getListaClavesRSA().get(i).getMsjCifrado();
					}
                    guardar_claves.obtenerClavePrivada();
                    for(int i=0; i<guardar_claves.getListaClavePrivada().size(); i++) {
                        clavePrivada = guardar_claves.getListaClavePrivada().get(i).getClavePrivada();
                    }
	            	msjDescifrado = procesos_RSA.descifrarRSA_ClavePrivada(claveP, claveQ, clavePhi, msjObtenido, clavePrivada);	
            	}catch(FileNotFoundException fnfe){
            		fnfe.printStackTrace();
            	}catch(IOException ioe){
            		ioe.printStackTrace();
            	}catch(ClassNotFoundException cnfe){
            		cnfe.printStackTrace();
            	}            	
            	JOptionPane.showMessageDialog(null, "Mensaje Descifrado Correctamente."); 
            	txtMensaje.setText(msjDescifrado);         	
            }    
        });      
    }
}