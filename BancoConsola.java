/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoconsola;

import java.sql.*;
import java.util.*;

/**
 *
 * @author FusionUser
 */
public class BancoConsola {

    /**
     * @param args the command line arguments
     */
    
    private static String ruta ="/Users/alemontano/Desktop/Bases\\ de\\ datos.db";
    
    
    public static void main(String[] args) {
        
        
       // if(crearBase()==true)
            
            Cliente nuevoCliente;
            ArrayList<Cliente> misClientes;

            nuevoCliente=leerClienteConsola();      
            guardarClienteBase(nuevoCliente);


            misClientes=leerClientesBase();
            imprimirClientesConsola(misClientes);
            
            Tarjeta nuevaTarjeta; 
            ArrayList<Tarjeta>misTarjetas; 
            
            nuevaTarjeta=leerTarjetaConsola(); 
            guardarTarjetasBase(nuevaTarjeta); 
            
            misTarjetas=leerTarjetasBase(); 
            imprimirTarjetasConsola(misTarjetas); 
            
            Cuentas nuevaCuenta; 
            ArrayList<Cuentas>misCuentas; 
            
            nuevaCuenta=leerCuentaConsola(); 
            guardarCuentasBase(nuevaCuenta); 
            
            misCuentas=leerCuentasBase(); 
            imprimirCuentasConsola(misCuentas); 
            
       // }
        //else
        //{
        //    System.out.println("Estúpido netbeans");
        //}
        
    }
   
    public static Cliente leerClienteConsola()
    {
        Cliente nuevoCliente= new Cliente();
        String fechaStr;
        boolean bandera=false;
        
        Scanner lector = new Scanner(System.in);  // Crea el objeto Scanner
    
        System.out.println("Ingrese Datos del Cliente:");
        
        System.out.print("Nombre: ");
        nuevoCliente.nombre = lector.nextLine();  
        System.out.print("Apellido Paterno: ");
        nuevoCliente.apellidoP = lector.nextLine();  
        System.out.print("Apellido Materno: ");
        nuevoCliente.apellidoM = lector.nextLine();
        do
        {
            System.out.print("Fecha Nacimiento: ");
            fechaStr=lector.nextLine(); 
            bandera=nuevoCliente.leerFechaNacimiento(fechaStr);
            
        }while(!bandera);
        
        System.out.print("Sexo: ");
        nuevoCliente.sexo = lector.nextLine().charAt(0);
        
        return nuevoCliente;
    }
    
    public static void imprimirClientesConsola(ArrayList<Cliente> listaClientes)
    {
        System.out.println("\nLos clientes son:");
        for(Cliente cliente:listaClientes)
        {
            System.out.println(cliente);
        }
    }
    
