package com.hzp.cardgame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 玩家
 */
public class Player {
	/**
	 * 玩家当前持有的牌数
	 */
	private List<Card> currentCards;

	/**
	 * 玩家名字
	 */
	private String name;
	public Player(String name) {
		this.currentCards = new ArrayList<>();
		this.name = name;
	}


	/**
	 * 从牌组中取牌
	 * @param deck
	 */
	public void takeCard(Deck deck){
		Card card = deck.takeTopCard();
		if(card != null){
			currentCards.add(card);
		}
	}

	/**
	 * 清除手牌
	 */
	public void clearCards(){
		currentCards.clear();
	}

	/**
	 * 获取玩家当前剩余手牌
	 * @return
	 */
	public List<Card> getCards(){
		return currentCards;
	}

	/**
	 * 出牌
	 */
	public void playCards(List<Card> cards){
		currentCards.removeAll(cards);
	}

	@Override
	public String toString() {
		return name;
	}
}
