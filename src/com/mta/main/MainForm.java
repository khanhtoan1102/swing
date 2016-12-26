package com.mta.main;

import java.awt.EventQueue;

import com.mta.swing.DrugForm;
import com.mta.swing.LoginForm;

public class MainForm {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
