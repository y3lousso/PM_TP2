package org.dictionaryActivity;

import org.dictionaryService.able.IDictionaryService;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

class DictionaryActivity extends JFrame {

    private JButton check = new JButton("Check");
    private JTextField textField = new JTextField();
    private JLabel textArea = new JLabel();

    private JMenuBar menuBar;
    private JMenu optionMenu;

    private JLabel label1 = new JLabel("Enter a word to check");
    private JLabel label2 = new JLabel("Word Suggestions :");
    private JLabel label3 = new JLabel("Type a word :");
    private JLabel statusLabel = new JLabel("No dictionary selected !");

    private IDictionaryService service;
    private DictionaryServiceFinder serviceFinder;

    DictionaryActivity(DictionaryServiceFinder serviceFinder) {
        super("Spelling");

        this.serviceFinder = serviceFinder;

        // Main panel :
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        GridBagLayout layout = (GridBagLayout) panel.getLayout();

        // Status panel
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);

        // layout dimensions
        layout.columnWidths = new int[]{150,200};
        layout.rowHeights = new int[]{50,200,200,50};

        // Add all the component in the frame
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(4,4,4,4);
        JPanel textFieldPanel = new JPanel(new BorderLayout());
        label3.setHorizontalAlignment(JLabel.CENTER);
        textFieldPanel.add(label3, BorderLayout.NORTH);
        textFieldPanel.add(textField, BorderLayout.SOUTH);
        panel.add(textFieldPanel, constraints);

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
        JScrollPane textAreaPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        panel.add(textAreaPane, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0,0,0,0);
        panel.add(statusPanel, constraints);

        // Listener
        check.addActionListener(e -> checkWord(textField.getText()));

        // menu
        createMenuBar();

        // frame dimension :
        int nx = Arrays.stream(layout.columnWidths).sum();
        int ny = Arrays.stream(layout.rowHeights).sum();
        this.setSize(nx, ny);

        // Frame Settings
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void checkWord(String word) {
        if (service == null) {
            serviceUnavailable();
        }
        else if (!word.equals("") && service.check(word)) {
            check.setText("Good");
        }
        else if (!word.equals("")){
            check.setText("Bad");
            printSuggestionWords(service.GetWordsBeginWith(word));
        }
    }

    private void printSuggestionWords(List<String> words) {
        if (words == null || words.isEmpty()) {
            textArea.setText("Any suggestion");
        }
        else {
            // Print all the words in the textArea
            StringBuilder content = new StringBuilder("<html>");
            words.forEach(s -> content.append("- ").append(s).append("<br/>"));
            content.append("</html>");
            textArea.setText(String.valueOf(content));
            textArea.setVerticalAlignment(JLabel.TOP);
        }
    }

    private void serviceUnavailable() {
        // Open a option pane with error message
        JOptionPane.showMessageDialog(this,
                "No language selected ! Please select one language",
                "Error !",
                JOptionPane.ERROR_MESSAGE);

    }

    private void createMenuBar() {
        // Create the menu bar, update each click on "options"
        menuBar = new JMenuBar();
        optionMenu = new JMenu("Options");


        optionMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                createDictionaryMenu();
            }

            @Override public void menuDeselected(MenuEvent e) {}
            @Override public void menuCanceled(MenuEvent e) {}
        });

        menuBar.add(optionMenu);
        setJMenuBar(menuBar);
    }

    private void createDictionaryMenu() {
        // Create the submenu "language" with all the dictionary found
        optionMenu.removeAll();
        JMenu submenu = new JMenu("Language");

        IDictionaryService[] services = serviceFinder.findAllDictionaryServices();
        if (services == null || services.length == 0) {
            // No dictionary found :
            JMenuItem menuItem = new JMenuItem("No dictionary found");
            menuItem.setEnabled(false);
            submenu.add(menuItem);
        }
        else {
            for (IDictionaryService service : services) {
                JMenuItem menuItem = new JMenuItem(service.getLanguage());
                menuItem.addActionListener(new DictionaryMenuItemListener(service));
                submenu.add(menuItem);
            }
        }
        optionMenu.add(submenu);
    }

    private class DictionaryMenuItemListener implements ActionListener {

        private IDictionaryService dictionaryService;

        public DictionaryMenuItemListener(IDictionaryService service) {
            dictionaryService = service;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Set the service when the language is selected
            service = dictionaryService;
            statusLabel.removeAll();
            statusLabel.setText("Language : " + service.getLanguage());

            // clear text
            textArea.setText("");
            textField.setText("");
        }
    }

}
