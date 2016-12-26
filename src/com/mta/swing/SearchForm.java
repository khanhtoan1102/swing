package com.mta.swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;

public class SearchForm implements ActionListener{

	private JFrame frame;
	private JTable tbSearch;
	private JTextField txtKQ;
	private JButton btnThoat;

	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTable getTbSearch() {
		return tbSearch;
	}

	public void setTbSearch(JTable tbSearch) {
		this.tbSearch = tbSearch;
	}

	public JTextField getTxtKQ() {
		return txtKQ;
	}

	public void setTxtKQ(JTextField txtKQ) {
		this.txtKQ = txtKQ;
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public SearchForm() {
		initialize();
	}
	
	
	public Object[] loadColumnName() {
		return new Object[] { "Mã Thuốc", "Tên Thuốc", "Nhà Sản Xuất", "Ngày Sản Xuất", "Ngày Hết Hạn", "Đơn Giá",
				"Số Lô" };
	}

	public Object[][] loadRowData() {
		return new Object[][] {};
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 677, 382);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "K\u1EBFt Qu\u1EA3 T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 65, 657, 267);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 21, 637, 235);
		panel.add(scrollPane);
		
		tbSearch = new JTable();
		tbSearch.setModel(new DefaultTableModel(loadRowData(), loadColumnName()));
		tbSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tbSearch);
		
		JLabel lblKtQu = new JLabel("Kết Quả");
		lblKtQu.setBounds(10, 16, 46, 20);
		frame.getContentPane().add(lblKtQu);
		
		txtKQ = new JTextField();
		txtKQ.setEnabled(false);
		txtKQ.setBounds(66, 16, 86, 20);
		frame.getContentPane().add(txtKQ);
		txtKQ.setColumns(10);
		
		btnThoat = new JButton("Thoát");
		btnThoat.setBounds(562, 15, 89, 23);
		btnThoat.addActionListener(this);
		frame.getContentPane().add(btnThoat);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnThoat){
			frame.dispose();
		}
		
	}
}
