package ca.antonious.habittracker.interactions;

import java.util.Date;
import java.util.List;

import ca.antonious.habittracker.habitstorage.IHabitRepository;
import ca.antonious.habittracker.models.Habit;

/**
 * Created by George on 2016-09-12.
 *
 * Exposes a method to create a new Habit in the System
 */
public class AddHabit {
    private IHabitRepository habitRepository;

    public AddHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void add(String name, Date startDate, List<Integer> days) {
        Habit habit = new Habit();
        habit.setName(name);
        habit.setStartDate(startDate);
        habit.setDaysToComplete(days);

        habitRepository.addHabit(habit);
    }
}
