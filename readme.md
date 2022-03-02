卡牌游戏项目

有两种游戏，斗地主和比大小

斗地主（Doudizhu）规则如下

允许游玩人数:3

每个人一次只能出一张牌，下一个人的出牌必须比上一个玩家出的牌打，否则只能跳过，
如果一个玩家打的牌过了一轮还是没有人出牌，那么该玩家可以出任意牌 ，最先把牌出完的玩家胜利。
自动进行，玩家只出最小的牌，每个玩家在可以出牌的情况下都会出牌

比大小（Bidaxiao）规则如下

允许游玩人数:2、3、4

分牌时每个玩家分的三张牌，三张牌点数的和取余10（J、Q、K算作10点）得到当前点数，点数最大的玩家取得游戏胜利。
如果有2名或者2名以上的玩家的点数相同，则比这些玩家点数小的玩家被淘汰，剩余玩家从牌堆中牌继续比，直到选出最终的胜者
如果牌堆剩余牌不足以进行下一轮游戏，则该局游戏无赢家

示例代码

```java
//输入参数代表游戏游玩人数
Game game = new Doudizhu(3);
game.reStartAGame();
```

