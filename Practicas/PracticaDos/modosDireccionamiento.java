import java.io.*;
import java.util.*;

public class modosDireccionamiento {
	String linea;
	int i=0;

	Scanner Leer = new Scanner (System.in);

	int TdT[][]={{2,1,3,-1},{1,1,1,6},{2,2,4,-1},{4,1,3,6},{4,4,5,6},{7,1,5,6},{-1,-1,-1,-1},
	             {7,7,8,6},{-1,1,8,6}};

    public modosDireccionamiento() {
    }
    
    public int analizaEntrada(char c){
    	if(c!=';' && !Character.isWhitespace(c) && c!=13)
    		return 0;
    	else
    		if(c==';')
    			return 1;
    		else
    			if(c==32 || c==9)
    				return 2;
    			else
    				return 3;
    }

    public void validaLinea(){
    	int estado=-1,entrada=-1,i=0, tam;
    	boolean b, etqErronea=false, codErroneo=false,end=true;
    	String etq, cod, ope, ruta, auxRuta;
    	File archINST, archERR;

    	System.out.println("Introduce la ruta del Archivo: ");
    	ruta = Leer.nextLine();

    	tam = ruta.length();
    	auxRuta = ruta.substring(tam-3,tam);
    	auxRuta = auxRuta.toUpperCase();

    	if(!auxRuta.equals("ASM"))
    		System.out.println("\n\"Solo se permiten archivos con extension .ASM\"");
    	else{
    		
    		buscarTabop tabop = new buscarTabop();
    		
    		archINST = new File("P2ASM.INST");
    		if(archINST.exists())
    			archINST.delete();

    		archERR = new File("P2ASM.ERR");
    		if(archERR.exists())
    			archERR.delete();
    		
    		archivoINST INST = new archivoINST();
			INST.crearArchivo();
			
			archivoERR ERR = new archivoERR();
			ERR.crearArchivoERR();

	    	try{
	    		BufferedReader br = new BufferedReader(new FileReader(ruta));
	    		while(br.ready())
	    		{
	    			StringTokenizer token = new StringTokenizer(br.readLine(),"\n");
	    			i++;
	    			while(token.hasMoreTokens() && end){
	    				linea = token.nextToken();

	    				System.out.println("Linea "+(i)+": "+linea);
	    				etq=cod=ope="";
	    				etqErronea=codErroneo=false;
	    				estado=0;
	    				b=true;
	    				tam = linea.length();

						for(int k=0; k<tam && b; k++){
							entrada = analizaEntrada(linea.charAt(k));
							if(entrada==-1){
								b=false;
								ERR.entradaInvalida(i);
							}
							else{
								estado = TdT[estado][entrada];
								if(estado==2){
									etq+=linea.charAt(k);
								}
								if(estado==4 && linea.charAt(k)!=32 && linea.charAt(k)!=9){
									cod+=linea.charAt(k);
								}
								if(estado==7 && linea.charAt(k)!=32 && linea.charAt(k)!=9){
									ope+=linea.charAt(k);
								}
								if(estado==-1){
									b=false;
									ERR.estadoInvalida(i);
								}
							}
						}

						if(cod.equals("END")|| cod.equals("End")||cod.equals("ENd")||
						   cod.equals("ENd")||cod.equals("enD")||cod.equals("eND"))
						   	end=false;

						System.out.println("ETIQUETA: "+ etq);
						System.out.println("CODOP: "+ cod);
						
						if(!etq.isEmpty()){
							if(etiquetaValida(etq))
								System.out.println("Etiqueta Valida");
							else{
								ERR.etiquetaInvalida(i);
								etqErronea=true;
							}
						}
						if(!cod.isEmpty()){
							if(codopValido(cod))
								System.out.println("CODOP Valido");
							else{
								ERR.codopInvalido(i);
								codErroneo=true;
							}
						}
						if(etq.isEmpty() && cod.isEmpty() && ope.isEmpty())
							b=false;
							
						if(cod.isEmpty() && ope.isEmpty() && !etq.isEmpty() && b){
							b=false;
							ERR.formatoLineaInvalido(i);
						}
						if(!etqErronea && !codErroneo && b){
							INST.escribirPalabras(i,etq,cod,ope);
							tabop.buscarCodop(cod,ope,i);
						}
	    			}
	    		}
	    		br.close();
	    		if(end==true){
					 ERR.noEnd();
	    		}
	    	}
	    	catch(FileNotFoundException f){System.out.println("No Existe Archivo....");}
	    	catch(IOException ioe){System.out.println("Error de archivo....");}
    	}
    }

    public boolean etiquetaValida(String etq){
    	if(etq.matches("^[a-zA-Z]([a-zA-Z]|([0-9])|(_)){0,7}"))
    		return true;
    	else
    		return false;
    }

    public boolean codopValido(String cod){
    	int punto=0, tam=cod.length();
    	boolean flag=false;
    	if(tam > 5){
    		return false;
    	}
    	else
    		if(!Character.isLetter(cod.charAt(0))){
    			return false;
    		}
    		else{
    			for(int i=1;i<tam && !flag;i++){
    				if(!(Character.isLetter(cod.charAt(i)) || cod.charAt(i)=='.'))
    					flag=true;
    				if(cod.charAt(i)=='.')
    					punto+=1;
    			}
    			if(flag || punto > 1)
    				return false;
    			else
    				return true;
    		}
    }
    public boolean operValido(String ope){
    	if(ope.matches(".+"))
    		return true;
    	else
    		return false;
    }

    public static void main(String []args){
    	modosDireccionamiento l = new modosDireccionamiento();
    	l.validaLinea();
    }
}