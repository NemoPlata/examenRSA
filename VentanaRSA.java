import java.math.BigInteger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaRSA extends JFrame{
	
	private JPanel panelRSA = new JPanel();
	private GuardarClaves guardar_claves = new GuardarClaves();
	private ProcesosRSA procesos_RSA = new ProcesosRSA();
	private JTextArea txtMensaje;
    private JLabel gif, gif2;
    private Font font = new Font("Arial Black",Font.BOLD,25);
    private Font font2 = new Font("Arial Black",Font.BOLD,15);
	private BigInteger claveP, claveQ, clavePhi, clavePrivada;
	private BigInteger[] msjCifrado;
	private BigInteger[] msjObtenido;
	private String msjDescifrado;
    private boolean bol_clave = false;


	public VentanaRSA(){
		setSize(600, 800);
        setTitle("Cifrado RSA");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 800));
        setMaximumSize(new Dimension(600, 800));                
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
        JLabel et0 = new JLabel("Examen RSA");
        et0.setBounds(210,20,215,50);  
        et0.setFont(font);     
        panelRSA.add(et0);
        
        JLabel et1 = new JLabel("Generar las Claves del Cifrado");
        et1.setBounds(170,100,300,25);  
        et1.setFont(font2);      
        panelRSA.add(et1);
        
        JLabel et2 = new JLabel("Mensaje:");
        et2.setBounds(130,210,300,25); 
        et2.setFont(font2);       
        panelRSA.add(et2);       
    
        gif = new JLabel();        
        ImageIcon icon = new ImageIcon("img/cerrando.gif");  
        gif.setIcon(icon);            
        gif.setBounds(160,480,300,225);  
        gif.setVisible(false);
        panelRSA.add(gif);    

        gif2 = new JLabel();        
        ImageIcon icon2 = new ImageIcon("img/abriendo.gif");  
        gif2.setIcon(icon2);            
        gif2.setBounds(160,480,300,225);  
        gif2.setVisible(false);
        panelRSA.add(gif2);
    }
    
    private void areaTextoRSA(){
    	txtMensaje = new JTextArea();
    	txtMensaje.setBounds(130,250,350,100);
    	txtMensaje.setEditable(true);
    	txtMensaje.setLineWrap(true);
    	panelRSA.add(txtMensaje);
    }
    
    private void botonesRSA(){
        JButton generar_Claves = new JButton("Generar Claves");
        generar_Claves.setBounds(240,140,130,35);
        generar_Claves.setEnabled(true);
        panelRSA.add(generar_Claves);
        
        generar_Claves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){                
                int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas generar nuevas Claves?","Alerta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                bol_clave = true;
                if(confirm==0){                    
                    gif.setVisible(false);
                    gif2.setVisible(false);
                    procesos_RSA.generarPrimos();       
                    procesos_RSA.generarClavePublicaPrivadaPhi();
                    JOptionPane.showMessageDialog(null, "Claves Generadas con Éxito.");             
                }         	
            }            
        });
        
        JButton btn_Cifrar = new JButton("Cifrar");
        btn_Cifrar.setBounds(205,370,80,35);
        btn_Cifrar.setEnabled(true);
        panelRSA.add(btn_Cifrar);
        
        btn_Cifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
            	String msjPlano = txtMensaje.getText();  
                Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]{1,150}$");
                Matcher matcher = pattern.matcher(msjPlano);
                msjPlano = msjPlano.replace(" ", "/");
                if(matcher.find()){
                    if(bol_clave){
                        try{
                            bol_clave = false; 
                            gif.setVisible(true);                   
                            msjCifrado = procesos_RSA.cifrarRSA_ClavePublica(msjPlano);  
                        }catch(IOException ioe){
                            ioe.printStackTrace();
                        }catch(ClassNotFoundException cnfe){
                            cnfe.printStackTrace();
                        }                           
                        JOptionPane.showMessageDialog(null, "Mensaje Cifrado Correctamente.");
                    }else{
                        JOptionPane.showMessageDialog(null, "Antes debe generar las claves.");
                    }                    
                }else{
                    JOptionPane.showMessageDialog(null, "Datos Erróneos.");
                }
            }            
        });
        
        JButton btn_Descifrar = new JButton("Descifrar");
        btn_Descifrar.setBounds(320,370,80,35);
        btn_Descifrar.setEnabled(true);
        panelRSA.add(btn_Descifrar);
        
        btn_Descifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
                    gif.setVisible(false);
                    gif2.setVisible(true);
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
                msjDescifrado = msjDescifrado.replace("/", " ");
            	txtMensaje.setText(msjDescifrado);         	
            }    
        });      
    }
}