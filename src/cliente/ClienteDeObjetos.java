package cliente;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import sop_corba.*;

public class ClienteDeObjetos
{
  //*** Atributo estático ***
  static SolucionOperations ref;
  static arquitecturaHolder objArquitectura;

  public static void main(String args[])
    {
      try{
        // crea e inicia el ORB
	    ORB orb = ORB.init(args, null);

        // obtiene la base del naming context
        org.omg.CORBA.Object objRef = 
	    orb.resolve_initial_references("NameService");     
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        // *** Resuelve la referencia del objeto en el N_S ***
        String name = "objSolucion";
        ref = SolucionHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Obtenido el manejador sobre el servidor de objetos: " +ref);
        
        menu();

	} catch (Exception e) {
          System.out.println("ERROR : " + e) ;
	  e.printStackTrace(System.out);
	  }
    }

    public static void menu(){
        int opc=0;
        
        do{
            System.out.println("======== Menu ========");
            System.out.println("1. Consultar arquitectura,version y sistema operativo");			
            System.out.println("2. Localizar archivos duplicados");
            System.out.println("3. Salir");
            System.out.println("=========== ==========");
            
            opc = UtilidadesConsola.leerEntero();
            
            switch(opc){
                case 1:
                    opcion1();
                    break;
                case 2:
                    opcion2();
                    break;
                case 3:
                    System.out.println("Saliendo del juego...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }while(opc!=3);
        
    }
    
    public static void opcion1(){
        objArquitectura = new arquitecturaHolder();
        ref.establecerArquitectura(objArquitectura);
        System.out.println("Informacion de maquina:");
        System.out.println(" Sistema operativo: "+objArquitectura.value.nombreSistemaOperativo);
        System.out.println(" Arquitectura: "+objArquitectura.value.arquitectura);
        System.out.println(" Version: "+objArquitectura.value.version);
        System.out.println(" Separador: "+objArquitectura.value.separador);
    }
    
    public static void opcion2(){
        String ruta="";
        ruta=UtilidadesConsola.leerCadena();
        fichero[] archivosDuplicados = ref.listarArchivosDuplicados(ruta);
        if(archivosDuplicados!=null){
            System.out.print("< ");
            for (int i = 0; i < archivosDuplicados.length; i++) {
                System.out.print(" "+archivosDuplicados[i].nombre+" ");
            }
            System.out.println(">");
        }else{
            System.out.println("No existe el directorio");
        }
    }
}

