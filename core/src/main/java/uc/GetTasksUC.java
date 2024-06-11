package uc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import models.TaskDto;
import services.TasksService;

import java.util.List;

@Component
@AllArgsConstructor
public class GetTasksUC {

    private final TasksService tasksService;

    public List<TaskDto<?,?>> tasks(String phone) {
        return tasksService.actor(phone);
    }
}
