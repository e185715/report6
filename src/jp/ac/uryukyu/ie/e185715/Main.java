package jp.ac.uryukyu.ie.e185715;

public class Main {
    public static void main(String[] args) {
        String[] intro = {"Twenty-Oneへようこそ！　ルール説明はする？", "Twenty-Oneはカードを1枚ずつ引いていき,合計を21にすることを目指すゲームだよ。",
                "山札は1〜11が1枚ずつ、初期手札2枚とアイテム1つが勝負のはじめに互いに配られるよ。", "お互いの1枚目の手札は見ることが出来て、アイテムは使い捨てだけど自分の番に何回も使えるよ。",
                "初期体力はお互い7,勝負に負けると、ラウンドの数だけ体力が減るよ。", "相手の体力を減らしきれば勝ちだよ！ルール説明は以上！", "各コマンドに割り当てられた数字を入力すると行動できて、それ以外の入力でゲームやめることが出来るよ。", "先行後攻を選んでね。"};

        Solver sol = new Solver();
        sol.check_rotate();

        while (sol.battle) {
            while (sol.player_ready == false || sol.enemy_ready == false) {
                sol.initialize();
                if (sol.turn == true) {

                    sol.getItem();
                    sol.player_turn();
                }
            }
        }
    }
}








