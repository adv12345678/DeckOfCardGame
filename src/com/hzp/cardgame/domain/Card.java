package com.hzp.cardgame.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 卡牌
 */
public class Card {
	/**
	 * 卡牌点数
	 */
	private Figure figure;

	/**
	 * 最大点数  11 12 13 对应点数 J Q K
	 */
	public final static int MAX_POINT = 13;

	/**
	 * 最小点数
	 */
	public final static int MIN_POINT = 1;

	/**
	 * 卡牌图片
	 */
	private FrenchSuit frenchSuit;

	/**
	 * 合法点数
	 */
	public static List<Figure> legalPoint;
	static {
		legalPoint = new ArrayList<>();
		for(int i=MIN_POINT;i<=MAX_POINT;i++){
			legalPoint.add(new Figure(i));
		}
	}

	/**
	 * 卡牌图片
	 */
	enum FrenchSuit {
		CLUEB,DIAMOND,HEART,SPADE
	}

	/**
	 * 点数
	 */
	public static class Figure{
		/**
		 * 对应大小
		 */
		int point;

		public String getName(){
			if(point<=10  && point>=1){
				return String.valueOf(point);
			}
			switch (point){
				case 11:
					return "J";
				case 12:
					return "Q";
				case 13:
					return "K";
			}
			return null;
		}
		private Figure(int point){
			this.point = point;
		}

		@Override
		public String toString() {
			return getName();
		}

		public int getPoint(){
			return point;
		}
	}


	public Card(Figure figure, FrenchSuit frenchSuit) {
		this.figure = figure;
		this.frenchSuit = frenchSuit;
	}

	@Override
	public String toString() {
		switch (this.frenchSuit){
			case CLUEB:
				return "梅花"+figure;
			case HEART:
				return "红桃"+figure;
			case SPADE:
				return "黑桃"+figure;
			case DIAMOND:
				return "方块"+figure;
		}
		return null;
	}

	public Figure getFigure() {
		return figure;
	}
}
