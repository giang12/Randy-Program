import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class RandomDice extends JLabel
{
   private  ImageIcon image;
   private  URL[]imageList={getClass().getResource("dice/dice1.png"),getClass().getResource("dice/dice2.png"),
      getClass().getResource("dice/dice3.png"),
                              getClass().getResource("dice/dice4.png"),
      								getClass().getResource("dice/dice5.png"),
      								getClass().getResource("dice/dice6.png")};
   //private  JLabel label1;
   private JButton button1=new JButton();
   int width,height;
   public RandomDice(int w, int h,int n)
   {
      width=(int)(w/((Math.sqrt(n))+1));
      height=(int)(h/((Math.sqrt(n))+1));
      RandomMethod(imageList);
      button1.addActionListener(new ButtonHit());
      button1.setBorderPainted( false );
      button1.setContentAreaFilled( false ); 
      setImage();
   }
   /*public RandomDice()
   {
      width=300;
      height=150;
      RandomMethod(imageList);
   
   }*/
	
   private void RandomMethod(URL[] imageList) 
   
   {
      Collections.shuffle(Arrays.asList(imageList));
      Integer imageNum = (int) (Math.random() * imageList.length);
      image = new ImageIcon(imageList[imageNum]);
      Image img=image.getImage();
      Image newimg=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      image=new ImageIcon(newimg);
      repaint();
   }
   private void setImage()
   {button1.setIcon(image);}
   @Override
   public void paintComponent (Graphics g)
   { 
      super.paintComponent (g);
      if(image != null)
         image.paintIcon(this	, g, 0, 0);
   }
   public JButton getButton()
   {
      return button1;}
   public  class  ButtonHit implements ActionListener
   { 
      @Override
      public void actionPerformed(ActionEvent ae)
      { 
         playSound("sounds/shakerolldice3.wav");
         RandomMethod(imageList);
         setImage();
      }
   }
   public synchronized void playSound(final String url) {
      if(RandomFace.offSound==false)
         try {
             //read audio data from whatever source (file/classloader/etc.)
            InputStream audioSrc = RandomFace.class.getResourceAsStream(url);
         //add buffer for mark/reset support
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
         
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); 
         } 
         catch (Exception e) {
            System.err.println(e.getMessage());
         }
               
            
   }
}