    public static void guardarClienteBase(Cliente nuevoCliente)
    {
        String cadenaConexion = "jdbc:sqlite:" + ruta;
        
        Connection miConexion = null;
        PreparedStatement miComando;
                
        try
        {      
            miConexion = DriverManager.getConnection(cadenaConexion);
             
            miComando = miConexion.prepareStatement("INSERT INTO Clientes " +
                "(Nombre,ApellidoP,ApellidoM,FechaNac,Sexo) VALUES(?,?,?,?,?)");
            
            miComando.setString(1, nuevoCliente.nombre);
            miComando.setString(2, nuevoCliente.apellidoP);
            miComando.setString(3, nuevoCliente.apellidoM);
            miComando.setString(4, nuevoCliente.imprimirFechaNacimiento());
            miComando.setObject(5, nuevoCliente.sexo);
            
            miComando.execute();
           
            System.out.println("Cliente Almacenado");
                            
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (miConexion != null) {
                    miConexion.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());               
            }
        }
        
    }
    
    public static ArrayList<Cliente> leerClientesBase()
    {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente clienteLeido;
                
        String cadenaConexion = "jdbc:sqlite:" + ruta;
        
        Connection miConexion = null;
        PreparedStatement miComando;
        ResultSet miLector;
                
        try 
        {      
            miConexion = DriverManager.getConnection(cadenaConexion);
            System.out.println("Conexion establecida");
            
            miComando = miConexion.prepareStatement("SELECT * FROM Clientes;");
            miLector = miComando.executeQuery();
           
            while (miLector.next())
            {
                clienteLeido= new Cliente();
                clienteLeido.noCliente = miLector.getInt(1);
                clienteLeido.nombre = miLector.getString(2);
                clienteLeido.apellidoP = miLector.getString(3);
                clienteLeido.apellidoM = miLector.getString(4);
                clienteLeido.leerFechaNacimiento(miLector.getString(5)); 
                clienteLeido.sexo = miLector.getString(6).charAt(0);
                
                listaClientes.add(clienteLeido);
            }
                            
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (miConexion != null) {
                    miConexion.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());               
            }
        }
                        
        return listaClientes;
    }
    
    public static Tarjeta leerTarjetaConsola()
    {
        Tarjeta nuevaTarjeta = new Tarjeta();
        String fechaStr;
        boolean bandera=false;
        
        Scanner lector = new Scanner(System.in);  // Crea el objeto Scanner
    
        System.out.println("Ingrese Datos de la tarjeta:");
        
        System.out.print("NIP: ");
        nuevaTarjeta.NIP = lector.nextLine();  
        System.out.print("Codigo: ");
        nuevaTarjeta.codigo = lector.nextLine();  
        System.out.print("Numero de cuenta: ");
        nuevaTarjeta.cuenta = lector.nextInt();
        System.out.print("Estatus: "); 
        nuevaTarjeta.estatus = lector.nextInt(); 
        while (!bandera)
        {
            System.out.print("Fecha de expedicion: ");
            fechaStr=lector.nextLine(); 
            bandera=nuevaTarjeta.leerFechaExp(fechaStr);
            
        }
        
        
        return nuevaTarjeta;
    }
    public static void imprimirTarjetasConsola(ArrayList<Tarjeta> listaTarjetas)
    {
        System.out.println("\nLos clientes son:");
        for(Tarjeta tarjeta:listaTarjetas)
        {
            System.out.println(tarjeta);
        }
    }
    
    public static void guardarTarjetasBase(Tarjeta nuevaTarjeta)
    {
        String cadenaConexion = "jdbc:sqlite:" + ruta;
        
        Connection miConexion = null;
        PreparedStatement miComando;
                
        try
        {      
            miConexion = DriverManager.getConnection(cadenaConexion);
             
            miComando = miConexion.prepareStatement("INSERT INTO Tarjetas " +
                "(NIP,Codigo,FechaExp,Cuenta,Estatus) VALUES(?,?,?,?,?)");
            
            miComando.setString(1, nuevaTarjeta.NIP);
            miComando.setString(2, nuevaTarjeta.codigo);
            miComando.setString(3, nuevaTarjeta.imprimirFechaExp());
            miComando.setInt(5, nuevaTarjeta.cuenta);
            miComando.setInt(6, nuevaTarjeta.estatus); 
            
            miComando.execute();
           
            System.out.println("Tarjeta guardada");
                            
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (miConexion != null) {
                    miConexion.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());               
            }
        }
        
    }
    
    public static ArrayList<Tarjeta> leerTarjetasBase()
    {
        ArrayList<Tarjeta> listaTarjetas = new ArrayList<>();
        Tarjeta tarjetaLeida;
                
        String cadenaConexion = "jdbc:sqlite:" + ruta;
        
        Connection miConexion = null;
        PreparedStatement miComando;
        ResultSet miLector;
                
        try 
        {      
            miConexion = DriverManager.getConnection(cadenaConexion);
            System.out.println("Hemos alcanzado la base");
            
            miComando = miConexion.prepareStatement("SELECT * FROM Tarjetas;");
            miLector = miComando.executeQuery();
           
            while (miLector.next())
            {
                tarjetaLeida= new Tarjeta();
                tarjetaLeida.noTarjeta = miLector.getInt(1);
                tarjetaLeida.NIP = miLector.getString(2);
                tarjetaLeida.codigo = miLector.getString(3);
                tarjetaLeida.leerFechaExp(miLector.getString(4)); 
                tarjetaLeida.cuenta = miLector.getInt(6);
                tarjetaLeida.estatus = miLector.getInt(7); 
                
                listaTarjetas.add(tarjetaLeida);
            }
                            
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (miConexion != null) {
                    miConexion.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());               
            }
        }
                        
        return listaTarjetas;
    }
    
    public static Cuentas leerCuentaConsola()
    {
        Cuentas nuevaCuenta= new Cuentas();
        
        Scanner lector = new Scanner(System.in);  
    
        System.out.println("Ingrese Datos de la cuenta:");
        
        System.out.print("Tipo de cuenta: ");
        nuevaCuenta.saldo = lector.nextInt();  
        System.out.print("Tipo de moneda: ");
        nuevaCuenta.moneda = lector.nextLine();
       
        return nuevaCuenta;
    }
    
    public static void imprimirCuentasConsola(ArrayList<Cuentas> listaCuentas)
    {
        System.out.println("\nLas cuentas son:");
        for(Cuentas Cuentas:listaCuentas) 
        {
            System.out.println(Cuentas);
        }
    }
    
    public static void guardarCuentasBase(Cuentas nuevaCuenta)
    {
        String cadenaConexion = "jdbc:sqlite:" + ruta;
        
        Connection miConexion = null;
        PreparedStatement miComando;
                
        try
        {      
            miConexion = DriverManager.getConnection(cadenaConexion);
             
            miComando = miConexion.prepareStatement("INSERT INTO Cuentas " +
                "(Tipo,Money,Cliente) VALUES(?,?,?)");
            
            miComando.setString(1, nuevaCuenta.tipo);
            miComando.setString(2, nuevaCuenta.moneda);
            
            miComando.execute();
           
            System.out.println("Cuenta guardada");
                            
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (miConexion != null) {
                    miConexion.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());               
            }
        }
        
    }
    
    public static ArrayList<Cuentas> leerCuentasBase()
    {
        ArrayList<Cuentas> listaCuentas = new ArrayList<>();
        Cuentas cuentaLeida;
                
        String cadenaConexion = "jdbc:sqlite:" + ruta;
        
        Connection miConexion = null;
        PreparedStatement miComando;
        ResultSet miLector;
                
        try 
        {      
            miConexion = DriverManager.getConnection(cadenaConexion);
            System.out.println("Conexion establecida");
            
            miComando = miConexion.prepareStatement("SELECT * FROM Cuentas;");
            miLector = miComando.executeQuery();
           
            while (miLector.next())
            {
                cuentaLeida= new Cuentas();
                cuentaLeida.tipo = miLector.getString(1);
                cuentaLeida.moneda = miLector.getString(2);
                listaCuentas.add(cuentaLeida);
            }
                            
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (miConexion != null) {
                    miConexion.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());               
            }
        }
                        
        return listaCuentas; 
    }
    
    public static boolean crearBase()
    {
        String cadenaConexion = "jdbc:sqlite:" + ruta;
        
        Connection miConexion = null;
        PreparedStatement miComando;
        
        String superComando="";
        try
        {      
            miConexion = DriverManager.getConnection(cadenaConexion);
             
            miComando = miConexion.prepareStatement(superComando);
            
            superComando="CREATE TABLE Clientes (\n" +
                        "	NoCliente 	INTEGER PRIMARY KEY\n" +
                        "	,Nombre 	VARCHAR(100)	NOT NULL\n" +
                        "	,ApellidoP 	VARCHAR(100)	NOT NULL\n" +
                        "	,ApellidoM 	VARCHAR(100)\n" +
                        "	,FechaNac 	DATETIME\n" +
                        "	,Sexo 		CHAR CHECK(Sexo IN('M','F')));\n"; 
             miComando.execute(); 
                    
            superComando="CREATE TABLE Cuentas(\n" +
                        "	NoCuenta 	INTEGER PRIMARY KEY\n" +
                        "	,Tipo 		VARCHAR(10) CHECK (Tipo IN ('AHORROS','CHEQUES','NOMINA'))\n" +
                        "			DEFAULT 'AHORROS'\n" +
                        "	,Saldo 	MONEY DEFAULT 0\n" +
                        "	,Moneda 	CHAR(3)  CHECK(Moneda IN ('MXN','USD','EUR'))\n" +
                        "			DEFAULT 'MXN'\n" +
                        "	,Cliente 	INT\n" +
                        "	,Estatus 	BIT DEFAULT 1\n" +
                        "	,FOREIGN KEY(Cliente) REFERENCES Clientes(NoCliente)\n" ;
            miComando.execute();
            
            superComando="CREATE TABLE Tarjetas(\n" +
                        "	NoTarjeta 	INTEGER PRIMARY KEY\n" +
                        "	,NIP 	CHAR(4) DEFAULT '1234' NOT NULL\n" +
                        "	,Codigo 	CHAR(3) DEFAULT '000' NOT NULL\n" +
                        "	,FechaExp 	DATETIME\n" +
                        "	,Cuenta 	INT\n" +
                        "	,Estatus 	BIT DEFAULT 1\n" +
                        "	,FOREIGN KEY(Cuenta) REFERENCES Cuentas(NoCuenta)\n";
              miComando.execute(); 
            
            superComando="CREATE TABLE Movimientos(\n" +
                        "	IdMov 		INTEGER PRIMARTY KEY\n" +
                        "	,NumCuenta 	INT\n" +
                        "	,Abono 		MONEY DEFAULT 0\n" +
                        "	,Cargo 		MONEY DEFAULT 0\n" +
                        "	,Descripcion 	VARCHAR(100) 	DEFAULT 'Movimiento en Ventanilla'	\n" +
                        "	,FechaMov	DATETIME\n" +
                        "	,FOREIGN KEY(NumCuenta) REFERENCES Cuentas(NoCuenta)\n"; 
            miComando.execute();
            System.out.println("Base Creada");
                            
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return false;
        } 
        finally 
        {
            try 
            {
                if (miConexion != null) {
                    miConexion.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());               
            }
        }
        
        return true;
    }
    
}
