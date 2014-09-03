import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;
public class Main
{
   public static void main(String args[]) throws InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException{
      //UIManager.put("nimbusBase", new Color(115,164,209));
      UIManager.put("nimbusBlueGrey", new Color(186,190,198));
      UIManager.put("control", new Color(115,164,209));
      UIManager.put("defaultFont", new Font(Font.SANS_SERIF, 0, 14));
      try {
         for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
               UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } 
      catch (Exception e) {
         UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }
   
      SwingUtilities.invokeLater(
            new Runnable() {
               @Override
               public void run() {  
                  String path=Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();            
                  String name=Main.class.getName();
                  try{
                     Runtime.getRuntime().exec( "java -jar "+path+name+".jar");
                     System.out.println("java -jar "+path+name+".jar");
                  }
                  catch (Exception e){};
                  RandomFace randomFace = new RandomFace();
               }
            });
   }
}