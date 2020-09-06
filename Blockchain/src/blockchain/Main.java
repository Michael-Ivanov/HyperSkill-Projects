package blockchain;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must start with: ");
        int zeroes = scanner.nextInt();
        LinkedList<Block> blockChain = new LinkedList<>();
        BlockchainService blockchainService = new BlockchainService(blockChain, zeroes);
        for (int i = 0; i < 5; i++) {
            long timeStart = System.currentTimeMillis();
            Block block = blockchainService.generateNewBlock();
            long timeStop = System.currentTimeMillis();
            blockChain.add(block);
            System.out.print(block.toString());
            System.out.println("Block was generating for " +
                    (timeStop - timeStart) / 1000 + " seconds\n");
        }
        /*for (int i = 0; i < 5; i++) {
            System.out.println(blockChain.get(i).toString());
        }*/
    }
}
