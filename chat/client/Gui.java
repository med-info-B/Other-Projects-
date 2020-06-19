package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.ListResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;



public class Gui  implements Observer{
	
	/**
	 * 
	 */
	private Client client;
	private JFrame frame; 
	private JMenuBar menuP;
	private JMenu menu1;
	private JMenuItem itemMenu1, itemMenu2, itemMenu3, itemDis;
	private JLabel labelMsg;
	private JPanel editPanel, bouttonPanel, listClientPanel;
	private JScrollPane scrollChat;// scrollClientsConnected;
	private JButton sendB, sendP,connecte, disconnect, creatSall, abonn;
	private TextArea textArea;
	private TextField textField;
	private ArrayList<JRadioButton> listClient = new ArrayList<>();
	private ButtonGroup group =new ButtonGroup(); 
	private String to=null;
	private  ActionListener sliceActionListener;
	
	public Gui(){
		super();
		client = new Client(1234, "localhost");	
		client.register(this);
		frame();
	
		composante();
		menu();
		listener();
		frame.pack();
		frame.repaint();
		frame.validate();


	}

	@Override
	public void notifyObserverMessageSent(String from, String msgR) {	 
		labelMsg.add(new JLabel(from+" : "+msgR));
		frame.validate();
	}
	
	
	@Override
	public void nottifyObserverClientConnected(String clients) {
		this.listClient.add(new JRadioButton(clients));
		for(JRadioButton b : listClient) {
			group.add(b);
			listClientPanel.add(b);
			b.addActionListener(sliceActionListener);
		}
		frame.validate();
	}
	
	// Cadre Pere
	private void frame(){
		
		frame = new JFrame("Chat ");
		// Dellégation  à la class BorderLayout en vue d'arranger les elements
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setBackground(Color.GRAY);
		frame.setSize(500, 500);		
	}
	
	
	/**
	 *    Composant
	 */
	private void composante() {
	
		// Partie a gauche
		labelMsg =  new JLabel();
		labelMsg.setLayout(new BoxLayout(labelMsg, BoxLayout.Y_AXIS));
		scrollChat = new JScrollPane(labelMsg);
	    scrollChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollChat,BorderLayout.CENTER);
	  
        // Editor chat 
        editPanel = new JPanel(new BorderLayout());
        bouttonPanel = new JPanel(new BorderLayout());
        
        textArea = new TextArea();
        sendB = new JButton("Send");
        sendP = new JButton("Private");
        bouttonPanel.add(sendB, BorderLayout.SOUTH);
        bouttonPanel.add(sendP, BorderLayout.CENTER);
         
        editPanel.add(textArea, BorderLayout.CENTER);
        editPanel.add(bouttonPanel, BorderLayout.EAST);
   
        frame.add(editPanel, BorderLayout.SOUTH);
        // Paritie a droite 
        JPanel partieG = new JPanel(new BorderLayout());
        partieG.setPreferredSize(new Dimension(140,100));
        listClientPanel = new JPanel();
        listClientPanel.setLayout(new BoxLayout(listClientPanel, BoxLayout.Y_AXIS));
        listClientPanel.add(new JLabel("Client Connected"));

        
        JScrollPane scrollList = new JScrollPane(listClientPanel);    
        listClientPanel.setPreferredSize(new Dimension(110,300));
        partieG.add(scrollList, BorderLayout.NORTH);
        
        JPanel listRoomPanel = new JPanel(new BorderLayout());
        JScrollPane scrollListR = new JScrollPane(listRoomPanel);
        listRoomPanel.add(new JLabel("Room existant"),BorderLayout.NORTH);
        partieG.add(scrollListR, BorderLayout.SOUTH);
        
        frame.add(partieG, BorderLayout.EAST);
        frame.validate();
        
	  
        
	  
	}

	
	
	/**
	 *   Menu de de l application
	 */
	private void menu() {		 
		 menuP = new JMenuBar();
		 menu1 = new  JMenu("File");
		 // Items ou Action
		 itemMenu1 = new JMenuItem("Address IP");
		 itemMenu2 =new JMenuItem("Quit");
		 this.connecte = new JButton("connect");
		 this.disconnect = new JButton("disconnect");
		 this.creatSall = new JButton("Create Salle");
		 textField = new TextField();
		 textField.setSize(10,10);
		 //  Rajout des Actions dans le sous le menu 
		 menu1.add(itemMenu1);
		 menu1.add(itemMenu2);
		 
		 menuP.add(menu1);
		 menuP.add(connecte);
		 menuP.add(disconnect);
		 menuP.add(creatSall);
		 menuP.add(textField);
		 
		 frame.add(menuP, BorderLayout.NORTH); 
	}

	
	private void listener() {
		
	    // Create Room
	    creatSall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("test ");
				System.out.println("test -> "+textField.getText());
				client.createSalle(textArea.getText());
				
			}
		});
		// Adresse Ip
		itemMenu1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane d = new JOptionPane();
				JOptionPane.showMessageDialog( new JFrame(), "Address IP : "+client.sock.getLocalAddress().toString(), 
				      "  ", JOptionPane.NO_OPTION);
			}
			
		});
		// Adresse Qui
		disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.quit();
			}
			
		});
		// connecter
		connecte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					client.connect();
			}
			
		});
		
		// Envoie mgs Privée
		sendP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent even){
				if(client.connected) {
				if(to != null) {
					client.envoieMessagePrive(textArea.getText(), to);
					textArea.setText(null);
				}else {
					JOptionPane d = new JOptionPane();
					JOptionPane.showMessageDialog( new JFrame(), "Aucun client n'est selectionée : ",     "  ", JOptionPane.NO_OPTION);
				}
				}
			}
		});
		// envoie message 
		sendB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent even) {
				if(client.connected) {
					client.envoieMessage(textArea.getText());
					textArea.setText(null);
				}
				JOptionPane d = new JOptionPane();
				JOptionPane.showMessageDialog( new JFrame(), "Vous êtes pas connecter ",     " Connecter vous SVP ", JOptionPane.NO_OPTION);
			}
		});
		  sliceActionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton aButton = (AbstractButton) actionEvent.getSource();
		        to = aButton.getText();
		      }
		    };

		
		
		
		}
		
		
	







}

	
	


	
