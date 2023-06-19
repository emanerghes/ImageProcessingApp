import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileSelectionPanel {
    private JPanel panel;
    private JTextField filePathTextField;
    private JButton chooseFileButton;
    private JButton openFileButton;
    private JButton saveButton;
    ImageProcessingApplication app = new ImageProcessingApplication();

    public FileSelectionPanel() {
        // Create the panel and components
        panel = new JPanel();
        filePathTextField = new JTextField(20);
        chooseFileButton = new JButton("Choose File");
        openFileButton = new JButton("Open");
        saveButton = new JButton("Save");

        JFrame frame = new JFrame("Choose File Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this.getPanel());
        frame.pack();
        frame.setVisible(true);

        // Action  for "Choose File" button
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser dialog
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(panel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file and set the path in the text field
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathTextField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // Action for "Open" button
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathTextField.getText();
                if (!filePath.isEmpty()) {
                    // Load the image using the ImageProcessingApplication
                    app.loadImage(filePath);
                } else {
                    JOptionPane.showMessageDialog(panel, "Please choose a file first", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action for "Save" button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathTextField.getText();
                if (!filePath.isEmpty()) {
                    // Prompt the user to enter a name for the saved photo
                    String saveName = JOptionPane.showInputDialog(panel, "Enter a name for the saved photo:");
                    if (saveName != null && !saveName.isEmpty()) {
                        // Construct the save path by combining the file path, name, and extension
                        String savePath = filePath + File.separator + saveName + ".png";
                        app.saveImage(savePath);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid save name", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please choose a file first", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the panel
        panel.add(filePathTextField);
        panel.add(chooseFileButton);
        panel.add(openFileButton);
        panel.add(saveButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this.getPanel());
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }
}
