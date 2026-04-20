/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package jobapplicationsystem1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


/**
 * FXML Controller class
 *
 * @author TOP
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private VBox rootPane;
    @FXML private TextField fullNameField;
    @FXML private TextArea addressArea;
    @FXML private TextField phoneField;
    @FXML private RadioButton singleRadio;
    @FXML private RadioButton marriedRadio;
    @FXML private RadioButton divorcedRadio;
    @FXML private ToggleGroup maritalGroup;
    @FXML private DatePicker birthDatePicker;
    @FXML private ImageView imageView;
    @FXML private ListView<String> availableSkillsList;
    @FXML private ListView<String> selectedSkillsList;
    @FXML private TextArea coverLetterArea;
    @FXML private Slider fontSizeSlider;
    @FXML private ColorPicker bgColorPicker;
    
    private String uploadedImagePath = "";
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // إضافة المهارات المتاحة
        availableSkillsList.getItems().addAll(
            "Java", "Python", "C++", "JavaScript", "SQL",
            "Spring Boot", "React", "Android", "iOS"
        );
        
        // تعطيل التواريخ بعد 2010
        birthDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isAfter(LocalDate.of(2010, 1, 1))) {
                    setDisable(true);
                }
            }
        });
        
        // تغيير حجم الخط فورياً
        fontSizeSlider.valueProperty().addListener((obs, old, val) -> {
            coverLetterArea.setStyle("-fx-font-size: " + val.intValue() + "px;");
        });
        
        // تجميع أزرار الحالة الاجتماعية
        maritalGroup = new ToggleGroup();
        singleRadio.setToggleGroup(maritalGroup);
        marriedRadio.setToggleGroup(maritalGroup);
        divorcedRadio.setToggleGroup(maritalGroup);
    }
    
    @FXML
    private void uploadImage() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fc.showOpenDialog(rootPane.getScene().getWindow());
        if (file != null) {
            imageView.setImage(new Image(file.toURI().toString()));
            uploadedImagePath = file.getAbsolutePath();
        }
    }
    
    @FXML
    private void moveToSelected() {
        String skill = availableSkillsList.getSelectionModel().getSelectedItem();
        if (skill != null) {
            availableSkillsList.getItems().remove(skill);
            selectedSkillsList.getItems().add(skill);
        }
    }
    
    @FXML
    private void moveToAvailable() {
        String skill = selectedSkillsList.getSelectionModel().getSelectedItem();
        if (skill != null) {
            selectedSkillsList.getItems().remove(skill);
            availableSkillsList.getItems().add(skill);
        }
    }
    
    @FXML
    private void uploadCoverLetter() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        File file = fc.showOpenDialog(rootPane.getScene().getWindow());
        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
                coverLetterArea.setText(content.toString());
            } catch (IOException e) {
                showAlert("Error", "Cannot read file");
            }
        }
    }
    
    @FXML
    private void changeBackgroundColor() {
        Color c = bgColorPicker.getValue();
        String rgb = String.format("#%02X%02X%02X",
            (int)(c.getRed()*255), (int)(c.getGreen()*255), (int)(c.getBlue()*255));
        rootPane.setStyle("-fx-background-color: " + rgb + ";");
    }
    
    @FXML
    private void handleExit() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void setFontArial() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-family: Arial;");
    }
    
    @FXML
    private void setFontTimes() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-family: 'Times New Roman';");
    }
    
    @FXML
    private void setFontVerdana() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-family: Verdana;");
    }
    
    @FXML
    private void setFontSizeSmall() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-size: 12px;");
    }
    
    @FXML
    private void setFontSizeMedium() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-size: 16px;");
    }
    
    @FXML
    private void setFontSizeLarge() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-size: 20px;");
    }
    
    @FXML
    private void setFontNormal() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-weight: normal; -fx-font-style: normal;");
    }
    
    @FXML
    private void setFontBold() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-weight: bold; -fx-font-style: normal;");
    }
    
    @FXML
    private void setFontItalic() {
        rootPane.setStyle(rootPane.getStyle() + "-fx-font-weight: normal; -fx-font-style: italic;");
    }
    
    @FXML
    private void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Job Application System");
        alert.setContentText("Developer: Reem S. Alsersawi\nPurpose: Job Application System\nUniversity: IUG");
        alert.showAndWait();
    }
    
    @FXML
    private void saveData() {
        // التحقق
        if (fullNameField.getText().trim().isEmpty()) {
            showAlert("Error", "Enter full name");
            return;
        }
        if (addressArea.getText().trim().isEmpty()) {
            showAlert("Error", "Enter address");
            return;
        }
        if (phoneField.getText().trim().isEmpty()) {
            showAlert("Error", "Enter phone number");
            return;
        }
        if (maritalGroup.getSelectedToggle() == null) {
            showAlert("Error", "Select marital status");
            return;
        }
        if (birthDatePicker.getValue() == null) {
            showAlert("Error", "Select birthdate");
            return;
        }
        if (selectedSkillsList.getItems().isEmpty()) {
            showAlert("Error", "Select at least one skill");
            return;
        }
        if (coverLetterArea.getText().trim().isEmpty()) {
            showAlert("Error", "Enter cover letter");
            return;
        }
        if (uploadedImagePath.isEmpty()) {
            showAlert("Error", "Upload image");
            return;
        }
        
        // حفظ الملف
        try {
            String name = fullNameField.getText().trim().replace(" ", "_");
            int random = new Random().nextInt(10000);
            String fileName = name + "_" + random + ".txt";
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                writer.println("Full Name: " + fullNameField.getText());
                writer.println("Address: " + addressArea.getText());
                writer.println("Phone: " + phoneField.getText());
                writer.println("Marital Status: " + getMaritalStatus());
                writer.println("Birthdate: " + birthDatePicker.getValue());
                writer.println("Image: " + uploadedImagePath);
                writer.println("Skills: " + String.join(", ", selectedSkillsList.getItems()));
                writer.println("Cover Letter:\n" + coverLetterArea.getText());
            }
            
            showAlert("Success", "Saved to: " + fileName);
        } catch (IOException e) {
            showAlert("Error", "Save failed");
        }
    }
    
    private String getMaritalStatus() {
        if (singleRadio.isSelected()) return "Single";
        if (marriedRadio.isSelected()) return "Married";
        return "Divorced";
    }
    
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
