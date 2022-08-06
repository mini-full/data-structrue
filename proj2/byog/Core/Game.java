package byog.Core;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 81;
    public static final int HEIGHT = 31;

    /**
     * Method used for playing a fresh game. The game should start from the main
     * menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will
     * be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The
     * game should
     * behave exactly as if the user typed these characters into the game after
     * playing
     * playWithKeyboard. If the string ends in ":q", the same world should be
     * returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should
     * return the same
     * world. However, the behavior is slightly different. After playing with
     * "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string
     * "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the
     * saved game.
     * 
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        input = toLower(input);
        TETile[][] finalWorldFrame = null;
        char firstChar = input.charAt(0);
        if (firstChar == 'n') {
            finalWorldFrame = newGame(input);
        } else if (firstChar == 'l') {
            finalWorldFrame = loadGame(input);
        } else if (firstChar == 's') {
            finalWorldFrame = saveGame(input);
        } else if (firstChar == 'q') {
            finalWorldFrame = quitGame(input);
        } else {
            finalWorldFrame = playWithKeyboard();
        }
        return finalWorldFrame;
    }

    private void quitGame(String input) {
        System.exit(0);
    }

    private TETile[][] newGame(String input) {
        TETile[][] worldFrame = new TETile[WIDTH][HEIGHT];
        int indexOfS = input.indexOf("s");
        long seed = convertIntoSeed(input.substring(1, indexOfS));
        finalWorldFrame = generateWorld(seed);

        play(finalWorldFrame, input.substring(indexOfS + 1));
        return finalWorldFrame;
    }

    private TETile[][] loadGame(String input) {
        TETile[][] finalWorldFrame = getSavedGame();
        play(finalWorldFrame, input.substring(1));
        return finalWorldFrame;
    }

    /** Play with the given input string. */
    private void play(TETile[][] world, String playString) {
        for (int i = 0; i < playString.length(); i++) {
            switch (playString.charAt(i)) {
                case 'w':
                    Player.walkUp(world);
                    break;
                case 'a':
                    Player.walkLeft(world);
                    break;
                case 's':
                    Player.walkDown(world);
                    break;
                case 'd':
                    Player.walkRight(world);
                    break;
                case ':':
                    i++;
                    if (i < playString.length() && playString.charAt(i) == 'q') {
                        saveGame(playString.substring(i + 1));
                    }
                    break;
            }
        }
    }

    /** Play with keyboard. */
    private void play(TETile[][] world) {
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char c = Character.toLowerCase(StdDraw.nextKeyTyped());
            switch (c) {
                case 'w':
                    Player.walkUp(world);
                    ter.renderFrame(world);
                    break;
                case 'a':
                    Player.walkLeft(world);
                    ter.renderFrame(world);
                    break;
                case 's':
                    Player.walkDown(world);
                    ter.renderFrame(world);
                    break;
                case 'd':
                    Player.walkRight(world);
                    ter.renderFrame(world);
                    break;
                case ':':
                    if (StdDraw.hasNextKeyTyped()) {
                        c = Character.toLowerCase(StdDraw.nextKeyTyped());
                        if (c == 'q') {
                            saveGame(world);
                            System.exit(0);
                        }
                    }
                    break;
            }
        }
    }

    private char getFirstChar() {
        char c;
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            c = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (c == 'n' || c == 'l' || c == 's' || c == 'q') {
                return c;
            }
        }
    }

    private void saveGame(TETile[][] finalWorldFrame) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
            out.writeObject(finalWorldFrame);
            out.writeObject(Player.getPos());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TETile[][] getSavedGame() {
        TETile[][] finalWorldFrame;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("savedGame.txt"));
            finalWorldFrame = in.readObject();
            Player.setPos((int[]) in.readObject());
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalWorldFrame;
    }

    private long convertIntoSeed(String input) {
        return Long.valueOf(input.toString());
    }

    private String toLower(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isUpperCase(input.charAt(i))) {
            sb.append(Character.toLowerCase(input.charAt(i)));
            } else {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }

    private TETile[][] generateWorld(long seed) {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        initiallizeWorld(world);

        Random rand = new Random(seed);

        List<Room> rooms = generateRooms(world, r, 10);
        generateHalls(world, r);
        generateConnectors(world, r, rooms);
        if (!rooms.isEmpty()) {
            carveDeadEnds(world);
        }
        carveExtraWalls(world);
        addDoorAndInitialPlayer(world, r);
        return world;
    }

    private void addDoorAndInitialPlayer(TETile[][] world, Random r) {
        List<Position> edges = new ArrayList<>();
    }
}