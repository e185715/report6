package jp.ac.uryukyu.ie.e185715;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolverTest {

    @Test
    public void initialize() {
        Solver sol = new Solver();
        sol.deck = new ArrayList<String>(Arrays.asList(sol.cards));
        for (int i = 0; i < 4; i++) {
            sol.draw();
            sol.changeTurn();
        }
        assertEquals(sol.player_hands.size(), 2);
    }

}