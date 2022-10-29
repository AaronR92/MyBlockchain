package com.aaronr92;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class Block {

    private String hash;

    private final String previousHash;

    private final String data;

    private final long timestamp;

    private int nonce;

    public Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return Util.applyHash(previousHash + data + timestamp + nonce);
    }

    public void mineBLock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        System.out.println("Target is " + target);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined! : " + hash);
    }
}
