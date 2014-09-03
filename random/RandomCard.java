import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
public class RandomCard extends JLabel
{  
   private  URL[]sourceList={
      getClass().getResource("cards/clubs-10-150.png"),
      getClass().getResource("cards/clubs-2-150.png"),
      getClass().getResource("cards/clubs-3-150.png"),
      getClass().getResource( "cards/clubs-4-150.png"),
      getClass().getResource("cards/clubs-5-150.png"),
      getClass().getResource("cards/clubs-6-150.png"),
      getClass().getResource("cards/clubs-7-150.png"),
      getClass().getResource("cards/clubs-8-150.png"),
      getClass().getResource("cards/clubs-9-150.png"),
      getClass().getResource("cards/clubs-a-150.png"),
      getClass().getResource("cards/clubs-j-150.png"),
      getClass().getResource("cards/clubs-k-150.png"),
      getClass().getResource("cards/clubs-q-150.png"),
      getClass().getResource("cards/diamonds-10-150.png"),
      getClass().getResource("cards/diamonds-2-150.png"),
      getClass().getResource("cards/diamonds-3-150.png"),
      getClass().getResource("cards/diamonds-4-150.png"),
      getClass().getResource("cards/diamonds-5-150.png"),
      getClass().getResource("cards/diamonds-6-150.png"),
      getClass().getResource("cards/diamonds-7-150.png"),
      getClass().getResource("cards/diamonds-8-150.png"),
      getClass().getResource("cards/diamonds-9-150.png"),
      getClass().getResource( "cards/diamonds-a-150.png"),
      getClass().getResource("cards/diamonds-j-150.png"),
      getClass().getResource("cards/diamonds-k-150.png"),
      getClass().getResource("cards/diamonds-q-150.png"),
      getClass().getResource("cards/hearts-10-150.png"),
      getClass().getResource("cards/hearts-2-150.png"),
      getClass().getResource("cards/hearts-3-150.png"),
      getClass().getResource("cards/hearts-4-150.png"),
      getClass().getResource("cards/hearts-5-150.png"),
      getClass().getResource("cards/hearts-6-150.png"),
      getClass().getResource("cards/hearts-7-150.png"),
      getClass().getResource("cards/hearts-8-150.png"),
      getClass().getResource("cards/hearts-9-150.png"),
      getClass().getResource("cards/hearts-a-150.png"),
      getClass().getResource("cards/hearts-j-150.png"),
      getClass().getResource("cards/hearts-k-150.png"),
      getClass().getResource( "cards/hearts-q-150.png"),
      getClass().getResource("cards/spades-10-150.png"),
      getClass().getResource("cards/spades-2-150.png"),
      getClass().getResource("cards/spades-3-150.png"),
      getClass().getResource("cards/spades-4-150.png"),
      getClass().getResource("cards/spades-5-150.png"),
      getClass().getResource("cards/spades-6-150.png"),
      getClass().getResource("cards/spades-7-150.png"),
      getClass().getResource("cards/spades-8-150.png"),
      getClass().getResource("cards/spades-9-150.png"),
      getClass().getResource("cards/spades-a-150.png"),
      getClass().getResource("cards/spades-j-150.png"),
      getClass().getResource("cards/spades-k-150.png"),
      getClass().getResource("cards/spades-q-150.png")};
   private URL[] clubs={
      getClass().getResource("cards/clubs-10-150.png"),
      getClass().getResource("cards/clubs-2-150.png"),
      getClass().getResource("cards/clubs-3-150.png"),
      getClass().getResource( "cards/clubs-4-150.png"),
      getClass().getResource("cards/clubs-5-150.png"),
      getClass().getResource("cards/clubs-6-150.png"),
      getClass().getResource("cards/clubs-7-150.png"),
      getClass().getResource("cards/clubs-8-150.png"),
      getClass().getResource("cards/clubs-9-150.png"),
      getClass().getResource("cards/clubs-a-150.png"),
      getClass().getResource("cards/clubs-j-150.png"),
      getClass().getResource("cards/clubs-k-150.png"),
      getClass().getResource("cards/clubs-q-150.png")};
   private URL[] diamonds={
      getClass().getResource("cards/diamonds-10-150.png"),
      getClass().getResource("cards/diamonds-2-150.png"),
      getClass().getResource("cards/diamonds-3-150.png"),
      getClass().getResource("cards/diamonds-4-150.png"),
      getClass().getResource("cards/diamonds-5-150.png"),
      getClass().getResource("cards/diamonds-6-150.png"),
      getClass().getResource("cards/diamonds-7-150.png"),
      getClass().getResource("cards/diamonds-8-150.png"),
      getClass().getResource("cards/diamonds-9-150.png"),
      getClass().getResource( "cards/diamonds-a-150.png"),
      getClass().getResource("cards/diamonds-j-150.png"),
      getClass().getResource("cards/diamonds-k-150.png"),
      getClass().getResource("cards/diamonds-q-150.png"),};
   private URL[] hearts={
      getClass().getResource("cards/hearts-10-150.png"),
      getClass().getResource("cards/hearts-2-150.png"),
      getClass().getResource("cards/hearts-3-150.png"),
      getClass().getResource("cards/hearts-4-150.png"),
      getClass().getResource("cards/hearts-5-150.png"),
      getClass().getResource("cards/hearts-6-150.png"),
      getClass().getResource("cards/hearts-7-150.png"),
      getClass().getResource("cards/hearts-8-150.png"),
      getClass().getResource("cards/hearts-9-150.png"),
      getClass().getResource("cards/hearts-a-150.png"),
      getClass().getResource("cards/hearts-j-150.png"),
      getClass().getResource("cards/hearts-k-150.png"),
      getClass().getResource( "cards/hearts-q-150.png"),};
   private URL[] spades={
      getClass().getResource("cards/spades-10-150.png"),
      getClass().getResource("cards/spades-2-150.png"),
      getClass().getResource("cards/spades-3-150.png"),
      getClass().getResource("cards/spades-4-150.png"),
      getClass().getResource("cards/spades-5-150.png"),
      getClass().getResource("cards/spades-6-150.png"),
      getClass().getResource("cards/spades-7-150.png"),
      getClass().getResource("cards/spades-8-150.png"),
      getClass().getResource("cards/spades-9-150.png"),
      getClass().getResource("cards/spades-a-150.png"),
      getClass().getResource("cards/spades-j-150.png"),
      getClass().getResource("cards/spades-k-150.png"),
      getClass().getResource("cards/spades-q-150.png")};
   private URL[] red={
      getClass().getResource("cards/diamonds-10-150.png"),
      getClass().getResource("cards/diamonds-2-150.png"),
      getClass().getResource("cards/diamonds-3-150.png"),
      getClass().getResource("cards/diamonds-4-150.png"),
      getClass().getResource("cards/diamonds-5-150.png"),
      getClass().getResource("cards/diamonds-6-150.png"),
      getClass().getResource("cards/diamonds-7-150.png"),
      getClass().getResource("cards/diamonds-8-150.png"),
      getClass().getResource("cards/diamonds-9-150.png"),
      getClass().getResource( "cards/diamonds-a-150.png"),
      getClass().getResource("cards/diamonds-j-150.png"),
      getClass().getResource("cards/diamonds-k-150.png"),
      getClass().getResource("cards/diamonds-q-150.png"),
      getClass().getResource("cards/hearts-10-150.png"),
      getClass().getResource("cards/hearts-2-150.png"),
      getClass().getResource("cards/hearts-3-150.png"),
      getClass().getResource("cards/hearts-4-150.png"),
      getClass().getResource("cards/hearts-5-150.png"),
      getClass().getResource("cards/hearts-6-150.png"),
      getClass().getResource("cards/hearts-7-150.png"),
      getClass().getResource("cards/hearts-8-150.png"),
      getClass().getResource("cards/hearts-9-150.png"),
      getClass().getResource("cards/hearts-a-150.png"),
      getClass().getResource("cards/hearts-j-150.png"),
      getClass().getResource("cards/hearts-k-150.png"),
      getClass().getResource( "cards/hearts-q-150.png"),};
   private URL[] black={
      getClass().getResource("cards/clubs-10-150.png"),
      getClass().getResource("cards/clubs-2-150.png"),
      getClass().getResource("cards/clubs-3-150.png"),
      getClass().getResource( "cards/clubs-4-150.png"),
      getClass().getResource("cards/clubs-5-150.png"),
      getClass().getResource("cards/clubs-6-150.png"),
      getClass().getResource("cards/clubs-7-150.png"),
      getClass().getResource("cards/clubs-8-150.png"),
      getClass().getResource("cards/clubs-9-150.png"),
      getClass().getResource("cards/clubs-a-150.png"),
      getClass().getResource("cards/clubs-j-150.png"),
      getClass().getResource("cards/clubs-k-150.png"),
      getClass().getResource("cards/clubs-q-150.png"),
      getClass().getResource("cards/spades-10-150.png"),
      getClass().getResource("cards/spades-2-150.png"),
      getClass().getResource("cards/spades-3-150.png"),
      getClass().getResource("cards/spades-4-150.png"),
      getClass().getResource("cards/spades-5-150.png"),
      getClass().getResource("cards/spades-6-150.png"),
      getClass().getResource("cards/spades-7-150.png"),
      getClass().getResource("cards/spades-8-150.png"),
      getClass().getResource("cards/spades-9-150.png"),
      getClass().getResource("cards/spades-a-150.png"),
      getClass().getResource("cards/spades-j-150.png"),
      getClass().getResource("cards/spades-k-150.png"),
      getClass().getResource("cards/spades-q-150.png")};
		

