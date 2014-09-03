  //Giang Nguyen email:nggiang12@gmail.com
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*; 

public class RandomPic extends JLabel
{static boolean picReplace=true;   
   private  ImageIcon image;
   private Image img,newimg;
   private  File[]imageList;
   private  JLabel label0;
   public RandomPic() 
   
   {
      ImageIcon errorimg =new ImageIcon(getClass().getResource("notFound1.gif"));
      label0=new JLabel("",errorimg,JLabel.CENTER);
   }
   public RandomPic(String path)
   {
      File my_dir = new File(path);
      
      if(my_dir.isDirectory()) // and is actually a directory
      {
         imageList= my_dir.listFiles(new Filter());}
      if(imageList.length==0)//no images found error
      {
         RandomFace.playSound("sounds/error.wav");
         ImageIcon errorimg =new ImageIcon(getClass().getResource("notFound1.gif"));
         label0=new JLabel("",errorimg,JLabel.CENTER);}
   }

   @Override
   public void paintComponent (Graphics g)
   { 
      super.paintComponent (g);
      if(image != null)
         image.paintIcon(this	, g, 350, 150);
   }

   public  class Filter implements FilenameFilter
   {
      @Override
      public boolean accept(File dir, String name)
      {
         File testhidden = new File(dir.getPath()+"/"+name);
         if(!testhidden.isHidden())
         {
            if (name != null &&
            name.toLowerCase().endsWith(".jpeg")|| name.toLowerCase().endsWith(".png")||name.toLowerCase().endsWith(".gif")
            ||name.toLowerCase().endsWith(".jpg"))
            {
               return true;
            }
            else
            {
               return false;
            }
         }
         else 
            return false;
      }
   }

   public void RandomMethod() 
   
   {
      Collections.shuffle(Arrays.asList(imageList));
      Integer imageNum = (int) (Math.random() * imageList.length);
      if(imageList.length==0)
      {JOptionPane.showMessageDialog(null,
                     "OOps, here are some possible errors:\n"+
            			"1.Run out of images for non-replacement=>reset(under setting)\n"+
            			"2.Selected file does not exist(may have been moved) or\n"+
            			"3.File is not a directory or \n"+
            			"4.File does not contain any images\n"+
            			"Check and Try again^^",
                     "Uh Oh,dont panic!", JOptionPane.INFORMATION_MESSAGE);}
      image = new ImageIcon(imageList[imageNum].toString());
      img=image.getImage();
      if(image.getIconWidth()>RandomFace.frame.getWidth()&&image.getIconHeight()>RandomFace.frame.getHeight())
      {newimg=img.getScaledInstance(RandomFace.frame.getWidth(), RandomFace.frame.getHeight(), Image.SCALE_SMOOTH);}
      else if(image.getIconWidth()>RandomFace.frame.getWidth())
      {newimg=img.getScaledInstance(RandomFace.frame.getWidth(), image.getIconHeight(), Image.SCALE_SMOOTH);}
      else if(image.getIconHeight()>RandomFace.frame.getHeight())
      {newimg=img.getScaledInstance(image.getIconWidth(), RandomFace.frame.getHeight(), Image.SCALE_SMOOTH);}
      else
      {newimg=img;}
      image=new ImageIcon(newimg);
      label0=new JLabel(image);
      repaint();
      if(picReplace==false)
      {RemoveItem(imageNum);}
   }
   public JLabel getLabel()
   {
      return label0;}
   public void RemoveItem(int index)
   {File[] localImageList=new File[imageList.length-1];int a=0;
      for(int i=0;i<imageList.length;i++)
      {
         if(i!=index)
         {
            localImageList[a]=imageList[i];
            a++;
         }
      }
      imageList=new File[localImageList.length];
      imageList=localImageList;
   }
   public void setReplace(boolean a)
   {
      picReplace=a;}
   public void ResetImageList(String path)
   {File my_dir = new File(path);
      
      if(my_dir.isDirectory()) // and is actually a directory
      {
         imageList= my_dir.listFiles(new Filter());}
      if(imageList.length==0)//no images found error
      {
         RandomFace.playSound("sounds/error.wav");
         ImageIcon errorimg =new ImageIcon(getClass().getResource("notFound1.gif"));
         label0=new JLabel("",errorimg,JLabel.CENTER);}}
}