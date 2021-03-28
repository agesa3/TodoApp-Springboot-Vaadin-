package com.agesa.todovaadin.app.model;

import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.lang.reflect.Field;
import java.util.Arrays;

public class TodoLayout extends HorizontalLayout {

    private final CheckBox done;
    private final TextField text;

    public TodoLayout(Todo todo, TodoList changeListener) {
        setWidth("100%");
        setSpacing(true);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        done=new CheckBox();
        text=new TextField();
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        text.setValueChangeMode(ValueChangeMode.BLUR);
        text.setWidth("100%");

        Binder<Todo> binder=new Binder<>(Todo.class);
        binder.bindInstanceFields(this);
        binder.setBean(todo);
        addComponents(done,text);
        addComponentsAndExpand(text);
        setExpandRatio(text,1);

        binder.addValueChangeListener(event -> changeListener.todoChanged(todo));

    }
}
