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
    boolean gameover = false;
    TextView textView;
    Button buttonReset;
    GridLayout g;
    int count;
    int drawCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        g = findViewById(R.id.gridLayout);
        textView = findViewById(R.id.textView);
        buttonReset = findViewById(R.id.buttonReset);

        initGame();
        addButtons();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
                addButtons();
            }
        });


    }


    public void initGame() {
        g.removeAllViews();
        gameover = false;
        isPlayer1 = true;
        count = 0;
        textView.setText("Turn of player1 [ X ]");
        g.setRowCount(NUM_ROWS_COLS);
        g.setColumnCount(NUM_ROWS_COLS);
        drawCounter = 0;
    }


    public void addButtons() {
        buttons = new Button[NUM_ROWS_COLS][NUM_ROWS_COLS];
        for (int i = 0; i < NUM_ROWS_COLS; i++) {
            for (int j = 0; j < NUM_ROWS_COLS; j++) {
                buttons[i][j] = new Button(getApplicationContext(), null, android.R.attr.buttonStyleSmall);
                g.addView(buttons[i][j]);
                pressButton(buttons[i][j]);
            }
        }
    }


    public void pressButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                drawCounter++;
                if (gameover == false && button.getText().toString().equals("")) {
                    if (isPlayer1) {
                        button.setText("X");
                        textView.setText("Turn of player2 [ 0 ]");
                        checkLine(isPlayer1);
                        isPlayer1 = false;
                    } else {
                        button.setText("O");
                        textView.setText("Turn of player1 [ X ]");
                        checkLine(isPlayer1);
                        isPlayer1 = true;
                    }
                }
            }
        });
    }


    public void checkLine(boolean isPlayerOne) {
        count = 0;
        String toCheck;
        int player;
        if (isPlayerOne) {
            toCheck = "X";
            player = 1;
        } else {
            toCheck = "O";
            player = 2;
        }

        for (int i = 0; i < NUM_ROWS_COLS; i++) {
            for (int j = 0; j < NUM_ROWS_COLS; j++) {
                if (buttons[i][j].getText().toString().equals(toCheck)) {
                    count++;
                }

            }
            if (count == 3) {
                textView.setText("You win player" + player);
                gameover = true;
                return;
            } else {
                count = 0;
            }
        }

        for (int i = 0; i < NUM_ROWS_COLS; i++) {
            for (int j = 0; j < NUM_ROWS_COLS; j++) {
                if (buttons[j][i].getText().toString().equals(toCheck)) {
                    count++;
                }

            }
            if (count == 3) {
                textView.setText("You win player" + player);
                gameover = true;
                return;
            } else {
                count = 0;
            }
        }


        for (int i = 0; i < NUM_ROWS_COLS; i++) {
            if (buttons[i][i].getText().toString().equals(toCheck)) {
                count++;
            }

        }
        if (count == 3) {
            textView.setText("You win player" + player);
            gameover = true;
            return;
        } else {
            count = 0;
        }


        for (int i = 0; i < NUM_ROWS_COLS; i++) {
            if (buttons[i][2 - i].getText().toString().equals(toCheck)) {
                count++;
            }
        }
        if (count == 3) {
            textView.setText("You win player" + player);
            gameover = true;
            return;
        } else {
            count = 0;
        }


        if (drawCounter == 9) {
            textView.setText("Draw!");
            gameover = true;
            return;
        }
    }
}
