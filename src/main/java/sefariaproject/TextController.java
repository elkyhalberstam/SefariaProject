package sefariaproject;

import io.reactivex.rxjava3.schedulers.Schedulers;
import sefariaproject.text.Text;

import javax.swing.*;

public class TextController {

    private String perek;
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

//    public String getPerek() {
//        return perek;
//    }

    public void setPerek(Text text) {
        this.text = text;
        StringBuilder perek1 = new StringBuilder();
        for (int i = 0; i < text.text.length; i++) {
            String engPasuk = text.text[i];
            String hebPasuk = text.he[i];
            String heCleanPasuk = removeBetweenBrackets(hebPasuk);
            String cleanPasuk = removeBetweenBrackets(engPasuk);
            perek1.append("\t\t\t\t").append(heCleanPasuk).append("\n");
            perek1.append(cleanPasuk).append("\n");
        }

        textArea.setText(String.valueOf(perek1));
    }

    public String removeBetweenBrackets(String input) {
        StringBuilder output = new StringBuilder();
        boolean withinBrackets = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '<' && (input.charAt(i + 1) == 'i' || input.charAt(i + 1) == 's')) {
                withinBrackets = true;
            } else if (c == '<' && input.charAt(i + 1) == '/' && (input.charAt(i + 2) == 'i' || input.charAt(i + 2) == 's')) {
                withinBrackets = false;
                if (input.charAt(i + 2) == 'i') {
                    i += 3;
                } else if (input.charAt(i + 2) == 's') {
                    i += 5;
                }
            } else if (!withinBrackets) {
                output.append(c);
            }
        }
        return output.toString();
    }
}
