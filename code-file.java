/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.util.Map;

/**
 *
 * @author abc
 */
public class Player56674959 extends NimPlayer {

    public Player56674959(String name) {
        super(name);
    }

    public static int calculateNimSum(int piles[]) {
        int nimsum = 0;
        for (int i = 0; i < piles.length; i++) {
            nimsum = piles[i] ^ nimsum;
        }
        return nimsum;
    }

    public static boolean isOddNumberOfOnes(int piles[]) {
        int NumberOfOnes = 0;
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] == 1) {
                NumberOfOnes += 1;
            }
        }
        if (NumberOfOnes % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isAllOnes(int piles[]) {
        boolean allOne = true;
        for (int i = 0; i < piles.length && allOne == true; i++) {
            if (piles[i] > 1) {
                allOne = false;
            }
        }
        return allOne;
    }

    @Override
    public Map.Entry<Integer, Integer> nextMove(int[] piles) {

        int pile, removeStones;
        boolean found = false;

        for (pile = 0; pile < piles.length && found == false; pile++) {
            for (removeStones = 1; removeStones <= piles[pile]; removeStones++) {
                piles[pile] -= removeStones;
                if (isAllOnes(piles) && isOddNumberOfOnes(piles)) {
                    return Map.entry(pile, removeStones);
                } else {
                    if (calculateNimSum(piles) != 0) {
                        piles[pile] += removeStones; //nimum is non zero, re-calculate
                    } else { //nimsum is 0
                        if (isAllOnes(piles) == true && isOddNumberOfOnes(piles) == false) { // 1 or 11 or 111

                            if (piles[pile] != 0) {
                                piles[pile] -= removeStones; //remove one more stone to make it 0
                                removeStones += 1; //update the number of stones removed
                            }
                            return Map.entry(pile, removeStones);

                        } else {
                            return Map.entry(pile, removeStones);
                        }
                    }
                }
            }
        }

        //if nimsum approach fails altogether, just remove one stone from the first non empty pile
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] != 0) {
                return Map.entry(i, 1);
            }
        }
        return Map.entry(0, 0);
    }
}
