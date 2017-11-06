import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

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

		JTextField name = new JTextField(db.getName());
		name.setEditable(false);
		name.setPreferredSize(new Dimension(520,45));
		name.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		name.setBackground(Color.WHITE);
		name.setBorder(null);

		JTextField major = new JTextField(db.getMajor());
		major.setEditable(false);
		major.setPreferredSize(new Dimension(520,45));
		major.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		major.setBackground(Color.WHITE);
		major.setBorder(null);

		JTextField university = new JTextField(db.getUniversity());
		university.setEditable(false);
		university.setPreferredSize(new Dimension(520,45));
		university.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		university.setBackground(Color.WHITE);
		university.setBorder(null);

		JTextField sort = new JTextField(db.getSortSelection());
		sort.setEditable(false);
		sort.setPreferredSize(new Dimension(520,45));
		sort.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		sort.setBackground(Color.WHITE);
		sort.setBorder(null);

		JLabel header = new JLabel("Profile");
		JLabel title1 = new JLabel("NAME");
		JLabel title2 = new JLabel("MAJOR");
		JLabel title3 = new JLabel("UNIVERSITY");
		JLabel title4 = new JLabel("INSTRUCTORS");
		JLabel title5 = new JLabel("COURSES");
		JLabel title6 = new JLabel("SORT BY");

		header.setFont(new Font("Segoe UI", Font.PLAIN, 50));
		title1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		title2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		title3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		title4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		title5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		title6.setFont(new Font("Segoe UI", Font.PLAIN, 20));


		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(0, 10, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.NONE;
		p.setBackground(Color.white);
		p.add(header, gbc);

		gbc.gridy++;
		gbc.insets = new Insets(20, 10, 0, 0);
		p.add(title1, gbc);

		gbc.gridy++;
		gbc.insets = new Insets(0, 10, 0, 0);
		p.add(name, gbc);

		gbc.gridy++;
		gbc.insets = new Insets(20, 10, 0, 0);
		p.add(title2, gbc);

		gbc.gridy++;
		gbc.insets = new Insets(0, 10, 0, 0);
		p.add(major, gbc);

		gbc.gridy++;
		gbc.insets = new Insets(20, 10, 0, 0);
		p.add(title3, gbc);

		gbc.gridy++;
		gbc.insets = new Insets(0, 10, 0, 0);
		p.add(university, gbc);

		gbc.gridy++;
		gbc.insets = new Insets(20, 10, 0, 0);
		p.add(title6, gbc);

		gbc.gridy++;
		gbc.insets = new Insets(0, 10, 0, 0);
		p.add(sort, gbc);

		//Create a combo box
		String[] choices = {"Edit personal information", "Save Changes", "Change sorting selection"};
		JComboBox<String> menu = new JComboBox<String>(choices);
		menu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menu.setPreferredSize(new Dimension(195,35));

		menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(menu.getSelectedItem().equals("Edit personal information")) {
					name.setEditable(true);
					major.setEditable(true);
					university.setEditable(true);
					Border border = BorderFactory.createLineBorder(Color.black, 2);
					name.setBorder(border);
					major.setBorder(border);
					university.setBorder(border);
				}else if(menu.getSelectedItem().equals("Change sorting selection")) {
					JCheckBox instructor = new JCheckBox("Instructor");
					instructor.setFont(new Font("Segoe UI", Font.PLAIN, 20));
					instructor.setMnemonic(KeyEvent.VK_C); 
					instructor.setBackground(Color.white);

					JCheckBox course = new JCheckBox("Course");
					course.setFont(new Font("Segoe UI", Font.PLAIN, 20));
					course.setMnemonic(KeyEvent.VK_G); 
					course.setBackground(Color.white);


					JLabel lb = new JLabel("SORT BY");
					lb.setFont(new Font("Segoe UI", Font.PLAIN, 30));

					//Create a panel and frame
					JPanel optionPanel = new JPanel(new GridBagLayout());
					GridBagConstraints gbc2 = new GridBagConstraints();
					gbc2.anchor = GridBagConstraints.NORTHWEST;
					gbc2.insets = new Insets(0, 20, 0, 0);
					gbc2.gridx = 0;
					gbc2.gridy = 0;
					gbc2.weightx = 1;
					gbc2.fill = GridBagConstraints.NONE;
					optionPanel.setBackground(Color.white);
					optionPanel.add(lb, gbc2);

					gbc2.gridy++;
					gbc2.insets = new Insets(20, 20, 0, 0);
					optionPanel.add(instructor, gbc2);

					gbc2.gridy++;
					gbc2.insets = new Insets(0, 20, 0, 0);
					optionPanel.add(course, gbc2);

					// Frame setting
					JFrame sortFrame = new JFrame();
					sortFrame.setLayout(new FlowLayout());
					sortFrame.setSize(300, 220);
					sortFrame.getContentPane().setLayout(new BorderLayout());
					JPanel empty = new JPanel();
					empty.setBackground(Color.white);
					sortFrame.add(optionPanel, BorderLayout.PAGE_START);
					sortFrame.add(empty);

					sortFrame.setLocationRelativeTo(null);
					sortFrame.setVisible(true);

					instructor.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							db.setSortSelection("instructor");
							try {
								db.changePersonalInfo("instructor", 3);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							sort.setText("instructor");
							//Close frame
							sortFrame.setVisible(false);;
						}
					});

					course.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							db.setSortSelection("course");
							try {
								db.changePersonalInfo("course", 3);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							sort.setText("course");
							//Close frame
							sortFrame.setVisible(false);;
						}
					});
				}else if(menu.getSelectedItem().equals("Save Changes")) {
					name.setEditable(false);
					major.setEditable(false);
					university.setEditable(false);
					name.setBorder(null);
					major.setBorder(null);
					university.setBorder(null);
					try {
						db.addPersonalInfo(name.getText(), major.getText(), university.getText(), db.getSortSelection());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		});

		gbc.gridy++;
		gbc.insets = new Insets(20, 10, 0, 0);
		p.add(menu, gbc);

		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.insets = new Insets(20, 10, 0, 0);
		p.add(title4, gbc);

		//Get the array of courses and instructors
		String[][] ci = db.getCourseAndINstructor();	    

		//Add the instructors
		for(int i = 0; i < ci.length; i++) {
			if(ci[i][1] != null) {
				JLabel instructor = new JLabel(ci[i][1]);
				instructor.setFont(new Font("Segoe UI", Font.PLAIN, 30));
				gbc.gridy++;
				gbc.insets = new Insets(0, 10, 0, 0);
				p.add(instructor, gbc);
			}
		}

		gbc.gridy++;
		gbc.insets = new Insets(20, 10, 0, 0);
		p.add(title5, gbc);

		//Add the courses	    
		for(int i = 0; i < ci.length; i++) {
			if(ci[i][0] != null) {
				JLabel course = new JLabel(ci[i][0]);
				course.setFont(new Font("Segoe UI", Font.PLAIN, 30));
				gbc.gridy++;
				gbc.insets = new Insets(0, 10, 0, 0);
				p.add(course, gbc);
			}
		}




		frame.getContentPane().setLayout(new BorderLayout());
		frame.add(p, BorderLayout.PAGE_START);
		JPanel white = new JPanel();
		white.setBackground(Color.white);
		frame.add(white);

		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);




	}
}

