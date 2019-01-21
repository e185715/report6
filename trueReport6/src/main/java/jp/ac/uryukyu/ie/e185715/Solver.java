package jp.ac.uryukyu.ie.e185715;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


class Solver {
    String[] cards = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    List<String> deck = new ArrayList<String>(Arrays.asList(cards));
    List<String> player_hands = new ArrayList<>(), enemy_hands = new ArrayList<>();
    boolean turn, battle = true;
    int num, goal, damage, input, player_sum, enemy_sum;
    Scanner scan = new Scanner(System.in);
    int player_hp = 7, enemy_hp = 7;
    boolean player_ready, enemy_ready = false;

    /**
     * boolean turnによってどちらの番か決める
     */
    void check_rotate() {
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

    /**
     * 山札、手札の初期化
     * @see draw()
     * @see changeTurn()
     */
    void initialize() {
        deck = new ArrayList<String>(Arrays.asList(cards));
        player_hands.clear();
        enemy_hands.clear();
        goal = 21;
        for (int i = 0; i < 4; i++) {
            draw();
            changeTurn();
        }
    }

    /**
     *turnの真偽値の入れ替え
     */
    void changeTurn() {
        if (turn == true) {
            turn = false;
        } else {
            turn = true;
        }
    }

    /**
     *手番プレイヤーのリストを返し、CPUもカードを入手出来る
     * @return 手番プレイヤーの手札リスト
     */

    List<String> getHand() {
        if (turn == true) {
            return player_hands;
        } else {
            return enemy_hands;
        }
    }

    /**
     *山札から1枚選んで手札にするメソッド
     * @param deck 山札
     */
    void draw() {
        num = (int) (Math.random() * deck.size());
        getHand().add(deck.get(num));
        deck.remove(deck.get(num));
    }

    /**
     * 想定外入力が来た時に対戦を中止できるようにするメソッド
     */
    void quit() {
        System.out.println("対戦を終了する？\n続けるなら1を入力してね");
        input = Integer.parseInt(scan.nextLine());
        if (input == 1) {
            player_turn();
        } else {
            battle = false;
        }
    }

    /**
     * それぞれの手札を合計するメソッド
     */
    void Sum() {
        for (int x = 0; x < getHand().size(); x++) {
            if (turn == true) {
                player_sum += Integer.parseInt(getHand().get(x));
            } else {
                enemy_sum += Integer.parseInt(getHand().get(x));
            }
        }
    }

    /**
     * プレイヤーが手番の時の処理
     * @param player_ready 手札を増やさずにターンを終了するとtrue
     *
     */

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

    /**
     * CPUターンの時の処理
     * 手札の合計が16以下の時、山札を引く
     */
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

    /**
     * どちらが勝ったか手札をそれぞれ合計するメソッド
     * ダメージも与える
     * 合計が同じ場合は手札が少ない方が勝利
     */
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