package com.company;

import com.company.enums.Brand;
import com.company.enums.Ganre;
import com.company.enums.Rating;
import com.company.innerclass.GameConsole;
import com.company.nested_static_class.Game;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class PlayRoom {
    public static void main(String[] args) {
        Game.GameDisk[] gameDisks = {
                Game.getDisk("FIFA 2024", Ganre.SPORT, "One of the best game for football"),
                Game.getDisk("Dune", Ganre.ACTION, "One of the best game"),
                Game.getDisk("Uncharted", Ganre.RACE, "Uncharted game chapter 2"),
                Game.getDisk("Spider men", Ganre.ACTION, "Game about the spider-men"),
        };
        Game.VirtualGame[] virtualGames = {
                Game.getVirtualGame("Dune", Ganre.ACTION),
                Game.getVirtualGame("Spider men", Ganre.ACTION),
                Game.getVirtualGame("FIFA 2024", Ganre.SPORT),
                Game.getVirtualGame("Uncharted", Ganre.RACE),
        };

        // инициализация рандомно рейтинга
        for (Game.VirtualGame virtual : virtualGames) {
            int randNum = new Random().nextInt(Rating.values().length);
            virtual.setRating(Rating.values()[randNum]);
        }

        // сортировка GameDisk по жанру
        Comparator<Game.GameDisk> genreComparator = new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk gd1, Game.GameDisk gd2) {
                return gd1.getGameData().getGanre().compareTo(gd2.getGameData().getGanre());
            }
        };
        Arrays.sort(gameDisks, genreComparator);
        for (Game.GameDisk disk : gameDisks) {
            Game gameData = disk.getGameData();
            System.out.println(gameData.getName() + " - " + gameData.getGanre());
        }

        // сортировка VirtualGame по рейтингу
        Comparator<Game.VirtualGame> ratingComparator = new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame vg1, Game.VirtualGame vg2) {
                return Integer.compare(vg1.getRating().getValue(), vg2.getRating().getValue());
            }
        };
        Arrays.sort(virtualGames, ratingComparator);
        System.out.println("-".repeat(30));
        for (Game.VirtualGame virtual : virtualGames) {
            Game gameData = virtual.getGameData();
            System.out.println(gameData.getName() + " - " + gameData.getGanre() + " - " + virtual.getRating().getValue());
        }

        // игра
        System.out.println("-".repeat(30));
        GameConsole console = new GameConsole(Brand.MICROSOFT, "DAN_123");
        console.setOn(true);
        console.turnOnFirstGamepad();
        console.turnOnSecondGamepad();
        console.loadGame(gameDisks[0].getGameData());
        console.playGame();
        console.playGame();
        /*console.playGame();
        console.playGame();
        console.playGame();
        console.playGame();
        console.playGame();*/
    }
}
