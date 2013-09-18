import java.io.*;
import java.util.*;

public class buscarTabop {

    public buscarTabop() {
    }
    
    public void buscarCodop(String codop, String oper, int i){
    	archivoINST INST = new archivoINST();
    	archivoERR ERR = new archivoERR();
    	String codopTabop, modoDir, porCalcular;
    	int contar=0, k=0;
    	codop = codop.toUpperCase();
    	try{
    		BufferedReader br = new BufferedReader(new FileReader("tabop.txt"));
    		while(br.ready()){
    			StringTokenizer token = new StringTokenizer(br.readLine());
    			while(token.hasMoreTokens()){
    				codopTabop = token.nextToken();
    				modoDir = token.nextToken();
    				token.nextToken();
    				token.nextToken();
    				porCalcular = token.nextToken();
    				token.nextToken();
    				
    				if(contar==1 && codop.equals(codopTabop))
    					INST.escribirComa();
    					
    				if(contar==1 && !codop.equals(codopTabop)){
    					break;
    				}
    				if(codop.equals(codopTabop)){
    					if(!oper.isEmpty() && (Integer.parseInt(porCalcular) == 0))
    						ERR.operNoValidoParaCodop(codop,oper,i);
    					
    					INST.escribirModoDir(modoDir,k);
    					k+=1;
    					contar=1;
    				}
    			}
    		}
    		
    		if(k==0){
    			ERR.codopNoEncontradoTabop(codop,i);
    		}	
    		    			
    		INST.nuevaLinea();
    		br.close();
    	}
    	catch(FileNotFoundException f){System.out.println("no existe archivo....");    	}
    	catch(IOException ioe){System.out.println("Error de archivo....");}
    }
    
}