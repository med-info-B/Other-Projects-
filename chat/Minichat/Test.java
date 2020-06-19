package Minichat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test{
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Test ");
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());  
		
		// Menu 
		JMenuBar menuP = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem item1 = new JMenuItem("test");		
		menu.add(item1);
		menuP.add(menu);
		frame.add(menuP, BorderLayout.NORTH);
		// Ecran 
			// Partie a gauche
		JPanel ecranPanel = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(ecranPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ecranPanel.setBackground(Color.YELLOW);	
		frame.add(scrollPane,BorderLayout.CENTER);
		
		JPanel editPanel = new JPanel(new BorderLayout());
		TextArea textArea = new TextArea();
		JButton sendB = new JButton("Send");
		JButton sendP = new  JButton("Private");
		JPanel panelBoutton = new JPanel(new BorderLayout());
		panelBoutton.add(sendB, BorderLayout.NORTH);
		panelBoutton.add(sendP, BorderLayout.CENTER);
		editPanel.add(textArea, BorderLayout.CENTER);
		editPanel.add(panelBoutton, BorderLayout.EAST);
		editPanel.setPreferredSize(new Dimension(1,100));
		frame.add(editPanel, BorderLayout.SOUTH);
		
		// Partie a adoite	
		JPanel ecranListClient = new JPanel(new BorderLayout());
		JScrollPane scrollPane2 = new JScrollPane(ecranListClient);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ecranListClient.setBackground(Color.blue);
		JLabel labelClient = new JLabel("ClientConnected");
		ecranListClient.add(labelClient, BorderLayout.NORTH);
		
		JPanel listclients = new JPanel();
		listclients.setLayout(new BoxLayout(listclients, BoxLayout.Y_AXIS));
		ecranListClient.add(listclients, BorderLayout.CENTER);
		ButtonGroup group = new ButtonGroup();
		ArrayList<JRadioButton> listRa = new ArrayList<JRadioButton>();
		for (int i = 0; i < 62; i++) {
			listRa.add(new JRadioButton("A"+i));
			group.add(listRa.get(i));
			listclients.add(listRa.get(i));
		}
		frame.add(scrollPane2, BorderLayout.EAST);
		frame.validate();
		
		
		for (int i = 0; i < listRa.size(); i++) {
			listRa.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent even) {
				JRadioButton test = (JRadioButton) even.getSource();
				System.out.println("dernièrement : "+test.getText());
				}
			});
		}
		
		
		
		
		
		
		
		
	
		
		
		
		
	}

 
}
