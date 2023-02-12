/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoconsola;

import java.text.*;
import java.util.Date;

/**
 *
 * @author FusionUser
 */
public class Cliente {
    
    public int noCliente;
    public String nombre;
    public String apellidoP;
    public String apellidoM;
    public Date fechaNac;
    public char sexo;
    
    public Cliente()
    {
    
    }
    
    public boolean leerFechaNacimiento(String fecha)
    {
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            fechaNac= formatoFecha.parse(fecha);
        } 
        catch (ParseException ex) {
            System.out.println("Fomato invalido!!! Debe ser: yyyy-MM-dd... ");
            return false;
        }
        return true;
    }
    
    public String imprimirFechaNacimiento()
    {
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        String fecha= "";
        
        if (fechaNac!=null)
        {
            fecha=formatoFecha.format(fechaNac);
        }
        return fecha;
    }
    
    @Override
    public String toString()
    {
        
        return noCliente + " - " + nombre + " " + apellidoP + " " + apellidoM + " | "+
               imprimirFechaNacimiento() + " " + (sexo=='M'?"Masculino" : "Femenino");
    }
    
}
