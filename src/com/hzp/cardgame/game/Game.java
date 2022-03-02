package com.hzp.cardgame.game;

import com.hzp.cardgame.domain.Deck;
import com.hzp.cardgame.domain.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象Game
 */
public abstract class Game {
    /**
     * 牌组所属牌
     */
    protected Deck deck;

    /**
     * 牌组玩家
     */
    protected List<Player> players;

    /**
     * 轮次
     */
    protected int turn = 1;

    /**
     * 合法的玩家数量
     *
     * @return
     */
    abstract List<Integer> legalPlayCountList();


    protected boolean isOver = false;

    protected Player winner;

    private boolean isLegalPlayerCount(int playerCount) {
        return legalPlayCountList().contains(playerCount);
    }

    public Game(int playerCount) {
        if (!isLegalPlayerCount(playerCount)) {
            throw new IllegalArgumentException("当前游戏不支持这个数量的玩家，合法玩家数量为" + legalPlayCountList());
        }
        deck = new Deck();
        players = new ArrayList<>();
        for(int i=1;i<=playerCount;i++){
            players.add(new Player("玩家" + i));
        }
    }

    /**
     * 重新开始一局游戏
     */
    public void reStartAGame() {
        init();
        startHook();
        System.out.println("游戏" + getName() + "开始");
        shuffle();
        divideCard();
        printCardState();
        while (!isOver) {
            System.out.println("第" + turn + "轮开始");
            goATurn();
            System.out.println("第" + turn + "轮结束");
            turn++;
        }
		System.out.println("游戏结束");
        Player player = winner;
        if(player == null){
			System.out.println("这局游戏没有赢家");
		}else {
			System.out.println("赢家是"+winner);
		}
    }

    protected void printCardState(){
        for(Player player:players){
            System.out.println(player+"持有手牌:"+player.getCards());
        }
    }

    public static void printCardState(List<Player> players){
        for(Player player:players){
            System.out.println(player+"持有手牌:"+player.getCards());
        }
    }

    protected void startHook() {
    }

    private  void init(){
        winner = null;
        isOver = false;
        turn = 1;
    }


    protected abstract String getName();

    /**
     * 洗牌
     */
    private void shuffle() {
        for (Player player : players) {
            player.clearCards();
        }
        deck.shuffle();
    }


    /**
     * 进行一轮
     */
    public abstract void goATurn();

    /**
     * 分牌,不同的游戏有不同的分牌规则
     */
    public abstract void divideCard();


}
