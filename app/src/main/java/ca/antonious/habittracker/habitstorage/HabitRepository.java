package ca.antonious.habittracker.habitstorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.observable.IObserver;

/**
 * Created by George on 2016-09-02.
 *
 * HabitRepository implements IHabitRepository and provides an in memory store
 * of habits. It utilizes an IHabitService to handle the task of data persistence.
 */
public class HabitRepository implements IHabitRepository {
    private IHabitService habitService;
    private Map<String, Habit> habits;

    private Set<IObserver<List<Habit>>> observers = new HashSet<>();

    public HabitRepository(IHabitService habitService) {
        this.habitService = habitService;
        this.habits = new HashMap<>();
    }

    public List<Habit> getHabits() {
        ensureHabits();
        return getSortedHabits();
    }


    public Habit getHabit(String id) {
        ensureHabits();
        return habits.get(id);
    }

    public void addHabit(Habit habit) {
        ensureHabits();

        habitService.addHabit(habit);
        habits.put(habit.getId(), habit);
        notifyChange();
    }

    public void updateHabit(Habit habit) {
        ensureHabits();

        habitService.updateHabit(habit);
        habits.put(habit.getId(), habit);
        notifyChange();
    }

    public void removeHabit(String id) {
        ensureHabits();

        habitService.removeHabit(id);
        habits.remove(id);
        notifyChange();
    }

    private void ensureHabits() {
        if (habits.isEmpty()) {
            for (Habit habit: habitService.getHabits()) {
                habits.put(habit.getId(), habit);
            }
        }
    }

    private List<Habit> getSortedHabits() {
        List<Habit> sortedHabits = new ArrayList<>(habits.values());
        Collections.sort(sortedHabits, reverseChronologicalHabitComparator);

        return sortedHabits;
    }

    private void notifyChange() {
        for (IObserver<List<Habit>> observer: observers) {
            observer.onNext(getHabits());
        }
    }

    @Override
    public void addObserver(IObserver<List<Habit>> observer) {
        observers.add(observer);
        observer.onNext(getHabits());
    }

    @Override
    public void removeObserver(IObserver<List<Habit>> observer) {
        observers.remove(observer);
    }

    @Override
    public void removeAllObservers() {
        observers.clear();
    }

    private static Comparator<Habit> reverseChronologicalHabitComparator  = new Comparator<Habit>() {
        @Override
        public int compare(Habit lhs, Habit rhs) {
            return rhs.getStartDate().compareTo(lhs.getStartDate());
        }
    };
}
