package com.vieejtddwcs.classmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

class GUI implements FocusListener {
	
	private JFrame frame;
	
	JTextField searchBar1;
	JTextField searchBar2;
	JTextField searchBar3;
	private JButton searchBtn1;
	private JButton searchBtn2;
	private JButton searchBtn3;
	
	JTable table1;
	private JButton addBtn1;
	private JButton editBtn;
	private JButton deleteBtn1;
	private JButton seeScheduleBtn;
	private JButton importBtn1;
	private JButton exportBtn1;
	private JLabel statusBar1;
	
	JTable table2;
	JComboBox<String> morningCB;
	JComboBox<String> afternoonCB;
	JComboBox<String> eveningCB;
	private JButton applyBtn2;
	private JButton deleteBtn2;
	private JButton exportBtn2;
	private JLabel statusBar2;
	
	JTable table3;
	private JButton addBtn3;
	private JButton deleteBtn3;
	private JButton importBtn3;
	private JButton saveBtn;
	private JLabel statusBar3;
	
	static String[] CLASS_COL_NAMES = {"STT", "Mã sinh viên", "Họ đệm", "Tên", "Ghi chú"};
	static String[] GROUP_COL_NAMES = {"STT", "Mã sinh viên", "Họ đệm", "Tên", "Nhóm", "Ghi chú"};
	
