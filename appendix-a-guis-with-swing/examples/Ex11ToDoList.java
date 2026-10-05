// Example 11: putting it together. A to-do list whose DATA (the model) is kept
// separate from the WINDOW (the view): the GUI only displays and edits the
// model. Tasks are saved to todo.txt, so they survive closing the program.
// Run it with:  java Ex11ToDoList.java

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Ex11ToDoList {

    static final Path FILE = Path.of("todo.txt");

    /** The model: just data and rules. No Swing in here, so it could be unit tested. */
    static class TaskList {
        private final List<String> tasks = new ArrayList<>();

        void add(String task) {
            if (task.isBlank()) {
                throw new IllegalArgumentException("A task can't be blank.");
            }
            tasks.add(task.trim());
        }

        void remove(int index) { tasks.remove(index); }
        List<String> all() { return new ArrayList<>(tasks); }

        void load() throws IOException {
            if (Files.exists(FILE)) {
                tasks.addAll(Files.readAllLines(FILE));
            }
        }

        void save() throws IOException {
            Files.write(FILE, tasks);
        }
    }

    static JPanel buildUi(TaskList model) {
        DefaultListModel<String> shown = new DefaultListModel<>();   // what the JList displays
        model.all().forEach(shown::addElement);
        JList<String> list = new JList<>(shown);
        list.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JTextField input = new JTextField(25);
        JButton add = new JButton("Add");
        JButton done = new JButton("Done (remove)");

        Runnable addTask = () -> {
            try {
                model.add(input.getText());
                shown.addElement(input.getText().trim());
                input.setText("");
                model.save();
            } catch (IllegalArgumentException | IOException ex) {
                JOptionPane.showMessageDialog(list, ex.getMessage());
            }
        };
        add.addActionListener(e -> addTask.run());
        input.addActionListener(e -> addTask.run());
        done.addActionListener(e -> {
            int i = list.getSelectedIndex();
            if (i < 0) {
                JOptionPane.showMessageDialog(list, "Select a task first.");
                return;
            }
            model.remove(i);
            shown.remove(i);
            try {
                model.save();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(list, "Couldn't save: " + ex.getMessage());
            }
        });

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(input);
        top.add(add);
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        panel.add(done, BorderLayout.SOUTH);
        return panel;
    }

    static JPanel buildUi() {             // used by the course's screenshot tool
        TaskList model = new TaskList();
        model.add("Buy a 10k resistor");
        model.add("Finish the Appendix A project");
        return buildUi(model);
    }

    public static void main(String[] args) {
        TaskList model = new TaskList();
        try {
            model.load();
        } catch (IOException e) {
            System.out.println("Couldn't load " + FILE + ": " + e.getMessage());
        }
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("To-do list");
            window.add(buildUi(model));
            window.setSize(460, 380);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
