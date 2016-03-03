package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.SimpleClient;
import model.Student;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainClientUI extends JFrame {

	private JPanel contentPane;
	private JTextField simpleTextField;
	JPanel simpleStringPanel;
	JPanel complexObjectPanel;
	JTabbedPane tabbedPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtID;
	private JTextField txtName;
	private JButton btnSaveObject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClientUI frame = new MainClientUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainClientUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		simpleStringPanel = new JPanel();
		complexObjectPanel = new JPanel();
		complexObjectPanel.setLayout(new GridLayout(0, 2));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Simple String", simpleStringPanel);
		
		JLabel lblEnterString = new JLabel("Enter String:");
		simpleStringPanel.add(lblEnterString);
		
		simpleTextField = new JTextField();
		simpleStringPanel.add(simpleTextField);
		simpleTextField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		simpleStringPanel.add(textArea);
		
		JButton btnSaveString = new JButton("Save String");
		
		simpleStringPanel.add(btnSaveString);
		tabbedPane.addTab("Complex Object", complexObjectPanel);
		
		txtID = new JTextField("sd");
		lblNewLabel_1 = new JLabel("ID");
		complexObjectPanel.add(lblNewLabel_1);
		
		txtName = new JTextField();
		complexObjectPanel.add(txtID);
		txtName.setColumns(10);
		
		complexObjectPanel.add(txtID);
		txtID.setColumns(10);
		
		lblNewLabel = new JLabel("Name");
		complexObjectPanel.add(lblNewLabel);
		
		txtName = new JTextField("sds");
		complexObjectPanel.add(txtName);
		txtName.setColumns(10);
		
		btnSaveObject = new JButton("Save Object");
		complexObjectPanel.add(btnSaveObject);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		
		btnSaveString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String simpleString = simpleTextField.getText();
				Long id = SimpleClient.saveObject(simpleString);
				if (id != null) {
					JOptionPane.showMessageDialog(MainClientUI.this, "Success, id=" + id);
				} else {
					JOptionPane.showMessageDialog(MainClientUI.this, "Saving failed!");
				}
			}
		});
		
		btnSaveObject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//get information from GUI text fields
				String studentId = txtID.getText();
				String name = txtName.getText();
				
				//create serializable object
				Student s = new Student();
				s.setId(studentId);
				s.setName(name);
			
				Long id = SimpleClient.saveObject(s);
				if (id != null) {
					JOptionPane.showMessageDialog(MainClientUI.this, "Success, id=" + id);
				} else {
					JOptionPane.showMessageDialog(MainClientUI.this, "Saving failed!");
				}
			}
		});
		
	}

}
