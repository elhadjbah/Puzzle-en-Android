package com.example.puzzle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    private int emptyX, emptyY;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    Button mButton5;
    Button mButton6;
    Button mButton7;
    Button mButton8;
    Button mButton9;
    Button mButton;
    Button emptyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[3][3];

        buttons[0][0] = findViewById(R.id.un);
        buttons[0][1] = findViewById(R.id.deux);
        buttons[0][2] = findViewById(R.id.trois);
        buttons[1][0] = findViewById(R.id.quatre);
        buttons[1][1] = findViewById(R.id.cinq);
        buttons[1][2] = findViewById(R.id.six);
        buttons[2][0] = findViewById(R.id.sept);
        buttons[2][1] = findViewById(R.id.huit);
        buttons[2][2] = findViewById(R.id.neuf);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = 0, y = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (view == buttons[i][j]) {
                            x = i;
                            y = j;
                            break;
                        }
                    }
                }

                if (isAdjacent(x, y, emptyX, emptyY)) {
                    swapButtons(x, y, emptyX, emptyY);
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(clickListener);
            }
        }

        Button shuffleButton = findViewById(R.id.sff);
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffleButtons();
            }
        });
    }

    private boolean isAdjacent(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1 - x2) == 1 && y1 == y2) || (x1 == x2 && Math.abs(y1 - y2) == 1);
    }

    private void swapButtons(int x1, int y1, int x2, int y2) {
        String temp = buttons[x1][y1].getText().toString();
        buttons[x1][y1].setText(buttons[x2][y2].getText().toString());
        buttons[x2][y2].setText(temp);

        if (buttons[x1][y1].getText().equals("")) {
            emptyX = x1;
            emptyY = y1;
        } else if (buttons[x2][y2].getText().equals("")) {
            emptyX = x2;
            emptyY = y2;
        }
    }

    private void shuffleButtons() {
        ArrayList<String> buttonLabels = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            buttonLabels.add(String.valueOf(i));
        }
        buttonLabels.add("");

        Collections.shuffle(buttonLabels);

        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = buttons[i][j];
                button.setText(buttonLabels.get(index));
                if (button.getText().equals("")) {
                    emptyButton = button;
                }
                index++;
            }
        }
    }

}




