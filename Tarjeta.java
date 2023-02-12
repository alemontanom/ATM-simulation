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
 * @author alemontano
 */
public class Tarjeta {
    
    public int noTarjeta;
    public String NIP;
    public String codigo;
    public Date fechaExp;
    public int cuenta;
    public int estatus;
    
    public Tarjeta()
    {
    
    }
    
    public boolean leerFechaExp(String fecha)
    {
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            fechaExp= formatoFecha.parse(fecha);
        } 
        catch (ParseException ex) {
            System.out.println("Fomato inválido. Debe ser: yyyy-MM-dd ");
            return false;
        }
        return true;
    }
    
    public String imprimirFechaExp()
    {
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        String fecha= "";
        
        if (fechaExp!=null)
        {
            fecha=formatoFecha.format(fechaExp);
        }
        return fecha;
    }
    
    @Override
    public String toString()
    {
        
        return noTarjeta + " - " + NIP + " " + codigo + " | " +
               imprimirFechaExp() + " " + cuenta + " " + estatus;
    }
    
}
