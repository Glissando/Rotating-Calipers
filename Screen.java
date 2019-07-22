//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

import java.util.List;
import java.awt.*;
import javax.swing.*;

public class Screen extends JPanel 
{
    private static final long serialVersionUID = 1L;
    private int screenWidth;
    private int screenHeight;
    Polygon hull;
    List<Point> points;
    Rectangle OMBB;
    Rectangle AABB;
    List<Rectangle> rects;

    public JFrame frame;

    public Screen()
    {
        screenWidth = 720;
        screenHeight = 1280;

        // create a basic JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Rotating Calipers");
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // add panel to main frame
        frame.add(this);
        
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public Screen(int width, int height)
    {
        screenWidth = width;
        screenHeight = height;

        // create a basic JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Rotating Calipers");
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // add panel to main frame
        frame.add(this);
        
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void paint(Graphics page)
    {
        super.paint(page);
        //Resize screen
        double scaleX = getSize().getWidth() / screenWidth;
        double scaleY = getSize().getHeight() / screenHeight;
        Graphics2D g2d = (Graphics2D)page;
        g2d.scale(scaleX, scaleY);
        super.paint(page);

        page.setColor(Color.BLACK);
        page.fillRect(0, 0, screenWidth, screenHeight);
        //Draw the polygons

        if(points != null)
        {
            for(int i=0;i<points.size();i++)
            {
                points.get(i).draw(page);
            }
        }

        if(hull != null && Main.ui.hull.isSelected())
        {
            hull.draw(page, Color.BLUE);
        }

        if(AABB != null && Main.ui.AABB.isSelected())
        {
            AABB.draw(page, Color.YELLOW);
        }
        
        if(OMBB != null && Main.ui.OMBB.isSelected())
        {
            OMBB.draw(page, Color.RED);
        }
    }
}