//Giang Nguyen email:nggiang12@gmail.com
/*************************************************************
* 1.User points to a folder contains images.�                *
* 2.Get path to folder .�                                    *
* 3.pass the pathname to RandomPic.�                         *
* 4.Create an array of images in the folder=>shuffle array   *
* =>pick out random item=>display on jframe.�                *
*************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import java.io.*;
import javax.sound.sampled.*;
import java.util.prefs.Preferences;
import javax.swing.UIManager.*;

public class RandomFace 
{ 
   static String path;
   static boolean offSound;
   static RandomPic mainpic;
   static JLabel label;
   static FilePreference filepref=new FilePreference();
   static JMenuItem lastOpen1=new JMenuItem();
   static JMenuItem lastOpen2=new JMenuItem();
   static JMenuItem lastOpen3=new JMenuItem();
   static JMenuItem lastOpen4=new JMenuItem();
   static JMenuItem lastOpen5=new JMenuItem();
   static JMenuItem lastOpen6=new JMenuItem();
   static JMenuItem lastOpen7=new JMenuItem();
   static final JFrame frame = new JFrame ("Randy");//Stochastic
   static final RandomPic error=new RandomPic();
   static final ImageIcon def =new ImageIcon(get("statman.png"));
   static final JLabel label1 = new JLabel(" ", def, JLabel.CENTER);
   static final JMenu openRecent=new JMenu("Open Recent");
   static final JSeparator seperator3=new JSeparator();
   static final JMenuItem clearMenu=new JMenuItem("Clear Menu");
   public  RandomFace()
   
   {
      playSound("sounds/hello.wav");
      filepref.loadPreference();
      JMenuBar mainbar= new JMenuBar();mainbar.setBorderPainted(false);
      JSeparator seperator=new JSeparator();JSeparator seperator1=new JSeparator();
      JSeparator seperator2=new JSeparator();
      JMenu file=new JMenu("File");
      JMenu setting=new JMenu("Setting");
      JMenu about=new JMenu("About");
      JMenuItem dice=new JMenuItem("New Dice Roller");dice.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  DiceRoller diceRoller = new DiceRoller();
               }});
                  
      JMenuItem card=new JMenuItem("New Card Shuffler");card.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  Card card1 = new Card();
               }});
      JMenuItem pascal=new JMenuItem("New Pascal Triangle");pascal.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  Pascal pascal1 = new Pascal();
               }});
   
      JMenuItem openFile=new JMenuItem("Open"); openFile.addActionListener(new Chooser());
      JMenuItem credit=new JMenuItem("Credit"); credit.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  JOptionPane.showMessageDialog(null,
                     "Copyright 2011\n" +
                     "Created By: Giang Nguyen\n"
                     ,
                     "Random Selection", JOptionPane.INFORMATION_MESSAGE);}});
      JMenuItem help=new JMenuItem("Help"); help.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  JOptionPane.showMessageDialog(null,
                     "Main window will display a random image from selected folder\n"+
                     "Click open under file to select a folder(contain images),\n"+
                     "or drag and drop a folder to main window.                   \n"+
                     "Click next button to display next random image.             \n"+
                     "Set sounds/images replacement...in setting menu.\n"+
                     "---------------------------------------------------------",
                     "Help", JOptionPane.INFORMATION_MESSAGE);}});
   
      JCheckBoxMenuItem soundoff= new JCheckBoxMenuItem("Sound off ?",false);soundoff.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {offSound=true;}
                  else
                  {offSound=false;}
               }});
      JRadioButton replacement = new JRadioButton("Replacement",true);replacement.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {RandomPic.picReplace=true;}
               }});
   
      JRadioButton nonreplacement = new JRadioButton("Non-replacement");nonreplacement.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {RandomPic.picReplace=false;}
               }});
               
      JMenuItem reset=new JMenuItem("Reset");reset.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/reset.wav");
                  mainpic.ResetImageList(path);
               }});
   
      clearMenu.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/clear.wav");
                  filepref.clearPreference();
                  filepref.loadPreference();
               }});
      lastOpen1.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  if(!(filepref.getPreference("LO1")).equals(""))
                  {frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     path=filepref.getPreference("LO1");
                     if(mainpic==null)
                     {mainpic=new RandomPic(path);}
                     else
                     {mainpic.ResetImageList(path);}
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);}
               
               }});
      lastOpen2.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  if(!(filepref.getPreference("LO2")).equals(""))
                  { frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     path=filepref.getPreference("LO2");
                     if(mainpic==null)
                     {mainpic=new RandomPic(path);}
                     else
                     {mainpic.ResetImageList(path);}
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);
                  }
               }});
      lastOpen3.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  if(!(filepref.getPreference("LO3")).equals(""))
                  {frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     path=filepref.getPreference("LO3");
                     if(mainpic==null)
                     {mainpic=new RandomPic(path);}
                     else
                     {mainpic.ResetImageList(path);}
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);
                  }
               }});
      lastOpen4.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  if(!(filepref.getPreference("LO4")).equals(""))
                  {frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     path=filepref.getPreference("LO4");
                     if(mainpic==null)
                     {mainpic=new RandomPic(path);}
                     else
                     {mainpic.ResetImageList(path);}
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);
                  }
               }});
      lastOpen5.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  if(!(filepref.getPreference("LO5")).equals(""))
                  {frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     path=filepref.getPreference("LO5");
                     if(mainpic==null)
                     {mainpic=new RandomPic(path);}
                     else
                     {mainpic.ResetImageList(path);}
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);
                  }
               }});
      lastOpen6.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  if(!(filepref.getPreference("LO6")).equals(""))
                  {frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     path=filepref.getPreference("LO6");
                     if(mainpic==null)
                     {mainpic=new RandomPic(path);}
                     else
                     {mainpic.ResetImageList(path);}
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);
                  }
               }});
      lastOpen7.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/menuclick.wav");
                  if(!(filepref.getPreference("LO7")).equals(""))
                  { frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     path=filepref.getPreference("LO7");
                     if(mainpic==null)
                     {mainpic=new RandomPic(path);}
                     else
                     {mainpic.ResetImageList(path);}
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);
                  }
               }});
   
      ButtonGroup buttongroup = new ButtonGroup();
      buttongroup.add(replacement);buttongroup.add(nonreplacement);
      mainbar.add(file);mainbar.add(setting);mainbar.add(about);
      file.add(openFile);file.add(openRecent);file.add(seperator2);file.add(dice);file.add(card);file.add(pascal);
      about.add(help);about.add(credit);
      setting.add(soundoff);setting.add(seperator);setting.add(replacement);setting.add(nonreplacement);
      setting.add(seperator1);setting.add(reset);
      frame.setSize(1000, 700);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(true);
      frame.setLocationRelativeTo(null);
      frame.getContentPane().setBackground(Color.white);
      JButton nextButton = new JButton("Next");
      nextButton.setPreferredSize(new Dimension(50, 50));
      nextButton.addActionListener(
            new ActionListener() 	{
               @Override
               public void actionPerformed(ActionEvent e)
               {
                  if(mainpic==null)
                  {  
                     frame.remove(label1);
                     playSound("sounds/error.wav");
                     frame.getContentPane().add(error.getLabel(),BorderLayout.CENTER);
                     frame.setVisible(true);}
                  else
                  { frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     playSound("sounds/nextbutton.wav");
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);}}});
      FileDrop fileDrop = new  FileDrop( frame, 
            new FileDrop.Listener()
            {   
               @Override
               public void  filesDropped(File[] files )
               {  
                  playSound("sounds/filedrop.wav");
                  File my_dir=new File(files[(files.length)-1].getPath());
                  if(my_dir.isDirectory())
                  {frame.remove(label1);
                     if(label!=null)
                     {frame.remove(label);}
                     frame.remove(error.getLabel());
                     path=my_dir.getPath();
                     if(mainpic==null)
                     {mainpic=new RandomPic(path);}
                     else
                     {mainpic.ResetImageList(path);}
                     mainpic.RandomMethod();
                     label=mainpic.getLabel();
                     frame.getContentPane().add(label,BorderLayout.CENTER);
                     frame.setVisible(true);
                     filepref.setPreference();
                     filepref.loadPreference();
                  }
                  else
                  {playSound("sounds/error.wav");}
               }  
            });
   
      frame.getContentPane().add(label1,BorderLayout.CENTER);
      frame.add(mainbar,BorderLayout.NORTH);
      frame.add(nextButton, BorderLayout.SOUTH);
      frame.validate();
      frame.setVisible(true);
   }
   public static class Chooser implements ActionListener
   {RandomPic file;
      @Override
      public void actionPerformed(ActionEvent ae)
      {
         JFileChooser chooser=new JFileChooser();
         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         int returnVal = chooser.showOpenDialog(null);
         
         if (returnVal == JFileChooser.APPROVE_OPTION) 
         {
            path=chooser.getSelectedFile().getPath();
            {
               if(mainpic==null)
               {mainpic=new RandomPic(path);}
               else
                  mainpic.ResetImageList(path);}
            if(label!=null)
            {frame.remove(label);}
            frame.remove(label1);
            frame.remove(error.getLabel());
            playSound("sounds/filedrop.wav");
            mainpic.RandomMethod();
            label=mainpic.getLabel();
            frame.getContentPane().add(label,BorderLayout.CENTER);
            frame.setVisible(true);
            filepref.setPreference();
            filepref.loadPreference();
         } 
         
      }
   }
   private static Image get(String pathName)
   {URL imgURL = RandomFace.class.getResource(pathName);
      Image image = Toolkit.getDefaultToolkit().getImage(imgURL);
      return image;}
   public static synchronized void playSound(final String url) {
      if (RandomFace.offSound==false)
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
   public static class FilePreference {
      private Preferences prefs= Preferences.userRoot().node(this.getClass().getName());
   
      String LO1="LastOpen1";
      String LO2 = "LastOpen2";
      String LO3 = "LastOpen3";
      String LO4 = "LastOpen4";
      String LO5 = "LastOpen5";
      String LO6 = "LastOpen6";
      String LO7 = "LastOpen7";
      public void setPreference() {
         prefs.put(LO7,prefs.get(LO6,""));
         prefs.put(LO6,prefs.get(LO5,""));
         prefs.put(LO5,prefs.get(LO4,""));
         prefs.put(LO4,prefs.get(LO3,""));
         prefs.put(LO3,prefs.get(LO2,""));
         prefs.put(LO2,prefs.get(LO1,""));			 
         prefs.put(LO1, path);
      
      }
      public void loadPreference()
      {
         lastOpen1.setText(new File(prefs.get(LO1,"")).getName());
         lastOpen2.setText(new File(prefs.get(LO2,"")).getName());
         lastOpen3.setText(new File(prefs.get(LO3,"")).getName());
         lastOpen4.setText(new File(prefs.get(LO4,"")).getName());
         lastOpen5.setText(new File(prefs.get(LO5,"")).getName());
         lastOpen6.setText(new File(prefs.get(LO6,"")).getName());
         lastOpen7.setText(new File(prefs.get(LO7,"")).getName());
         if(!(filepref.getPreference("LO1")).equals(""))
         {openRecent.add(lastOpen1);
            openRecent.add(seperator3);}
         if(!(filepref.getPreference("LO2")).equals(""))
         {openRecent.add(lastOpen2);
            openRecent.add(seperator3);}
         if(!(filepref.getPreference("LO3")).equals(""))
         {openRecent.add(lastOpen3);
            openRecent.add(seperator3);}
         if(!(filepref.getPreference("LO4")).equals(""))
         {openRecent.add(lastOpen4);
            openRecent.add(seperator3);}
         if(!(filepref.getPreference("LO5")).equals(""))
         {openRecent.add(lastOpen5);
            openRecent.add(seperator3);}
         if(!(filepref.getPreference("LO6")).equals(""))
         {openRecent.add(lastOpen6);
            openRecent.add(seperator3);}
         if(!(filepref.getPreference("LO7")).equals(""))
         {openRecent.add(lastOpen7);
            openRecent.add(seperator3);}
         openRecent.add(clearMenu);
         if(openRecent.getItemCount()<2)
         {clearMenu.setEnabled(false);}
         else
         {clearMenu.setEnabled(true);}
      
      }
      public void clearPreference()
      {  prefs.remove(LO1);
         prefs.remove(LO2);
         prefs.remove(LO3);
         prefs.remove(LO4);
         prefs.remove(LO5);
         prefs.remove(LO6);
         prefs.remove(LO7);
         openRecent.removeAll();}
      public String getPreference(String a)
      {String output=new String();
         if(a.equals("LO1"))
            output=prefs.get(LO1,"");
         if(a.equals("LO2"))
            output= prefs.get(LO2,"");
         if(a.equals("LO3"))
            output= prefs.get(LO3,"");
         if(a.equals("LO4"))
            output= prefs.get(LO4,"");
         if(a.equals("LO5"))
            output= prefs.get(LO5,"");
         if(a.equals("LO6"))
            output= prefs.get(LO6,"");
         if(a.equals("LO7"))
            output= prefs.get(LO7,"");
      
         return output;
      }
   }
}