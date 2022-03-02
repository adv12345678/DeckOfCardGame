package com.hzp.cardgame.game;

import com.hzp.cardgame.domain.Card;
import com.hzp.cardgame.domain.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 比大小，分牌时每个玩家分的三张牌，三张牌点数的和取余10（J、Q、K算作10点）得到当前点数，点数最大的玩家取得游戏胜利。
 * 如果有2名或者2名以上的玩家的点数相同，则比这些玩家点数小的玩家被淘汰，剩余玩家从牌堆中牌继续比，直到选出最终的胜者
 * 如果牌堆剩余牌不足以进行下一轮游戏，则该局游戏无赢家
 */
public class Bidaxiao extends Game{

    /**
     * 剩余玩家玩家
     */
    private List<Player> leftPlayers;

    public Bidaxiao(int playerCount) {
        super(playerCount);
        leftPlayers = new ArrayList<>(players);
    }


    @Override
    List<Integer> legalPlayCountList() {
        return Arrays.asList(2,3,4);
    }

    @Override
    protected String getName() {
        return "比大小";
    }

    @Override
    public void goATurn() {
        int maxPoint = getCurrentMaxPoint();
        printCardState(leftPlayers);
        System.out.println("当前最大点数为"+maxPoint);
        Iterator<Player> iterator =  leftPlayers.iterator();
        //迭代剩余玩家，把手牌点数小于最大点数的玩家淘汰
        while (iterator.hasNext()){
            Player player = iterator.next();
            List<Card> cards = player.getCards();
            int totalPoint = getTotalPoint(cards);
            player.playCards(cards);
            if(totalPoint < maxPoint){
                System.out.println(player+"当前点数为"+totalPoint+"，小于最大点数,被淘汰");
                iterator.remove();
            }
        }
        //判断是否有玩家胜利
        if(leftPlayers.size() == 1){
            System.out.println(leftPlayers.get(0)+"取得胜利");
            isOver = true;
            winner = leftPlayers.get(0);
            return;
        }
        //牌堆不够分，这种情况应该很少见
        if(!canDivideCard()){
            System.out.println("当前牌堆不足以进行下一轮游戏，游戏结束");
            isOver = true;
            return;
        }
        divideCard();
    }

    /**
     * 获取这轮最大的点数
     * @return
     */
    private int getCurrentMaxPoint() {
        int maxPoint = -1;
        for(Player player:leftPlayers){
            List<Card> cards = player.getCards();
            int totalPoint = getTotalPoint(cards);
            if(totalPoint > maxPoint){
                maxPoint = totalPoint;
            }
        }
        return maxPoint;
    }

    private static int getTotalPoint(List<Card> cards) {
        int totalPoint = 0;
        for(Card card:cards){
            if(card.getFigure().getPoint() > 10){
                totalPoint+=10;
            }else {
                totalPoint+=card.getFigure().getPoint();
            }
        }
        return totalPoint%10;
    }

    @Override
    public void divideCard() {
        for(int i=0;i<3;i++){
            for(Player player:leftPlayers){
                player.takeCard(deck);
            }
        }
    }

    /**
     * 剩余牌是否足够分牌
     * @return
     */
    private boolean canDivideCard(){
        return deck.getLeftCardNum() >= leftPlayers.size()*3;
    }

    @Override
    protected void startHook() {
        leftPlayers.clear();
        leftPlayers.addAll(players);
    }
}
