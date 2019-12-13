package com.duatson.studentapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.yugansh.tyagi.smileyrating.SmileyRatingView;

public class FeedbackActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private SmileyRatingView smileyRating;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity);

        smileyRating = findViewById(R.id.smiley_view);
        btnSubmit = findViewById(R.id.btnSubmit);

        ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d("Rating", String.valueOf(rating));
                smileyRating.setSmiley(rating);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FeedbackActivity.this, "Thank you for your rating.", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
