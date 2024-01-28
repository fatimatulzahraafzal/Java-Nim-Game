/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assignment2;

import java.util.Map;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author abc
 */
public class Player56674959Test {
    
    Player56674959 instance;
   
    
    @Before
    public void initialize(){
        instance = new Player56674959("");
    }
    
    
    @Test
    public void testNextMove1() {
        //7 6 2 ---> 4 6 2 (nimsum=0) : expect to remove 3 stones from pile 0
        System.out.println("test case 1");
        int[] piles = {7,6,2};
        Map.Entry<Integer, Integer> expResult1 = Map.entry(0,3); //4,6,2
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }
    
    @Test
    public void testNextMove2(){
        //4 2 1 ---> 3 2 1 (nimsum=0) : expect to remove 1 stones from pile 0
        System.out.println("test case 2");     
        int[] piles = {4,2,1};     
        Map.Entry<Integer, Integer> expResult1 = Map.entry(0,1); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }
    
    @Test
    public void testNextMove3(){
        //4 5 2 ---> 4 5 1 (nimsum=0) : expect to remove 1 stones from pile 2
        System.out.println("test case 3");
        int[] piles = {4,5,2};     
        Map.Entry<Integer, Integer> expResult1 = Map.entry(2,1); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }    

    @Test
    public void testNextMove4(){
        //4 2 4 ---> 4 0 4 (nimsum=0) : expect to remove 2 stones from pile 1
        System.out.println("test case 4");
        int[] piles = {4,2,4};
        Map.Entry<Integer, Integer> expResult1 = Map.entry(1,2); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }    
    
    @Test
    public void testNextMove5(){
        //1 2 1 ---> 1 1 1 (nimsum not 0 but odd number of ones) : expect to remove 1 stones from pile 1
        System.out.println("test case 5");
        int[] piles = {1,2,1};
        Map.Entry<Integer, Integer> expResult1 = Map.entry(1,1); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }    

    @Test
    public void testNextMove6(){
        //0 5 1 ---> 0 0 1 (nimsum not 0 but odd number of ones) : expect to remove 5 stones from pile 1
        System.out.println("test case 6");
        int[] piles = {0,5,1};
        Map.Entry<Integer, Integer> expResult1 = Map.entry(1,5); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }        

    @Test
    public void testNextMove7(){
        //0 0 6 ---> 0 0 1 (nimsum not 0 but odd number of ones) : expect to remove 1 stones from pile 1
        System.out.println("test case 7");
        int[] piles = {0,0,6};
        Map.Entry<Integer, Integer> expResult1 = Map.entry(2,5); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }    

    @Test
    public void testNextMove8(){
        //7 4 3 ---> 6 4 3 (nimsum cannot be calculated) : expect to remove 1 stone from the first non empty pile
        System.out.println("test case 8");
        int[] piles = {7,4,3};
        Map.Entry<Integer, Integer> expResult1 = Map.entry(0,1); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }    
    
    @Test
    public void testNextMove9(){
        //0 2 2 ---> 0 1 2 (nimsum cannot be calculated) : expect to remove 1 stone from the first non empty pile
        System.out.println("test case 9");
        int[] piles = {0,2,2};
        Map.Entry<Integer, Integer> expResult1 = Map.entry(1,1); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }   
    
    @Test
    public void testNextMove10(){
        //this is not really an important test case because nimplayer will never be called if there are 0 stones left
        //when 0 stones are left, the game finishes automatically
        //however, i wrote this so my code is compatible with methods written by other coders too.
        System.out.println("test case 10");
        int[] piles = {0,0,0};
        Map.Entry<Integer, Integer> expResult1 = Map.entry(0,0); 
        Map.Entry<Integer, Integer> result1 = instance.nextMove(piles);
        assertEquals(expResult1, result1);
    }        
    
}
