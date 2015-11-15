import java.io.File;

import javax.swing.JFileChooser;

public class Start {
  public static void main(String[] args) {
    
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          PicToASCII.run(selectedFile.getAbsolutePath());
        }
    
  }
}