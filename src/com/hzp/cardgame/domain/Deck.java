package com.hzp.cardgame.domain;

import sun.text.CollatorUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 卡组
 */
public class Deck {

	private List<Card> cards;

	/**
	 * 当前牌堆剩余的卡
	 */
	private List<Card> leftCards;

	/**
	 * 被取走的卡
	 */
	private List<Card> takenCards;

	public Deck(){
		cards = new ArrayList<>();
		Card.FrenchSuit[] frenchSuits = Card.FrenchSuit.values();
		List<Card.Figure> legalPoint = Card.legalPoint;
		for(Card.FrenchSuit suit:frenchSuits){
			for(Card.Figure figure : legalPoint){
				cards.add(new Card(figure,suit));
			}
		}
		leftCards = new ArrayList<>();
		leftCards.addAll(cards);
		takenCards = new ArrayList<>();
		shuffle();
	}

	/**
	 * 洗牌,将所有取走的牌放入剩余牌堆，
	 */
	public void shuffle(){
		leftCards.addAll(takenCards);
		takenCards.clear();
		Collections.shuffle(leftCards);
	}

	/**
	 * 取走牌顶的卡
	 * @return
	 */
	public Card takeTopCard(){
		if(leftCards.size() == 0){
			return null;
		}
		Card card = leftCards.remove(0);
		takenCards.add(card);
		return card;
	}

	/**
	 * 获取剩余卡牌数量
	 * @return
	 */
	public int getLeftCardNum(){
		return leftCards.size();
	}
}
