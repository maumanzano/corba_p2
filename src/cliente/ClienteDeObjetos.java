package cliente;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import sop_corba.*;

public class ClienteDeObjetos
{
  //*** Atributo estático ***
  static xxxxxx ref;

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
        String name = "xxx";
        ref = xxxxHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Obtenido el manejador sobre el servidor de objetos: " +ref);
        
        ref.xxxxxxMetodoRemoto();
        

	} catch (Exception e) {
          System.out.println("ERROR : " + e) ;
	  e.printStackTrace(System.out);
	  }
    }

}

