package com.mta.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.UIManager;
import java.awt.Color;

import com.mta.model.Drug;
import com.mta.utilities.FindBy;
import com.mta.utilities.TableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AdvanceForm implements ActionListener{

	private JFrame frame;
	private JTable tbDug;
	private JTextField txtName;
	private JTextField txtProducer;
	private JDateChooser dcMFG;
	private JDateChooser dcEXP;
	private JButton btnAdvSearch;

	private ArrayList<Drug> list = new ArrayList<Drug>();
	/**
	 * Launch the application.
	 */
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

	public JTable getTbDug() {
		return tbDug;
	}

	public void setTbDug(JTable tbDug) {
		this.tbDug = tbDug;
	}

	/**
	 * Create the application.
	 */
	public AdvanceForm() {
		initialize();
		defaultValue();
	}

	
	public void defaultValue(){
		txtName.setEnabled(false);
		txtProducer.setEnabled(false);
		dcEXP.setEnabled(false);
		dcMFG.setEnabled(false);
		dcEXP.setDate(new Date());
		dcMFG.setDate(new Date());
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 668, 472);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Advance Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 652, 203);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Search Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 34, 632, 158);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JCheckBox chkName = new JCheckBox("NAME");
		chkName.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chkName.isSelected()){
					txtName.setEnabled(true);
				}
				else{
					txtName.setEnabled(false);
				}
			}
		});
		chkName.setBounds(37, 35, 97, 23);
		panel_2.add(chkName);
		
		JCheckBox chkProducer = new JCheckBox("PRODUCER");
		chkProducer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(chkProducer.isSelected()){
					txtProducer.setEnabled(true);
				}
				else{
					txtProducer.setEnabled(false);
				}
			}
		});
		chkProducer.setBounds(37, 61, 97, 23);
		panel_2.add(chkProducer);
		
		JCheckBox chkMFG = new JCheckBox("MFG");
		chkMFG.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(chkMFG.isSelected()){
					dcMFG.setEnabled(true);
				}
				else{
					dcMFG.setEnabled(false);
				}
			}
		});
		chkMFG.setBounds(37, 88, 97, 23);
		panel_2.add(chkMFG);
		
		JCheckBox chkEXP = new JCheckBox("EXP");
		chkEXP.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(chkEXP.isSelected()){
					dcEXP.setEnabled(true);
				}
				else{
					dcEXP.setEnabled(false);
				}
			}
		});
		chkEXP.setBounds(37, 114, 97, 23);
		panel_2.add(chkEXP);
		
		txtName = new JTextField();
		txtName.setBounds(179, 36, 244, 20);
		panel_2.add(txtName);
		txtName.setColumns(10);
		
		txtProducer = new JTextField();
		txtProducer.setBounds(179, 61, 244, 20);
		panel_2.add(txtProducer);
		txtProducer.setColumns(10);
		
		btnAdvSearch = new JButton("SEARCH");
		btnAdvSearch.setBounds(479, 35, 143, 99);
		panel_2.add(btnAdvSearch);
		
		dcMFG = new JDateChooser();
		dcMFG.setDateFormatString("dd/MM/yyyy");
		dcMFG.setBounds(179, 88, 244, 20);
		panel_2.add(dcMFG);
		
		dcEXP = new JDateChooser();
		dcEXP.setDateFormatString("dd/MM/yyyy");
		dcEXP.setBounds(179, 114, 244, 20);
		panel_2.add(dcEXP);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(kt == JOptionPane.YES_OPTION){
					frame.dispose();
				}
			}
		});
		btnThoat.setBounds(541, 11, 89, 23);
		panel.add(btnThoat);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "K\u1EBFt Qu\u1EA3 T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(0, 210, 652, 223);
		frame.getContentPane().add(panel_1);
		
		tbDug = new JTable();
		tbDug.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		panel_1.add(tbDug);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnAdvSearch){
			DefaultTableModel tableModel = new DefaultTableModel(TableModel.loadRowData(), TableModel.loadColumnName());
			
		}
	}
}
