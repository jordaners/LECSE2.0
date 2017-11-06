import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ProfileGUI {
	

	public static void main(String[] args) throws FileNotFoundException, IOException{
		//Load the database
		Database db = new Database();
		db.loadUserInfo();
		db.loadCourseInstructor();
		db.loadLectures();
		
	    JFrame frame = new JFrame();
	    frame.setSize(900, 1000);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new FlowLayout()); 
	    
	    JLabel header = new JLabel("Profile");
	    JLabel name = new JLabel(db.getName());
	    JLabel major = new JLabel(db.getMajor());
	    JLabel university = new JLabel(db.getUniversity());
	    JLabel title1 = new JLabel("NAME");
	    JLabel title2 = new JLabel("MAJOR");
	    JLabel title3 = new JLabel("UNIVERSITY");
	    JLabel title4 = new JLabel("INSTRUCTORS");
	    JLabel title5 = new JLabel("COURSES");

	    header.setFont(new Font("Segoe UI", Font.PLAIN, 50));
	    title1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	    title2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	    title3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	    title4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	    title5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	    name.setFont(new Font("Segoe UI", Font.PLAIN, 30));
	    major.setFont(new Font("Segoe UI", Font.PLAIN, 30));
	    university.setFont(new Font("Segoe UI", Font.PLAIN, 30));
	    
	    Box verticalBox = Box.createVerticalBox();
	    verticalBox.add(header);
	    verticalBox.add(Box.createRigidArea(new Dimension(15,20)));
	    verticalBox.add(title1);
	    verticalBox.add(name);
	    verticalBox.add(Box.createRigidArea(new Dimension(15,20)));
	    verticalBox.add(title2);
	    verticalBox.add(major);
	    verticalBox.add(Box.createRigidArea(new Dimension(15,20)));
	    verticalBox.add(title3);
	    verticalBox.add(university);
	    verticalBox.add(Box.createRigidArea(new Dimension(15,20)));
	    verticalBox.add(title4);
	    
	    //Get the array of courses and instructors
	    String[][] ci = db.getCourseAndINstructor();	    
	    
	    //Add the instructors
	    for(int i = 0; i < ci.length; i++) {
	    	if(ci[i][1] != null) {
	    		JLabel instructor = new JLabel(ci[i][1]);
	    		instructor.setFont(new Font("Segoe UI", Font.PLAIN, 30));
	    		verticalBox.add(instructor);
	    	}
	    }
	    
	    verticalBox.add(Box.createRigidArea(new Dimension(15,20)));
	    verticalBox.add(title5);
	    
	    //Add the courses	    
	    for(int i = 0; i < ci.length; i++) {
	    	if(ci[i][0] != null) {
	    		JLabel course = new JLabel(ci[i][0]);
	    		course.setFont(new Font("Segoe UI", Font.PLAIN, 30));
	    		verticalBox.add(course);
	    	}
	    }
	   
	    //Create a button
	    JButton bn = new JButton("EDIT");
	    bn.setPreferredSize(new Dimension(300,200));
	    bn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
        	    String name = JOptionPane.showInputDialog("NEW NAME");
        	    try {
					db.changePersonalInfo(name, 0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	    db.setName(name);

            }

        });
	    
	    verticalBox.add(Box.createRigidArea(new Dimension(15,30)));
	    verticalBox.add(bn);
	    
 
	    
	    JPanel p = new JPanel(new GridLayout(1,2));
	    p.add(verticalBox);
	    p.setBackground(Color.white);
	    frame.setContentPane(p);
	    
	    frame.setResizable(true);

	    frame.setVisible(true);


 

	}
}