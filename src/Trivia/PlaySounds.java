/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Yosef
 */
public class PlaySounds extends Thread{
 private static AudioPlayer MGP=AudioPlayer.player;
 private static AudioStream BGM;
 private static AudioData MD;
 private static AudioFileFormat sound;
 private static final int BUFFER_SIZE = 128000;
 private static File soundFile;
 private static AudioInputStream audioStream;
 private static AudioFormat audioFormat;
 private static SourceDataLine sourceLine;
 private String file;
 public static String correctSound="C:\\Users\\יוסף\\Documents\\NetBeansProjects\\Irit\\Question\\src\\Trivia\\Sounds\\CorrectSound.wav";
 public static String worngSound="C:\\Users\\יוסף\\Documents\\NetBeansProjects\\Irit\\Question\\src\\Trivia\\Sounds\\Wrong Buzzer.wav";
 
// /**
//  * Plays the sound of the sent file name
//  * @param file Audio File's path
//  */
 public PlaySounds(String file)
 {
     super("PlaySounds");
     this.file = file;
     start();
 }
 
 public void run()
 {
     String strFilename = file;

     try {
         soundFile = new File(strFilename);
     } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
     }

     try {
         audioStream = AudioSystem.getAudioInputStream(soundFile);
     } catch (Exception e){
        e.printStackTrace();
        System.exit(1);
     }

     audioFormat = audioStream.getFormat();

     DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
     try {
         sourceLine = (SourceDataLine) AudioSystem.getLine(info);
         sourceLine.open(audioFormat);
     } catch (LineUnavailableException e) {
         e.printStackTrace();
         System.exit(1);
     } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
     }

     sourceLine.start();

     int nBytesRead = 0;
     byte[] abData = new byte[BUFFER_SIZE];
     while (nBytesRead != -1) {
         try {
             nBytesRead = audioStream.read(abData, 0, abData.length);
         } catch (IOException e) {
             e.printStackTrace();
         }
         if (nBytesRead >= 0) {
             @SuppressWarnings("unused")
             int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
         }
     }
     sourceLine.drain();
     sourceLine.close();
     this.stop();
 }

}
