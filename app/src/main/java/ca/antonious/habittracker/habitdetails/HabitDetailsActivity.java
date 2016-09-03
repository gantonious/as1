package ca.antonious.habittracker.habitdetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.Date;

import ca.antonious.habittracker.BaseActivity;
import ca.antonious.habittracker.HabitRepository;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;
import ca.antonious.habittracker.models.HabitCompletion;

public class HabitDetailsActivity extends BaseActivity {
    private TextView titleTextView;
    private TextView creationDateTextView;
    private TextView habitDatesTextView;
    private RecyclerView completionsRecyclerView;

    private LinearLayoutManager linearLayoutManager;
    private HabitCompletionAdapter habitCompletionAdapter;

    private HabitRepository habitRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);

        habitRepository = getHabitTrackerApplication().getHabitRepository();

        titleTextView = (TextView) findViewById(R.id.habit_details_title);
        creationDateTextView = (TextView) findViewById(R.id.habit_details_created_date);
        habitDatesTextView = (TextView) findViewById(R.id.habit_details_dates);
        completionsRecyclerView = (RecyclerView) findViewById(R.id.habit_details_recent_completions_list);

        setUpRecyclerView();

        // actually use id
        bindHabit(habitRepository.getHabits().get(0));
    }

    private void setUpRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        habitCompletionAdapter = new HabitCompletionAdapter();

        completionsRecyclerView.setLayoutManager(linearLayoutManager);
        completionsRecyclerView.setAdapter(habitCompletionAdapter);
    }

    private void bindHabit(Habit habit) {
        titleTextView.setText(habit.getName());

        habitCompletionAdapter.clear();
        habitCompletionAdapter.addAll(habit.getCompletions());
        habitCompletionAdapter.notifyDataSetChanged();
    }
}
