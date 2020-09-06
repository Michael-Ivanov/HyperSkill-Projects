package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.Random;

public class BlockchainService {

    private LinkedList<Block> chain;
    private int zeroes;

    public BlockchainService(LinkedList<Block> chain, int zeroes) {
        this.chain = chain;
        this.zeroes = zeroes;
    }

    public Block generateNewBlock() {
        Block newBlock = null;
        long magicNum;
        boolean search = true;

        String prevBlockHash;
        int id;
        if (chain.size() == 0) {
            id = 1;
            prevBlockHash = "0";
        } else {
            id = chain.size() + 1;
            prevBlockHash = chain.get(chain.size() - 1).getHash();
        }
        while (search) {
            magicNum = new Random().nextLong();
            newBlock = new Block(id, "some data", prevBlockHash, magicNum);
            if (newBlock.getHash().startsWith(getZeroes(zeroes))) {
                search = false;
            }
        }
        return newBlock;
    }

    private String getZeroes(int zeroes) {
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
