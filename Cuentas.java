/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bancoconsola;


/**
 *
 * @author alemontano
 */
public class Cuentas {
    public int noCuenta;
    public String tipo;
    public int saldo = 0;
    public String moneda;
    public int cliente;
    public int estatus;
    
    public Cuentas()
    {
    
    }
    
    @Override
    public String toString()
    {
        
        return noCuenta + " - " + tipo + " " + saldo + " " + moneda + " | "+
               cliente + " " + estatus;
    }
}
