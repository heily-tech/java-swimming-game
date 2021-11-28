package game.swimming.res.sfxs;

import game.swimming.MainActivity;
import sun.applet.Main;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.InputStream;

public class backgroundMusic {
    private static InputStream input;
    private static AudioStream bgm;
    private static AudioPlayer player = AudioPlayer.player;
    static String path = MainActivity.class.getResource("").getPath();

    public backgroundMusic(String filename) {
        input = MainActivity.class.getResourceAsStream(filename);
    }
    public static void play() {
        try {
            bgm = new AudioStream(input);
            player.start(bgm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void change(String filename) {
        input = MainActivity.class.getResourceAsStream(filename);
    }
    public static void stop() {
        player.stop(bgm);
    }
}