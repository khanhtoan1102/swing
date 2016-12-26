package com.mta.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mta.model.Drug;
import com.mta.utilities.FindBy;
import com.mta.utilities.TimeZone;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dialog;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.ScrollPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class DrugForm implements ActionListener {

	private JFrame frame;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtProducer;
	private JTextField txtPrice;
	private JTextField txtBatchNumber;
	private JDateChooser dcMFG;
	private JDateChooser dcEXP;
	private JDateChooser dcExpSearch;
	private JTextField txtSearch;
	private JButton btnGenCode;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnDelete;
	private JRadioButton rdbID;
	private JRadioButton rdbName;
	private JRadioButton rdbProducer;
	private JRadioButton rdbEXPSearch;
	private JButton btnSearch;
	private JTable tbDrug;
	private JButton btnUpdate;

	private ArrayList<Drug> list;
	public static final int FILE_OPEN = 1;
	public static final int FILE_SAVE = 2;
	public static final int EXPORT_EXCEL = 3;
	public static final int IMPORT_EXCEL = 4;

	public ArrayList<Drug> getList() {
		return list;
	}

	public void setList(ArrayList<Drug> list) {
		this.list = list;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrugForm window = new DrugForm();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public DrugForm() {
		initialize();
		defaultValue(false);
	}

	public void defaultValue(boolean value) {
		txtBatchNumber.setEnabled(value);
		txtName.setEnabled(value);
		txtPrice.setEnabled(value);
		txtProducer.setEnabled(value);
		dcEXP.setEnabled(value);
		dcMFG.setEnabled(value);
		rdbID.setSelected(true);
	}

	public void resetValue() {
		this.txtID.setText("");
		this.txtName.setText("");
		this.txtPrice.setText("");
		this.txtProducer.setText("");
		this.txtBatchNumber.setText("");
		this.dcEXP.setDate(new Date());
		this.dcMFG.setDate(new Date());

	}

	public Drug getData() {
		Drug data = new Drug();
		data.setBatchNumber(txtBatchNumber.getText());
		data.setExp(dcEXP.getDate());
		data.setId(txtID.getText());
		data.setMfg(dcMFG.getDate());
		data.setName(txtName.getText());
		data.setPrice(Double.parseDouble(txtPrice.getText()));
		data.setProducer(txtProducer.getText());
		return data;
	}

	public boolean checkData() {
		if (txtBatchNumber.getText().equals("") || txtID.getText().equals("") || txtName.getText().equals("")
				|| txtPrice.getText().equals("") || txtProducer.getText().equals("")) {
			return false;
		}
		return true;
	}

	public Object[] loadColumnName() {
		return new Object[] { "Mã Thuốc", "Tên Thuốc", "Nhà Sản Xuất", "Ngày Sản Xuất", "Ngày Hết Hạn", "Đơn Giá",
				"Số Lô" };
	}

	public Object[][] loadRowData() {
		return new Object[][] {};
	}

	public Object[] rowData(Drug obj) {
		return new Object[] { obj.getId(), obj.getName(), obj.getProducer(), TimeZone.formatDate(obj.getMfg()),
				TimeZone.formatDate(obj.getExp()), obj.getPrice(), obj.getBatchNumber() };
	}

	public Drug converttoDrug(Object[] obj) {
		Drug data = new Drug();
		data.setId(obj[0].toString());
		data.setName(obj[1].toString());
		data.setProducer(obj[2].toString());
		data.setMfg(TimeZone.formatDate(obj[3].toString()));
		data.setExp(TimeZone.formatDate(obj[4].toString()));
		data.setPrice(Double.parseDouble(obj[5].toString()));
		data.setBatchNumber(obj[6].toString());
		return data;
	}

	public void showData() {
		DefaultTableModel dataModel = new DefaultTableModel(loadRowData(), loadColumnName());
		for (int i = 0; i < list.size(); i++) {
			dataModel.addRow(rowData(list.get(i)));
		}
		this.tbDrug.setModel(dataModel);
	}

	public void operateFile(String title, int type) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(title);
		int choose = -1;
		switch (type) {
		case FILE_OPEN:
			choose = chooser.showOpenDialog(null);
			break;
		case FILE_SAVE:
			choose = chooser.showSaveDialog(null);
			break;
		case IMPORT_EXCEL:
			choose = chooser.showOpenDialog(null);
			break;
		case EXPORT_EXCEL:
			choose = chooser.showSaveDialog(null);
			break;
		}
		if (choose == chooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			switch (type) {
			case FILE_OPEN:
				openFile(file);
				break;
			case FILE_SAVE:
				saveFile(file);
				break;
			case IMPORT_EXCEL:
				importExcel(file);
				break;
			case EXPORT_EXCEL:
				exportExcel(file);
				break;
			}
		}
	}

	public void openFile(File file) {
		FileReader fr;
		list.clear();
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				Drug obj = new Drug();
				String[] str = line.split(" ");
				obj.setId(str[0]);
				obj.setName(str[1]);
				obj.setProducer(str[2]);
				obj.setMfg(TimeZone.formatDate(str[3]));
				obj.setExp(TimeZone.formatDate(str[4]));
				obj.setPrice(Double.parseDouble(str[5]));
				obj.setBatchNumber(str[6]);
				list.add(obj);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error to open file" + e.toString(), "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}

		showData();
	}

	public void saveFile(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			String data = "";
			for (int i = 0; i < list.size(); i++) {
				data += list.get(i).getId() + " " + list.get(i).getName() + " " + list.get(i).getProducer() + " "
						+ TimeZone.formatDate(list.get(i).getMfg()) + " " + TimeZone.formatDate(list.get(i).getExp())
						+ " " + list.get(i).getPrice() + " " + list.get(i).getBatchNumber();
				data += "\n";
			}
			bw.write(data);
			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(null, "Save file successfully", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error to save file" + e.toString(), "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void exportExcel(File file) {
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Drug Data");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", loadColumnName());
		for (int i = 0; i < list.size(); i++) {
			data.put(i + 2 + "", rowData(list.get(i)));
		}

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			System.out.println("written successfully on disk.");
			JOptionPane.showMessageDialog(null, "Save file successfully", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void importExcel(File file) {
		try {
			FileInputStream fs = new FileInputStream(file);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Drug data = new Drug();
			boolean header = true;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (header) {
					header = false;
					continue;
				}
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				Object[] obj = new Object[7];
				int i = 0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						obj[i] = cell.getNumericCellValue();
						i++;
						break;
					case Cell.CELL_TYPE_STRING:
						obj[i] = cell.getStringCellValue();
						i++;
						break;
					}
				}
				list.add(converttoDrug(obj));
			}
			showData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 677, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		list = new ArrayList<Drug>();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "List Drug", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 657, GroupLayout.PREFERRED_SIZE)
								.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_3.setLayout(null);

		tbDrug = new JTable();
		tbDrug.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDrug.setModel(new DefaultTableModel(loadRowData(), loadColumnName()));

		JScrollPane scroll = new JScrollPane(tbDrug);
		scroll.setBounds(10, 21, 637, 235);
		panel_3.add(scroll);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Enter the information", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã Thuốc");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(174, 22, 89, 23);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tên Thuốc");
		lblNewLabel_1.setBounds(49, 68, 89, 23);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nhà Sản Xuất");
		lblNewLabel_2.setBounds(49, 102, 89, 23);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ngày Sản Xuất");
		lblNewLabel_3.setBounds(49, 136, 89, 23);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Ngày Hết Hạn");
		lblNewLabel_4.setBounds(346, 68, 89, 23);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Đơn Giá");
		lblNewLabel_5.setBounds(346, 102, 89, 23);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Số Lô");
		lblNewLabel_6.setBounds(346, 136, 89, 23);
		panel.add(lblNewLabel_6);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(259, 22, 89, 23);
		panel.add(txtID);
		txtID.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(136, 68, 184, 23);
		panel.add(txtName);
		txtName.setColumns(10);

		txtProducer = new JTextField();
		txtProducer.setBounds(136, 102, 184, 23);
		panel.add(txtProducer);
		txtProducer.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setBounds(428, 102, 184, 23);
		panel.add(txtPrice);
		txtPrice.setColumns(10);

		txtBatchNumber = new JTextField();
		txtBatchNumber.setBounds(428, 136, 184, 23);
		panel.add(txtBatchNumber);
		txtBatchNumber.setColumns(10);

		dcMFG = new JDateChooser();
		dcMFG.setDateFormatString("dd/MM/yyyy");
		dcMFG.setBounds(136, 136, 184, 23);
		panel.add(dcMFG);

		dcEXP = new JDateChooser();
		dcEXP.setDateFormatString("dd/MM/ yyyy");
		dcEXP.setBounds(428, 68, 184, 23);
		panel.add(dcEXP);

		btnGenCode = new JButton("GenCode");
		btnGenCode.setBounds(350, 22, 89, 23);
		btnGenCode.addActionListener(this);
		panel.add(btnGenCode);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(90, 182, 89, 23);
		btnAdd.addActionListener(this);
		panel.add(btnAdd);

		btnSave = new JButton("Save");
		btnSave.setBounds(223, 182, 89, 23);
		btnSave.addActionListener(this);
		panel.add(btnSave);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(499, 182, 89, 23);
		btnDelete.addActionListener(this);
		panel.add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(362, 182, 89, 23);
		btnUpdate.addActionListener(this);
		panel.add(btnUpdate);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tab Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setToolTipText("");
		tabbedPane.addTab("Search", null, panel_1, null);

		panel_1.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Search Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(26, 34, 153, 138);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		rdbID = new JRadioButton("ID ");
		rdbID.setBounds(22, 21, 93, 23);
		panel_4.add(rdbID);

		rdbName = new JRadioButton("NAME");
		rdbName.setBounds(22, 47, 93, 23);
		panel_4.add(rdbName);

		rdbProducer = new JRadioButton("PRODUCER");
		rdbProducer.setBounds(22, 73, 93, 23);
		panel_4.add(rdbProducer);

		rdbEXPSearch = new JRadioButton("EXP");
		rdbEXPSearch.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbEXPSearch.isSelected()) {
					txtSearch.setVisible(false);
					dcExpSearch.setVisible(true);
				} else {
					txtSearch.setVisible(true);
					dcExpSearch.setVisible(false);
				}
			}
		});
		rdbEXPSearch.setBounds(22, 99, 93, 23);
		panel_4.add(rdbEXPSearch);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbProducer);
		btnGroup.add(rdbName);
		btnGroup.add(rdbID);
		btnGroup.add(rdbEXPSearch);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Tab Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(189, 53, 456, 114);
		panel_1.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Thông tin tìm kiếm");
		lblNewLabel_7.setBounds(10, 49, 125, 23);
		panel_5.add(lblNewLabel_7);

		txtSearch = new JTextField();
		txtSearch.setBounds(145, 46, 202, 23);
		panel_5.add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(357, 45, 89, 23);
		btnSearch.addActionListener(this);
		panel_5.add(btnSearch);

		dcExpSearch = new JDateChooser();
		dcExpSearch.setDateFormatString("dd/MM/yyyy");
		dcExpSearch.setBounds(145, 46, 202, 23);
		panel_5.add(dcExpSearch);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Export File",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tabbedPane.addTab("Backup", null, panel_2, null);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Caculator", null, panel_6, null);
		panel_6.setLayout(null);
		frame.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnuFile = new JMenu("File");
		mnuFile.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnuFile);

		JMenuItem mniOpenFile = new JMenuItem("Open File");
		mniOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operateFile("Open a file", FILE_OPEN);
			}
		});
		mniOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnuFile.add(mniOpenFile);

		JMenuItem mniSaveFile = new JMenuItem("Save File");
		mniSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operateFile("Save a file", FILE_SAVE);
			}
		});
		mniSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnuFile.add(mniSaveFile);

		JMenuItem mniExit = new JMenuItem("Exit");
		mniExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mniExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát", "Thông Báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (kt == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mnuFile.add(mniExit);

		JMenu mnuEdit = new JMenu("Sorted");
		menuBar.add(mnuEdit);

		JMenuItem mniSortedByName = new JMenuItem("Sorted By Name");
		mniSortedByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list.sort(new Comparator<Drug>() {
					@Override
					public int compare(Drug o1, Drug o2) {
						// TODO Auto-generated method stub
						return o1.getName().compareTo(o2.getName());
					}
				});
				showData();
			}
		});
		mnuEdit.add(mniSortedByName);

		JMenuItem mniSortedByPrice = new JMenuItem("Sorted By Price");
		mniSortedByPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.sort(new Comparator<Drug>() {
					@Override
					public int compare(Drug o1, Drug o2) {
						// TODO Auto-generated method stub
						if (o1.getPrice() > o2.getPrice())
							return 1;
						else if (o1.getPrice() == o2.getPrice())
							return 0;
						else
							return -1;
					}
				});
				showData();
			}
		});
		mnuEdit.add(mniSortedByPrice);

		JMenu mnuTool = new JMenu("Tool");
		menuBar.add(mnuTool);

		JMenuItem mniExportExcel = new JMenuItem("Export Excel");
		mniExportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operateFile("save a file", EXPORT_EXCEL);
			}
		});
		mnuTool.add(mniExportExcel);

		JMenuItem mniImportExcel = new JMenuItem("Import Excel");
		mniImportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operateFile("open a file", IMPORT_EXCEL);
			}
		});
		mnuTool.add(mniImportExcel);

		JMenu mnuHelp = new JMenu("Help");
		menuBar.add(mnuHelp);

		JMenuItem mniAbout = new JMenuItem("About");
		mniAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Product create by KhanhToanMTA", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnuHelp.add(mniAbout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnAdd) {
			defaultValue(true);
			resetValue();

		}

		if (e.getSource() == btnSave) {
			if (checkData()) {
				try {
					double pri = Double.parseDouble(txtPrice.getText());
					DefaultTableModel tableModel = (DefaultTableModel) tbDrug.getModel();
					Drug obj = new Drug();
					obj = getData();
					int rowCount = tableModel.getRowCount();
					boolean check = true;
					for (int i = 0; i < rowCount; i++) {
						if (obj.getId().equals(tableModel.getValueAt(i, 0).toString())) {
							check = false;
						}
					}
					if (check) {
						list.add(obj);
						tableModel.addRow(rowData(obj));
					} else {
						JOptionPane.showMessageDialog(null, "ID đã tồn tại", "Thông Báo", JOptionPane.ERROR_MESSAGE);
					}
					tbDrug.setModel(tableModel);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Giá Thuốc Phải là số", "Thông Báo",
							JOptionPane.WARNING_MESSAGE);
					txtPrice.requestFocus();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Điền đầy đủ thông tin", "Thông Báo", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource() == btnGenCode) {
			int i = list.size();
			txtID.setText(++i + "");
		}

		if (e.getSource() == btnDelete) {
			DefaultTableModel tableModel = (DefaultTableModel) tbDrug.getModel();
			int row = tbDrug.getSelectedRow();
			if (row >= 0) {
				int kt = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (kt == JOptionPane.YES_OPTION) {
					String id = tableModel.getValueAt(row, 0).toString();
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getId().equals(id)) {
							list.remove(i);
						}
					}
					tableModel.removeRow(row);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Không có dữ liệu được chọn", "Thông Báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (e.getSource() == btnUpdate) {
			if (checkData()) {
				DefaultTableModel tableModel = (DefaultTableModel) tbDrug.getModel();
				int row = tbDrug.getSelectedRow();
				if (row >= 0) {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getId().equals(tableModel.getValueAt(row, 0).toString())) {
							Drug obj = getData();
							list.set(i, obj);
						}
					}
					Drug obj = getData();
					Object[] rowData = rowData(obj);
					tableModel.removeRow(row);
					tableModel.insertRow(row, rowData);
				} else {
					JOptionPane.showMessageDialog(null, "Không có dữ liệu được chọn", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Điền đầy đủ thông tin", "Thông Báo", JOptionPane.ERROR_MESSAGE);
			}
		}

		tbDrug.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					DefaultTableModel tableModel = (DefaultTableModel) tbDrug.getModel();
					int row = tbDrug.getSelectedRow();
					if (row >= 0) {
						defaultValue(true);
						List<Object> obj = new ArrayList<Object>();
						for (int i = 0; i < tbDrug.getColumnCount(); i++) {
							obj.add(tbDrug.getValueAt(row, i));
						}
						txtID.setText(obj.get(0).toString());
						txtName.setText(obj.get(1).toString());
						txtProducer.setText(obj.get(2).toString());
						dcMFG.setDate(TimeZone.formatDate(obj.get(3).toString()));
						dcEXP.setDate(TimeZone.formatDate(obj.get(4).toString()));
						txtPrice.setText(obj.get(5).toString());
						txtBatchNumber.setText(obj.get(6).toString());
					}
				}
				showData();
			}
		});

		if (e.getSource() == btnSearch) {
			if (rdbID.isSelected()) {
				SearchForm form = new SearchForm();
				DefaultTableModel tableModel = new DefaultTableModel(loadRowData(), loadColumnName());
				Drug obj = new Drug();
				int kq = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getId().equals(txtSearch.getText())) {
						obj = list.get(i);
						kq++;
					}
				}
				if (kq > 0) {
					Object[] data = rowData(obj);
					tableModel.addRow(data);
					form.getTbSearch().setModel(tableModel);
					form.getFrame().setVisible(true);
					form.getTxtKQ().setText(kq + "");
				} else {
					JOptionPane.showMessageDialog(null, "Không có kết quả", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			if (rdbName.isSelected()) {
				SearchForm form = new SearchForm();
				DefaultTableModel tableModel = new DefaultTableModel(loadRowData(), loadColumnName());
				int kq = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getName().equals(txtSearch.getText())) {
						Object[] obj = rowData(list.get(i));
						tableModel.addRow(obj);
						kq++;
					}
				}
				if (kq > 0) {
					form.getTbSearch().setModel(tableModel);
					form.getFrame().setVisible(true);
					form.getTxtKQ().setText(kq + "");
				} else {
					JOptionPane.showMessageDialog(null, "Không có kết quả", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			if (rdbProducer.isSelected()) {
				SearchForm form = new SearchForm();
				DefaultTableModel tableModel = new DefaultTableModel(loadRowData(), loadColumnName());
				int kq = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getProducer().equals(txtSearch.getText())) {
						Object[] obj = rowData(list.get(i));
						tableModel.addRow(obj);
						kq++;
					}
				}
				if (kq > 0) {
					form.getTbSearch().setModel(tableModel);
					form.getFrame().setVisible(true);
					form.getTxtKQ().setText(kq + "");
				} else {
					JOptionPane.showMessageDialog(null, "Không có kết quả", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			if (rdbEXPSearch.isSelected()) {
				SearchForm form = new SearchForm();
				int kq = 0;
				DefaultTableModel tableModel = new DefaultTableModel(loadRowData(), loadColumnName());
				ArrayList<Drug> arr = FindBy.typeKey(list, dcExpSearch.getDate(), 5);
				for (int i = 0; i < arr.size(); i++) {
					Object[] data = rowData(arr.get(i));
					tableModel.addRow(data);
					kq++;
				}
				if (kq > 0) {
					form.getTbSearch().setModel(tableModel);
					form.getFrame().setVisible(true);
					form.getTxtKQ().setText(kq + "");
				} else {
					JOptionPane.showMessageDialog(null, "Không có kết quả", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

	}
}
