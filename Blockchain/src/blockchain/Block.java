package blockchain;

import java.util.Date;

public class Block {
    private int id;
    private long timeStamp;
    private long timeToCreate;
    private String data;
    private String prevHash;
    private String hash;
    private long magicNumber;
    private String nState;

    public Block(int id, String data, String prevBlockHash, long magicNumber) {
        this.id = id;
        this.data = data;
        this.prevHash = prevBlockHash;
        this.timeStamp = new Date().getTime();
        this.magicNumber = magicNumber;
        hash = new StringUtil().applySha256(getAllFields());
    }

    public int getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    private String getAllFields() {
        return "" + id + timeStamp + data + prevHash + magicNumber;
    }

    public void setTimeToCreate(long timeToCreate) {
        this.timeToCreate = timeToCreate;
    }

    public void setnState(String nState) {
        this.nState = nState;
    }

    @Override
    public String toString() {
        String threadName = Thread.currentThread().getName();
        return  "Block: \n" +
                "Created by miner # " + threadName.substring(threadName.indexOf("thread-") + 7) + "\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timeStamp + "\n" +
                "Magic number: " + magicNumber + "\n" +
                "Hash of the previous block: \n" + prevHash + "\n" +
                "Hash of the block: \n" + hash + "\n" +
                "Block was generating for " + timeToCreate / 1000 + " seconds\n" +
                nState + "\n\n";
    }
}
