package com.aaronr92;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    static List<Block> blockchain = new ArrayList<>();
    public static final int difficulty = 6;

    public static void main(String[] args) {
        blockchain.add(new Block("The first block", "0"));
        System.out.println("Trying to Mine block 1...");
        blockchain.get(0).mineBLock(difficulty);

        blockchain.add(new Block("Just second block", blockchain.get(blockchain.size() - 1).getHash()));
        System.out.println("Trying to Mine block 2...");
        blockchain.get(1).mineBLock(difficulty);

        blockchain.add(new Block("Yo third block is here!", blockchain.get(blockchain.size() - 1).getHash()));
        System.out.println("Trying to Mine block 3...");
        blockchain.get(2).mineBLock(difficulty);

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe blockchain : ");
        System.out.println(blockchainJson);
    }

    static boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block current = blockchain.get(i);
            Block previous = blockchain.get(i - 1);
            if (!Objects.equals(current.getHash(), current.calculateHash())) {
                System.err.println("Current hashes are not equal!");
                return false;
            }
            if (!Objects.equals(previous.getHash(), previous.calculateHash())) {
                System.err.println("Previous hashes are not equal!");
                return false;
            }
        }
        return true;
    }
}

