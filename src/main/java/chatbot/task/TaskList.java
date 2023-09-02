package chatbot.task;

import java.util.ArrayList;

import chatbot.exceptions.InvalidDescriptionException;
import chatbot.exceptions.InvalidIndexException;

/**
 * Representation of a list that takes in tasks,
 * and is able to modify their states
 * 
 * @author Owen Yeo
 */
public class TaskList {

    //ArrayList to store the tasks
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for an isntance of TaskList
     * 
     * @param tasks ArrayList for tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Empty constructor for a TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList
     * 
     * @param taskString representing the descriptor for the task
     * @param taskType type of the task getting added
     * @throws InvalidDescriptionException
     */
    public void addTask(String taskString, TaskType taskType) 
        throws InvalidDescriptionException {
        switch(taskType) {
            case TODO:
                if (taskString.equals(" ")) {
                    throw new InvalidDescriptionException(
                        "What? Where's your label? Stop this.");
                }
                tasks.add(new ToDo(taskString));
                break;

            case DEADLINE:
                try {
                    String[] deadlineParts = taskString.split("/by");
                    tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidDescriptionException(
                        "Are you stupid? Can you follow instructions?");
                }
                break;

            case EVENT:
                try {
                    String[] eventParts = taskString.split("/from");
                    String eventLabel = eventParts[0];
                    String[] eventParts2 = eventParts[1].split("/to");
                    tasks.add(new Event(eventLabel.trim(), eventParts2[0].trim(), eventParts2[1].trim()));
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidDescriptionException(
                        "Are you stupid? Can you follow instructions?");
                }
                break;
        }
    }

    /**
     *  Deletes the item off the list.
     * 
     * @param listNum Index of the item of the list to delete.
     */
    public void delete(int listNum) {
        int index = listNum - 1;
        tasks.remove(index);
    }

    /**
     * To mark tasks as done.
     * 
     * @param int listNum the item on the list to mark.
     */
    public void mark(int listNum) throws InvalidIndexException {
        Task task = tasks.get(listNum - 1);
        task.done();
    }

    /**
      * To unmark a list item as undone.
      *
      * @param listNum Index of the item on the list to unmark.
      */
      public void unmark(int listNum) throws InvalidIndexException  {
        Task task = tasks.get(listNum - 1);
        task.undone();
    }

    public Task getTask(int listNum) {
        int index = listNum - 1;
        return tasks.get(index);
    }

    public int getLength() {
        return tasks.size();
    }

}