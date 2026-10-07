import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {
    public static void main(String[] args) {
        // --- 1. Set Look and Feel for cleaner UI foundations ---
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Fallback silently
        }

        // --- 2. Color Palette & Typography ---
        Color bgDark = new Color(18, 18, 18);          // Sleek deep charcoal background
        Color cardDark = new Color(28, 28, 30);        // Secondary container background
        Color accentPink = new Color(255, 46, 99);     // Vibrant pink branding accent
        Color accentPinkHover = new Color(255, 85, 127); // Lighter pink for hover states
        Color textLight = new Color(245, 245, 247);    // Premium white text
        Color textMuted = new Color(150, 150, 150);    // Gray for secondary text

        Font mainFont = new Font("Segoe UI", Font.PLAIN, 15);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);
        Font lyricFont = new Font("Georgia", Font.ITALIC, 18);

        // --- 3. Create Main Frame ---
        JFrame frame = new JFrame("👁️ Bad Bunny Lyric Spitter Premium 👁️");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 550); // Wider frame to fit album art gracefully
        
        JPanel mainPanel = new JPanel(new BorderLayout(0, 15));
        mainPanel.setBackground(bgDark);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.add(mainPanel);

        // --- 4. Custom Top Header Selection Bar ---
        JPanel topPanel = new JPanel(new BorderLayout(0, 8));
        topPanel.setBackground(bgDark);

        JLabel selectLabel = new JLabel("CHOOSE YOUR VIBE:");
        selectLabel.setFont(titleFont.deriveFont(12f)); 
        selectLabel.setForeground(accentPink);
        topPanel.add(selectLabel, BorderLayout.NORTH);

        String[] songs = { 
            "Select a song...", 
            "Titi Me Preguntó", 
            "Monaco", 
            "Dakiti", 
            "Ojitos Lindos", 
            "Efecto", 
            "La Romana" 
        };
        JComboBox<String> songDropdown = new JComboBox<>(songs);
        songDropdown.setFont(mainFont);
        songDropdown.setBackground(cardDark);
        songDropdown.setForeground(textLight);
        songDropdown.setPreferredSize(new Dimension(0, 40)); 
        topPanel.add(songDropdown, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- 5. Split Display (Left: Album Art | Right: Lyrics Screen) ---
        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setBackground(bgDark);

        // Custom Component Canvas to Draw Album Artwork dynamically
        AlbumArtPanel albumArtView = new AlbumArtPanel(cardDark, textMuted);
        contentPanel.add(albumArtView, BorderLayout.WEST);

        // Lyrics Area Panel
        JTextArea lyricArea = new JTextArea("\n\n  Choose a track above\n  to spit lyrics...");
        lyricArea.setFont(lyricFont);
        lyricArea.setBackground(cardDark);
        lyricArea.setForeground(textMuted);
        lyricArea.setEditable(false);
        lyricArea.setLineWrap(true);
        lyricArea.setWrapStyleWord(true);
        lyricArea.setBorder(new EmptyBorder(25, 25, 25, 25));

        JScrollPane scrollPane = new JScrollPane(lyricArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(cardDark, 1));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // --- 6. Custom Modern Action Button with Smooth Hover Animations ---
        JButton spitButton = new JButton("SPIT LYRICS");
        spitButton.setFont(titleFont);
        spitButton.setBackground(accentPink);
        spitButton.setForeground(Color.WHITE);
        spitButton.setFocusPainted(false);
        spitButton.setBorderPainted(false);
        spitButton.setOpaque(true);
        spitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Turns pointer to hand icon
        spitButton.setPreferredSize(new Dimension(0, 50));

        // Adding Mouse Listeners to calculate structural hover changes manually in Swing
        spitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                spitButton.setBackground(accentPinkHover); // Lightens on Hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                spitButton.setBackground(accentPink); // Reverts back on Exit
            }
        });

        mainPanel.add(spitButton, BorderLayout.SOUTH);

        // --- 7. Button Activation Logic & Media Engine Changes ---
        spitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSong = (String) songDropdown.getSelectedItem();
                String lyrics = "";

                lyricArea.setForeground(textLight);

                switch (selectedSong) {
                    case "Titi Me Preguntó":
                        albumArtView.setAlbum("Un Verano Sin Ti", "❤️", new Color(240, 100, 40));
                        lyrics = "\"Titi me preguntó\n"
                               + "Si tengo many novia'\n"
                               + "Many novia'\n\n"
                               + "Hoy tengo a una, mañana a otra\n"
                               + "Hey, pero no dejes que nadie te mienta...\"";
                        break;

                    case "Monaco":
                        albumArtView.setAlbum("Nadie Sabe...", "🐎", new Color(40, 40, 45));
                        lyrics = "\"Dime, ¿gato, qué tú sabe' de esto?\n"
                               + "Ustedes viajan en económica, yo viajo en jet\n\n"
                               + "Bebe, los carros son F1, no son Corvette\n"
                               + "Ayer me vio Verstappen en el paddock...\"";
                        break;

                    case "Dakiti":
                        albumArtView.setAlbum("El Último Tour", "👁️", new Color(50, 20, 90));
                        lyrics = "\"Baby, ya yo me enteré, se nota que ya estás sola\n"
                               + "Y te gusta bailar con mi canción\n\n"
                               + "Y yo me emborraché\n"
                               + "Pa' no pensarte tanto, pero fue peor...\"";
                        break;

                    case "Ojitos Lindos":
                        albumArtView.setAlbum("Un Verano Sin Ti", "☀️", new Color(245, 160, 60));
                        lyrics = "\"Hace tiempo que no agarro a nadie de la mano\n"
                               + "Hace tiempo que no llevo rosas\n\n"
                               + "Pero tú me tienes enredado\n"
                               + "Y de esos ojitos lindos que me miran...\"";
                        break;

                    case "Efecto":
                        albumArtView.setAlbum("Un Verano Sin Ti", "🌊", new Color(30, 140, 200));
                        lyrics = "\"El party en el babi no para, no para\n"
                               + "Tú te mueves heavy, tú te mueves rara\n\n"
                               + "Tú me metes el efecto, me pones perfecto\n"
                               + "Esta nota nunca se acaba...\"";
                        break;

                    case "La Romana":
                        albumArtView.setAlbum("X 100PRE", "👁️", new Color(220, 50, 50));
                        lyrics = "\"Pásame la botella, eh, eh\n"
                               + "Que la vida es bella, eh, eh\n\n"
                               + "¡Fuego, fuego, wey, fuego!\n"
                               + "Bad Bunny, El Alfa, la calle botando humo...\"";
                        break;

                    default:
                        lyricArea.setForeground(accentPink);
                        albumArtView.resetAlbum();
                        lyrics = "\n\n  Por favor, selecciona una canción válida primero!";
                        break;
                }
                
                lyricArea.setText(lyrics);
            }
        });

        // Center and reveal application window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// --- 8. Helper Canvas Class to render Vector Graphic Album Arts Safely ---
