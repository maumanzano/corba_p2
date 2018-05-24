/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.File;
import java.util.ArrayList;
import sop_corba.SolucionPOA;
import sop_corba.arquitectura;
import sop_corba.arquitecturaHolder;
import sop_corba.fichero;


/**
 *
 * @author Mauricio
 */
public class SolucionImpl extends SolucionPOA{

    @Override
    public void establecerArquitectura(arquitecturaHolder objArquitectura) {
        
        String nSistemOper = System.getProperty("os.name");
        String aSistemOper = System.getProperty("os.arch");
        String vSistemOper = System.getProperty("os.version");
        String sSistemOper = System.getProperty("path.separator");
        
        arquitectura obj;
        obj = new arquitectura(nSistemOper,aSistemOper,vSistemOper,sSistemOper);
        objArquitectura.value=obj;
        
        
    }

    @Override
    public fichero[] listarArchivosDuplicados(String rutaDirectorio) {
        
        File f=new File(rutaDirectorio);
        fichero[] ficheros;
        ArrayList<fichero> ficherosTmp=new ArrayList<fichero>();
        
        ficheros = null;
        if(f.exists()){//Si el directorio existe
            File[] files = f.listFiles();
            if(files.length==0){
                fichero fichero=new fichero("$",false);
                ficheros[0]=fichero;
            }
            for (int i = 0; i < files.length; i++) {
                fichero fTmp = listarArchivosDuplicados(files[i],files);
                if(fTmp!=null){
                    ficherosTmp.add(fTmp);
                }
            }
        }else{ //no existe
            return null;
        }
        
        for (int i = 0; i < ficherosTmp.size(); i++) {
            ficheros[i]=ficherosTmp.get(i);
        }
        return ficheros;
    }
    
    private fichero listarArchivosDuplicados(File file,File[] files){
        fichero fichero = null;
        if(file.isFile()){
            if(validarNombre(files,file)){
                fichero=new fichero(file.getName(),false);
            }
            
        }else{
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                fichero=listarArchivosDuplicados(listFiles[i],listFiles);
            }
        }
        
        return fichero;
    }
    
    private boolean validarNombre(File[] files, File file){
        int cont=0;
        boolean flag=false;
        for (int i = 0; i < files.length; i++) {
            if(file.getName().equals(files[i].getName())){
                cont ++;
            }
        }
        if(cont>1){
            flag=true;
        }
        
        return flag;
    }
}
