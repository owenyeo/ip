package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.task.TaskType;
import chatbot.ui.Ui;

/**
 * Add an event task to the TaskList
 * 
 * @author Owen Yeo
 */
public class AddEvent extends Command {

    public AddEvent(String input) {
        super(input);
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(input, TaskType.EVENT);

        ui.print(new String[] {
            "What? You ain't finishing it. Added: ",
            tasks.getTask(tasks.getLength()).toString(),
            "Now you have an overwhelming " + tasks.getLength() + " things to do."
        });

        storage.saveTasks(tasks);
    }
}