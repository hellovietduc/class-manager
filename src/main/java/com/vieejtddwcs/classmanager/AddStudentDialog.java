package com.vieejtddwcs.classmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

class AddStudentDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JButton addBtn;
	private JButton cancelBtn;
	
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel() {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 0) return true;
			return false;
		}
		
		@Override
		public Class<?> getColumnClass(int colIndex) {
			return getValueAt(0, colIndex).getClass();
		}
	};

	AddStudentDialog() {
		final JPanel contentPanel = new JPanel();
		setResizable(false);
		setTitle("Thêm sinh viên");
		setSize(610, 425);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addBtn = new JButton("Thêm");
				addBtn.setActionCommand("add");
				buttonPane.add(addBtn);
				getRootPane().setDefaultButton(addBtn);
			}
			{
				cancelBtn = new JButton("Hủy");
				cancelBtn.setActionCommand("cancel");
				cancelBtn.addActionListener(this);
				buttonPane.add(cancelBtn);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
	
	void addListener(ActionListener listener) {
		addBtn.addActionListener(listener);
	}
	
	List<Student> getSelectedStudents(List<Student> list) {
		int[] selectedRows = table.getSelectedRows();
		List<Student> selectedStudents = new ArrayList<>();
		
		for (int i = 0; i < selectedRows.length; i++) {
			selectedStudents.add(list.get(selectedRows[i]));
		}
		
		return selectedStudents;
	}
	
	void displayContent(List<Student> list) {
		String[] colNames = {"STT", "Mã sinh viên", "Họ đệm", "Tên"};
		Object[][] content = StudentList.get2DArray(list, 4, null);
		
		tableModel.setDataVector(content, colNames);
		table.setModel(tableModel);
	}
	
}
