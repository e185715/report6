package jp.ac.uryukyu.ie.e185715;

public class Main {
    public static void main(String[] args) {
        System.out.println("Twenty-Oneへようこそ！　ルール説明はする？\n1:はい！\t2:いいえ");
        Solver sol = new Solver();
        sol.input = Integer.parseInt(sol.scan.nextLine());
        if (sol.input==1){
            System.out.print(sol.Intro);
        }
        System.out.print("\n先攻後攻を選んでね。\n1:先攻\t2:後攻\t3:おまかせ\n");
        sol.check_rotate();

        while (sol.battle) {
            sol.initialize();
            sol.damage+=1;
            System.out.print("--ラウンド"+sol.damage+"--\n");
            while (sol.player_ready == false || sol.enemy_ready == false) {
                if (sol.turn == true) {
                    sol.player_turn();
                }else{
                    sol.enemy_turn();
                }
            }
            sol.judge();
            sol.player_ready=false;
            sol.enemy_ready=false;
            if(sol.player_hp<=0){
                System.out.print("Game Set!! CPUの勝ち。");
                sol.battle=false;
            }else if (sol.enemy_hp<=0){
                System.out.print("Game Set!! あなたの勝ち!!!!");
                sol.battle=false;
            }
        }
    }
}