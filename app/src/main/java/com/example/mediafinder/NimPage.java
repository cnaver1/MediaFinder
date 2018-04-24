package com.example.mediafinder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NimPage extends Fragment{

    private static TextView nimCount;
    private static TextView nimTurn;
    private static TextView nimPlayer;
    private NimState ns = new NimState();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nim, container, false);

        final Button plusOneButton = (Button) view.findViewById(R.id.plus_one);
        final Button plusTwoButton = (Button) view.findViewById(R.id.plus_two);
        nimCount = (TextView) view.findViewById(R.id.nim_count);
        nimTurn = (TextView) view.findViewById(R.id.nim_turn);
        nimPlayer = (TextView) view.findViewById(R.id.nim_player);
        nimCount.setText("0");
        nimTurn.setText("Turn: 0");
        nimPlayer.setText("Player: 1");

        plusOneButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ns.increment(1);
                        if(ns.gameOver()) {
                            endGame();
                        } else {
                            update();
                        }
                    }
                }
        );

        plusTwoButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ns.increment(2);
                        if(ns.gameOver()){
                            endGame();
                        } else {
                            update();
                        }
                    }
                }
        );

        return view;
    }

    public void update(){
        nimCount.setText(String.valueOf(ns.getCount()));
        nimTurn.setText("Turn: " + String.valueOf(ns.getTurn()));
        nimPlayer.setText("Player: " + String.valueOf(ns.player()));
    }

    public void endGame(){
        nimCount.setText("0");
        nimTurn.setText("Thanks for Playing");
        if(ns.player() == 1) {
            nimPlayer.setText("Player 2 wins!");
        }
        else{
            nimPlayer.setText("Player 1 wins!");
        }
    }

}
