package jp.ac.uryukyu.ie.e185715;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Solver{
    String[] cards = new String[] {"1","2","3","4","5","6","7","8","9","10","11"};
    List<String> deck = new ArrayList<String>(Arrays.asList(cards));
    List<String> p1_hands,enemy_hands;
    boolean turn,battle;
    int num,damage = 0;
    String[] action = {"アイテム","カードを引く","これでしょうぶ！"};
    List<String> p1_item,enemy_item;

    void initialize(){//山札手札の初期化
        deck = new ArrayList<String>(Arrays.asList(cards));
        p1_hands.clear();
        enemy_hands.clear();
        damage += 1;
    }

    void draw(){//山札からカードを1枚引く
        num = (int)Math.random()*deck.size();
        if(this.turn == true){
            p1_hands.add(String.valueOf(num));
        } else{
            enemy_hands.add(String.valueOf(num));
        }
        deck.remove(num);
    }

    void item(){
        System.out.println(p1_item);
        

    }



}