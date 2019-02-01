package model;

public class GUIInputText {
    String inputText;

    public GUIInputText(){
    }

    public void updateInputText(String string){
        inputText = string;
    }

    public String returnString(){
        return inputText;
    }
}

