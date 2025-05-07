import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MARKS extends JFrame implements ActionListener {
    private JLabel universityLabel, dateLabel, facultyLabel, imageLabel, marksUploadedLabel, idLabel, selectedFileLabel, overallMarksLabel;
    private JButton calculateButton, selectFileButton, uploadButton;
    private JTable table, uploadedMarksTable;
    private DefaultTableModel model, uploadedMarksModel;
    private final String[] subjectNames = {"Aptitude", "DSA", "Neural", "Programming"};
    private final int[] credits = {4, 3, 4, 3}; // Credits for Subject respectively
    private File selectedFile;
    private JTextField idTextField;
    private JButton back;

    public MARKS() {
        setTitle("Student Grading System");
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.lightGray);

        // Load and resize the image
        try {
            Image img = ImageIO.read(new File("logo.png"));
            Image scaledImg = img.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImg);
            imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(550, 20, 300, 150); // Set bounds for image label
            add(imageLabel);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        universityLabel = new JLabel("Student Grading System");
        universityLabel.setFont(new Font("Arial", Font.BOLD, 24));
        universityLabel.setBounds(550, 170, 300, 30); // Set bounds for university label
        add(universityLabel);

        String currentDate = java.time.LocalDate.now().toString();
        dateLabel = new JLabel("Date : " + currentDate);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dateLabel.setBounds(1200, 30, 200, 30); // Set bounds for date label
        add(dateLabel);

        facultyLabel = new JLabel("Faculty Name : Mr.AKASH");
        facultyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        facultyLabel.setBounds(50, 210, 300, 30); // Set bounds for faculty label
        add(facultyLabel);

        idLabel = new JLabel("Enter Student ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 18));
        idLabel.setBounds(50, 250, 200, 30); // Set bounds for ID label
        add(idLabel);

        idTextField = new JTextField(10);
        idTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        idTextField.setBounds(220, 250, 150, 30); // Set bounds for ID text field
        add(idTextField);

        selectFileButton = new JButton("Select File");
        selectFileButton.addActionListener(this);
        selectFileButton.setBounds(50, 290, 150, 30); // Set bounds for select file button
        add(selectFileButton);

        selectedFileLabel = new JLabel("");
        selectedFileLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectedFileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedFileLabel.setBounds(200, 290, 300, 30); // Set bounds for selected file label
        add(selectedFileLabel);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(Color.ORANGE); // Set table header background color
        table.setBackground(new Color(255, 204, 153)); // Set table background color
        table.setRowHeight(30);

        model.addColumn("Subject");
        model.addColumn("Credit");
        model.addColumn("Internal");
        model.addColumn("External");

        for (int i = 0; i < 4; i++) {
            model.addRow(new Object[]{subjectNames[i], credits[i], "", ""});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 330, 1000, 150); // Set bounds for scroll pane
        add(scrollPane);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(Color.BLUE);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(this);
        buttonPanel.add(calculateButton);
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        uploadButton = new JButton("Upload");
        uploadButton.setFont(new Font("Arial", Font.BOLD, 14));
        uploadButton.setBackground(Color.GREEN);
        uploadButton.setForeground(Color.WHITE);
        uploadButton.addActionListener(this);
        buttonPanel.add(uploadButton);
        buttonPanel.setBounds(150, 500, 800, 30); // Set bounds for button panel
        add(buttonPanel);

        // Marks uploaded label
        marksUploadedLabel = new JLabel("Upload the marks.");
        marksUploadedLabel.setFont(new Font("Arial", Font.BOLD, 16));
        marksUploadedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        marksUploadedLabel.setBounds(150, 540, 800, 30); // Set bounds for marks uploaded label
        add(marksUploadedLabel);

        // Overall marks label
        overallMarksLabel = new JLabel("Overall Marks: ");
        overallMarksLabel.setFont(new Font("Arial", Font.BOLD, 16));
        overallMarksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        overallMarksLabel.setBounds(350, 540, 800, 30); // Set bounds for overall marks label
        add(overallMarksLabel);

        // Uploaded marks table
        uploadedMarksModel = new DefaultTableModel();
        uploadedMarksTable = new JTable(uploadedMarksModel);
        uploadedMarksModel.addColumn("Student ID");
        uploadedMarksModel.addColumn("Subject");
        uploadedMarksModel.addColumn("Score");
        uploadedMarksModel.addColumn("Grade");

        JScrollPane uploadedScrollPane = new JScrollPane(uploadedMarksTable);
        uploadedScrollPane.setBounds(50, 570, 1000, 150); // Set bounds for uploaded scroll pane
        uploadedScrollPane.setBackground(new Color(255, 204, 153)); // Set background color for uploaded marks table
        uploadedMarksTable.getTableHeader().setBackground(Color.orange);
        uploadedMarksTable.setBackground(new Color(255, 204, 153));
        uploadedMarksTable.setRowHeight(30);
        uploadedMarksTable.setFont(new Font("Arial", Font.PLAIN, 14));
        uploadedMarksTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        add(uploadedScrollPane);

        back = new JButton("RETURN");
        back.setBounds(50, 20, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectFileButton) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                selectedFileLabel.setText("Selected File: " + selectedFile.getName());
            }
        }else if(e.getSource()==back){
            setVisible(false);
            SwingUtilities.invokeLater(() -> new OptionPage());
        } 
        else if (e.getSource() == calculateButton) {
            if (selectedFile != null) {
                String studentID = idTextField.getText();
                if (!studentID.isEmpty()) {
                    // Clear table selection
                    uploadedMarksTable.clearSelection();
                    // Calculate marks and display them in the table
                    calculateMarks(studentID);
                    // Calculate and display overall marks
                    calculateOverallMarks();
                    // Update the "Marks Uploaded" label
                    marksUploadedLabel.setText("Marks Uploaded Successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter Student ID.", "ID Not Entered", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a file.", "File Not Selected", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == uploadButton) {
            if (selectedFile != null) {
                String studentID = idTextField.getText();
                if (!studentID.isEmpty()) {
                    writeAverageScoreToFile(studentID, selectedFile.getName(),calculateOverallMarks());
                    JOptionPane.showMessageDialog(this, "Marks uploaded successfully for Student ID: " + studentID, "Upload Successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter Student ID.", "ID Not Entered", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a file.", "File Not Selected", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void calculateMarks(String studentID) {
        for (int i = 0; i < 4; i++) {
            double internalMarks = Double.parseDouble(model.getValueAt(i, 2).toString());
            double externalMarks = Double.parseDouble(model.getValueAt(i, 3).toString());
            double totalMarks = internalMarks + externalMarks;
            String grade = calculateGrade(totalMarks);
            uploadedMarksModel.addRow(new Object[]{studentID, subjectNames[i], totalMarks, grade});
        }
    }

    private double calculateOverallMarks() {
        double totalMarks = 0;
        for (int i = 0; i < 4; i++) {
            double internalMarks = Double.parseDouble(model.getValueAt(i, 2).toString());
            double externalMarks = Double.parseDouble(model.getValueAt(i, 3).toString());
            totalMarks += (internalMarks + externalMarks);
        }
        double overallMarks = totalMarks / 4; // Dividing by 4 because there are 4 subjects
        overallMarksLabel.setText("Overall Marks: " + overallMarks);
        return overallMarks;
    }

    private String calculateGrade(double totalMarks) {
        if (totalMarks >= 90) {
            return "A+";
        } else if (totalMarks >= 85) {
            return "A";
        } else if (totalMarks >= 80) {
            return "B+";
        } else if (totalMarks >= 70) {
            return "B";
        } else if (totalMarks >= 60) {
            return "C+";
        } else if (totalMarks >= 50) {
            return "C";
        } else if (totalMarks >= 40) {
            return "D";
        } else {
            return "F";
        }
    }

    public static String[] readtxt(String filename) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
    
            String[] temp = new String[1000000]; // temp is an array which is used to store row String
            int i = 0; // the number of each row
    
            while ((temp[i] = br.readLine()) != null) {
                ++i;
            }
            br.close();
    
            // resize the String
            String[] newData = new String[i];
    
            for (int j = 0; j < i; ++j) {
                newData[j] = temp[j];
            }
            return newData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void writeAverageScoreToFile(String studentID, String fileName,double m) {
        try {
            String[] data = readtxt(fileName);
            if (data != null) {
                for (int i = 1; i < data.length; i++) {
                    String[] parts = data[i].split(",");
                    if (parts.length == 4 && parts[2].equals(studentID)) {
                        double totalMarks = m;
                        data[i] = parts[0] + "," + parts[1] + "," + parts[2] + "," + totalMarks;
                    }
                }
                FileWriter writer = new FileWriter(fileName);
                for (String line : data) {
                    writer.write(line + "\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        MARKS system = new MARKS();
        system.setVisible(true);
    }
}
