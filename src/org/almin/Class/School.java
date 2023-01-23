package org.almin.Class;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class School extends JFrame {

	private List<JButton> studentButtonlist;
	private JPanel mainPanelRight, studentPanel;
	private JSplitPane splitPane;
	private JLabel lablForTextDoc;
	private JButton studentButton;
	private List<Student> students;
	private JTextArea textArea;
	private List<String> listOfFinalResult;
	private JButton save;
	private LocalDateTime localDateTime;

	protected School() {
		configPanels();
		add(splitPane);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

		save.addActionListener(x -> {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
			String name = getTitle() + "_" + localDateTime.getDayOfMonth() + "." + localDateTime.getMonthValue();
			fileChooser.setSelectedFile(new File(name + ".txt"));
			fileChooser.setFileFilter(new FileNameExtensionFilter("txt file", "txt"));

			if ((fileChooser.showSaveDialog(mainPanelRight) == JFileChooser.APPROVE_OPTION)) {
				String filename = fileChooser.getSelectedFile().toString();
				if (!filename.endsWith(".txt"))
					filename += ".txt";				
				try {
					FileWriter fileWriter = new FileWriter(filename);
					fileWriter.write("Datum: " + localDateTime.getDayOfMonth() + " " + localDateTime.getMonthValue()
							+ " " + localDateTime.getYear() + "\n");
					for (String s : listOfFinalResult) {
						fileWriter.write(s + "\n");
					}
					fileWriter.close();
					JOptionPane.showMessageDialog(null, "File Saved");
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		});
	}

	public void populateClass(int numberOfStudentsInClass, JPanel studentPanel, Student student) {
		studentButtonlist = new ArrayList<>();
		students = new ArrayList<>();

		for (int i = 1; i <= numberOfStudentsInClass; i++) {

			student = new Student(i);
			studentButton = new JButton("" + i);
			studentButton.setBackground(Color.GREEN);
			studentButton.addActionListener(new OnClickButton());
			studentButtonlist.add(studentButton);
			students.add(student);
		}

		for (int i = 0; i < studentButtonlist.size(); i++) {
			studentPanel.add(studentButtonlist.get(i));
		}
	}

	public void studentClickOnButton(JButton button) {
		if (button.getBackground().equals(Color.GREEN)) {
			Student student1 = students.stream()
					.filter(student -> student.getLogNumber() == Integer.parseInt(button.getText())).findFirst().get();
			button.setBackground(Color.red);
			student1.setStartTime(System.nanoTime());
		} else {
			button.setBackground(Color.GREEN);
			Student student1 = students.stream()
					.filter(student -> student.getLogNumber() == Integer.parseInt(button.getText())).findFirst().get();
			student1.setEndTime(System.nanoTime() - student1.getStartTime());
			student1.setResultTime((student1.getEndTime()) / 1000000000);
			student1.setMin(student1.getResultTime() / 60);
			student1.setSec(student1.getResultTime() % 60);
			lablForTextDoc.setText("Ucenik br: " + student1.getLogNumber() + " vani je bio: " + student1.getMin() + " min "
					+ student1.getSec() + " sec");
			lablForTextDoc.setVisible(true);
			listOfFinalResult.add(lablForTextDoc.getText());
			textArea.append(lablForTextDoc.getText() + "\n");

		}
	}

	public void configPanels() {

		save = new JButton("Save All");
		lablForTextDoc = new JLabel();
		textArea = new JTextArea();
		listOfFinalResult = new ArrayList<>();
		textArea.setBackground(Color.decode("#f0d36c"));
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		textArea.setFont(new Font("Verdana", Font.BOLD, 22));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(250, 400));
		splitPane = new JSplitPane();
		mainPanelRight = new JPanel();
		studentPanel = new JPanel();
		studentPanel.setLayout(new GridLayout(6, 4));
		splitPane.setDividerLocation(500);

		mainPanelRight.setLayout(new BoxLayout(mainPanelRight, BoxLayout.Y_AXIS));

		localDateTime = LocalDateTime.now();
		localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		JLabel vrijeme = new JLabel(localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		vrijeme.setFont(new Font("Verdana", Font.BOLD, 20));
		mainPanelRight.add(vrijeme);
		mainPanelRight.add(scrollPane);
		mainPanelRight.add(save);
		mainPanelRight.setBackground(Color.decode("#a7f06c"));

		studentPanel.setBackground(Color.decode("#a7f06c"));
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

		splitPane.setLeftComponent(studentPanel);
		splitPane.setRightComponent(mainPanelRight);
	}

	public class OnClickButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton studentButtonresult = (JButton) e.getSource();
			studentClickOnButton(studentButtonresult);
		}
	}

	public List<JButton> getStudents() {
		return studentButtonlist;
	}

	public void setStudents(List<JButton> students) {
		this.studentButtonlist = students;
	}

	public JPanel getMainPanelRight() {
		return mainPanelRight;
	}

	public void setMainPanelRight(JPanel mainPanelRight) {
		this.mainPanelRight = mainPanelRight;
	}

	public JPanel getStudentPanel() {
		return studentPanel;
	}

	public void setStudentPanel(JPanel studentPanel) {
		this.studentPanel = studentPanel;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	public JLabel getLablForTextDoc() {
		return lablForTextDoc;
	}

	public void setLablForTextDoc(JLabel lablForTextDoc) {
		this.lablForTextDoc = lablForTextDoc;
	}

	

}
