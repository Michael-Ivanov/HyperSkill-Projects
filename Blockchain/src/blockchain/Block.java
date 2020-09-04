package blockchain;

import java.util.Date;

public class Block {
    private int id;
    private long timeStamp;
    private String prevHash;
    private String hash;

    public Block(int id, String prevBlockHash) {
        this.id = id;
        this.prevHash = prevBlockHash;
        this.timeStamp = new Date().getTime();
        hash = new StringUtil().applySha256(getAllFields());
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    private String getAllFields() {
        return "" + id + timeStamp + prevHash;
    }

    @Override
    public String toString() {
        return  "Block: \n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timeStamp + "\n" +
                "Hash of the previous block: \n" + prevHash + "\n" +
                "Hash of the block: \n" + hash + "\n";
    }
}
