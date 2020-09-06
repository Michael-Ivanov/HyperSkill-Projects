package blockchain;

import java.util.Date;

public class Block {
    private int id;
    private long timeStamp;
    private String data;
    private String prevHash;
    private String hash;
    private long magicNumber;

    public Block(int id, String data, String prevBlockHash, long magicNumber) {
        this.id = id;
        this.data = data;
        this.prevHash = prevBlockHash;
        this.timeStamp = new Date().getTime();
        this.magicNumber = magicNumber;
        hash = new StringUtil().applySha256(getAllFields());
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

    @Override
    public String toString() {
        return  "Block: \n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timeStamp + "\n" +
                "Magic number: " + magicNumber + "\n" +
                "Hash of the previous block: \n" + prevHash + "\n" +
                "Hash of the block: \n" + hash + "\n";
    }
}
