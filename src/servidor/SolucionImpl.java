/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

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
        
    }
    
}