class AlbumArtPanel extends JPanel {
    private String albumTitle = "No Track";
    private String graphicIcon = "🎵";
    private Color coverColor;
    private Color defaultBg;
    private Color defaultText;

    public AlbumArtPanel(Color defaultBg, Color defaultText) {
        this.defaultBg = defaultBg;
        this.defaultText = defaultText;
        this.coverColor = defaultBg;
        this.setPreferredSize(new Dimension(220, 0)); // Clean square proportions layout
    }

    public void setAlbum(String title, String icon, Color themeColor) {
        this.albumTitle = title;
        this.graphicIcon = icon;
        this.coverColor = themeColor;
        this.repaint(); // Tells Swing UI engine to visually refresh the graphics canvas
    }

    public void resetAlbum() {
        this.albumTitle = "No Track";
        this.graphicIcon = "🎵";
        this.coverColor = defaultBg;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Smooth edges out
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw Base Cover Square
        g2.setColor(coverColor);
        g2.fillRoundRect(0, 0, getWidth(), getWidth(), 16, 16);
        // Center Art Motif Icon symbol
        g2.setFont(new Font("Segoe UI", Font.PLAIN, 64));
        FontMetrics fmIcon = g2.getFontMetrics();
        int iconX = (getWidth() - fmIcon.stringWidth(graphicIcon)) / 2;
        int iconY = (getWidth() / 2) + 20;
        g2.drawString(graphicIcon, iconX, iconY);
        // --- UPDATED DESIGN ELEMENT ---
        // Dynamically flags color profile text explicitly to BLACK if a cover is loaded
        if (coverColor.equals(defaultBg)) {
        g2.setColor(defaultText); // Reverts back to soft gray when application has no song selected
        } else {
        g2.setColor(Color.BLACK); // Clean, sharp black font subtitle overlayed underneath the artwork graphics canvas
        }
        g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        FontMetrics fmText = g2.getFontMetrics();
        int textX = (getWidth() - fmText.stringWidth(albumTitle)) / 2;
        g2.drawString(albumTitle, textX, getWidth() + 40);
        }
}
