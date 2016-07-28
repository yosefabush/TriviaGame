/*Autor: Yuceli Polanco*/

package Trivia;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 *this class activate the sound in the game
 * @author Yosef
 */
public class MusicClass 
{
    
    FileInputStream FIS;
    BufferedInputStream BIS;
    
    public Player player;
    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;
    /**
    *
    * Stop
    */
    public void Stop(){
        if(player != null){
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
    public void Pause() throws IOException{
        if(player != null){
            try{
               pauseLocation =  FIS.available();
               player.close();
              
            }
            catch(IOException ex){
                
            }
           
        }
    }
    /**
    *
    * Play
    */
    public void Play(String path){
        
        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            songTotalLength = FIS.available();
            fileLocation = path + "";
            
        } catch (FileNotFoundException  | JavaLayerException ex ) {
            
        } catch (IOException ex) {
            Logger.getLogger(MusicClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                    
                    if(player.isComplete() && SelectGame1.count == 1){
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
    public void Resume(){
        
        try {
            FIS = new FileInputStream(fileLocation);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            FIS.skip(songTotalLength - pauseLocation);
    
        } catch (FileNotFoundException  | JavaLayerException ex ) {
            
        } catch (IOException ex) {
            Logger.getLogger(MusicClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                } catch (JavaLayerException ex) {
                    
                }
            }
        }.start();
        
    }
    
   public void setVouloum(int vol){
    
    }
    
}
