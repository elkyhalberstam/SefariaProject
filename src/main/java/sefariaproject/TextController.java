package sefariaproject;

import io.reactivex.rxjava3.schedulers.Schedulers;
import sefariaproject.text.Text;

import javax.swing.*;
import java.awt.*;

public class TextController {

    private JTextArea textArea;
    private Text text;
    private SefariaService service;

    public TextController(SefariaService service, JTextArea textArea) {
        this.textArea = textArea;
        this.service = service;
    }

    public void updateText(String textName) {
        service.getSefariaText(textName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::setPerek,
                        Throwable::printStackTrace);
    }

    public void setPerek(Text text) {
        this.text = text;
        StringBuilder perek1 = new StringBuilder();
        for (int i = 0; i < text.text.length; i++) {
            String engPasuk = text.text[i];
            String hebPasuk = text.he[i];
//           System.out.println(hebPasuk);
            String heCleanPasuk = removeBetweenBrackets(hebPasuk);
            String cleanPasuk = removeBetweenBrackets(engPasuk);
            perek1.append(i+1 +") ");
            perek1.append("\n").append(heCleanPasuk).append("\n");
            perek1.append(cleanPasuk).append("\n");
//            textArea.append(i+1 +") ");
//            textArea.append("\n");
//            textArea.append(heCleanPasuk);
//            textArea.append("\n");
//            textArea.append(cleanPasuk);
//            textArea.append("\n");
        }

        textArea.setText(String.valueOf(perek1));

    }

    public String removeBetweenBrackets(String input) {
        StringBuilder output = new StringBuilder();
        boolean withinBrackets = false;
        char letter = ' ';
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '<' && !withinBrackets) {
                withinBrackets = true;
                letter = input.charAt(i+1);
                if(letter == 'b' || letter == '/')
                {
                    while(true)
                    {
                        if(input.charAt(i + 1) == '>')
                        {
                            i++;
                            break;
                        }
                        else {i++;}
                    }
                    withinBrackets = false;

                }
            } else if (c == '<' && withinBrackets && input.charAt(i+2) == letter)
            {
                withinBrackets = false;
                letter = ' ';
                while(true)
                {
                    if(input.charAt(i + 1) == '>')
                    {
                        i++;
                        break;
                    }
                    else {i++;}
                }
            } else if (!withinBrackets) {
                output.append(c);
            }
        }
        return output.toString();
    }
}
