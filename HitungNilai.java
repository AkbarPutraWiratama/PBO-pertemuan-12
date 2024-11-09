package Modul;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HitungNilai {

    private JFrame frame;
    private JTable table;
    private JTextField nimField, nameField, addressField, courseField;
    private JTextField grade1Field, grade2Field, grade3Field, grade4Field, grade5Field, finalGradeField;
    private DefaultTableModel tableModel;
    private ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HitungNilai window = new HitungNilai();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HitungNilai() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 648, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Label dan Text Field
        JLabel nimLabel = new JLabel("NIM");
        nimLabel.setBounds(40, 20, 80, 25);
        frame.getContentPane().add(nimLabel);

        nimField = new JTextField();
        nimField.setBounds(130, 20, 150, 25);
        frame.getContentPane().add(nimField);

        JLabel nameLabel = new JLabel("Nama");
        nameLabel.setBounds(40, 50, 80, 25);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 50, 150, 25);
        frame.getContentPane().add(nameField);

        JLabel addressLabel = new JLabel("Alamat");
        addressLabel.setBounds(40, 80, 80, 25);
        frame.getContentPane().add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(130, 80, 150, 25);
        frame.getContentPane().add(addressField);

        JLabel courseLabel = new JLabel("Mata Kuliah");
        courseLabel.setBounds(40, 110, 80, 25);
        frame.getContentPane().add(courseLabel);

        courseField = new JTextField();
        courseField.setBounds(130, 110, 150, 25);
        frame.getContentPane().add(courseField);

        // Grade Fields
        JLabel grade1Label = new JLabel("Nilai 1 [10%]");
        grade1Label.setBounds(330, 20, 113, 25);
        frame.getContentPane().add(grade1Label);

        grade1Field = new JTextField();
        grade1Field.setBounds(453, 20, 150, 25);
        frame.getContentPane().add(grade1Field);

        JLabel grade2Label = new JLabel("Nilai 2 [15%]");
        grade2Label.setBounds(330, 50, 113, 25);
        frame.getContentPane().add(grade2Label);

        grade2Field = new JTextField();
        grade2Field.setBounds(453, 50, 150, 25);
        frame.getContentPane().add(grade2Field);

        JLabel grade3Label = new JLabel("Nilai 3 - UTS [25%]");
        grade3Label.setBounds(330, 80, 113, 25);
        frame.getContentPane().add(grade3Label);

        grade3Field = new JTextField();
        grade3Field.setBounds(453, 80, 150, 25);
        frame.getContentPane().add(grade3Field);

        JLabel grade4Label = new JLabel("Nilai 4 [15%]");
        grade4Label.setBounds(330, 110, 113, 25);
        frame.getContentPane().add(grade4Label);

        grade4Field = new JTextField();
        grade4Field.setBounds(453, 110, 150, 25);
        frame.getContentPane().add(grade4Field);

        JLabel grade5Label = new JLabel("Nilai 5 [35%]");
        grade5Label.setBounds(330, 140, 113, 25);
        frame.getContentPane().add(grade5Label);

        grade5Field = new JTextField();
        grade5Field.setBounds(453, 140, 150, 25);
        frame.getContentPane().add(grade5Field);

        JLabel finalGradeLabel = new JLabel("Nilai Akhir");
        finalGradeLabel.setBounds(330, 170, 113, 25);
        frame.getContentPane().add(finalGradeLabel);

        finalGradeField = new JTextField();
        finalGradeField.setBounds(453, 170, 150, 25);
        finalGradeField.setEditable(false);
        frame.getContentPane().add(finalGradeField);

        // Buttons
        JButton saveButton = new JButton("Simpan");
        saveButton.setBounds(130, 150, 80, 30);
        frame.getContentPane().add(saveButton);

        JButton deleteButton = new JButton("Hapus");
        deleteButton.setBounds(220, 150, 80, 30);
        frame.getContentPane().add(deleteButton);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"NIM", "Nama", "Alamat", "Mata Kuliah", "Nilai Akhir"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 200, 560, 250);
        frame.getContentPane().add(scrollPane);

        // Action Listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
    }

    private void saveData() {
        try {
            String nim = nimField.getText();
            if (nim.length() != 13 || !nim.matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "NIM harus berisi 13 digit angka.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double grade1 = Double.parseDouble(grade1Field.getText()) * 0.10;
            double grade2 = Double.parseDouble(grade2Field.getText()) * 0.15;
            double grade3 = Double.parseDouble(grade3Field.getText()) * 0.25;
            double grade4 = Double.parseDouble(grade4Field.getText()) * 0.15;
            double grade5 = Double.parseDouble(grade5Field.getText()) * 0.35;
            double finalGrade = grade1 + grade2 + grade3 + grade4 + grade5;

            finalGradeField.setText(String.valueOf(finalGrade));

            Student student = new Student(
                    nim,
                    nameField.getText(),
                    addressField.getText(),
                    courseField.getText(),
                    finalGrade
            );
            studentList.add(student);

            tableModel.addRow(new Object[]{
                    student.getNim(),
                    student.getName(),
                    student.getAddress(),
                    student.getCourse(),
                    student.getFinalGrade()
            });

            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid untuk nilai.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            studentList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin dihapus.", "Hapus Data", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearFields() {
        nimField.setText("");
        nameField.setText("");
        addressField.setText("");
        courseField.setText("");
        grade1Field.setText("");
        grade2Field.setText("");
        grade3Field.setText("");
        grade4Field.setText("");
        grade5Field.setText("");
        finalGradeField.setText("");
    }

    static class Student {
        private String nim;
        private String name;
        private String address;
        private String course;
        private double finalGrade;

        public Student(String nim, String name, String address, String course, double finalGrade) {
            this.nim = nim;
            this.name = name;
            this.address = address;
            this.course = course;
            this.finalGrade = finalGrade;
        }

        public String getNim() { return nim; }
        public String getName() { return name; }
        public String getAddress() { return address; }
        public String getCourse() { return course; }
        public double getFinalGrade() { return finalGrade; }
    }
}
