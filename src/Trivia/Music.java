/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;
import java.io.*;
import java.net.URL;
import sun.audio.*;

/**
 *
 * @author Yosef
 */

public class Music implements java.applet.AudioClip {
  private AudioData audiodata;

  private AudioDataStream audiostream;

  private ContinuousAudioDataStream continuousaudiostream;

  static int length;

  public Music(URL url) throws java.io.IOException {
    audiodata = new AudioStream(url.openStream()).getData();
    audiostream = null;
    continuousaudiostream = null;
  }

  public Music(String filename) throws java.io.IOException {
    FileInputStream fis = new FileInputStream(filename);
    AudioStream audioStream = new AudioStream(fis);
    audiodata = audioStream.getData();
    audiostream = null;
    continuousaudiostream = null;
  }

  public void play() {
    audiostream = new AudioDataStream(audiodata);
    AudioPlayer.player.start(audiostream);
  }

  public void loop() {
    continuousaudiostream = new ContinuousAudioDataStream(audiodata);
    AudioPlayer.player.start(continuousaudiostream);
  }

  public void stop() {
    if (audiostream != null)
      AudioPlayer.player.stop(audiostream);
    if (continuousaudiostream != null)
      AudioPlayer.player.stop(continuousaudiostream);
  }
}