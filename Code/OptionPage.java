import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OptionPage extends JFrame {
    public OptionPage() {
        setTitle("School Management System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Add school logo at the top
        ImageIcon schoolLogoIcon = new ImageIcon("logo1.png");
        JLabel schoolLogoLabel = new JLabel(schoolLogoIcon);
        schoolLogoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(schoolLogoLabel, BorderLayout.NORTH);

        // Add faculty details labels
        JPanel facultyDetailsPanel = new JPanel();
        facultyDetailsPanel.setLayout(new BoxLayout(facultyDetailsPanel, BoxLayout.Y_AXIS));
        facultyDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        facultyDetailsPanel.setBackground(Color.LIGHT_GRAY); // Set background color

        Font labelFont = new Font("Calibri", Font.PLAIN, 20); // Define font for labels

        JLabel facultyNameLabel = new JLabel("Faculty Name : Mr.AKASH PANDEY");
        facultyNameLabel.setFont(labelFont); // Set font for label
        JLabel facultySectionLabel = new JLabel("Class Alloted: MCA");
        facultySectionLabel.setFont(labelFont); // Set font for label
        JLabel facultyPositionLabel = new JLabel("Position : Professor");
        facultyPositionLabel.setFont(labelFont); // Set font for label
        JLabel DateLabel = new JLabel("Date : " + getCurrentDate());
        DateLabel.setFont(labelFont); // Set font for label

        facultyDetailsPanel.add(facultyNameLabel);
        facultyDetailsPanel.add(facultySectionLabel);
        facultyDetailsPanel.add(facultyPositionLabel);
        facultyDetailsPanel.add(DateLabel);
        add(facultyDetailsPanel, BorderLayout.CENTER);

        // Create panel for options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 2, 50, 0)); // 1 row, 2 columns
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50)); // Add padding
        optionsPanel.setBackground(Color.RED); // Set background color
        add(optionsPanel, BorderLayout.SOUTH);

        Font ButtonFont = new Font("Calibri", Font.BOLD, 26);
        // Option 1: Student Marks View
        JButton viewMarksButton = new JButton("Student Marks View");
        viewMarksButton.setFont(ButtonFont);
        viewMarksButton.setBackground(Color.WHITE);
        viewMarksButton.setPreferredSize(new Dimension(200, 150)); // Larger vertically
        viewMarksButton.setToolTipText("This section will display the marks of the students.");
        viewMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to perform when view marks button is clicked
                System.out.println("View Marks clicked");
                openViewMarks();
            }
        });
        optionsPanel.add(viewMarksButton);

        // Option 2: Student Marks Upload
        JButton uploadMarksButton = new JButton("Student Marks Upload");
        uploadMarksButton.setFont(ButtonFont);
        uploadMarksButton.setBackground(Color.WHITE);
        uploadMarksButton.setPreferredSize(new Dimension(200, 150)); // Larger vertically
        uploadMarksButton.setToolTipText("This section allows the teacher to upload/update the marks of the students.");
        uploadMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to perform when upload marks button is clicked
                System.out.println("Upload Marks clicked");
                openUploadMarks();
            }
        });
        optionsPanel.add(uploadMarksButton);

        setVisible(true);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(new Date());
    }

    public void openUploadMarks(){
        setVisible(false);
        MARKS system = new MARKS();
        system.setVisible(true);
    }

    public void openViewMarks(){
        setVisible(false);
        GUI frame = new GUI();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OptionPage());
    }
}
