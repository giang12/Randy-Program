import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
public class DiceRoller extends JFrame
{ private  GridLayout layout;

   private  JFrame dFrame;
   private final JMenuBar mainbar= new JMenuBar();
   private final JMenuItem setting=new JMenuItem("Setting");
   private final JMenu file=new JMenu("File");
   private final JMenuItem help=new JMenuItem("Help");
   private  JButton rollButton;
   private  int numdie;
   public  JPanel panel1;
   Font font1=new Font(Font.SANS_SERIF, 0, 14);

   public DiceRoller()
   {
      setFrame();
      layout=new GridLayout();
      panel1=new JPanel();
      panel1.setLayout(layout);
      panel1.setSize(dFrame.getWidth(),dFrame.getHeight()-rollButton.getHeight());
      RandomDice d=new RandomDice(panel1.getWidth(),panel1.getHeight(),1);
      panel1.add(d.getButton());
      dFrame.add(panel1,BorderLayout.CENTER);
      dFrame.validate();
      numdie=1;
   }
   public DiceRoller(int n)
   {  
      if (panel1!=null)
      {dFrame.remove(panel1);}
      setFrame();
      int col=(int)(Math.round(Math.sqrt(n)));
      int row=(int)(Math.round(n/col));
      panel1=new JPanel();
      layout=new GridLayout(row,col);
      panel1.setLayout(layout);
      panel1.setSize(dFrame.getWidth(),dFrame.getHeight()-rollButton.getHeight());
      for(int i=0;i<n;i++)
      {
         RandomDice d=new RandomDice(panel1.getWidth(),panel1.getHeight(),n);
         panel1.add(d.getButton());
      }
      dFrame.add(panel1,BorderLayout.CENTER);
      dFrame.validate();
      numdie=n;
   
   }

   private void setFrame()
   {
      dFrame=new JFrame("Dice Roller");   
      mainbar.setBorderPainted(true);
      rollButton= new JButton("Roll");
      rollButton.addActionListener(new Roll());
      dFrame.setSize(1000, 700);
      rollButton.setPreferredSize(new Dimension(dFrame.getWidth()/20, dFrame.getHeight()/12));
      setting.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  String input=JOptionPane.showInputDialog("How many dice do you like to roll?");
                  int n= Integer.parseInt(input); 
                  numdie=n;
                  dFrame.remove(panel1);
                  int col=(int)(Math.round(Math.sqrt(numdie)));
                  int row=(int)(Math.round(numdie/col));
                  panel1=new JPanel();
                  layout=new GridLayout(col,row);
                  panel1.setLayout(layout);
                  panel1.setSize(dFrame.getWidth(),dFrame.getHeight()-rollButton.getHeight());
                  for(int i=0;i<numdie;i++)
                  {
                     RandomDice d=new RandomDice(panel1.getWidth(),panel1.getHeight(),numdie);
                     panel1.add(d.getButton());
                  }
                  dFrame.add(panel1,BorderLayout.CENTER);
                  dFrame.validate();
               }});
      help.setFont(font1);
      help.addActionListener(
            new ActionListener()
            { 
               @Override
               public void actionPerformed(ActionEvent ae){
                  JOptionPane.showMessageDialog(null,
                     "Click setting under file to choose number of dice!\n"+
                     "Click roll to roll all dice,\n" +
                     "or click on individual die to roll that single die! ",
                     "Help", JOptionPane.INFORMATION_MESSAGE);}});
      help.setMaximumSize(new Dimension(65, 50));
      dFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      dFrame.setResizable(true);
      dFrame.add(mainbar,BorderLayout.NORTH);mainbar.add(file);file.add(setting);mainbar.add(help);
      dFrame.add(rollButton,BorderLayout.SOUTH);
      dFrame.setLocationRelativeTo(null);
      dFrame.getContentPane().setBackground(Color.white);            
      dFrame.validate();
      dFrame.setVisible(true);
   }
   public  class Roll implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent ae)
      {
         dFrame.remove(panel1);
         int col=(int)(Math.round(Math.sqrt(numdie)));
         int row=(int)(Math.round(numdie/col));
         panel1=new JPanel();
         layout=new GridLayout(col,row);
         panel1.setLayout(layout);
         panel1.setSize(dFrame.getWidth(),dFrame.getHeight()-rollButton.getHeight());
         for(int i=0;i<numdie;i++)
         {RandomDice d=new RandomDice(panel1.getWidth(),panel1.getHeight(),numdie);
            panel1.add(d.getButton());}
         playSound("sounds/shakerolldice3.wav");
         dFrame.add(panel1,BorderLayout.CENTER);
         dFrame.validate();  
      }
   }
   public  synchronized void playSound(final String url) {
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
}