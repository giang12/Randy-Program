import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
public class Card extends JFrame
{ 
   private  GridLayout layout;
   private  JFrame dFrame;
   private final JMenuBar mainbar= new JMenuBar();
   private final JMenuItem setting=new JMenuItem("Set cards");
   private final JMenu mainsetting=new JMenu("Setting");
   private final JMenuItem help=new JMenuItem("Help");
  
   private final JSeparator seperator=new JSeparator();
   private final JSeparator seperator1=new JSeparator();
   private final JSeparator seperator2=new JSeparator();
   
   private final JRadioButton replacement = new JRadioButton("Replacement",true);
   private final JRadioButton nonreplacement = new JRadioButton("Non-replacement");
   private final JRadioButton allcards = new JRadioButton("All cards",true);
   private final JRadioButton allred = new JRadioButton("All red cards");
   private final JRadioButton allblack = new JRadioButton("All black cards");
   private final JRadioButton clubs = new JRadioButton("Only clubs");
   private final JRadioButton diamonds = new JRadioButton("Only diamonds");
   private final JRadioButton hearts = new JRadioButton("Only hearts");
   private final JRadioButton spades = new JRadioButton("Only spades");

   private final ButtonGroup buttongroup = new ButtonGroup();
   private final ButtonGroup buttongroup1=new ButtonGroup();
   private final JMenuItem reset=new JMenuItem("Reset");
   private  JButton shuffleButton;
   private  int numdie;
   public  JPanel panel1;
   private RandomCard mainpic;
   Font font1=new Font(Font.SANS_SERIF, 0, 14);
   public Card()
   {
      setFrame();
      layout=new GridLayout();
      panel1=new JPanel();
      panel1.setLayout(layout);
      panel1.setSize(dFrame.getWidth(),dFrame.getHeight()-shuffleButton.getHeight());
      mainpic=new RandomCard(panel1.getWidth(),panel1.getHeight(),1);
      panel1.add(mainpic.getButton());
      dFrame.add(panel1);
      dFrame.validate();
      numdie=1;
   }
   /*public Card(int n)
   {  
      if (panel1!=null)
      {dFrame.remove(panel1);}
      setFrame();  
      int col=(int)(Math.round(Math.sqrt(n)));
      int row=(int)(Math.round(n/col));
      
      panel1=new JPanel();
      layout=new GridLayout(col,row);
      panel1.setLayout(layout);
      panel1.setSize(dFrame.getWidth(),dFrame.getHeight()-shuffleButton.getHeight());
      for(int i=0;i<n;i++)
      {
         mainpic.RandomMethod(panel1.getWidth(),panel1.getHeight(),n);
         mainpic.setButton();
         panel1.add(mainpic.getButton());
      }
      dFrame.add(panel1);
      dFrame.validate();
      numdie=n;
   
   }*/

