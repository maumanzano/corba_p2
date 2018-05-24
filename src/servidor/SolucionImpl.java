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
        
        System.out.println(" Usuarios solicita informacion de arquitectura");
        
        String nSistemOper = System.getProperty("os.name");
        String aSistemOper = System.getProperty("os.arch");
        String vSistemOper = System.getProperty("os.version");
        String sSistemOper = System.getProperty("file.separator");
        
        arquitectura obj;
        obj = new arquitectura(nSistemOper,aSistemOper,vSistemOper,sSistemOper);
        objArquitectura.value=obj;
        
        
    }

    @Override
    public fichero[] listarArchivosDuplicados(String rutaDirectorio) {
        
        System.out.println(" Usuario solicita listar archivos duplicados");
        
        File f=new File(rutaDirectorio);
        ArrayList<fichero> ficherosTmp=new ArrayList<fichero>();
        ArrayList<fichero> ficherosDuplicados=new ArrayList<fichero>();
        
        if(f.exists()){//Si el directorio existe
            ficherosTmp=listarArchivosDuplicados(f,ficherosTmp);
        }else{ //no existe
            return null;
        }
        
        for (int i = 0; i < ficherosTmp.size(); i++) {
            int cont=0;
            for (int j = 0; j < ficherosDuplicados.size(); j++) {
                if(ficherosDuplicados.get(j).equals(ficherosTmp.get(j))){
                   cont ++; 
                }
            }
            if(cont==0){
                if(validarNombre(ficherosTmp.get(i),ficherosTmp)){
                        ficherosDuplicados.add(ficherosTmp.get(i));
                }
            }
        }
        
        fichero[] ficheros = new fichero[ficherosDuplicados.size()];
        
        for (int j = 0; j < ficherosDuplicados.size(); j++) {
            ficheros[j]=ficherosDuplicados.get(j);
        }
        
        return ficheros;
    }
    
    private ArrayList<fichero> listarArchivosDuplicados(File file,ArrayList<fichero> files){
        
        fichero fichero;
        if(file.isFile()){
            //if(validarNombre(files,file)){
                fichero=new fichero(file.getName(),false);
                files.add(fichero);
            //}
            
        }else{
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                files=listarArchivosDuplicados(listFiles[i],files);
            }
        }
        
        return files;
    }
    
    private boolean validarNombre(fichero file,ArrayList<fichero> files){
        int cont=0;
        boolean flag=false;
        for (int i = 0; i < files.size(); i++) {
            if(file.nombre.equals(files.get(i).nombre)){
                cont ++;
            }
        }
        if(cont>1){
            flag=true;
        }
        
        return flag;
    }
}
