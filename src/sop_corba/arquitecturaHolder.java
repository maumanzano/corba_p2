package sop_corba;

/**
* sop_corba/arquitecturaHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from registro.idl
* martes 22 de mayo de 2018 07:24:34 AM COT
*/

public final class arquitecturaHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.arquitectura value = null;

  public arquitecturaHolder ()
  {
  }

  public arquitecturaHolder (sop_corba.arquitectura initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.arquitecturaHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.arquitecturaHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.arquitecturaHelper.type ();
  }

}