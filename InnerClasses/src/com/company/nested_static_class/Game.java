package com.company.nested_static_class;

import com.company.enums.Ganre;
import com.company.enums.Rating;

public class Game {
    public enum Type {
        VIRTUAL,
        PHYSICAL
    }

    public static class GameDisk {
        private final String description;
        private final Game gameData;

        private GameDisk(String name, Ganre ganre, String description) {
            this.description = description;
            this.gameData = new Game(name, ganre, Type.PHYSICAL);
        }

        public String getDescription() {
            return description;
        }

        public Game getGameData() {
            return gameData;
        }
    }

    public static class VirtualGame {
        private Rating rating;
        private final Game gameData;

        private VirtualGame(String name, Ganre ganre) {
            this.gameData = new Game(name, ganre, Type.VIRTUAL);
        }

        public Rating getRating() {
            return rating;
        }

        public Game getGameData() {
            return gameData;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }
    }


    private String name;
    Ganre ganre;
    Type type;

    private Game(String name, Ganre ganre, Type type) {
        this.name = name;
        this.ganre = ganre;
        this.type = type;
    }

    public static GameDisk getDisk(String name, Ganre ganre, String description) {
        return new GameDisk(name, ganre, description);
    }

    public static VirtualGame getVirtualGame(String name, Ganre ganre) {
        return new VirtualGame(name, ganre);
    }

    public String getName() {
        return name;
    }

    public Ganre getGanre() {
        return ganre;
    }

    public Type getType() {
        return type;
    }
}
