package com.vieejtddwcs.classmanager;

import java.awt.Dialog.ModalityType;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Main {
	
	private static String DATA_FILE = "data.xml";
	private List<Student> studentList = new ArrayList<>();
	private List<Student> availableStudentList = new ArrayList<>();
	private List<Student> groupStudentList = new ArrayList<>();
	private String selectedShift = null;
	private int selectedDate = -1;
	private String groupDataFile = null;
	private String lastSearchInput = null;
	private int lastResultIndex = 0;
	private int lastSearchBar = 0;

	public static void main(String[] args) {
		Main main = new Main();
		
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (Throwable e) {
				e.printStackTrace();
			}
			GUI gui = new GUI();
			main.preload(gui);
			
			gui.addListener((ActionEvent e) -> {
				String cmd = e.getActionCommand();
				
				switch (cmd) {
					// Danh sách sinh viên tab.
					case "addStudent1":
						main.createAddStudentDialog(gui);
						break;
					case "editStudent":
						main.createEditStudentDialog(gui);
						break;
					case "deleteStudent1":
						main.deleteStudentsFromClass(gui);
						break;
					case "seeSchedule":
						main.createSeeStudentScheduleDialog(gui);
						break;
					case "importData1":
						main.importClassData(gui);
						break;
					case "exportToFile1":
						main.exportToFile(GUI.CLASS_COL_NAMES, main.studentList, gui);
						break;
					
					// Xếp lịch học tab.
					case "Sáng":
						main.filterStudents(cmd, gui.morningCB.getSelectedIndex() + 1, gui);
						break;
					case "Chiều":
						main.filterStudents(cmd, gui.afternoonCB.getSelectedIndex() + 1, gui);
						break;
					case "Tối":
						main.filterStudents(cmd, gui.eveningCB.getSelectedIndex() + 1, gui);
						break;
					case "applySchedule":
						main.applySchedule(main.selectedShift, main.selectedDate, gui);
						break;
					case "deleteStudent2":
						main.deleteStudentsOnTable(main.availableStudentList, gui.table2, gui);
						break;
					case "exportToFile2":
						main.exportToFile(GUI.GROUP_COL_NAMES, main.availableStudentList, gui);
						break;
					
					// Danh sách nhóm học tab.
					case "addStudent3":
						main.addStudentToGroup(gui);
						break;
					case "deleteStudent3":
						main.deleteStudentsOnTable(main.groupStudentList, gui.table3, gui);
						break;
					case "importData3":
						main.importGroupData(gui);
						break;
					case "saveFile":
						main.saveGroupData(main.groupStudentList, gui);
						break;
					
					// In all 3 tabs.
					case "searchStudent1":
						main.searchStudent(1, gui);
						break;
					case "searchStudent2":
						main.searchStudent(2, gui);
						break;
					case "searchStudent3":
						main.searchStudent(3, gui);
						break;
				}
			});
			
			gui.addListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent e) {
					switch (e.getKeyCode()) {
						case KeyEvent.VK_INSERT:
							if (e.getComponent() == gui.table1) // addStudent1
								main.createAddStudentDialog(gui);
							if (e.getComponent() == gui.table3) // addStudent3
								main.addStudentToGroup(gui);
							break;
						case KeyEvent.VK_DELETE:
							if (e.getComponent() == gui.table1) // deleteStudent1
								main.deleteStudentsFromClass(gui);
							if (e.getComponent() == gui.table2) // deleteStudent2
								main.deleteStudentsOnTable(main.availableStudentList, gui.table2, gui);
							if (e.getComponent() == gui.table3) // deleteStudent3
								main.deleteStudentsOnTable(main.groupStudentList, gui.table3, gui);
							break;
					}
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					
				}
				
				@Override
				public void keyTyped(KeyEvent e) {
					
				}
			});
		});

	}
	
	/*
	 * Read or generate data file at startup. 
	 */
	private void preload(GUI gui) {
		if (new File(DATA_FILE).isFile()) {
			studentList = StudentList.readXml(DATA_FILE);
			gui.displayContent(studentList, 1, null);
			gui.setStatusBar("Load file dữ liệu thành công: "
							+ studentList.size()
							+ " sinh viên");
		}
		else {
			try {
				Files.write(Paths.get(DATA_FILE), "".getBytes());
				StudentList.writeXml(new ArrayList<Student>(), DATA_FILE);
				gui.setStatusBar("Khởi tạo file dữ liệu mới thành công");
			} catch (IOException e) {
				gui.createMessageDialog("Không tạo được file dữ liệu.");
				gui.setStatusBar(null);
				e.printStackTrace();
			}
		}
	}
	
	private void createAddStudentDialog(GUI gui) {
		StudentDialog dialog = new StudentDialog();
		dialog.setStatusText("Nhập đầy đủ thông tin trước khi ấn Thêm");
		
		dialog.addListener(e -> {
			String cmd = e.getActionCommand();
			switch (cmd) {
				case "add":
					addStudentToClass(dialog, gui);
					break;
				case "reset":
					dialog.resetInput();
					dialog.setStatusText("Đã reset input");
					break;
			}
		});
		
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setVisible(true);
	}
	
	private void createEditStudentDialog(GUI gui) {
		int[] selectedRows = gui.table1.getSelectedRows();
		if (selectedRows.length == 1) {
			int i = selectedRows[0];
			
			StudentDialog dialog = new StudentDialog();
			dialog.enableEditMode();
			
			dialog.addListener(e -> {
				if (e.getActionCommand().equals("edit"))
					editStudentInClass(i, dialog, gui);
			});
			
			Student student = studentList.get(i);
			dialog.setStudentName(student.getName());
			dialog.setStudentID(student.getID());
			dialog.setStudentNote(student.getNote());
			dialog.setMorningFree(student.getMorningFree());
			dialog.setAfternoonFree(student.getAfternoonFree());
			dialog.setEveningFree(student.getEveningFree());
			
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.setVisible(true);
		}
	}
	
	private void createSeeStudentScheduleDialog(GUI gui) {
		int[] selectedRows = gui.table1.getSelectedRows();
		if (selectedRows.length == 1) {
			int i = selectedRows[0];
			
			StudentDialog dialog = new StudentDialog();
			dialog.enableSeeScheduleMode();
			
			Student student = studentList.get(i);
			dialog.setStudentName(student.getName());
			dialog.setStudentID(student.getID());
			dialog.setStudentNote(student.getNote());
			dialog.setMorningFree(student.getMorningFree());
			dialog.setAfternoonFree(student.getAfternoonFree());
			dialog.setEveningFree(student.getEveningFree());
			
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.setVisible(true);
		}
	}
	
	/*
	 * Get input, check input valid.
	 * Only add new student if studentID doesn't exist.
	 */
	private void addStudentToClass(StudentDialog dialog, GUI gui) {
		String studentName = dialog.getStudentName();
		String studentID = dialog.getStudentID();
		String morning = dialog.getMorningFree();
		String afternoon = dialog.getAfternoonFree();
		String evening = dialog.getEveningFree();
		String note = dialog.getStudentNote();
		
		if (dialog.checkInput(studentName, studentID)) {
			boolean studentIDExists = false;
			for (Student s : studentList) {
				if (studentID.equals(s.getID())) {
					dialog.setStatusText("Mã sinh viên đã tồn tại");
					studentIDExists = true;
					break;
				}
			}
			if (!studentIDExists) {
				studentList.add(new Student(studentName, studentID,
						morning, afternoon, evening, note));
				Collections.sort(studentList, new StudentComparator());
				StudentList.writeXml(studentList, DATA_FILE);
				
				gui.displayContent(studentList, 1, null);
				gui.setStatusBar("Đã thêm sinh viên: " + studentName);
				dialog.dispose();
			}
		}
	}
	
	/*
	 * Get input, check input valid.
	 * Replace the student at studentIndex.
	 */
	private void editStudentInClass(int studentIndex, StudentDialog dialog, GUI gui) {
		String studentName = dialog.getStudentName();
		String studentID = dialog.getStudentID();
		String morning = dialog.getMorningFree();
		String afternoon = dialog.getAfternoonFree();
		String evening = dialog.getEveningFree();
		String note = dialog.getStudentNote();
		
		if (dialog.checkInput(studentName, studentID)) {
			studentList.set(studentIndex, new Student(studentName, studentID, morning, afternoon, evening, note));
			Collections.sort(studentList, new StudentComparator());
			StudentList.writeXml(studentList, DATA_FILE);
			
			gui.displayContent(studentList, 1, null);
			gui.setStatusBar("Đã cập nhật sinh viên: " + studentName);
			dialog.dispose();
		}
	}
	
	/*
	 * table1 indexes match studentList indexes
	 * so delete students directly.
	 */
	private void deleteStudentsFromClass(GUI gui) {
		int[] selectedRows = gui.table1.getSelectedRows();
		Arrays.sort(selectedRows);
		
		if (selectedRows.length > 0 && gui.createYesNoDialog("Bạn có chắc chắn muốn xóa những sinh viên đã chọn không?") == 0) {
			for (int i = selectedRows.length - 1; i >= 0; i--) {
				studentList.remove(selectedRows[i]);
			}
			
			StudentList.writeXml(studentList, DATA_FILE);
			gui.displayContent(studentList, 1, null);
			gui.setStatusBar("Đã xóa " + selectedRows.length + " sinh viên");
		}
	}
	
	/*
	 * Importing steps: selected file -> workbook -> data list -> data file.
	 */
	private void importClassData(GUI gui) {
		JFileChooser fileChooser = gui.createFileChooser();
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				Workbook workbook = new XSSFWorkbook(new FileInputStream(fileChooser.getSelectedFile()));
				Sheet sheet = workbook.getSheetAt(0);
				
				// Check if it has the bookmark cell.
				String bookmarkCell = formatCell(sheet.getRow(0).getCell(1));
				if (bookmarkCell.equals("Họ và tên")) {
					
					studentList.clear();
					int newStudentCount = 0;
					
					for (Row row : sheet) {
						if (row.getRowNum() == 0) continue; // Skip the header row.
						
						Student newStudent = new Student();
						newStudent.setName(formatCell(row.getCell(1)));
						newStudent.setID(formatCell(row.getCell(2)));
						newStudent.setMorningFree(Student.getBinaryDatesFree(formatCell(row.getCell(3))));
						newStudent.setAfternoonFree(Student.getBinaryDatesFree(formatCell(row.getCell(4))));
						newStudent.setEveningFree(Student.getBinaryDatesFree(formatCell(row.getCell(5))));
						
						studentList.add(newStudent);
						newStudentCount++;
					}
					
					if (newStudentCount == 0) {
						gui.setStatusBar("File excel không có dữ liệu để import");
					}
					else {
						Collections.sort(studentList, new StudentComparator());
						StudentList.writeXml(studentList, DATA_FILE);
						
						gui.displayContent(studentList, 1, null);
						gui.setStatusBar("Đã import dữ liệu từ file excel: "
										+ studentList.size()
										+ " sinh viên");
					}
					
					workbook.close();
				}
				else {
					gui.createMessageDialog("File excel không ở đúng mẫu có thể import.");
					gui.setStatusBar(null);
				}
				
			} catch (IOException e) {
				gui.createMessageDialog("Không thể đọc định dạng file này.");
				gui.setStatusBar(null);
				e.printStackTrace();
			}
		}
	}
	
	private void filterStudents(String shift, int date, GUI gui) {
		selectedShift = shift;
		selectedDate = date;
		availableStudentList = StudentList.getAvailableStudents(studentList, shift, date);
		gui.displayContent(availableStudentList, 2, shift + " thứ " + date);
		gui.setStatusBar("Đã lọc sinh viên rảnh vào " + shift + " thứ " + date + ": "
						+ availableStudentList.size()
						+ " sinh viên");
	}
	
	private void applySchedule(String shift, int date, GUI gui) {
		if (availableStudentList.size() > 0 && gui.createYesNoDialog("Bạn có chắc chắn áp dụng lịch học cho những sinh viên trong danh sách không?") == 0) {
			studentList = StudentList.applyStudentSchedule(studentList, availableStudentList, shift, date);
			Collections.sort(studentList, new StudentComparator());
			StudentList.writeXml(studentList, DATA_FILE);
			
			availableStudentList.clear();
			gui.displayContent(availableStudentList, 2, shift + " thứ " + date);
			gui.setStatusBar("Đã áp dụng lịch học");
		}
	}
	
	/*
	 * Exporting steps: data list -> workbook -> file.
	 */
	private void exportToFile(String[] colNames, List<Student> list, GUI gui) {
		if (list.size() > 0) {
			JFileChooser fileChooser = gui.createFileChooser();
			int returnVal = fileChooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					Workbook workbook = new XSSFWorkbook();
					Sheet sheet = workbook.createSheet();
					
					// Create the bookmark cell.
					Cell bookmarkCell = sheet.createRow(0).createCell(0);
					bookmarkCell.setCellValue("@QuanLyLop:" + colNames.length);
					
					// Create the header row.
					Row headerRow = sheet.createRow(1);
					writeToRow(headerRow, 0, colNames);
					
					// Create the content rows.
					String group = null;
					if (colNames.length == 6)
						group = (String)gui.tableModel2.getValueAt(0, 4);
					
					Object[][] content = StudentList.get2DArray(list, colNames.length, group);
					for (int i = 0; i < content.length; i++) {
						Row row = sheet.createRow(2+i);
						writeToRow(row, 0, content[i]);
					}
					
					// Write to file.
					workbook.write(new FileOutputStream(fileChooser.getSelectedFile().getPath() + ".xlsx"));
					workbook.close();
					
					gui.createMessageDialog("Xuất file excel thành công.");
					gui.setStatusBar(null);
					
				} catch (IOException e) {
					gui.createMessageDialog("Không thể tạo file excel.");
					gui.setStatusBar(null);
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Only able to add students who are not in groupStudentList.
	 */
	private void addStudentToGroup(GUI gui) {
		if (groupStudentList.size() > 0) {
			AddStudentDialog dialog = new AddStudentDialog();
			
			List<Student> addableStudentList = StudentList.copyList(studentList);
			StudentList.removeDatesFree(addableStudentList);
			addableStudentList.removeAll(groupStudentList);
			dialog.displayContent(addableStudentList);
			
			dialog.addListener(e -> {
				if (e.getActionCommand().equals("add")) {
					
					List<Student> selectedStudents = dialog.getSelectedStudents(addableStudentList);
					groupStudentList.addAll(selectedStudents);
					Collections.sort(groupStudentList, new StudentComparator());
					
					String group = (String)gui.tableModel3.getValueAt(0, 4);
					gui.displayContent(groupStudentList, 3, group);
					gui.setStatusBar("Đã thêm " + selectedStudents.size() + " sinh viên vào nhóm");
					dialog.dispose();
				}
			});
			
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.setVisible(true);
		}
	}
	
	/*
	 * table2 and table3 indexes differ studentList indexes
	 * so loop through the list and delete students by studentID.
	 */
	private void deleteStudentsOnTable(List<Student> list, JTable table, GUI gui) {
		int[] selectedRows = table.getSelectedRows();
		Arrays.sort(selectedRows);
		
		if (selectedRows.length > 0 && gui.createYesNoDialog("Bạn có chắc chắn muốn xóa những sinh viên đã chọn không?") == 0) {
			String group = (String)table.getValueAt(0, 4);
			
			for (int i = selectedRows.length - 1; i >= 0; i--) {
				String studentID = (String)table.getValueAt(selectedRows[i], 1);
				for (Student s : list) {
					if (studentID.equals(s.getID())) {
						list.remove(s);
						break;
					}
				}
				gui.displayContent(list, table == gui.table2 ? 2 : 3, group);
			}
			
			gui.setStatusBar("Đã xóa " + selectedRows.length + " sinh viên");
		}
	}
	
	/*
	 * Importing steps: selected file -> workbook -> data list.
	 */
	private void importGroupData(GUI gui) {
		JFileChooser fileChooser = gui.createFileChooser();
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				groupDataFile = fileChooser.getSelectedFile().getPath();
				Workbook workbook = new XSSFWorkbook(new FileInputStream(groupDataFile));
				Sheet sheet = workbook.getSheetAt(0);
				
				// Find the bookmark cell.
				boolean hasBookmark = false;
				int bookmarkCellRowIndex = 0;
				int bookmarkCellColIndex = 0;
				for (Row row : sheet) {
					for (Cell cell : row) {
						if (formatCell(cell).equals("@QuanLyLop:6")) {
							hasBookmark = true;
							bookmarkCellRowIndex = cell.getRowIndex();
							bookmarkCellColIndex = cell.getColumnIndex();
							break;
						}
					}
				}
				
				if (hasBookmark) {
					groupStudentList.clear();
					
					for (Row row : sheet) {
						if (row.getRowNum() < bookmarkCellRowIndex + 2) continue; // Skip the header row and above.
						
						Student newStudent = new Student();
						newStudent.setID(formatCell(row.getCell(bookmarkCellColIndex + 1)));
						newStudent.setName(formatCell(row.getCell(bookmarkCellColIndex + 2))
										+ " "
										+ formatCell(row.getCell(bookmarkCellColIndex + 3)));
						newStudent.setNote(formatCell(row.getCell(bookmarkCellColIndex + 5)));
						
						groupStudentList.add(newStudent);
					}
					
					if (groupStudentList.size() == 0) {
						gui.setStatusBar("File excel không có dữ liệu để import");
					}
					else {
						Collections.sort(groupStudentList, new StudentComparator());
						String group = formatCell(sheet.getRow(bookmarkCellRowIndex + 2).getCell(bookmarkCellColIndex + 4));
						gui.displayContent(groupStudentList, 3, group);
						gui.setStatusBar("Đã import dữ liệu từ file excel: "
										+ groupStudentList.size()
										+ " sinh viên");
					}
					
					workbook.close();
				}
				else {
					gui.createMessageDialog("File excel không ở đúng mẫu có thể import.");
					gui.setStatusBar(null);
				}
			} catch (IOException e) {
				gui.createMessageDialog("Không thể đọc định dạng file này.");
				gui.setStatusBar(null);
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Saving steps: data list -> workbook -> file.
	 */
	private void saveGroupData(List<Student> list, GUI gui) {
		if (list.size() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(new FileInputStream(groupDataFile));
				Sheet sheet = workbook.getSheetAt(0);
				
				// Find the bookmark cell.
				int bookmarkCellRowIndex = 0;
				int bookmarkCellColIndex = 0;
				for (Row row : sheet) {
					for (Cell cell : row) {
						if (formatCell(cell).equals("@QuanLyLop:6")) {
							bookmarkCellRowIndex = cell.getRowIndex();
							bookmarkCellColIndex = cell.getColumnIndex();
							break;
						}
					}
				}
				
				// Remove the old content rows.
				for (int i = bookmarkCellRowIndex + 2; i <= sheet.getLastRowNum(); i++) {
					sheet.removeRow(sheet.getRow(i));
				}
				
				// Create the new content rows.
				int columnLength = 6;
				String group = (String)gui.tableModel3.getValueAt(0, 4);
				
				Object[][] content = StudentList.get2DArray(list, columnLength, group);
				for (int i = 0; i < content.length; i++) {
					Row row = sheet.createRow(bookmarkCellRowIndex + 2 + i);
					writeToRow(row, bookmarkCellColIndex, content[i]);
				}
				
				// Write to file.
				workbook.write(new FileOutputStream(groupDataFile));
				workbook.close();
				
				gui.createMessageDialog("Đã lưu file.");
				gui.setStatusBar(null);
				
			} catch (IOException e) {
				gui.createMessageDialog("Không thể lưu file.");
				gui.setStatusBar(null);
				e.printStackTrace();
			}
		}
	}
	
	private void searchStudent(int searchBar, GUI gui) {
		
		String input = null;
		JTable table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel();
		
		// Specify which panel to work on.
		if (searchBar == 1) {
			input = gui.searchBar1.getText();
			table = gui.table1;
			tableModel = gui.tableModel1;
		}
		if (searchBar == 2) {
			input = gui.searchBar2.getText();
			table = gui.table2;
			tableModel = gui.tableModel2;
		}
		if (searchBar == 3) {
			input = gui.searchBar3.getText();
			table = gui.table3;
			tableModel = gui.tableModel3;
		}
		
		if (!input.trim().equals("") && !input.equals("Nhập tên hoặc mã sinh viên")) {
			boolean hasResult = false;
			
			// Input is studentID, search the whole table.
			if (Pattern.matches("[0-9]+", input)) {
				for (int i = 0; i < table.getRowCount(); i++) {
					String studentID = (String)tableModel.getValueAt(i, 1);
					if (input.equals(studentID)) {
						showSearchResult(input, i, table, gui);
						hasResult = true;
						break;
					}
				}
				
				if (!hasResult)
					gui.createMessageDialog("Không có kết quả tìm kiếm cho: " + input + ".");
			}
			
			// Input is studentName, search from lastResultIndex to the end of the table.
			else {
				String lowercaseInput = input.toLowerCase();
				
				if (searchBar != lastSearchBar)
					lastSearchInput = null; // Reset lastSearchInput.
				
				if (!lowercaseInput.equals(lastSearchInput))
					lastResultIndex = 0; // Reset lastResultIndex.
				
				for (int i = lastResultIndex; i < table.getRowCount(); i++) {
					String studentName = (String)tableModel.getValueAt(i, 2) + " " + (String)tableModel.getValueAt(i, 3);
					if (studentName.toLowerCase().contains(lowercaseInput)) {
						showSearchResult(input, i, table, gui);
						hasResult = true;
						lastResultIndex = i+1;
						break;
					}
				}
				
				if (!hasResult)
					gui.createMessageDialog("Không còn kết quả tìm kiếm cho: " + input + ".");
				
				lastSearchInput = lowercaseInput;
				lastSearchBar = searchBar;
			}
		}
	}
	
	private void showSearchResult(String keyword, int row, JTable table, GUI gui) {
		table.setRowSelectionInterval(row, row);
		table.scrollRectToVisible(new Rectangle(table.getCellRect(row, 0, true)));
		gui.setStatusBar("Kết quả tìm kiếm cho: " + keyword);
	}
	
	private String formatCell(Cell cell) {
		return new DataFormatter().formatCellValue(cell);
	}
	
	private void writeToRow(Row row, int startIndex, Object[] content) {
		for (int i = 0; i < content.length; i++) {
			Cell cell = row.createCell(startIndex + i);
			cell.setCellValue((String)content[i]);
		}
	}

}
