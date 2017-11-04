package br.edu.ifsp.ingresso;

import javax.persistence.EntityManager;

import br.edu.ifsp.ingresso.conn.FactoryEntityManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        EntityManager em = FactoryEntityManager.getEntityManager();
        
    }
}
