package com.agesa.todovaadin.app.model;

import com.agesa.todovaadin.app.repository.TodoRepository;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoList extends VerticalLayout  implements TodoChangeListener {
    @Autowired
    TodoRepository todoRepository;
    private List<Todo> todos;

    @PostConstruct
    void init(){
        setSpacing(true);
        update();
    }

    private void setTodos(List<Todo> todos) {
        this.todos = todos;
        removeAllComponents();

        todos.forEach(todo -> {
            addComponent(new TodoLayout(todo,this));
        });
    }

    public void save(Todo todo) {
        todoRepository.save(todo);
        update();
    }

    private void update() {
        setTodos(todoRepository.findAll());
    }

    @Override
    public void todoChanged(Todo todo) {
        save(todo);
    }

    public void deleteCompleted() {
        todoRepository.deleteInBatch(
        todos.stream().filter(Todo::isDone).collect(Collectors.toList()));
        update();
    }
}
