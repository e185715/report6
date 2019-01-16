package jp.ac.uryukyu.ie.e185715;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Solver{
    String[] cards = new String[] {"1","2","3","4","5","6","7","8","9","10","11"};
    List<String> deck = new ArrayList<String>(Arrays.asList(cards));
    String[] itemList = {"ドロー1","ドロー2","ドロー3","ドロー4","ドロー5","ドロー6","ドロー7","ドロー8","ドロー9","ドロー10","ドロー11","シールド","リセット","リムーブ","ダメージUP","ゴール24","ゴール27"};
    List<String> item = new ArrayList<String>(Arrays.asList(itemList));
    List<String> p1_hands= new ArrayList<>(),enemy_hands = new ArrayList<>();
    boolean turn=true,battle=true;
    int num,goal,damage;
    List<String> p1_item,enemy_item;
    Scanner in = new Scanner(System.in);

    void initialize(){//山札手札の初期化
        deck = new ArrayList<String>(Arrays.asList(cards));
        p1_hands.clear();
        enemy_hands.clear();
        System.out.println(p1_hands);
        damage += 1;
        goal = 21;
        for (int i=0;i<2;i++){
            turn = true;
            draw();
            turn = false;
            draw();
        }
    }

    List<String> handring(){//引いたカードを、引いた人の手札に加えさせるためのboolean操作
        if (this.turn == true){
            return this.p1_hands;
        }else{
            return this.enemy_hands;
        }
    }

    void draw(){//山札からカードを1枚引く
        num = (int)(Math.random()*deck.size());
        p1_hands.add(deck.get(num));
        deck.remove(deck.get(num));
    }

    void getItem(){//お互いにアイテムをランダムでひとつ入手する
        num = (int)Math.random()*item.size();
        p1_item.add(item.get(num));
        num = (int)Math.random()*item.size();
        enemy_item.add(item.get(num));
    }


}