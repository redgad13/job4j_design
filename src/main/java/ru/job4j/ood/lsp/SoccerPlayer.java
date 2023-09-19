package ru.job4j.ood.lsp;

public class SoccerPlayer extends FootballPlayer {

    public SoccerPlayer(String name, boolean playWithLegs) {
        super(name, playWithLegs);
    }

    @Override
    public void playGame() {
        if (!playWithLegs) {
            System.out.println("Playing soccer");
        }
    }
}
