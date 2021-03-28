package com.agesa.todovaadin.app.ui;


import com.agesa.todovaadin.app.model.Todo;
import com.agesa.todovaadin.app.model.TodoList;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
//import com.vaadin.flow.component.button.Button;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class TodoUI extends UI {

    private VerticalLayout layout;

    @Autowired
     TodoList todoList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addActionButtons();
    }

    private void addTodoList() {
        layout.addComponent(todoList);
        todoList.setWidth("80%");

    }

    private void addActionButtons() {
        Button deleteButton=new Button("Delete Completed");
        deleteButton.addClickListener(clickEvent ->todoList.deleteCompleted());
        layout.addComponent(deleteButton);
    }

    private void setupLayout() {
        layout=new VerticalLayout();
        layout.setSpacing(true);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);

    }

    private void addHeader() {
        Label header=new Label("TODO");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.setSizeUndefined();
        layout.addComponent(header);
    }
    private void addForm() {
        HorizontalLayout formLayout=new HorizontalLayout();
        TextField taskField=new TextField();
        taskField.setWidth("100%");
        taskField.addStyleName(ValoTheme.LABEL_COLORED);
        formLayout.setSpacing(true);
        formLayout.setWidth("80%");
        Button addButton=new Button();
        addButton.setIcon(FontAwesome.PLUS);
         addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        formLayout.addComponents(taskField,addButton);
        formLayout.setExpandRatio(taskField,1);
        layout.addComponent(formLayout);

        addButton.addClickListener(clickEvent -> {
            if (!(taskField.getValue().isEmpty())){
                todoList.save(new Todo(taskField.getValue()));
                taskField.clear();
                taskField.focus();
            }else {

            }

        });
        taskField.focus();
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);


    }
}