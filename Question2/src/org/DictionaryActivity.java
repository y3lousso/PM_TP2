package org;

import org.dictionaryService.FrenchDictionaryActivator;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.spellingService.Callback;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DictionaryActivity extends JFrame implements Callback {

    private JButton check = new JButton("Check");
    private JTextField textField = new JTextField();
    private JLabel textArea = new JLabel();

    private JLabel label1 = new JLabel("Enter a word to check");
    private JLabel label2 = new JLabel("Word Suggestions :");

    public DictionaryActivity() {
        super("Spelling");

        // Main panel :
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        GridBagLayout layout = (GridBagLayout) panel.getLayout();

        layout.columnWidths = new int[]{150,200};
        layout.rowHeights = new int[]{50,200,200};

        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(4,4,4,4);
        textField.setMaximumSize(textField.getPreferredSize());
        panel.add(textField, constraints);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(4,4,4,4);
        panel.add(check, constraints);


        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0,0,0,0);
        panel.add(label2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(4,4,4,4);
        JScrollPane textAreaPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        panel.add(textAreaPane, constraints);

        // Init bundle
        initBundles();

        // frame dimension :
        int nx = Arrays.stream(layout.columnWidths).sum();
        int ny = Arrays.stream(layout.rowHeights).sum();

        // Frame Settings
        this.setContentPane(panel);
        this.setSize(nx, ny);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initBundles() {

        File file = new File("test");
        Bundle bundle = FrameworkUtil.getBundle(FrenchDictionaryActivator.class);
        System.out.println("bundle = " + bundle);

//        SpellingService spellingActivator = (SpellingService) FrameworkUtil.getBundle(SpellingService.class);
//        FrenchDictionaryActivator frenchDictionaryActivator = (FrenchDictionaryActivator) FrameworkUtil.getBundle(FrenchDictionaryActivator.class);
//
//        // Setup the spelling service
//        SpellingService spellingService = null;//spellingActivator.getSpellingService();
//        spellingService.addCallback(this);
//        spellingService.setDictionaryService(frenchDictionaryActivator.getDictionaryService());
    }

    @Override
    public void callGoodSpelling() {

    }

    @Override
    public void callBadSpelling(List<String> suggestions) {

    }

    @Override
    public void callServiceUnavailable() {

    }

    public static void main(String[] args) {
        new DictionaryActivity();
    }
}
