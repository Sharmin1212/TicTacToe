package org.ieselcaminas.pmdm.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons;
    public static final int NUM_ROWS_COLS = 3;
    boolean isPlayer1 = true;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout g = findViewById(R.id.gridLayout);
        textView = findViewById(R.id.textView);


        buttons = new Button[NUM_ROWS_COLS][NUM_ROWS_COLS];
        g.setRowCount(NUM_ROWS_COLS);
        g.setColumnCount(NUM_ROWS_COLS);

        for (int i = 0; i < NUM_ROWS_COLS; i++) {
            for (int j = 0; j < NUM_ROWS_COLS; j++) {
                buttons[i][j] = new Button(getApplicationContext(), null, android.R.attr.buttonStyleSmall);
                g.addView(buttons[i][j]);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button button = (Button) v;
                        if (isPlayer1) {
                            button.setText("X");
                            isPlayer1 = false;
                            textView.setText("Turn of player2 [ 0 ]");
                            checkLine();

                        } else {
                            button.setText("O");
                            isPlayer1 = true;
                            textView.setText("Turn of player1 [ X ]");
                            checkLine();
                        }
                    }
                });
            }
        }
    }


    public void checkLine() {
        int countX = 0;
        int countY = 0;
        for (int i = 0; i < NUM_ROWS_COLS; i++) {
            for (int j = 0; j < NUM_ROWS_COLS; j++) {
                if (buttons[i][j].getText().equals("X")) {
                    countX++;
                    if (countX == 3) {
                        textView.setText("You win player1");
                    } else {
                        countX = 0;
                    }

                    if (countY == 3) {
                        textView.setText("You win player2");
                    } else {
                        countY = 0;
                    }
                }
            }


        }
    }
}
