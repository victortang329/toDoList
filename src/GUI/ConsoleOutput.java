package GUI;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutput extends OutputStream {
    private JTextArea textArea;

    public ConsoleOutput(JTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException{
        textArea.append(String.valueOf((char)b));
/*        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);*/
//        textArea.append(String.valueOf((char)b));
//        textArea.setCaretPosition(textArea.getDocument().getLength());
//        textArea.update(textArea.getGraphics());
}
}
