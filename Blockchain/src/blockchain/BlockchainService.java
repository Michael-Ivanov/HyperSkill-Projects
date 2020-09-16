package blockchain;

import java.util.LinkedList;
import java.util.Random;

public class BlockchainService {

    private volatile LinkedList<Block> chain;
    private volatile int zeroes;
    private volatile int reqId;

    public BlockchainService(LinkedList<Block> chain, int zeroes) {
        this.chain = chain;
        this.zeroes = zeroes;
    }

    public Block generateNewBlock() {
        Block newBlock;
        long magicNum;
        String prevBlockHash = "0";
        int id;
        long timeStart = System.currentTimeMillis();

        while (true) {

            if (!chain.isEmpty()) {
                prevBlockHash = chain.get(chain.size() - 1).getHash();
            }
            id = chain.size() + 1;
            magicNum = new Random().nextLong();
            newBlock = new Block(id, "some data", prevBlockHash, magicNum);
            synchronized (this) {
                if (newBlock.getHash().startsWith(getZeroesString(zeroes))) {
                    long timeStop = System.currentTimeMillis();
                    long timeToCreate = timeStop - timeStart;
                    newBlock.setTimeToCreate(timeToCreate);

                    if ((timeToCreate / 1000) < 10) {
                        zeroes += 1;
                        newBlock.setnState("N was increased to " + zeroes);
                    } else if (timeToCreate / 1000 > 60) {
                        zeroes = zeroes > 0 ? zeroes - 1 : zeroes;
                        newBlock.setnState("N was decreased by 1");
                    } else {
                        newBlock.setnState("N stays the same");
                    }

                    return newBlock;
                }
            }

        }
    }

    private String getZeroesString(int zeroes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < zeroes; i++) {
            builder.append("0");
        }
        return builder.toString();
    }

    public boolean validate() {
        String prevHash = "0";
        for (Block block : chain) {
            if (!prevHash.equals(block.getPrevHash())) {
                return false;
            }
            prevHash = block.getHash();
        }
        return true;
    }

    public LinkedList<Block> getChain() {
        return chain;
    }
}
