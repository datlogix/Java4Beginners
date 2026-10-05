// Run it with:
//     javac -d out *.java
//     java -cp out PlannerWindow

import java.awt.*;
import java.nio.file.Path;
import javax.swing.*;

/** The study planner window. */
public class PlannerWindow {

    static JPanel buildUi(TaskStore store) {
        DefaultListModel<Task> shown = new DefaultListModel<>();
        JList<Task> list = new JList<>(shown);
        // TODO: load the tasks into shown; a form (title, subject, due date as YYYY-MM-DD,
        //       a Priority combo box) with an Add button; Mark done / Delete buttons; a filter
        //       combo (All / To do / Overdue / by subject); save after every change.
        // Stretch: colour each row by priority with a custom ListCellRenderer (see the README).
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        panel.add(new JLabel("TODO: the form and the buttons"), BorderLayout.NORTH);
        return panel;
    }

    static JPanel buildUi() {      // for the course's screenshot tool
        return buildUi(new TaskStore(Path.of("tasks.csv")));
    }

    public static void main(String[] args) {
        TaskStore store = new TaskStore(Path.of("tasks.csv"));
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Study planner");
            window.add(buildUi(store));
            window.setSize(720, 480);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
