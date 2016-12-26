package com.mta.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.mta.model.Drug;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginForm implements ActionListener{

	private JFrame frame;
	private JTextField txtuserName;
	private JButton btnExit;
	private JButton btnLogin;
	private JPasswordField txtpassword;

	
	/**
	 * Launch the application.
	 */

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTnngNhp = new JLabel("Tên đăng nhập");
		lblTnngNhp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTnngNhp.setBounds(33, 53, 136, 33);
		frame.getContentPane().add(lblTnngNhp);
		
		JLabel lblMtKhu = new JLabel("Mất khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMtKhu.setBounds(33, 99, 136, 33);
		frame.getContentPane().add(lblMtKhu);
		
		txtuserName = new JTextField();
		txtuserName.setBounds(189, 53, 168, 33);
		frame.getContentPane().add(txtuserName);
		txtuserName.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(69, 164, 117, 42);
		btnLogin.addActionListener(this);
		frame.getContentPane().add(btnLogin);
		
		btnExit = new JButton("Thoát");
		btnExit.setBounds(235, 166, 117, 42);
		btnExit.addActionListener(this);
		frame.getContentPane().add(btnExit);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(189, 99, 168, 33);
		frame.getContentPane().add(txtpassword);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnLogin){
			if(txtuserName.getText().equals("admin") && txtpassword.getText().equals("admin")){
				DrugForm frm = new DrugForm();
				frm.getFrame().setVisible(true);
				frame.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khấu không chính xác","Thông báo",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getSource() == btnExit){
			int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát","Thông báo", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(kt == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
	}
}
