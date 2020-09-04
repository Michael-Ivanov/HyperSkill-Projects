package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.LinkedList;

public class Blockchain {

    private LinkedList<Block> chain;

    public Blockchain() {
        chain = new LinkedList<>();
    }

    public void generateNewBlock() {
        Block newBlock;
        if (chain.size() == 0) {
            newBlock = new Block(1, "0");
        } else {
            Block prevBlock = chain.get(chain.size() - 1);
            newBlock = new Block(chain.size() + 1, prevBlock.getHash());
        }
        chain.add(newBlock);
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
