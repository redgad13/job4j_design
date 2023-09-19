package ru.job4j.ood.lsp;

public class FootballPlayer {
    protected String name;
    protected boolean playWithLegs;

    public FootballPlayer(String name, boolean playWithLegs) {
        this.name = name;
        this.playWithLegs = playWithLegs;
    }

    public void playGame() {
        System.out.println("Playing football");
    }
}
