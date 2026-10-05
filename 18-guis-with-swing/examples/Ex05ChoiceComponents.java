// Example 5: check boxes, radio buttons, a drop-down list and a slider.
// Run it with:  java Ex05ChoiceComponents.java

import java.awt.*;
import javax.swing.*;

public class Ex05ChoiceComponents {

    static JPanel buildUi() {
        // A JComboBox: choose one from a drop-down list
        JComboBox<String> lab = new JComboBox<>(new String[]{"Electronics lab", "Robotics lab", "3D printing room"});

        // JRadioButtons in a ButtonGroup: only one can be selected
        JRadioButton morning = new JRadioButton("Morning", true);
        JRadioButton afternoon = new JRadioButton("Afternoon");
        ButtonGroup session = new ButtonGroup();
        session.add(morning);
        session.add(afternoon);

        // JCheckBoxes: any number can be ticked
        JCheckBox scope = new JCheckBox("Oscilloscope");
        JCheckBox solder = new JCheckBox("Soldering station");

        // A JSlider: a number in a range
        JSlider people = new JSlider(1, 10, 2);
        people.setMajorTickSpacing(1);
        people.setPaintTicks(true);
        people.setPaintLabels(true);

        JLabel summary = new JLabel(" ");
        JButton book = new JButton("Book it");
        book.addActionListener(e -> {
            String text = lab.getSelectedItem() + ", " + (morning.isSelected() ? "morning" : "afternoon")
                    + ", " + people.getValue() + " people"
                    + (scope.isSelected() ? ", oscilloscope" : "")
                    + (solder.isSelected() ? ", soldering station" : "");
            summary.setText("Booked: " + text);
        });

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));   // 0 rows means "as many as needed"
        form.add(new JLabel("Room:"));
        form.add(lab);
        form.add(new JLabel("Session:"));
        JPanel radios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radios.add(morning);
        radios.add(afternoon);
        form.add(radios);
        form.add(new JLabel("Equipment:"));
        JPanel checks = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        checks.add(scope);
        checks.add(solder);
        form.add(checks);
        form.add(new JLabel("People:"));
        form.add(people);

        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(form, BorderLayout.CENTER);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(book, BorderLayout.WEST);
        bottom.add(summary, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Lab booking");
            window.add(buildUi());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
