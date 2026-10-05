// Example 7: a menu bar, keyboard shortcuts, and file dialogs: a tiny text editor.
// Run it with:  java Ex07Menus.java

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;

public class Ex07Menus {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Tiny editor");
            JTextArea text = new JTextArea(20, 60);
            text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
            window.add(new JScrollPane(text));

            // Ctrl on Windows/Linux, Cmd on a Mac
            int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

            JMenu file = new JMenu("File");
            JMenuItem open = new JMenuItem("Open...");
            open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, shortcut));
            open.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
                    try {
                        text.setText(Files.readString(chooser.getSelectedFile().toPath()));
                        window.setTitle("Tiny editor - " + chooser.getSelectedFile().getName());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(window, "Couldn't open it: " + ex.getMessage());
                    }
                }
            });
            JMenuItem save = new JMenuItem("Save as...");
            save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut | InputEvent.SHIFT_DOWN_MASK));
            save.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
                    try {
                        Files.writeString(chooser.getSelectedFile().toPath(), text.getText());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(window, "Couldn't save it: " + ex.getMessage());
                    }
                }
            });
            JMenuItem quit = new JMenuItem("Quit");
            quit.addActionListener(e -> window.dispose());
            file.add(open);
            file.add(save);
            file.addSeparator();
            file.add(quit);

            JMenu tools = new JMenu("Tools");
            JMenuItem count = new JMenuItem("Word count");
            count.addActionListener(e -> {
                String t = text.getText().trim();
                int words = t.isEmpty() ? 0 : t.split("\\s+").length;
                JOptionPane.showMessageDialog(window, words + " words, " + t.length() + " characters");
            });
            JCheckBoxMenuItem wrap = new JCheckBoxMenuItem("Wrap lines");
            wrap.addActionListener(e -> text.setLineWrap(wrap.isSelected()));
            tools.add(count);
            tools.add(wrap);

            JMenuBar bar = new JMenuBar();
            bar.add(file);
            bar.add(tools);
            window.setJMenuBar(bar);

            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
