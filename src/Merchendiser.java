public class Merchendiser implements Seller {
    public String sell(Goods goods) {
        String res = "";
                if (goods == Goods.POTION) {
                res = "potion";
        }
        return res;

    }

    public enum Goods {
        POTION

    }
}
