package com.hzp.cardgame;

import com.hzp.cardgame.game.Bidaxiao;
import com.hzp.cardgame.game.Doudizhu;
import com.hzp.cardgame.game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Doudizhu(3);
        game.reStartAGame();
        Game game2 = new Bidaxiao(4);
        game2.reStartAGame();
    }
}
