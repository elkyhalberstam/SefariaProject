package sefariaproject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sefariaproject.text.Text;


import javax.swing.*;
import java.awt.*;

public class SefariaFrame extends JFrame {

    public SefariaFrame()
    {
        setSize(800, 500);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.sefaria.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        SefariaService service = retrofit.create(SefariaService.class);

        Insets insets = new Insets(10,10,4,10);

        JTextArea textArea = new JTextArea("output");
        textArea.setLineWrap(true);
        textArea.setMargin(insets);
        textArea.setFont(new Font("Monaco", Font.PLAIN, 15));
        textArea.setSize(200,200);
        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        TextController controller = new TextController(service, textArea);

        JPanel enterTextPanel = new JPanel();
        enterTextPanel.setLayout(new FlowLayout());


        String[] titles = new String[]{"Genesis", "Exodus", "Leviticus",
                "Numbers", "Deuteronomy", "Joshua", "Judges",
                "I Samuel", "II Samuel", "I Kings", "II Kings" , "Isaiah",
                "Jeremiah", "Ezekiel", "Hosea", "Joel", "Amos",
                "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah",
                "Haggai" , "Zechariah", "Malachi", "Psalms", "Proverbs",
                "Job", "Song of Songs", "Ruth", "Lamentations", "Ecclesiastes",
                "Esther", "Daniel", "Ezra", "Nehemiah", "I Chronicles", "II Chronicles"};

        JComboBox tanach = new JComboBox(titles);
        enterTextPanel.add(tanach);

        int[] chaptersList = new int[]{50, 40, 27, 36, 34, 24, 21, 31, 24, 22, 25, 66, 52, 48, 14, 4, 9,
                1, 4, 7, 3, 3, 3, 2, 14, 3, 150, 31, 42, 8, 4, 5, 12, 10, 12, 10, 13, 29, 36};

        JComboBox chapters = new JComboBox();
        enterTextPanel.add(chapters);

        JPanel selectTextPanel = new JPanel();
        enterTextPanel.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(enterTextPanel, BorderLayout.NORTH);
        panel.add(selectTextPanel, BorderLayout.SOUTH);

        setContentPane(panel);

        panel.add(scroll, BorderLayout.CENTER);

        tanach.addActionListener(e -> {
            chapters.removeAllItems();
            int[] numbers  = new int[chaptersList[tanach.getSelectedIndex()]];
            for(int i = 0; i<numbers.length; i++)
            {
                numbers[i] = i+1;
                chapters.addItem(i+1);
            }
        });

        chapters.addActionListener(e -> {
            controller.updateText((String) tanach.getSelectedItem() + " " +chapters.getSelectedItem());
            //String perek = controller.getPerek();
            //textArea.setText(perek);
        });
    }


}
