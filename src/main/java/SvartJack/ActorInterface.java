package SvartJack;

public interface ActorInterface {

    public Hand getHand();
    public int getHandvalue();
    public void hit(Card card);
    public boolean isBust();
    
}
