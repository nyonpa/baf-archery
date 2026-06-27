package com.archery.tournament.model;

public enum TournamentType {
    BF1(1),
    BF2(2),
    BF3(3),
    BF4(4),
    BF5(5),
    BF6(6),
    BF7(7),
    BF8(8),
    BF9(9),
    BF10(10),
    BF11(11),
    BF12(12);

    private final int archerNumber;
     TournamentType(int archerNumber) {

        this.archerNumber = archerNumber;
    }
    public int getArcherNumber() {
         return archerNumber;
    }
}
