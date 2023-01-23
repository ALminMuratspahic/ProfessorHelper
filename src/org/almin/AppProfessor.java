package org.almin;

import org.almin.Class.IX1;
import org.almin.Class.IX2;
import org.almin.Class.IX3;
import org.almin.Class.VI1;
import org.almin.Class.VI2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppProfessor extends JFrame{

    private JButton sesti1, sesti2,deveti1,deveti2,deveti3;
    private JPanel panel;
    JLabel label;

    public static void main( String[] args )
    {
        new AppProfessor().startProgram();

    }
    public void startProgram(){
  	
        label=new JLabel("Choose your class: ");
        panel=new JPanel();
        sesti1 =new JButton("VI - 1");
        sesti2 =new JButton("VI - 2");
        deveti1 =new JButton("IX - 1");
        deveti2=new JButton("IX - 2");
        deveti3 = new JButton("IX - 3");
    
        panel.add(label);
        panel.add(sesti1);
        panel.add(sesti2);
        panel.add(deveti1);
        panel.add(deveti2);
        panel.add(deveti3);
        add(panel);

        sesti2.addActionListener(new ButtonClicker());
        sesti1.addActionListener(new ButtonClicker());
        deveti1.addActionListener(new ButtonClicker());
        deveti2.addActionListener(new ButtonClicker());
        deveti3.addActionListener(new ButtonClicker());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,200);
        setVisible(true);
    }

    public class ButtonClicker implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                Object o =e.getSource();
            if(o== sesti1){
                new VI1();
            }else if (o== sesti2){
                new VI2();
            } else if (o==deveti1) {
                new IX1();
            }else if(o==deveti2){
				new IX2();
			}else if(o==deveti3) {
				new IX3();
			}
        }
    }


}
