import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Stack;
import java.util.regex.Pattern;


public class GUI extends JFrame {
    private JFrame fr = new JFrame("SCHOOL GRADING SYSTEM");
    private JPanel contentPane = new JPanel();
    private static JTextField ReqInput;
    public static JTextField CourseInput;
    public static JTextArea CourseList = new JTextArea();
    public JLabel titleLabel = new JLabel("SCHOOL GRADING SYSTEM");
    public static JRadioButton RadioButton1 = new JRadioButton("Ascending Order");
    public static JRadioButton RadioButton2 = new JRadioButton("Descending Order");
    public static JTable Screen = new JTable();
    public static JLabel lblCredit = new JLabel("Total Credits:");
    public static JLabel lblNumberOfStudents = new JLabel("Number of Students: ");
    public static JButton AddButton = new JButton("Add");  
    public static JButton DeleteButton = new JButton("Delete"); 
    public JLabel creator = new JLabel("Software by: Akash Pandey(NIU-24-20901), Riya Kumari(NIU-24-26905)");
    public static JComboBox<String> Choice_1 = new JComboBox<String>();   

    public static Stack<String> sta = new Stack<String>();  
    public static Stack<Student> stu = new Stack<Student>();  
    public static int index = 0;  
    public static int num;  
    public static int num2; 
    public static String fileRoute = "";                
    private JMenuBar MenuBar = new JMenuBar();
    private JMenu File = new JMenu("File");
    private JMenuItem closeItem = new JMenuItem("Exit");
    private JMenuItem openItem = new JMenuItem("Open File");
    private JMenuItem importItem = new JMenuItem("Import File");
    private FileDialog openDia;
    private JButton back;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    } 

    public GUI() {
        num = 0;
        num2 = 0;

        contentPane.setBackground(Color.lightGray);
        contentPane.setLayout(null);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fr.setVisible(true);
        fr.getContentPane().add(contentPane);

        Screen.setEnabled(false);  // screen non-editable
        Screen.setShowGrid(false);  // clear the grid
        RadioButton1.setVisible(false);
        RadioButton2.setVisible(false);  // hide the button group

        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
        titleLabel.setBounds(500, 10, 400, 30); 
        contentPane.add(titleLabel);

        // awt components
        File.add(openItem);
        File.add(importItem);
        File.add(closeItem);
        MenuBar.add(File);
        fr.getContentPane().add(MenuBar, BorderLayout.NORTH);  // the menu bar lies north of the panel

        // Label of asking for input
        JLabel lblCourseCode = new JLabel("Class:");
        lblCourseCode.setFont(new Font("Arial", Font.BOLD, 18));
        lblCourseCode.setBounds(31, 60, 124, 25);
        contentPane.add(lblCourseCode);

        // Course List label
        JLabel FileList = new JLabel("Class List:");
        FileList.setFont(new Font("Arial", Font.BOLD, 18));
        FileList.setBounds(31, 513, 124, 29);
        contentPane.add(FileList);

        //Back button
        back = new JButton("RETURN");
        back.setBounds(1400, 10, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        contentPane.add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                fr.setVisible(false);
                new OptionPage();
            }
        });

        // Radio Button Ascending
        RadioButton1.setSelected(true);
        RadioButton1.setFont(new Font("Arial", Font.BOLD, 18));
        RadioButton1.setBounds(950, 98, 191, 29);
        contentPane.add(RadioButton1);
        RadioButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                RadioButton2.setSelected(false);
                RadioButton1.setSelected(true);

            }
        });

        // Radio Button Descending
        RadioButton2.setSelected(false);
        RadioButton2.setFont(new Font("Arial", Font.BOLD, 18));
        RadioButton2.setBounds(950, 134, 211, 29);
        contentPane.add(RadioButton2);
        RadioButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                RadioButton1.setSelected(false);
                RadioButton2.setSelected(true);

            }
        });

        //Creator Label
        creator.setBounds(31,675,1000,30);
        creator.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(creator);


        // Choice 1: basic permutation in 4 aspects
        Choice_1.setBackground(SystemColor.activeCaption);
        Choice_1.setFont(new Font("Arial", Font.BOLD, 18));
        Choice_1.setModel(new DefaultComboBoxModel<String>(new String[]{"Sort", "Name", "ID", "Percentage", "Grade"}));
        Choice_1.setBounds(980, 61, 133, 29);
        contentPane.add(Choice_1);
        Choice_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                num = Choice_1.getSelectedIndex();
                RadioButton1.setVisible(true);
                RadioButton2.setVisible(true);
            }
        });

        // Display Button
        JButton DisplayButton = new JButton("Display");
        DisplayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // sort and output
                try {
                    SortAndOutput();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        DisplayButton.setFont(new Font("Arial", Font.BOLD, 18));
        DisplayButton.setBounds(1150, 60, 157, 51);
        contentPane.add(DisplayButton);

        // the text field of choice 1
        CourseInput = new JTextField();
        CourseInput.setEditable(false);
        CourseInput.setFont(new Font("Arial", Font.BOLD, 16));
        CourseInput.setBounds(164, 57, 113, 33);
        contentPane.add(CourseInput);
        CourseInput.setColumns(10);

        // Query Button
        JButton QueryButton = new JButton("Query");
        QueryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Do you want to query based on current course list ??");
                Query(num2);
            }
        });
        QueryButton.setFont(new Font("Arial", Font.BOLD, 18));
        QueryButton.setBounds(727, 592, 157, 51);
        QueryButton.setVisible(false);
        contentPane.add(QueryButton);

        // Choice 2: basic & advanced search in 3 different ways
        JComboBox<String> Choice_2 = new JComboBox<String>();
        Choice_2.setEnabled(false);
        Choice_2.setFont(new Font("Arial", Font.BOLD, 18));
        Choice_2.setModel(new DefaultComboBoxModel<String>(new String[]{"Query", "ID", "Name"}));
        Choice_2.setBounds(477, 513, 133, 29);
        contentPane.add(Choice_2);
        Choice_2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                num2 = Choice_2.getSelectedIndex();
                ReqInput.setVisible(true);
                QueryButton.setVisible(true);
            }
        });

        // the text field of choice 2
        ReqInput = new JTextField();
        ReqInput.setFont(new Font("Arial", Font.BOLD, 18));
        ReqInput.setBounds(642, 517, 266, 43);
        ReqInput.setVisible(false);
        contentPane.add(ReqInput);
        ReqInput.setColumns(10);

        // Delete Button
        DeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!sta.isEmpty()) {
                    sta.pop();
                    CourseList.setText(null);
                    Choice_1.setSelectedIndex(0);
                    RadioButton1.setVisible(false);
                    RadioButton2.setVisible(false);

                    for (int i = 0; i < sta.size(); ++i) {
                        CourseList.append(sta.elementAt(i));
                    }

                    --index;
                    for (int i = 0; i < stu.size(); ++i) {
                        stu.elementAt(i).takecourse[index] = null;
                        stu.elementAt(i).courseCredit[index] = 0;
                    }

                    if (sta.isEmpty()) {
                        Choice_2.setSelectedIndex(0);
                        ReqInput.setVisible(false);
                        QueryButton.setVisible(false);
                        Choice_2.setEnabled(false);
                    }
                } // end if
            }
        });
        DeleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        DeleteButton.setBounds(198, 514, 93, 29);
        contentPane.add(DeleteButton);

        // JScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(CourseList);
        scrollPane.setBounds(31, 550, 260, 114);
        contentPane.add(scrollPane);

        // added course list
        CourseList.setEditable(false);
        CourseList.setBackground(SystemColor.activeCaptionText);
        CourseList.setForeground(SystemColor.controlHighlight);
        CourseList.setFont(new Font("Arial", Font.BOLD, 18));
        CourseList.setBounds(31, 550, 260, 114);

        // Add Button (store data)
        AddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // add a new course and store data
                if (CourseInput.getText().isEmpty()) {
                    // if the input is empty, show the message
                    JOptionPane.showMessageDialog(null, "The course name cannot be empty!");
                } else {
                    try {
                        // read the file
                        String[] src = readtxt(fileRoute);

                        // add course to the course list without repetition
                        if (!src.equals(null)) {
                            String str = CourseInput.getText() + "\n";
                            // if sta doesn't contain str
                            if (IsDifferent(sta, str)) {
                                // push the course into the stack
                                sta.push(str);
                                CourseList.setText(null);
                                for (int i = 0; i < sta.size(); ++i) {
                                    CourseList.append(sta.elementAt(i));
                                }
                                CourseList.paintImmediately(Screen.getBounds());
                                // store the course credit
                                Course cour = new Course();
                                cour.getCourseData(src[0]);
                                int tmp = Integer.parseInt(cour.credit);
                                // store the data of each student
                                Student[] ST = new Student[src.length - 2];
                                // store the data of students
                                for (int i = 0; i < ST.length; ++i) {
                                    ST[i] = new Student(); // initialization (necessary)
                                    ST[i].stu = src[i + 2];
                                    ST[i].getStuData(ST[i].stu);
                                    ST[i].takecourse[index] = CourseInput.getText();
                                    ST[i].courseCredit[index] = tmp;
                                    ST[i].credit[index] = ST[i].Grade2Credit(ST[i].Grade);
                                    // store ST[i] in stack
                                    if (IsDifferent(stu, ST[i])) {
                                        stu.push(ST[i]);
                                    } else {
                                        for (int j = 0; j < stu.size(); ++j) {
                                            if (stu.elementAt(j).id.equals(ST[i].id)) {
                                                stu.elementAt(j).credit[index] = ST[i].credit[index];
                                                stu.elementAt(j).takecourse[index] = CourseInput.getText();
                                                stu.elementAt(j).courseCredit[index] = tmp;
                                            }
                                        }
                                    }
                                }
                                ++index;  // move to the next
                                Choice_2.setEnabled(true);
                                Choice_1.setSelectedIndex(0);
                                RadioButton1.setVisible(false);
                                RadioButton2.setVisible(false);
                            } // end if
                            // if add twice
                            else {
                                JOptionPane.showMessageDialog(null, "This course has been added, please input another one!");
                            }
                        } 
                    } 
                    catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "Cannot find the course in the source, please check!");
                    }
                } // end else
                CourseInput.setText(null); // clear the textarea
            } // end the event
        });
        AddButton.setFont(new Font("Arial", Font.BOLD, 18));
        AddButton.setBounds(30, 20, 90, 29);
        AddButton.setVisible(true);
        contentPane.add(AddButton);

        // JScrollPane Screen
        JScrollPane scrollPane_Screen = new JScrollPane();
        scrollPane_Screen.setBounds(31, 181, 877, 307);
        scrollPane_Screen.setViewportView(Screen);
        contentPane.add(scrollPane_Screen);

        // the main screen
        Screen.setForeground(Color.BLACK);
        Screen.setBackground(new Color(255, 204, 153));
        Screen.setFont(new Font("SansSerif", Font.BOLD, 16));
         
        Screen.setModel(new DefaultTableModel(
             new Object[][] {
                 {null, "", "", ""},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null},
                 {null, null, null, null}
             },
             new String[] {
                 "Name", "ID", "Percentage", "Grade"
             }
         ));
         Screen.getColumnModel().getColumn(0).setPreferredWidth(200);
         Screen.getColumnModel().getColumn(1).setPreferredWidth(100);
         Screen.getColumnModel().getColumn(2).setPreferredWidth(100);
         Screen.getColumnModel().getColumn(3).setPreferredWidth(100);

         Screen.getTableHeader().setBackground(Color.ORANGE);
         Screen.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

         Screen.setBorder(new LineBorder(new Color(64, 64, 64)));
         Screen.setFont(new Font("Ebrima", Font.PLAIN, 16));
         Screen.setBounds(31, 161, 877, 307);
         Screen.paintImmediately(Screen.getBounds());
         
                 
         // label	
         lblCredit.setFont(new Font("Ebrima", Font.BOLD, 18));
         lblCredit.setBounds(31, 100, 124, 25);
         contentPane.add(lblCredit);
         
         // label
         lblNumberOfStudents.setFont(new Font("Ebrima", Font.BOLD, 18));
         lblNumberOfStudents.setBounds(31, 140, 368, 25);
         contentPane.add(lblNumberOfStudents);
         
         JLabel lblNewLabel = new JLabel("");
         lblNewLabel.setBounds(31, 169, 81, 21);
         contentPane.add(lblNewLabel);
         
         //Ä¬ÈÏÄ£Ê½Îª FileDialog.LOAD
         openDia = new FileDialog(fr,"My Opening",FileDialog.LOAD);	 
         myEvent();   
     }
     private void myEvent()
     {
         // file open
         openItem.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 openDia.setVisible(true);
                 String dirPath = openDia.getDirectory();
                 String fileName = openDia.getFile();
                 
                 fileRoute = dirPath + fileName;
                 
                 if(dirPath == null || fileName == null)  
                     return ;
  
                 CourseInput.setText(fileName);
                 
             }
         });
  
         
         // file import
         importItem.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 openDia.setVisible(true);
                 String dirPath = openDia.getDirectory(); // get the path of file
                 String fileName = openDia.getFile(); // get the name of file
                 
                 fileRoute = dirPath + fileName; // record the route of the file
                 
                 // if the path is null, return null
                 if(dirPath == null || fileName == null)  
                     return ;
  
                 // write down the filename
                 CourseInput.setText(fileName);
                 AddButton.doClick();
                 
             }
         });
         
         
         // exit
         closeItem.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 System.exit(0);
             }
         });
  
         fr.addWindowListener(new WindowAdapter()
         {
             public void windowClosing(WindowEvent e)
             {
                 System.exit(0);
             }
         });
     }
         
     
     // distinguish the different course input
     public static boolean IsDifferent(Stack<String> stack, String str) {
         boolean boo = true;
         if(stack.isEmpty()) {return true;}
         else {
             for(int i = 0 ; i < stack.size(); ++i) {
        
                 boo = boo && (!stack.get(i).equals(str));
             }
             return boo;
         }
      }
         
         
     // override
     public static boolean IsDifferent(Stack<Student> stack, Student stu) {
         boolean boo = true;
         if(stack.isEmpty()) {return true;}
         else {
             for(int i = 0 ; i < stack.size(); ++i) {
                     
                 boo = boo && (!stack.elementAt(i).id.equals(stu.id));
             }
             return boo;
         }
      }
 
     
     // read the file, and output the row string array
     public static String[] readtxt(String filename) throws IOException {
             
         try {
         BufferedReader br = new BufferedReader(new FileReader(filename));
                 
         String[] temp = new String[1000000]; // temp is an array which is used to store row String
         int i = 0; // the number of each row
             
         while(( temp[i] = br.readLine())!= null){  ++i; }	
         br.close();
             
         // resize the String
         String[] newData = new String[i];
             
         for(int j = 0; j < i; ++j) {
             newData[j] = temp[j];
             }
                return newData;
         }		
             
         // if can't read the file, output a message
         catch(Exception e) {
             JOptionPane.showMessageDialog(null, "Please open a \".txt\" file !");
             return null;
             }		
         }
         
     public static <textArea> void display(Student[] ST, Course cour) {
             
         // clear the table
         for(int i = 0; i < 50; ++i ) {
             Screen.setValueAt(null, i, 0);
             Screen.setValueAt(null, i, 1);
             Screen.setValueAt(null, i, 2);
             Screen.setValueAt(null, i, 3);
         }
             
         // print a diagram
         lblCredit.setText("Credit: " + cour.credit);
         lblNumberOfStudents.setText("Number of Students: " + cour.num);
                             
         for(int i = 0; i < ST.length; ++i) {
             Screen.setValueAt(ST[i].sur + ", " + ST[i].giv, i, 0); 
             Screen.setValueAt(ST[i].id, i, 1);
             Screen.setValueAt(ST[i].score, i, 2);
             Screen.setValueAt(ST[i].Grade, i, 3);
         }
                 
         // output the grade statistics
         String statistics = "Statistics Report:\n";
         statistics += "\nThe average score: " + Course.averscore;
         statistics += "\nThe highest score: " + Course.highscore;
         statistics += "\nThe lowest score: " + Course.lowscore;
         statistics += "\n";
         JOptionPane.showMessageDialog(null, statistics);
             
         }
         
     // sort and output method
     public static void SortAndOutput() throws IOException{
             
         // if choice 1 is 5
         if(num==5) {
             
             if(sta.isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Please import course into course list first !");
                 Choice_1.setSelectedIndex(0);		
                 RadioButton1.setVisible(false);
                 RadioButton2.setVisible(false);
             }
             
             else {
                 
                 // create an array to store the student data
                 Student[] tmpst = new Student[stu.size()];
                 
                 // copy the whole stack to array
                 for(int i = 0; i < stu.size(); ++i) {
                     tmpst[i] = new Student();
                     tmpst[i] = stu.elementAt(i);
                     tmpst[i].GPA = tmpst[i].computeGPA(tmpst[i]);
                 }
                 
                 // using sort algorithms
                 tmpst = SortAlgorithms.sortWay(SortAlgorithms.sort(tmpst, 5));
                                 
                 // output		
                 String str = "ID                                       GPA          Name\n";
                 
                 for(int i = 0; i < tmpst.length; ++i) {
                     str += tmpst[i].id + "     " + tmpst[i].GPA + "          " + tmpst[i].sur + ", " +  tmpst[i].giv + "\n";					
                 }  
                 
                 JOptionPane.showMessageDialog(null, str);
                 
                 // clear the course list
                 while(!sta.isEmpty()) {
                     DeleteButton.doClick();
                 }
     
             }
         } // end if	
                 
         else {
             
             // create ST  	
             String[] src = readtxt(fileRoute);
             Student[] ST = new Student[src.length-2];
             
                     
             // store the data of course
             Course cour = new Course();
             cour.coName = CourseInput.getText();
             cour.course = src[0];
             cour.getCourseData(cour.course);
             cour.num = Integer.parseInt(src[1]);
                     
             // store the data of students
             for(int i = 0; i < ST.length; ++i) {
                         
                 ST[i] = new Student(); // initialization (necessary)
                 ST[i].stu = src[i+2];
                 ST[i].getStuData(ST[i].stu); 	    		
                                
                 if(Course.lowscore > ST[i].score) {Course.lowscore = (int)ST[i].score;}  // update the low score of this course
                 if(Course.highscore < ST[i].score) {Course.highscore = (int)ST[i].score;} // update the high score of this course
                         
                 Course.averscore += ST[i].score; 
             }
                 Course.averscore /= ST.length; 
                 Course.averscore = (double) Math.round(Course.averscore * 100) / 100; 
                 display(SortAlgorithms.sortField(ST),cour);  
              } 
         }
     public static void Query(int num2){
         Stack<Student> auxstu = new Stack<Student>();
         for(int i = 0; i < stu.size(); ++i) {
             auxstu.push(stu.elementAt(i));
         }
             
         int t = 0;  
         int t2 = 0; 
         int t3 = 0; 
         String str2 = "";  
         String str3 = "";  
         String str4 = "";  
         for(int i = 0; !auxstu.isEmpty(); ++i) {
                 
             Student[] st = new Student[stu.size()];
             st[i] = new Student();
             st[i] = auxstu.pop();
             st[i].GPA = st[i].computeGPA(st[i]);
             switch(num2) {
             case 1:
                 if(ReqInput.getText().equals(st[i].id)) {
                     String str = st[i].sur + "," + st[i].giv + "  " + st[i].id + "\n\n" + "Taken Course: \n";
                     for(int j = 0; j < st[i].takecourse.length; ++j) {
                         if(st[i].takecourse[j] == null) {continue;} 
                             str += st[i].takecourse[j] + ":  " + st[i].Credit2Grade(st[i], j) + "\n";
                     }
                     str += "\nGPA:  " + st[i].GPA;
                     JOptionPane.showMessageDialog(null, str); 
                 }	    	    
                 break;
             case 2:
                 if(auxstu.isEmpty()) {
                     JOptionPane.showMessageDialog(null, "There are " + t2 + " outcome(s) in UGMS: \n\n" + str2);   	    
                 }	
                 if(ReqInput.getText().equals(st[i].sur)) {
                     ++t2; 
                     str2 += st[i].sur + "," + st[i].giv + "  " + st[i].id + "\n" + "Taken Course: \n";
                     for(int j = 0; j < st[i].takecourse.length; ++j) {
                         if(st[i].takecourse[j] == null) {continue;} 
                         str2 += st[i].takecourse[j] + ":  " + st[i].Credit2Grade(st[i], j) + "\n";
                     }
                     str2 += "GPA:  " + st[i].GPA + "\n\n";
                 }		
                 break;    	    	
                 case 3:
                     String string = ReqInput.getText();
                     String pattern = "^"+ string +"[a-z]*";
                     Pattern r = Pattern.compile(pattern);
                     
                     // show the final output	
                     if(auxstu.isEmpty()) {
                         JOptionPane.showMessageDialog(null, "There are " + t + " outcome(s) in UGMS: \n\n" + str3);  
                     }   
                                     
                     if(r.matcher(st[i].sur).matches()) {
                         str3 += st[i].sur + "," + st[i].giv + "  " + st[i].id + "\n" + "Taken Course: \n";
                         for(int j = 0; j < st[i].takecourse.length; ++j) {
                             
                             if(st[i].takecourse[j] == null) {continue;} 
                             str3 += st[i].takecourse[j] + ":  " + st[i].Credit2Grade(st[i], j) + "\n";
                             
                         }
                         
                         str3 += "GPA:  " + st[i].GPA + "\n\n";
                         ++t;
                                                  
                     }	
                     
                 break; 
                     
                 case 4:
                     
                     String op = ReqInput.getText().split(" ")[0];   
                     double num = Double.parseDouble(ReqInput.getText().split(" ")[1]);   
                     
                     // show the final output	
                     if(auxstu.isEmpty()) {
                         JOptionPane.showMessageDialog(null, "There are " + t3 + " outcome(s) in UGMS: \n\n" + str4);
                         }
                         
                     
                     if(op.equals(">")) {
                         
                         if(st[i].GPA > num) {
                             ++t3;	    	    			
                             str4 += st[i].sur + "," + st[i].giv + "  " + st[i].id + "\n" + "GPA:  " + st[i].GPA + "\n\n";
                         }
                                             
                     }
                     else if(op.equals("<")) {
                         if(st[i].GPA < num) {
                             ++t3;	    	    			
                             str4 += st[i].sur + "," + st[i].giv + "  " + st[i].id + "\n" + "GPA:  " + st[i].GPA + "\n\n";	    	  		
                         }
                     }
                     else if(op.equals(">=")) {
                         if(st[i].GPA >= num) {
                             ++t3;	    	    			
                             str4 += st[i].sur + "," + st[i].giv + "  " + st[i].id + "\n" + "GPA:  " + st[i].GPA + "\n\n";
                         }
                     }
                     else if(op.equals("<=")) {
                         if(st[i].GPA <= num) {
                             ++t3;
                             str4 += st[i].sur + "," + st[i].giv + "  " + st[i].id + "\n" + "GPA:  " + st[i].GPA + "\n\n";
                         }
                     }
                     else if(op.equals("=")) {
                         if(st[i].GPA == num) {
                             ++t3;
                             str4 += st[i].sur + "," + st[i].giv + "  " + st[i].id + "\n" + "GPA:  " + st[i].GPA + "\n\n";
                         }
                     }
                                 
                 break;	
                         
                 } 
             }       
         }      
 } 