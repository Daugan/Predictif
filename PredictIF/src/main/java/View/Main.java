/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Dao.JpaUtil;

/**
 *
 * @author Romain
 */
public class Main {
    
    public static void main(String args[]) {
        JpaUtil.init();
        
        System.out.println("Hello World!");
    }
    
}
