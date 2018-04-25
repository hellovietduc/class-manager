package com.vieejtddwcs.classmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

class StudentDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField nameTF;
	private JTextField IDTF;
	private JTextField noteTF;
	private JButton addBtn;
	private JButton resetBtn;
	private JButton doneBtn;
	
	private JLabel nameLb;
	private JLabel IDLb;
	private JLabel checkBoxLb;
	private JLabel statusLb;
	
	private JCheckBox s2CB;
	private JCheckBox s3CB;
	private JCheckBox s4CB;
	private JCheckBox s5CB;
	private JCheckBox s6CB;
	private JCheckBox s7CB;
	private JCheckBox c2CB;
	private JCheckBox c3CB;
	private JCheckBox c4CB;
	private JCheckBox c5CB;
	private JCheckBox c6CB;
	private JCheckBox c7CB;
	private JCheckBox t2CB;
	private JCheckBox t3CB;
	private JCheckBox t4CB;
	private JCheckBox t5CB;
	private JCheckBox t6CB;
	private JCheckBox t7CB;

	StudentDialog() {
		final JPanel contentPanel = new JPanel();
		setResizable(false);
		setTitle("Thêm sinh viên");
		setSize(504, 345);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		nameLb = new JLabel("Nhập tên sinh viên:");
		
		nameTF = new JTextField();
		nameTF.setColumns(10);
		
		IDLb = new JLabel("Nhập mã sinh viên:");
		
		IDTF = new JTextField();
		IDTF.setColumns(10);
		
		checkBoxLb = new JLabel("Đánh dấu tích vào những buổi đi học:");
		JLabel morningLb = new JLabel("Sáng:");
		JLabel monLb = new JLabel("Thứ 2");
		JLabel tueLb = new JLabel("Thứ 3");
		JLabel wedLb = new JLabel("Thứ 4");
		JLabel thuLb = new JLabel("Thứ 5");
		JLabel friLb = new JLabel("Thứ 6");
		JLabel satLb = new JLabel("Thứ 7");
		
		s2CB = new JCheckBox("");
		s3CB = new JCheckBox("");
		s4CB = new JCheckBox("");
		s5CB = new JCheckBox("");
		s6CB = new JCheckBox("");
		s7CB = new JCheckBox("");
		
		JLabel afternoonLb = new JLabel("Chiều:");
		c2CB = new JCheckBox("");
		c3CB = new JCheckBox("");
		c4CB = new JCheckBox("");
		c5CB = new JCheckBox("");
		c6CB = new JCheckBox("");
		c7CB = new JCheckBox("");
		
		JLabel eveningLb = new JLabel("Tối:");
		t2CB = new JCheckBox("");
		t3CB = new JCheckBox("");
		t4CB = new JCheckBox("");
		t5CB = new JCheckBox("");
		t6CB = new JCheckBox("");
		t7CB = new JCheckBox("");
		
		JLabel noteLb = new JLabel("Ghi chú:");
		
		noteTF = new JTextField();
		noteTF.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(99)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(monLb)
								.addComponent(s2CB))
							.addGap(34)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(tueLb)
								.addComponent(s3CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(37)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(wedLb)
								.addComponent(s4CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(37)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(thuLb)
								.addComponent(s5CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(friLb)
								.addComponent(s6CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(satLb)
								.addComponent(s7CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(morningLb)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(nameLb)
										.addComponent(nameTF, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
									.addGap(48)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(IDTF, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
										.addComponent(IDLb)))
								.addComponent(checkBoxLb)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(afternoonLb, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(c2CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(c3CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(44)
									.addComponent(c4CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(44)
									.addComponent(c5CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(c6CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(39)
									.addComponent(c7CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(eveningLb, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
									.addGap(47)
									.addComponent(t2CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(t3CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(44)
									.addComponent(t4CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(44)
									.addComponent(t5CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(t6CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(39)
									.addComponent(t7CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addComponent(noteLb)
								.addComponent(noteTF))))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLb)
						.addComponent(IDLb))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(nameTF, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(IDTF, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(checkBoxLb)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(monLb)
						.addComponent(tueLb)
						.addComponent(wedLb)
						.addComponent(thuLb)
						.addComponent(friLb)
						.addComponent(satLb))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addComponent(morningLb))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(s3CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(s2CB)
								.addComponent(s4CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(s5CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(s6CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(s7CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(c2CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(c3CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(c4CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(c5CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(c6CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(c7CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addComponent(afternoonLb)))
					.addGap(5)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(eveningLb))
						.addComponent(t2CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(t3CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(t4CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(t5CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(t6CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(t7CB, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(noteLb)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(noteTF, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			statusLb = new JLabel();
			buttonPane.add(statusLb);
			{
				addBtn = new JButton("Thêm");
				addBtn.setActionCommand("add");
				buttonPane.add(addBtn);
				getRootPane().setDefaultButton(addBtn);
			}
			{
				resetBtn = new JButton("Reset");
				resetBtn.setActionCommand("reset");
				buttonPane.add(resetBtn);
				
				doneBtn = new JButton("Hủy");
				doneBtn.setActionCommand("cancel");
				doneBtn.addActionListener(this);
				buttonPane.add(doneBtn);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
	
	void addListener(ActionListener listener) {
		addBtn.addActionListener(listener);
		resetBtn.addActionListener(listener);
	}
	
	String getStudentName() {
		return nameTF.getText(); 
	}
	
	void setStudentName(String name) {
		nameTF.setText(name);
	}
	
	String getStudentID() {
		return IDTF.getText().trim();
	}
	
	void setStudentID(String ID) {
		IDTF.setText(ID);
	}
	
	String getMorningFree() {
		char[] morningFree = {'0', '0', '0', '0', '0', '0'};
		if (s2CB.isSelected()) morningFree[0] = '1';
		if (s3CB.isSelected()) morningFree[1] = '1';
		if (s4CB.isSelected()) morningFree[2] = '1';
		if (s5CB.isSelected()) morningFree[3] = '1';
		if (s6CB.isSelected()) morningFree[4] = '1';
		if (s7CB.isSelected()) morningFree[5] = '1';
		return new String(morningFree);
	}
	
	void setMorningFree(String free) {
		char[] morningFree = free.toCharArray();
		if (morningFree[0] == '1') s2CB.setSelected(true); else s2CB.setSelected(false);
		if (morningFree[1] == '1') s3CB.setSelected(true); else s3CB.setSelected(false);
		if (morningFree[2] == '1') s4CB.setSelected(true); else s4CB.setSelected(false);
		if (morningFree[3] == '1') s5CB.setSelected(true); else s5CB.setSelected(false);
		if (morningFree[4] == '1') s6CB.setSelected(true); else s6CB.setSelected(false);
		if (morningFree[5] == '1') s7CB.setSelected(true); else s7CB.setSelected(false);
	}
	
	String getAfternoonFree() {
		char[] afternoonFree = {'0', '0', '0', '0', '0', '0'};
		if (c2CB.isSelected()) afternoonFree[0] = '1';
		if (c3CB.isSelected()) afternoonFree[1] = '1';
		if (c4CB.isSelected()) afternoonFree[2] = '1';
		if (c5CB.isSelected()) afternoonFree[3] = '1';
		if (c6CB.isSelected()) afternoonFree[4] = '1';
		if (c7CB.isSelected()) afternoonFree[5] = '1';
		return new String(afternoonFree);
	}
	
	void setAfternoonFree(String free) {
		char[] afternoonFree = free.toCharArray();
		if (afternoonFree[0] == '1') c2CB.setSelected(true); else c2CB.setSelected(false);
		if (afternoonFree[1] == '1') c3CB.setSelected(true); else c3CB.setSelected(false);
		if (afternoonFree[2] == '1') c4CB.setSelected(true); else c4CB.setSelected(false);
		if (afternoonFree[3] == '1') c5CB.setSelected(true); else c5CB.setSelected(false);
		if (afternoonFree[4] == '1') c6CB.setSelected(true); else c6CB.setSelected(false);
		if (afternoonFree[5] == '1') c7CB.setSelected(true); else c7CB.setSelected(false);
	}
	
	String getEveningFree() {
		char[] eveningFree = {'0', '0', '0', '0', '0', '0'};
		if (t2CB.isSelected()) eveningFree[0] = '1';
		if (t3CB.isSelected()) eveningFree[1] = '1';
		if (t4CB.isSelected()) eveningFree[2] = '1';
		if (t5CB.isSelected()) eveningFree[3] = '1';
		if (t6CB.isSelected()) eveningFree[4] = '1';
		if (t7CB.isSelected()) eveningFree[5] = '1';
		return new String(eveningFree);
	}
	
	void setEveningFree(String free) {
		char[] eveningFree = free.toCharArray();
		if (eveningFree[0] == '1') t2CB.setSelected(true); else t2CB.setSelected(false);
		if (eveningFree[1] == '1') t3CB.setSelected(true); else t3CB.setSelected(false);
		if (eveningFree[2] == '1') t4CB.setSelected(true); else t4CB.setSelected(false);
		if (eveningFree[3] == '1') t5CB.setSelected(true); else t5CB.setSelected(false);
		if (eveningFree[4] == '1') t6CB.setSelected(true); else t6CB.setSelected(false);
		if (eveningFree[5] == '1') t7CB.setSelected(true); else t7CB.setSelected(false);
	}
	
	String getStudentNote() {
		return noteTF.getText();
	}
	
	void setStudentNote(String note) {
		noteTF.setText(note);
	}
	
	/*
	 * Disable edit for the ID TextField.
	 * Change the text for the title bar and button.
	 */
	void enableEditMode() {
		setTitle("Sửa sinh viên");
		IDLb.setText("Mã sinh viên:");
		addBtn.setText("Sửa");
		addBtn.setActionCommand("edit");
		resetBtn.setEnabled(false);
		IDTF.setEditable(false);
	}
	
	/*
	 * Disable edit for all components.
	 * Change the text for the title bar and button.
	 */
	void enableSeeScheduleMode() {
		setTitle("Xem thời gian biểu");
		nameLb.setText("Tên sinh viên:");
		IDLb.setText("Mã sinh viên:");
		checkBoxLb.setText("Những buổi đi học:");
		
		addBtn.setEnabled(false);
		resetBtn.setEnabled(false);
		statusLb.setText(null);
		nameTF.setEditable(false);
		IDTF.setEditable(false);
		noteTF.setEditable(false);
		
		s2CB.setEnabled(false);
		s3CB.setEnabled(false);
		s4CB.setEnabled(false);
		s5CB.setEnabled(false);
		s6CB.setEnabled(false);
		s7CB.setEnabled(false);
		c2CB.setEnabled(false);
		c3CB.setEnabled(false);
		c4CB.setEnabled(false);
		c5CB.setEnabled(false);
		c6CB.setEnabled(false);
		c7CB.setEnabled(false);
		t2CB.setEnabled(false);
		t3CB.setEnabled(false);
		t4CB.setEnabled(false);
		t5CB.setEnabled(false);
		t6CB.setEnabled(false);
		t7CB.setEnabled(false);
	}
	
	void setStatusText(String text) {
		statusLb.setText(text);
	}
	
	void resetInput() {
		setStudentName(null);
		setStudentID(null);
		setStudentNote(null);
		setMorningFree("000000");
		setAfternoonFree("000000");
		setEveningFree("000000");
	}
	
	boolean checkInput(String studentName, String studentID) {
		if (studentName.trim().isEmpty()) {
			setStatusText("Tên sinh viên không được để trống");
			return false;
		}
		else if (!studentName.contains(" ")) {
			setStatusText("Tên sinh viên phải có đủ họ và tên");
			return false;
		}
		else if (studentID.isEmpty()) {
			setStatusText("Mã sinh viên không được để trống");
			return false;
		}
		else if (!Pattern.matches("[0-9]+", studentID)) {
			setStatusText("Mã sinh viên chỉ được chứa kí tự chữ số");
			return false;
		}
		else return true;
	}
	
}
