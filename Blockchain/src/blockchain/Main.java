package blockchain;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        for (int i = 0; i < 10; i++) {
            blockchain.generateNewBlock();
        }
        List<Block> chain = blockchain.getChain();
        for (int i = 0; i < 5; i++) {
            System.out.println(chain.get(i).toString());
        }
    }
}
