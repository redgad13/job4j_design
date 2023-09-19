package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

class SoccerPlayerTest {

    @Test
    public void whenMethodIsIncorrect() {
        FootballPlayer sp = new SoccerPlayer("Ivan", false);
        sp.playGame();
        FootballPlayer fp = new FootballPlayer("John", false);
        fp.playGame();
    }
}