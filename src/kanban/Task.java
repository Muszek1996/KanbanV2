package kanban;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.input.DataFormat;

import javax.tools.Tool;
import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.EventListener;

public class Task extends ListCell<Task> implements Serializable {
    private String title;
    private String description;
    private Priority priority;
    private LocalDate expiryDate;

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    Task(String title, String description, Priority priority, LocalDate expiryDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.expiryDate = expiryDate;
    }

    Task(String title, String description, String priority, String expiryDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lDate = LocalDate.parse(expiryDate,format);

        this.title = title;
        this.description = description;
        this.priority = Priority.valueOf(priority);
        this.expiryDate = lDate;
    }

    public String getPriorityString(){
        return priority.toString();
    }
    public Priority getPriority(){
        return priority;
    }
    public String getDescriptionString(){ return description.toString(); }
    public Tooltip getDescription(){
        return new Tooltip(description);
    }
    @Override
    public String toString() {
        return title;
    }

    public String getCSV(){
        return (title+";"+description+";"+priority.toString()+";"+expiryDate.toString()+";");
    }
}
