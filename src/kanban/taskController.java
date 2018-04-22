package kanban;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class taskController implements Initializable {
    private static Node descriptionStatic,
                        nameStatic,
                        choiceBoxStatic,
                        dateStatic;
    static Stage stageStatic;
    @FXML TextArea descriptionArea;
    @FXML TextField taskName;
    @FXML ChoiceBox<Priority> priorityChoiceBox;
    @FXML DatePicker datePicker;
    @FXML Button addButton;



    @FXML private void addNewTask(){

        String warningMessage = "FILL THIS FIELD!";
        if(taskName.getText().isEmpty()||taskName.getText()==(warningMessage))
            taskName.setText(warningMessage);
        else if(priorityChoiceBox.getValue()==null)
            priorityChoiceBox.setValue(Priority.Low);
        else if(datePicker.getValue()==null)
            datePicker.setValue(LocalDate.now());
        else if(descriptionArea.getText().isEmpty()||descriptionArea.getText()==(warningMessage))
            descriptionArea.setText(warningMessage);
        else{
            Task newTask = new Task(taskName.getText(),descriptionArea.getText().toString(),priorityChoiceBox.getValue(),LocalDate.now());

            mainController.todoStatic.getItems().addAll(newTask);

            stageStatic.close();
        }
    }



    public static void editTask(Task task,ListView list){

        ((TextArea)descriptionStatic).setText(task.getDescription().getText());
        ((TextField)nameStatic).setText(task.toString());
        ((DatePicker)dateStatic).setValue(task.getExpiryDate());
        ((ChoiceBox<Priority>)choiceBoxStatic).setValue(task.getPriority());

        String warningMessage = "FILL THIS FIELD!";
        if(((TextArea)descriptionStatic).getText().isEmpty()||((TextArea)descriptionStatic).getText()==warningMessage)
            ((TextArea)descriptionStatic).setText(warningMessage);
        else if( ((TextField)nameStatic).getText().isEmpty()|| ((TextField)nameStatic).getText()==warningMessage)
            ((TextField)nameStatic).setText(warningMessage);
        else if(((DatePicker)dateStatic).getValue()==null)
            ((TextField)dateStatic).setText(warningMessage);
        else{
            list.getItems().remove(task);
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priorityChoiceBox.getItems().addAll(Priority.Low,Priority.Medium,Priority.High,Priority.TurboImportant);
        dateStatic=datePicker;
        descriptionStatic=descriptionArea;
        nameStatic=taskName;
        choiceBoxStatic = priorityChoiceBox;
    }
}
