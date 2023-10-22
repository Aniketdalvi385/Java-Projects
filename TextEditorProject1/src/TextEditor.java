import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    // file menu items
    JMenuItem newFile, openFile, saveFile;

    // edit menu items
    JMenuItem  cut, copy, paste, selectAll, close;

    JTextArea textArea;
    TextEditor() {
        // initializing the first frame
        frame = new JFrame();

        // initializing the menu-bar
        menuBar = new JMenuBar();

        //initializing textArea
        textArea = new JTextArea();

        // initializing the menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // adding actionlistiners to file items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // adding file menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // initializing edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // adding actionlistiners to edit items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        // adding menus to the menu-bar
        menuBar.add(file);
        menuBar.add(edit);


        // setting the menu bar to the frame
        frame.setJMenuBar(menuBar);

        // adding text area to the frame
        //frame.add(textArea);

        // creating new content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //adding text area to panel
        panel.add(textArea, BorderLayout.CENTER);

        //creating scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // adding scroll pane to panel
        panel.add(scrollPane);

        // adding panel to frame
        frame.add(panel);

        // setting the dimensions
        frame.setBounds(100, 100, 400, 400);
        frame.setTitle("Retro Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    textArea.setText(output);
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor textEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}