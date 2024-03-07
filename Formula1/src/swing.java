import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class swing {
    Formula1MenaxhimKampionati formula1 = new Formula1MenaxhimKampionati();
    JTextArea area1, area2, area3;
    JButton button1, button2, button3, button4, button5, button6, button7, button8;

    swing() throws FileNotFoundException {
        formula1.lexoFile();
        JFrame f = new JFrame();
        area1 = new JTextArea();
        area2 = new JTextArea();
        area3 = new JTextArea();
        area1.setEditable(false);
        area3.setEditable(false); // nuk preket teksti
        area3.setBounds(420, 10, 160, 50);
        area3.setText("Elidjana Nako, Alisa Ibrahimi\nAna Qafa, Elton Xhebro");
        area1.setBounds(150, 40, 700, 580);
        area2.setBounds(450, 750, 100, 100);
        button1 = new JButton("Shfaq shoferet");
        button1.setBounds(10, 630, 120, 30);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    formula1.updateShoferetFile();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                String text = formula1.shfaqTeGjitheShoferetGui();
                area1.setText(text);
            }
        });
        button2 = new JButton("Rendit sipas pikeve ne rend zbrites");
        button2.setBounds(140, 630, 250, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formula1.sortDesc();
                String text = formula1.shfaqTeGjitheShoferetGui();
                area1.setText(text);
            }
        });
        button3 = new JButton("Rendit sipas pikeve ne rend rrites");
        button3.setBounds(400, 630, 250, 30);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formula1.sortAsc();
                String text = formula1.shfaqTeGjitheShoferetGui();
                area1.setText(text);
            }
        });
        button4 = new JButton("Rendit sipas fitoreve");
        button4.setBounds(660, 630, 150, 30);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formula1.sortDescFitore();
                String text = formula1.shfaqTeGjitheShoferetGui();
                area1.setText(text);
            }
        });
        button5 = new JButton("Simulo gare");
        button5.setBounds(820, 630, 120, 30);
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formula1.pozicionetCfaredo();
                String text = formula1.simuloGareGui();
                try {
                    formula1.updateGaratFile();
                    formula1.updateDataFile();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                area1.setText(text);
            }
        });
        button6 = new JButton("Simulo gare me shans");
        button6.setBounds(10, 670, 200, 30);
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formula1.pozicionetCfaredoShans();
                String text = formula1.simuloGareGui();
                try {
                    formula1.updateGaratFile();
                    formula1.updateDataFile();
                } catch (FileNotFoundException ex) {throw new RuntimeException(ex);

    }
                area1.setText(text);
            }
        });
        button7 = new JButton("Shfaq garat ne baze te dates");
        button7.setBounds(220, 670, 250, 30);
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formula1.renditGaratSipasDatesAscending();
                String text = formula1.renditGaratGui();
                area1.setText(text);
            }
        });
        button8 = new JButton("Kerko garat e shoferit");
        button8.setBounds(350, 710, 300, 30);
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = area2.getText();
                String text2 = formula1.kerkoSipasShoferit(text);
                area1.setText(text2);
            }
        });
        f.add(area1);
        f.add(area2);
        f.add(area3);
        f.add(button1);
        f.add(button2);
        f.add(button3);
        f.add(button4);
        f.add(button5);
        f.add(button6);
        f.add(button7);
        f.add(button8);
        f.setSize(1000, 1000);
        f.setLayout(null);
        f.setVisible(true);
    }
}