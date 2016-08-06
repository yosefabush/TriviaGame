/*Autor: Yosef*/
package Trivia;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * this class activate the sound in the game
 *
 * @author Yosef
 */
public class MusicClass {

    FileInputStream FIS;
    BufferedInputStream BIS;

    public Player player;
    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;
    private static LinkedList<Line> speakers = new LinkedList<Line>();

    /**
     *
     * Stop
     */
    public void Stop() {
        if (player != null) {
            player.close();

            pauseLocation = 0;
            songTotalLength = 0;

            //  MP3PlayerGUI.Display.setText("");
        }
    }

    /**
     *
     * Pause
     */
    public void Pause() throws IOException {
        if (player != null) {
            try {
                pauseLocation = FIS.available();
                player.close();

            } catch (IOException ex) {

            }

        }
    }

    /**
     *
     * Play
     */
    public void Play(String path) {

        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);

            player = new Player(BIS);
            songTotalLength = FIS.available();
            fileLocation = path + "";

        } catch (FileNotFoundException | JavaLayerException ex) {

        } catch (IOException ex) {
            Logger.getLogger(MusicClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();

                    if (player.isComplete() && SelectGame.count == 1) {
                        Play(fileLocation);
                    }
                } catch (JavaLayerException ex) {

                }
            }
        }.start();

    }

    /**
     *
     * Resume
     */
    public void Resume() {

        try {
            FIS = new FileInputStream(fileLocation);
            BIS = new BufferedInputStream(FIS);

            player = new Player(BIS);
            FIS.skip(songTotalLength - pauseLocation);

        } catch (FileNotFoundException | JavaLayerException ex) {

        } catch (IOException ex) {
            Logger.getLogger(MusicClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException ex) {

                }
            }
        }.start();

    }

    /**
     *
     * set volume
     */
    public void setVouloum(float vol) {
        
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        //System.out.println("There are " + mixers.length + " mixer info objects");
        for (Mixer.Info mixerInfo : mixers) {
          //  System.out.println("mixer name: " + mixerInfo.getName());
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo(); // target, not source
            for (Line.Info lineInfo : lineInfos) {
            //    System.out.println("  Line.Info: " + lineInfo);
                Line line = null;
                boolean opened = true;
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    if (!opened) {
                        line.open();
                    }
                    FloatControl volCtrl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    volCtrl.setValue(1);
                    System.out.println("    volCtrl.getValue() = " + volCtrl.getValue());
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException iaEx) {
                    System.out.println("    " + iaEx);
                } finally {
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }

//        if (player instanceof JavaSoundAudioDevice)
//        {
//            JavaSoundAudioDevice jsAudio = (JavaSoundAudioDevice) player;
//            jsAudio.setLineGain(vol);
//        }
            //Info source = Port.Info.SPEAKER;
//            if (player != null) {
//                // player.volume(vol);
//                // player.
//
//            }
//        }
    }
}
    