   private void setFrame()
   {
      dFrame=new JFrame("Cards Shuffler");
      shuffleButton = new JButton("Shuffle");
      mainbar.setBorderPainted(true);
      shuffleButton.addActionListener(new Shuffle());
      dFrame.setSize(1000, 700);
      shuffleButton.setPreferredSize(new Dimension(dFrame.getWidth()/20, dFrame.getHeight()/12));
      setting.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  String input=JOptionPane.showInputDialog("How many cards do you want?");
                  int n= Integer.parseInt(input); 
                  numdie=n;
                  dFrame.remove(panel1);
                  int col=(int)(Math.round(Math.sqrt(numdie)));
                  int row=(int)(Math.round(numdie/col));
                  panel1=new JPanel();
                  layout=new GridLayout(col,row);
                  panel1.setLayout(layout);
                  panel1.setSize(dFrame.getWidth(),dFrame.getHeight()-shuffleButton.getHeight());
                  mainpic.ResetButtonnum();
                  for(int i=0;i<numdie;i++)
                  {mainpic.ResetImageList();
                     mainpic.RandomMethod(panel1.getWidth(),panel1.getHeight(),numdie);
                     mainpic.setButton();
                     panel1.add(mainpic.getButton());
                  }
                  dFrame.add(panel1);
                  dFrame.validate();
               }});
      help.setFont(font1);
      help.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  JOptionPane.showMessageDialog(null,
                     "Adjust options under setting!\n"+
                     "Click shuffle to shuffle all cards,\n" +
                     "or click on one card to shuffle that single card! \n"+
                     "--------------------------------------------------------\n"+
                     "Maximum possible cards without replacement:\n"+
                     "ÔøΩAll cards: 52 cards\n"+
                     "ÔøΩAll red or black: 26 cards\n"+
                     "ÔøΩOnly clubs/diamonds/hearts/spades: 13 cards\n" ,
                     "Help", JOptionPane.INFORMATION_MESSAGE);}});
      help.setMaximumSize(new Dimension(55, 50));
      replacement.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setReplace(true);
                  }
               }});
   
      nonreplacement.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setReplace(false);}
               }});
      reset.setFont(font1);
      reset.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  playSound("sounds/reset.wav");
                  mainpic.ResetButtonnum();
                  mainpic.ResetImageList();
                 	
               }});
      reset.setMaximumSize(new Dimension(65, 50));
      allcards.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setAllCard();
                     mainpic.ResetButtonnum();
                     mainpic.ResetImageList();
                  }
               }});
      allred.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setAllRed();
                     mainpic.ResetButtonnum();
                     mainpic.ResetImageList();
                  }
               }});
      allblack.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setAllBlack();
                     mainpic.ResetButtonnum();
                     mainpic.ResetImageList();
                  }
               }});
      clubs.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setClub();
                     mainpic.ResetButtonnum();
                     mainpic.ResetImageList();
                  }
               }});
      diamonds.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setDiamond();
                     mainpic.ResetButtonnum();
                     mainpic.ResetImageList();
                  }
               }});
      hearts.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setHeart();
                     mainpic.ResetButtonnum();
                     mainpic.ResetImageList();
                  }
               }});
      spades.addItemListener(
            new ItemListener()
            {
               @Override
               public void itemStateChanged(ItemEvent event) {
                  AbstractButton aButton = (AbstractButton) event.getSource();
                  int state = event.getStateChange();
                  if (state == ItemEvent.SELECTED) 
                  {mainpic.setSpade();
                     mainpic.ResetButtonnum();
                     mainpic.ResetImageList();
                  }
               }});
   
      buttongroup.add(replacement);buttongroup.add(nonreplacement);
      buttongroup1.add(allcards);buttongroup1.add(allred);
      buttongroup1.add(allblack);buttongroup1.add(clubs);
      buttongroup1.add(diamonds);buttongroup1.add(hearts);buttongroup1.add(spades);
      mainbar.add(mainsetting);mainbar.add(help);mainbar.add(reset);
      mainsetting.add(setting);mainsetting.add(seperator);mainsetting.add(replacement);mainsetting.add(nonreplacement);
      mainsetting.add(seperator2);
      mainsetting.add(allcards);mainsetting.add(allred);
      mainsetting.add(allblack);mainsetting.add(clubs);
      mainsetting.add(diamonds);mainsetting.add(hearts);mainsetting.add(spades);
   
      mainsetting.add(seperator1);
   
      dFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
      dFrame.setResizable(true);
      dFrame.add(mainbar,BorderLayout.NORTH);
      dFrame.add(shuffleButton,BorderLayout.SOUTH);
      dFrame.setLocationRelativeTo(null);
      dFrame.getContentPane().setBackground(Color.white);            
      dFrame.validate();
      dFrame.setVisible(true);
   }
   public  class Shuffle implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent ae)
      {
         dFrame.remove(panel1);
         mainpic.ResetButtonnum();
         int col=(int)(Math.round(Math.sqrt(numdie)));
         int row=(int)(Math.round(numdie/col));
         panel1=new JPanel();
         layout=new GridLayout(col,row);
         panel1.setLayout(layout);
         panel1.setSize(dFrame.getWidth(),dFrame.getHeight()-shuffleButton.getHeight());
         for(int i=0;i<numdie;i++)
         {
            mainpic.RandomMethod(panel1.getWidth(),panel1.getHeight(),numdie);
            mainpic.setButton();
            panel1.add(mainpic.getButton());
         }
         playSound("sounds/shuffling.wav");
         dFrame.add(panel1);
         dFrame.validate();  
      }
   }
   public  synchronized void playSound(final String url) {
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
