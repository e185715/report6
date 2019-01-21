package jp.ac.uryukyu.ie.e185715;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


class Solver {
    String[] cards = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    List<String> deck = new ArrayList<String>(Arrays.asList(cards));
    String[] itemList = {"リセット", "ゴール27", "ドロー1", "ドロー2", "ドロー3",
            "ドロー4", "ドロー5", "ドロー6", "ドロー7", "ドロー8", "ドロー9", "ドロー10", "ドロー11"};
    List<String> item = new ArrayList<String>(Arrays.asList(itemList));
    String[] infomation = {"勝ち負け判定せず、次のラウンドへ進む", "これを使ったラウンドの間勝利条件を21から27にする", "山札に存在するとき、1を引く", "山札に存在するとき、2を引く"
            , "山札に存在するとき、3を引く", "山札に存在するとき、4を引く", "山札に存在するとき、5を引く", "山札に存在するとき、6を引く", "山札に存在するとき、7を引く",
            "山札に存在するとき、8を引く", "山札に存在するとき、9を引く", "山札に存在するとき、10を引く", "山札に存在するとき、11を引く"};
    List<String> info = new ArrayList<String>(Arrays.asList(infomation));
    List<String> player_hands = new ArrayList<>(), enemy_hands = new ArrayList<>();
    List<String> player_effect = new ArrayList<>(), enemy_effect = new ArrayList<>();
    List<String> player_item = new ArrayList<>(), enemy_item = new ArrayList<>();
    boolean turn, battle = true;
    int num, goal, damage, input, player_sum, enemy_sum;
    Scanner scan = new Scanner(System.in);
    int player_hp = 7, enemy_hp = 7;
    boolean player_ready, enemy_ready = false;
    Main main = new Main();

    void check_rotate() { //プレイ順番を決めるメソッド
        input = Integer.parseInt(scan.nextLine());
        switch (input) {
            case 1:
                turn = true;
                break;
            case 2:
                turn = false;
                break;
            case 3:
                input = ((int) Math.random() * 2);
                if (input == 1) {
                    turn = false;
                } else {
                    turn = true;
                }
        }
    }

    void initialize() {//山札手札の初期化
        deck = new ArrayList<String>(Arrays.asList(cards));
        player_hands.clear();
        enemy_hands.clear();
        //player_effect.clear();
        //enemy_effect.clear();
        goal = 21;
        //getItem();
        for (int i = 0; i < 4; i++) {
            draw();
            changeTurn();
        }
    }

    void changeTurn() {
        if (turn == true) {
            turn = false;
        } else {
            turn = true;
        }
    }

    List<String> getHand() {//引いたカードを、引いた人の手札に加えさせるためのboolean操作
        if (turn == true) {
            return player_hands;
        } else {
            return enemy_hands;
        }
    }


    //List<String> set_Itemlist() {
    //    if (turn == true) {
    //        return player_item;
    //    } else {
    //        return enemy_item;
    //    }
    //}

    //List<String> getEffect() {
    //    if (turn == true) {
    //        return player_effect;
    //    } else {
    //        return enemy_effect;
    //    }
    //}

    void draw() {//山札からカードを1枚引く
        num = (int) (Math.random() * deck.size());
        getHand().add(deck.get(num));
        deck.remove(deck.get(num));
    }

    //void getItem() {//お互いにアイテムをランダムでひとつ入手するメソッド
    //num = (int) (Math.random() * item.size());
    //player_item.add(item.get(num));
    //    num = (int) (Math.random() * item.size());
    //    enemy_item.add(item.get(num));
    //}

    void quit() {
        System.out.println("対戦を終了する？\n続けるなら1を入力してね");
        input = Integer.parseInt(scan.nextLine());
        if (input == 1) {
            player_turn();
        } else {
            battle = false;
        }
    }

    //void item_use() {
    //getEffect().add(set_Itemlist().get(input - 1));
    //    set_Itemlist().remove(set_Itemlist().get(input - 1));
    //    changeTurn();
    //}

    void Sum() {
        for (int x = 0; x < getHand().size(); x++) {
            if (turn == true) {
                player_sum += Integer.parseInt(getHand().get(x));
            } else {
                enemy_sum += Integer.parseInt(getHand().get(x));
            }
        }
    }

    void player_turn() {//プレーヤーのターン時の画面
        player_ready = false;
        String[] action = {"1:アイテムリスト, 2:カードを引く, 3:これでしょうぶ！"};
        System.out.println("---あなたのターンです！---\n自分の手札" + player_hands + "\nあなたの番だよ！どうする？(番号を入力してね)\n" + Arrays.asList(action));
        input = Integer.parseInt(scan.nextLine());
        switch (input) {
            case 2:
                draw();
                player_sum = 0;
                Sum();
                System.out.println(player_hands.get(player_hands.size() - 1) + "を引いた！\nあなたの手札" + player_hands + "\n合計=" + player_sum+"\n");
                changeTurn();
                break;
            case 3:
                changeTurn();
                player_ready = true;
                break;
            default:
                quit();
        }
    }

    void enemy_turn() {
        enemy_ready = false;
        System.out.println("---CPUのターン---\nCPUの手札に" + enemy_hands.get(0) + "があります！");
        enemy_sum = 0;
        Sum();
        if (enemy_sum < 16) {
            draw();
            System.out.print("CPUはカードを引いた\nCPUの枚数は"+enemy_hands.size()+"\n");
            changeTurn();
            } else {
            enemy_ready = true;
            changeTurn();
            }
        if (enemy_ready == true) {
            System.out.print("CPUはこれでしょうぶ！\n");
        }
    }

    void judge() {
        if (player_sum > goal && enemy_sum > goal) {
            if (player_sum > enemy_sum) {
                player_hp -= damage;
            } else {
                enemy_hp -= damage;
            }
        }
        if(player_sum<goal&&enemy_sum>goal){
            enemy_hp-=damage;
        }else if(player_sum>goal&&enemy_sum<goal){
            player_hp-=damage;
        }
        if(player_sum<goal&&enemy_sum<goal){
            if(player_sum>enemy_sum){
                enemy_hp-=damage;
            }else{
                player_hp-=damage;
            }
        }
        if (player_sum == enemy_sum) {
            System.out.println("引き分け");
            initialize();
            player_ready = false;
            enemy_ready = false;
        }
        System.out.println("あなたの手札:" + player_hands + "プレイヤーの合計" + player_sum + "\tCPUの手札:" + enemy_hands +"CPUの合計" + enemy_sum + "\nプレイヤーのHP:" + player_hp + "\tCPUのHP" + enemy_hp + "\n");
    }
}


