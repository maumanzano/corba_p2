package sop_corba;


/**
* sop_corba/SolucionOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from registro.idl
* martes 22 de mayo de 2018 07:24:34 AM COT
*/

public interface SolucionOperations 
{
  void establecerArquitectura (sop_corba.arquitecturaHolder objArquitectura);
  sop_corba.fichero[] listarArchivosDuplicados (String rutaDirectorio);
} // interface SolucionOperations
