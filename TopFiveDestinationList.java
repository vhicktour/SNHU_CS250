import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// The main class for the top 5 destinations list application.
public class TopFiveDestinationList {
    public static void main(String[] args) {
        // Use Swing's thread-safe way to create the GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}

// Main application frame that displays the destinations.
class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;  // The model for the list of destinations.

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();

        // Add the top destinations and their corresponding images to the list.
        addDestinationNameAndPicture("1. Top Destination (Great wall of China - A world-renowned architectural wonder, the Great Wall stretches over 13,000 miles and represents China's historic strength and strategic defense. Built over several dynasties, it's a testament to the engineering capabilities of ancient Chinese civilization.)", new ImageIcon(getClass().getResource("/resources/Great_Wall_of_China_July_2006.JPG")));
        addDestinationNameAndPicture("2. 2nd Top Destination (Ilimanaq Buiobuione Greenland - Nestled in Greenland, Ilimanaq is a striking vision of untouched beauty. Its rugged terrains and serene vistas are a testament to the Earth's raw, natural beauty.\r\n" + 
        		")", new ImageIcon(getClass().getResource("/resources/Ilimanaq_Greenland_Buiobuione.jpg")));
        addDestinationNameAndPicture("3. 3rd Top Destination (lighthouse Iceland - Iceland's lighthouses stand as beacons amidst the country's dramatic landscapes, often contrasted by the wild seas, black beaches, and Northern Lights. These structures symbolize guidance and hope in the often-harsh Icelandic environment.\r\n" + 
        		")", new ImageIcon(getClass().getResource("/resources/Lighthouse_Iceland.jpg")));
        addDestinationNameAndPicture("4. 4th Top Destination (abbey paulinzella church germany - The Paulinzella Abbey Church in Germany showcases the country's rich religious and architectural heritage. The church's intricate designs and serene ambiance reflect the profound religious sentiment of the region.\r\n" + 
        		") ", new ImageIcon(getClass().getResource("/resources/Parque_nacional_de_Ordesa_y_Monte_Perdido.jpg")));
        addDestinationNameAndPicture("5. 5th Top Destination (parque nacional de ordesa Spain - Parque Nacional de Ordesa offers a glimpse into Spain's diverse ecosystems, with towering peaks, deep canyons, and lush forests. This natural wonder captures the heart of Spain's wilderness and scenic beauty.)", new ImageIcon(getClass().getResource("/resources/Paulinzella_abbey_church.jpg")));

        // Create the list and embed it inside a scroll pane.
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        
        // Use a custom renderer for list items.
        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(10, 10, 10, 10);
        list.setCellRenderer(renderer);

        // Add components to the main frame.
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // A label showing the creator of the application.
        JLabel nameLabel = new JLabel("Created by [Victor Udeh]");
        getContentPane().add(nameLabel, BorderLayout.SOUTH);
    }

    // Method to add a destination name and its corresponding picture to the list.
    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}

// Helper class to represent both text and icon for a destination.
class TextAndIcon {
    private String text;  // The name or description of the destination.
    private Icon icon;    // The image icon of the destination.
    
    // Constants to control the icon dimensions.
    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        setIcon(icon);  // Resizes the icon if necessary.
    }

    // Getters and setters.
    public String getText() { return text; }
    public Icon getIcon() { return icon; }
    public void setText(String text) { this.text = text; }

    // Setter for the icon that resizes if necessary.
    public void setIcon(Icon icon) {
        // Check the icon dimensions and resize if needed.
        if(icon != null && icon.getIconWidth() > IMG_WIDTH && icon.getIconHeight() > IMG_HEIGHT) {
            Image img = ((ImageIcon)icon).getImage();
            Image newImg = img.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
            this.icon = new ImageIcon(newImg);
        } else {
            this.icon = icon;
        }
    }
}

// Custom renderer for list items to display both text and icon.
class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1); // Default border without focus.
    private Border insideBorder;  // Padding inside the cell.

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);  // Ensure background color is visible.
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        TextAndIcon tai = (TextAndIcon) value;
        setText(tai.getText());
        setIcon(tai.getIcon());

        // Style for selected items.
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        // Handle focus styling.
        Border outsideBorder = hasFocus ? UIManager.getBorder("List.focusCellHighlightBorder") : NO_FOCUS_BORDER;
        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // Performance optimization: Override some methods to do nothing for a cell renderer.
    @Override public void validate() {}
    @Override public void invalidate() {}
    @Override public void repaint() {}
    @Override public void revalidate() {}
    @Override public void repaint(long tm, int x, int y, int width, int height) {}
    @Override public void repaint(Rectangle r) {}
}
