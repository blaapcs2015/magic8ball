import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A panel maintaining a picture of a do not enter sign.
 */
 public class MagicEightBall extends JPanel 
 {
     /** Set this constant to the number of colors you've programmed */
     public static final int NUMBER_COLOR_CHOICES = 8;
     
     /** Set this constant to the number of answers you've programmed */
     public static final int NUMBER_ANSWER_CHOICES = 8;
     
     /** Set this constant to the answer font size */
     public static final int ANSWER_FONT_SIZE = 16;
     
     /** Set this constant to cause a longer wait before the answer */
     public static final int ANSWER_DELAY_LOOP_CNTR = 25;
     
     /** Set this constant to the color to use for the eight ball */
     public static final Color MAGIC_EIGHT_BALL_COLOR = Color.BLACK;
     
     /** Variable to prevent duplicate colors */
     private static Color lastRandomColor = Color.BLACK;
     
    /**
     * The Magic 8 Ball will answer all of life's questions!
     * 
     * Your challenge is to write code to provide 8 random 
     * answers that will display in response to your questions.
     * You will also need to write a loop (what kind??) that 
     * accepts a question from the keyboard to which one of
     * your random answers will be provided as an answer.
     * 
     * For those of you who are interested, this class 
     * demonstrates some of Java's built in graphics capabilities.
     * The program only touches the surface of what graphics
     * Java can generate!
     * 
     */
    public static void main(String[] args) 
    {
        /*
         * Do not delete this line of code, it creates a
         * MagicEightBall that will answer all your questions.
         * Soon, you will learn how to create your own classes.
         */
        MagicEightBall eightBall = new MagicEightBall();
        
        /*
         * Create a scanner to get keyboard input. You
         * will type in the questions you want your
         * MagicEightBall to answer
         */
        Scanner keyboard = new Scanner(System.in);
        
        /*
         * Write a "while" loop that does the following:
         * 1) prompts the user to enter a question
         * 2) uses the "nextLine" method on the scanner class
         *    to read the user's question from the keyboard
         * 3) check if the read text contains a "?" character
         * 4) if it does, call eightBall.poseQuestion, passing it
         *    the line of text/question read from the keyboard
         * 5) if it doesn't, breaks out of the while loop (do
         *    you remember the keyword to use to break out of
         *    a loop?)
         */
        
        // we're all done. It is always a good idea to close resources 
        // we are no longer using
        eightBall.shutdown();
        keyboard.close();
    }
    
    /**
     * Return a random color. You should modify the logic
     * of the "while" loop to add more colors. Colors
     * are a part of the Java AWT package. Some other
     * choices are: Color.BLUE, Color.RED, and Color.YELLOW.
     * Many other choices exist as well, see the Javadoc for
     * the "Color" class for more. 
     * @return a random color
     */
    public static Color getRandomColor()
    {
        return Color.BLACK;
    }

    /**
     * Write a new static method named "getRandomAnswer".
     * Your method should take no parameters and return a
     * String value. It must do the following:
     * 
     * Return a random answer to be displayed in the
     * Magic 8 Ball Answer box. Set the number of
     * answers you want by changing the constant
     * NUMBER_ANSWER_CHOICES at the top of this class.
     * Then, use Math.random() to select the answer
     * to return.
     * @return a random answer
     */
    
    /*
     * The code below this point will not look familiar to you. Don't worry,
     * we'll get there later this year! For now, know that with what you have 
     * already learned, you can do a lot. And soon, you will be able to do
     * almost anything!!
     * 
     * Feel free to read what's below, but please DO NOT MODIFY IT or
     * your MagicEightBall may lose all its mojo...
     */
    private String theQuestion;
    private String theAnswer;
    private JFrame mainFrame;
    private Label answerLabel;
    
    /**
     * This method is called a Constructor. It makes a new
     * instance of MagicEightBall every time it is called.
     */
    public MagicEightBall() 
    {
        super();
        mainFrame = new JFrame("The Magic 8 Ball");
        mainFrame.setSize(700, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBackground(Color.LIGHT_GRAY);
        mainFrame.getContentPane().add(this, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
    
    /**
     * This method gets the ball rolling. It makes some graphics calls
     * to display a "?" in random colors to simulate that the MagicEightBall
     * is "thinking". Then, it displays a random answer.
     * @param question the question to ask
     */
    public void poseQuestion(String question)
    {
        // force the eight ball window to the front of the screen
        toFront();
        
        // hide any old answers
        if (answerLabel != null)
            answerLabel.setVisible(false);
        
        // clearing these variables makes the repaint method
        // draw the clasic "8"
        theAnswer = null;
        theQuestion = null;
        
        this.repaint();
        pause(1000);
        
        // Setting the question triggers the repaint method
        // to draw a "?"
        theQuestion = question;       
        for (int i = 0; i < ANSWER_DELAY_LOOP_CNTR; i++) {
            pause(50 + (i * 2 * 10));
            this.repaint();
        }
        
        // finally, setting the answer causes
        // repaint to display the answer
        theAnswer = getRandomAnswer();
        this.repaint();
    }

    /**
     * Call this method to shut down the MagicEightBall.
     */
    public void shutdown()
    {
        mainFrame.dispose();
    }

    /**
     * Wait a period of time
     * @param pauseTimeInMilliSeconds the time to wait, in milliseconds (1/1000 of a second)
     */
    private void pause(int pauseTimeInMilliSeconds)
    {
        try
        {
            Thread.sleep(pauseTimeInMilliSeconds);
        }
        catch (InterruptedException e) { }
    }

    /**
     * A helper routine to force the eight ball window to the front of the display
     */
    public void toFront() {
      super.setVisible(true);
      int state = mainFrame.getExtendedState();
      state &= ~JFrame.ICONIFIED;
      mainFrame.setExtendedState(state);
      mainFrame.setAlwaysOnTop(true);
      mainFrame.toFront();
      mainFrame.requestFocus();
      mainFrame.setAlwaysOnTop(false);
    }
    
    /**
     * paintComponent is called by Java AWT when a component needs to
     * be redrawn, such as when its content changes, or when it pops to
     * the top of the display.
     */
    public void paintComponent(Graphics g) 
    {
        // paint (draw) the parents of this component
        super.paintComponent(g);

        // figure out the center of the display window.
        // This will change, based on the size of the main frame
        Point center = new Point(getWidth() / 2, getHeight() / 2);
        int radius = Math.min(getWidth() / 2, getHeight() / 2) - 5;
        int innerRadius = (int)(radius * 0.4);

        // Draw a big circle; this is the body of the 8 ball
        g.setColor(MAGIC_EIGHT_BALL_COLOR);
        g.fillOval(center.x - radius, center.y - radius,
                   radius * 2, radius * 2);
        
        // Draw the inner circle; this is where the "8" is displayed
        // and the answer to life's questions is displayed
        g.setColor(Color.WHITE);
        g.fillOval(center.x - innerRadius, center.y - innerRadius,
                   innerRadius * 2, innerRadius * 2);        
        
        /*
         * For those of you reading the code, the variables "theQuestion"
         * and "theAnswer" control our "state". In this case, the "state" 
         * of interest is what do we draw at the center of the 8 ball.
         * 
         * There are three cases to consider:
         * 
         * 1) theAnswer is empty && theQuestion is empty:
         *      display the "8" character
         * 
         * 2) theAnswer is empty && theQuestion isn't:
         *      display the "?" character
         * 
         * 3) theAnswer && theQuestion both are not empty:
         *      display the answer
         */
        if (this.theQuestion == null && theAnswer == null) {
            g.setColor(Color.BLACK);
            drawSingleCharacter('8', g, center);            
        }
        else if (this.theQuestion != null && theAnswer == null) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(center.x - innerRadius, center.y - innerRadius,
                       innerRadius * 2, innerRadius * 2);        
            
            g.setColor(getRandomColor());
            drawSingleCharacter('?', g, center);
        }
        else { // theAnswer must not be null
            drawAnswer(g, center, innerRadius);
        }       
    }

    /**
     * A helper method to draw a single character in a large font
     */
    private void drawSingleCharacter(char theChar, Graphics g, Point center)
    {
        g.setFont(new Font("Arial", Font.BOLD, 128));
        
        FontMetrics fm = g.getFontMetrics();
        int txtX = center.x - fm.charWidth(theChar)/2;
        int txtY = center.y + (fm.getHeight() - fm.getDescent()*2)/2;
        
        g.drawString(String.valueOf(theChar), txtX, txtY);
    }

    /**
     * A helper method to draw the answer
     */
    private void drawAnswer(Graphics g, Point center, int innerRadius)
    {
        if (answerLabel == null) {
            answerLabel = new Label();
            
            int labelHeight = (int)(innerRadius * .9);
            answerLabel.setSize(labelHeight*2, labelHeight);
            answerLabel.setBackground(Color.WHITE);
            answerLabel.setForeground(Color.BLACK);
            
            Point labelCenter = new Point(center.x - labelHeight,
                                          center.y - labelHeight / 2);
            
            answerLabel.setLocation(labelCenter);
            answerLabel.setAlignment(Label.CENTER);
            Font f = new Font("SanSerif", Font.BOLD + Font.ITALIC, ANSWER_FONT_SIZE);
            answerLabel.setFont(f);
            answerLabel.setEnabled(true);
            mainFrame.getContentPane().add(answerLabel);
        }
        
        answerLabel.setText(theAnswer);
        answerLabel.setVisible(true);  
    }
}
