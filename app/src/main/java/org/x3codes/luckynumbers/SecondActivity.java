package org.x3codes.luckynumbers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeText, luckyNumberText;
    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        welcomeText = findViewById(R.id.textView2);
        luckyNumberText = findViewById(R.id.lucky_number_text);
        shareBtn = findViewById(R.id.share_btn);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");

        int randomNumber = generateRandomNumber();
        luckyNumberText.setText("" + randomNumber);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(userName, randomNumber);
            }
        });

    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 1000;

        int randomNumber = random.nextInt(upper_limit);

        return randomNumber;
    }

    public void shareData(String username, int randomNum) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        intent.putExtra(Intent.EXTRA_TEXT, "The lucky number is: " + randomNum);

        startActivity(Intent.createChooser(intent, "Choose a Platform"));

    }
}