   private boolean replace=true;
   private boolean allcards=true;
   private boolean allred=false;
   private boolean allblack=false;
   private boolean club=false;
   private boolean diamond=false;
   private boolean heart=false;
   private boolean spade=false;

   private  ImageIcon image;

   private  URL[] imageList;
   int width,height;
   int tempw,temph,tempn;
   private int buttonnum=0;
   private JButton button[]=new JButton[10000];
 
   public void ResetImageList()
   {
      if(allcards==true)
         imageList=sourceList;
      else if(allred==true)
         imageList=red;
      else if(allblack==true)
         imageList=black;
      else if(club==true)
         imageList=clubs;
      else if(diamond==true)
         imageList=diamonds;
      else if(heart==true)
         imageList=hearts;
      else if(spade==true)
         imageList=spades;
   
   }

   public RandomCard(int w, int h,int n)
   { ResetImageList();
      RandomMethod(w,h,n);
      setButton();
   }
   public JButton getButton()
   {
      return button[buttonnum-1];}
   public void setButton()
   { 
      if(buttonnum>10000)
      {buttonnum=0;}
      String action=Integer.toString(buttonnum);
      button[buttonnum]=new JButton();
      button[buttonnum].setActionCommand(action);
      button[buttonnum].addActionListener(new ButtonHit());
      button[buttonnum].setBorderPainted( false );
      button[buttonnum].setContentAreaFilled( false ); 
      button[buttonnum].setIcon(image);buttonnum++;
   }
    	
