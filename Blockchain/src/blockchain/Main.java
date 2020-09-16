package blockchain;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Block> blockChain = new LinkedList<>();
        BlockchainService blockchainService = new BlockchainService(blockChain, 0);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                Block newBlock = blockchainService.generateNewBlock();
                blockChain.add(newBlock);
                System.out.println(newBlock.toString());
            });
            Thread.sleep(1000);
        }
        executor.shutdown();

    }
}
