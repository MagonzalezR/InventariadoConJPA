/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemainventario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class SistemaInventario {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static String nameC = "SistemaInventarioPU";
    
    public static void main(String[] args) {
        
        Controlador cont= new Controlador();
//        initEntityManager();
//        MuebleJpaController controlMueble= new MuebleJpaController(emf);
//        Mueble mueble=new Mueble(1, "Silla ejecutiva", "Silla", 35000);
//        controlMueble.create(mueble);
//        InventarioempresaJpaController controlInventario= new InventarioempresaJpaController(emf);
//        Inventarioempresa inventario= new Inventarioempresa(1, 0, 500, 500);
//        controlInventario.create(inventario);
//        closeEntityManager();
    }
    
    
    
}