   public void RandomMethod(int w, int h,int n) 
   
   {tempw=w;temph=h;tempn=n;
      width=(int)(w/((Math.sqrt(n+10))+1));
      height=(int)(h/((Math.sqrt(n))+1));
      if(imageList.length==0)
      {
         if(replace==true)
         {ResetImageList();}
         else
            
         {RandomFace.playSound("sounds/error.wav");
            JOptionPane.showMessageDialog(null,
                     "ÔøΩ All cards have been displayed, pls hit reset under setting.\n"+
               		"ÔøΩ See help for more info.",
                     "User error.", JOptionPane.INFORMATION_MESSAGE);}}
   
      Collections.shuffle(Arrays.asList(imageList));
      Integer imageNum = (int) (Math.random() * imageList.length);
      image = new ImageIcon(imageList[imageNum]);
      Image img=image.getImage();
      Image newimg=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      image=new ImageIcon(newimg);
      repaint();
      if(replace==false)
      {RemoveItem(imageNum);}
   }

   @Override
   public void paintComponent (Graphics g)
   { 
      super.paintComponent (g);
      if(image != null)
         image.paintIcon(this	, g, 0, 0);
   }
   public  class  ButtonHit implements ActionListener
   { 
      @Override
      public void actionPerformed(ActionEvent ae)
      {int target = Integer.parseInt(ae.getActionCommand());
         playSound("sounds/shuffling.wav");
         RandomMethod(tempw,temph,tempn);
         button[target].setIcon(image); 
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
   public void RemoveItem(int index)
   {URL[] localImageList=new URL[imageList.length-1];int a=0;
      for(int i=0;i<imageList.length;i++)
      {
         if(i!=index)
         {
            localImageList[a]=imageList[i];
            a++;
         }
      }
      imageList=new URL[localImageList.length];
      imageList=localImageList;
   }

   public void ResetButtonnum()
   {
      buttonnum=0;
   }
   public void setReplace(boolean a)
   {
      replace=a;}

   public void setAllCard()
   {allcards=true;
      allred=false;
      allblack=false;
      club=false;
      diamond=false;
      heart=false;
      spade=false;
   }
   public void setAllRed()
   {allcards=false;
      allred=true;
      allblack=false;
      club=false;
      diamond=false;
      heart=false;
      spade=false;
   }
   public void setAllBlack()
   {allcards=false;
      allred=false;
      allblack=true;
      club=false;
      diamond=false;
      heart=false;
      spade=false;
   }
   public void setClub()
   {allcards=false;
      allred=false;
      allblack=false;
      club=true;
      diamond=false;
      heart=false;
      spade=false;}
   public void setDiamond()
   {allcards=false;
      allred=false;
      allblack=false;
      club=false;
      diamond=true;
      heart=false;
      spade=false;}
   public void setHeart()
   {allcards=false;
      allred=false;
      allblack=false;
      club=false;
      diamond=false;
      heart=true;
      spade=false;}
   public void setSpade()
   {allcards=false;
      allred=false;
      allblack=false;
      club=false;
      diamond=false;
      heart=false;
      spade=true;}


}