	DefaultTableModel tableModel1 = new DefaultTableModel() {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	DefaultTableModel tableModel2 = new DefaultTableModel() {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	DefaultTableModel tableModel3 = new DefaultTableModel() {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	GUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Quản Lý Lớp");
		frame.setSize(1085, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();
		tabbedPane.addTab("Danh sách sinh viên", null, panel1, null);
		
		JScrollPane scrollPane1 = new JScrollPane();
		
		searchBar1 = new JTextField("Nhập tên hoặc mã sinh viên");
		searchBar1.setColumns(10);
		searchBar1.addFocusListener(this);
		
		searchBtn1 = new JButton("Tìm");
		searchBtn1.setActionCommand("searchStudent1");
		
		addBtn1 = new JButton("Thêm sinh viên");
		addBtn1.setActionCommand("addStudent1");
		
		editBtn = new JButton("Sửa sinh viên");
		editBtn.setActionCommand("editStudent");
		
		deleteBtn1 = new JButton("Xóa sinh viên");
		deleteBtn1.setActionCommand("deleteStudent1");
		
		seeScheduleBtn = new JButton("Xem thời gian biểu");
		seeScheduleBtn.setActionCommand("seeSchedule");
		
		importBtn1 = new JButton("Import dữ liệu");
		importBtn1.setActionCommand("importData1");
		
		exportBtn1 = new JButton("Xuất ra file");
		exportBtn1.setActionCommand("exportToFile1");
		
		statusBar1 = new JLabel("");
		
		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1.setHorizontalGroup(
			gl_panel1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
						.addGroup(gl_panel1.createSequentialGroup()
							.addComponent(addBtn1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteBtn1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(seeScheduleBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(importBtn1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(exportBtn1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
							.addComponent(searchBar1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(statusBar1))
					.addContainerGap())
		);
		gl_panel1.setVerticalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(searchBar1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(exportBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(importBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(seeScheduleBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(deleteBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(editBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(addBtn1, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(searchBtn1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(statusBar1)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table1 = new JTable();
		table1.setRowHeight(17);
		table1.setGridColor(Color.LIGHT_GRAY);
		scrollPane1.setViewportView(table1);
		panel1.setLayout(gl_panel1);
		
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Xếp lịch học", null, panel2, null);
		
		JScrollPane scrollPane2 = new JScrollPane();
		
		searchBar2 = new JTextField("Nhập tên hoặc mã sinh viên");
		searchBar2.setColumns(10);
		searchBar2.addFocusListener(this);
		
		searchBtn2 = new JButton("Tìm");
		searchBtn2.setActionCommand("searchStudent2");

		applyBtn2 = new JButton("Áp dụng");
		applyBtn2.setActionCommand("applySchedule");
		
		deleteBtn2 = new JButton("Xóa sinh viên");
		deleteBtn2.setActionCommand("deleteStudent2");

		exportBtn2 = new JButton("Xuất ra file");
		exportBtn2.setActionCommand("exportToFile2");
		
		JLabel label1 = new JLabel("Sáng");
		
		morningCB = new JComboBox<String>();
		morningCB.setActionCommand("Sáng");
		morningCB.addItem("(không chọn)");
		morningCB.addItem("Thứ 2");
		morningCB.addItem("Thứ 3");
		morningCB.addItem("Thứ 4");
		morningCB.addItem("Thứ 5");
		morningCB.addItem("Thứ 6");
		morningCB.addItem("Thứ 7");
		
		JLabel label2 = new JLabel("Chiều");
		
		afternoonCB = new JComboBox<String>();
		afternoonCB.setActionCommand("Chiều");
		afternoonCB.addItem("(không chọn)");
		afternoonCB.addItem("Thứ 2");
		afternoonCB.addItem("Thứ 3");
		afternoonCB.addItem("Thứ 4");
		afternoonCB.addItem("Thứ 5");
		afternoonCB.addItem("Thứ 6");
		afternoonCB.addItem("Thứ 7");
		
		JLabel label3 = new JLabel("Tối");
		
		eveningCB = new JComboBox<String>();
		eveningCB.setActionCommand("Tối");
		eveningCB.addItem("(không chọn)");
		eveningCB.addItem("Thứ 2");
		eveningCB.addItem("Thứ 3");
		eveningCB.addItem("Thứ 4");
		eveningCB.addItem("Thứ 5");
		eveningCB.addItem("Thứ 6");
		eveningCB.addItem("Thứ 7");
		
		statusBar2 = new JLabel("");
		
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(
			gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE)
						.addGroup(gl_panel2.createSequentialGroup()
							.addComponent(label1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(morningCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(afternoonCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(eveningCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(applyBtn2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteBtn2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(exportBtn2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
							.addComponent(searchBar2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(statusBar2))
					.addContainerGap())
		);
		gl_panel2.setVerticalGroup(
			gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
						.addComponent(searchBtn2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel2.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(searchBar2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(label1)
							.addComponent(morningCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label2)
							.addComponent(afternoonCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label3)
							.addComponent(eveningCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(applyBtn2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(deleteBtn2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(exportBtn2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(statusBar2)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		table2 = new JTable();
		table2.setRowHeight(17);
		table2.setGridColor(Color.LIGHT_GRAY);
		scrollPane2.setViewportView(table2);
		panel2.setLayout(gl_panel2);
		
		JPanel panel3 = new JPanel();
		tabbedPane.addTab("Danh sách nhóm học", null, panel3, null);
		
		JScrollPane scrollPane3 = new JScrollPane();
		
		searchBar3 = new JTextField("Nhập tên hoặc mã sinh viên");
		searchBar3.setColumns(10);
		searchBar3.addFocusListener(this);
		
		searchBtn3 = new JButton("Tìm");
		searchBtn3.setActionCommand("searchStudent3");
		
		addBtn3 = new JButton("Thêm sinh viên");
		addBtn3.setActionCommand("addStudent3");
		
		deleteBtn3 = new JButton("Xóa sinh viên");
		deleteBtn3.setActionCommand("deleteStudent3");
		
		importBtn3 = new JButton("Import dữ liệu");
		importBtn3.setActionCommand("importData3");
		
		saveBtn = new JButton("Lưu thay đổi");
		saveBtn.setActionCommand("saveFile");
		
		statusBar3 = new JLabel("");
		
		GroupLayout gl_panel3 = new GroupLayout(panel3);
		gl_panel3.setHorizontalGroup(
			gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
						.addGroup(gl_panel3.createSequentialGroup()
							.addComponent(addBtn3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteBtn3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(importBtn3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
							.addComponent(searchBar3, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(statusBar3))
					.addContainerGap())
		);
		gl_panel3.setVerticalGroup(
			gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(searchBtn3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(searchBar3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(addBtn3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addComponent(deleteBtn3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(importBtn3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(statusBar3)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table3 = new JTable();
		table3.setRowHeight(17);
		table3.setGridColor(Color.LIGHT_GRAY);
		scrollPane3.setViewportView(table3);
		panel3.setLayout(gl_panel3);
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == searchBar1) {
			searchBar1.setText(null);
			frame.getRootPane().setDefaultButton(searchBtn1);
		}
		if (e.getSource() == searchBar2) {
			searchBar2.setText(null);
			frame.getRootPane().setDefaultButton(searchBtn2);
		}
		if (e.getSource() == searchBar3) {
			searchBar3.setText(null);
			frame.getRootPane().setDefaultButton(searchBtn3);
		}
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		
	}
	
	void addListener(ActionListener listener) {
		searchBtn1.addActionListener(listener);
		addBtn1.addActionListener(listener);
		editBtn.addActionListener(listener);
		deleteBtn1.addActionListener(listener);
		seeScheduleBtn.addActionListener(listener);
		importBtn1.addActionListener(listener);
		exportBtn1.addActionListener(listener);
		
		searchBtn2.addActionListener(listener);
		applyBtn2.addActionListener(listener);
		deleteBtn2.addActionListener(listener);
		exportBtn2.addActionListener(listener);
		morningCB.addActionListener(listener);
		afternoonCB.addActionListener(listener);
		eveningCB.addActionListener(listener);
		
		searchBtn3.addActionListener(listener);
		addBtn3.addActionListener(listener);
		deleteBtn3.addActionListener(listener);
		importBtn3.addActionListener(listener);
		saveBtn.addActionListener(listener);
	}
	
	void addListener(KeyListener listener) {
		frame.addKeyListener(listener);
		table1.addKeyListener(listener);
		table2.addKeyListener(listener);
		table3.addKeyListener(listener);
	}
	
	void displayContent(List<Student> list, int tableNumber, String group) {
		if (tableNumber == 1) {
			tableModel1.setDataVector(StudentList.get2DArray(list, 5, null), CLASS_COL_NAMES);
			table1.setModel(tableModel1);
		}
		if (tableNumber == 2) {
			tableModel2.setDataVector(StudentList.get2DArray(list, 6, group), GROUP_COL_NAMES);
			table2.setModel(tableModel2);
		}
		if (tableNumber == 3) {
			tableModel3.setDataVector(StudentList.get2DArray(list, 6, group), GROUP_COL_NAMES);
			table3.setModel(tableModel3);
		}
	}
	
	void setStatusBar(String str) {
		statusBar1.setText(str);
		statusBar2.setText(str);
		statusBar3.setText(str);
	}
	
	JFileChooser createFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
		fileChooser.setFileFilter(filter);
		return fileChooser;
	}
	
	void createMessageDialog(String msg) {
		JOptionPane.showMessageDialog(null,
			 	msg,
			 	"Quản Lý Lớp",
			 	JOptionPane.WARNING_MESSAGE);
	}
	
	int createYesNoDialog(String msg) {
		return JOptionPane.showOptionDialog(null,
				msg,
				"Quản Lý Lớp",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				new String[] {"Có", "Không"},
				null);
	}
}
