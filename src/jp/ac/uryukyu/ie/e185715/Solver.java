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
    List<String> player_hands= new ArrayList<>(),enemy_hands = new ArrayList<>();
    boolean turn=true;
    int num,goal,damage;
    List<String> player_item= new ArrayList<>(),enemy_item = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    String input,select;


    void initialize(){//山札手札の初期化
        deck = new ArrayList<String>(Arrays.asList(cards));
        player_hands.clear();
        enemy_hands.clear();
        damage += 1;
        goal = 21;
        for (int i=0;i<4;i++){
            draw();
        }
    }

    List<String> handring(){//引いたカードを、引いた人の手札に加えさせるためのboolean操作
        if (turn == true){
            turn = false;
            return player_hands;
        }else{
            turn = true;
            return enemy_hands;
        }
    }

    void draw(){//山札からカードを1枚引く
        num = (int)(Math.random()*deck.size());
        handring().add(deck.get(num));
        deck.remove(deck.get(num));
    }

    void getItem(){//お互いにアイテムをランダムでひとつ入手する
        num = (int)(Math.random()*item.size());
        player_item.add(item.get(num));
        num = (int)(Math.random()*item.size());
        enemy_item.add(item.get(num));
    }

    void player_turn(){//プレーヤーのターン時の画面
        String[] action = {"1:アイテム, 2:カードを引く, 3:これでしょうぶ！"};
        System.out.println("自分の手札"+player_hands+"\nあなたの番だよ！どうする？(番号を入力してね)\n"+Arrays.asList(action));
        input = scan.nextLine();
        switch(input){
            case "1":
            case "アイテム":
                for(int i=0;i<player_item.size();i++)
                    System.out.print((i+1) +":" + player_item.get(i)+"\t");

                break;
            case "2":
            case "カードを引く":
                draw();
                break;

        }
    }
}