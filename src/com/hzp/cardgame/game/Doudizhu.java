package com.hzp.cardgame.game;

import com.hzp.cardgame.domain.Card;
import com.hzp.cardgame.domain.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 简化版斗地主，每个人一次只能出一张牌，下一个人的出牌必须比上一个玩家出的牌打，否则只能跳过，
 * 如果一个玩家打的牌过了一轮还是没有人出牌，那么该玩家可以出任意牌
 * 最先把牌出完的玩家胜利。
 * 自动进行，玩家只出最小的牌，每个玩家在可以出牌的情况下都会出牌
 */
public class Doudizhu extends Game{

    public Doudizhu(int playerCount) {
        super(playerCount);
    }



    /**
     * 上一个出牌的玩家
     */
    private Player prePlayer;

    /**
     * 上一张出的牌
     */
    private Card preCard;


    /**
     * 只能允许3人玩
     * @return
     */
    @Override
    List<Integer> legalPlayCountList() {
        return Collections.singletonList(3);
    }

    @Override
    protected String getName() {
        return "斗地主";
    }


    @Override
    public void goATurn() {
        for(Player player:players){
            if(isOver){
                continue;
            }
            if(preCard==null || prePlayer == player){
                //当前微第一轮第一个玩家，或者前一个出牌的玩家就是自己，出牌中最小的一张
                Card card = getMinPointCard(0,player.getCards());
                playCard(player,card);
            }else {
                Card card = getMinPointCard(preCard.getFigure().getPoint(),player.getCards());
                if(card == null){
                    System.out.println(player+"无牌可出，跳过这一轮");
                }else{
                    playCard(player,card);
                }
            }
            if(player.getCards().size() == 0){
                gameOver(player);
            }
        }
    }

    private void playCard(Player player,Card card){
        player.playCards(Collections.singletonList(card));
        preCard = card;
        prePlayer = player;
        System.out.println(player+"出牌"+card);
    }

    private void gameOver(Player player) {
        System.out.println(player+"打出手中所有的牌获取胜利");
        isOver = true;
        winner = player;
    }

    /**
     * 获取点数最小的牌,该牌的点数需要大于minValue
     */
    private static Card getMinPointCard(int minValue,List<Card> cards){
        if(cards.size() == 0){
            return null;
        }
        int min = Integer.MAX_VALUE;
        Card minCard = null;
        for(Card card:cards){
            int point = card.getFigure().getPoint();
            if(point < min && point > minValue){
                min = card.getFigure().getPoint();
                minCard = card;
            }
        }
        return minCard;
    }

    @Override
    public void divideCard() {
        //分牌，每个人分三张牌，最后剩下一张牌。
        while (deck.getLeftCardNum() >= 1){
            for(Player player:players){
                player.takeCard(deck);
            }
        }
    }

}
