//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI
{
    Screen screen;
    JCheckBox hull;
    JCheckBox OMBB;
    JCheckBox AABB;

    public UI(Screen screen)
    {
        this.screen = screen;
        JFrame frame = screen.frame;
        JButton random = new JButton("Random");
        hull = new JCheckBox("Convex Hull");
        OMBB = new JCheckBox("Mininmum Box");
        AABB = new JCheckBox("Bounding Box");
        OMBB.setSelected(true);

        random.setBounds(700,500,100,40);
        screen.add(random);
        screen.add(hull);
        screen.add(OMBB);
        screen.add(AABB);
        screen.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        screen.setVisible(true);
        
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);

        frame.setVisible(true);
        //frame.pack();
        //frame.setVisible(true);
        //screen.setVisible(true);
        screen.invalidate();
        screen.repaint();
        
        screen.addMouseListener(new MouseAdapter()
        { 
            public void mousePressed(MouseEvent e)
            { 
                onClick(e);
            } 
        });

        random.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Main.createOMBB(Main.randomPoints(25));
                screen.repaint();
            }
        });

        hull.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                screen.repaint();
            }
        });

        OMBB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                screen.repaint();
            }
        });

        AABB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                screen.repaint();
            }
        });
    }
    

    public void onClick(MouseEvent e)
    {
        java.awt.geom.Point2D p2d = e.getPoint();
        Point point = new Point(p2d.getX(), p2d.getY());
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            screen.points.add(point);
            System.out.println(point);
            Main.createOMBB(screen.points);
            screen.repaint();
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            for(int i=0;i<screen.points.size();i++)
            {
                if(screen.points.get(i).distance(point) < 20)
                {
                    screen.points.remove(i);
                    if(screen.points.size() > 3)
                        Main.createOMBB(screen.points);
                    screen.repaint();
                    
                }
            }
        }
    }
}