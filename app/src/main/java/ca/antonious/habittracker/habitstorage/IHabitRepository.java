package ca.antonious.habittracker.habitstorage;

import java.util.List;

import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObservable;

/**
 * Created by George on 2016-09-03.
 *
 * IHabitRepository provides access to habits in memory and abstracts away
 * the type of persistence used to store them
 */
public interface IHabitRepository extends IObservable<List<Habit>> {
    Habit getHabit(String id);
    List<Habit> getHabits();

    void removeHabit(String id);
    void addHabit(Habit habit);
    void updateHabit(Habit habit);

}
