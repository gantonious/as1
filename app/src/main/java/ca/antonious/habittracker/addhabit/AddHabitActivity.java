package ca.antonious.habittracker.addhabit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.antonious.habittracker.AndroidFileHandler;
import ca.antonious.habittracker.BaseActivity;
import ca.antonious.habittracker.FileHandler;
import ca.antonious.habittracker.HabitRepository;
import ca.antonious.habittracker.HabitService;
import ca.antonious.habittracker.IHabitService;
import ca.antonious.habittracker.R;
import ca.antonious.habittracker.models.Habit;

public class AddHabitActivity extends BaseActivity {
    private Button addButton;
    private EditText habitTitle;

    private HabitRepository habitRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        habitRepository = getHabitTrackerApplication().getHabitRepository();

        addButton = (Button) findViewById(R.id.add_habit);
        habitTitle = (EditText) findViewById(R.id.new_habit_title);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdd();
                finish();
            }
        });
    }

    private void onAdd() {
        Habit habit = new Habit();
        habit.setName(habitTitle.getText().toString());

        habitRepository.addHabit(habit);
    }

}