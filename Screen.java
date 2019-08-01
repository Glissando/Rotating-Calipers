//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Screen extends JPanel 
{
    private static final long serialVersionUID = 1L;
    private int screenWidth;
    private int screenHeight;
    AffineTransform transform;
    Polygon hull;
    List<Point> points;
    Rectangle OMBB;
    Rectangle AABB;
    List<Rectangle> rects;

    public JFrame frame;

    public Screen()
    {
        screenWidth = 1280;
        screenHeight = 720;

        // create a basic JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Rotating Calipers");
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // add panel to main frame
        frame.add(this);
        setBackground(Color.BLACK);
        frame.setResizable(true);
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
        setBackground(Color.BLACK);
        frame.setResizable(true);
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                java.awt.Rectangle b = componentEvent.getComponent().getBounds();

                componentEvent.getComponent().setBounds(b.x, b.y, b.width, b.width * 9/16);
            }
        });
    }
    
    public void paint(Graphics page)
    {
        //Resize screen
        double scaleX = getSize().getWidth() / screenWidth;
        double scaleY = getSize().getHeight() / screenHeight;
        Graphics2D g2d = (Graphics2D)page;
        transform = g2d.getTransform();
        
        g2d.scale(scaleX, scaleY);

        page.setColor(Color.BLACK);
        page.fillRect(0, 0, screenWidth, screenHeight);
        //Draw the polygons
        super.paint(page);
        
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

    public Point2D screenPos(Point2D p)
    {
        Point2D ret = null;
        transform.transform(p, ret);
        return ret;
    }
}