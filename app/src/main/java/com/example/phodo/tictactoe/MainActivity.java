package com.example.phodo.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    //Data structure that stores all winning moves
    HashMap <String, String [][]> winningMoves = new HashMap <String, String [][]>();

    //Winning moves that includes these buttons
    String [][] button0Moves = { {"button0", "button1", "button2"}, {"button0", "button3", "button6"}, {"button0", "button4", "button8"} };
    String [][] button1Moves = { {"button0", "button1", "button2"}, {"button1", "button4", "button7"} };
    String [][] button2Moves = { {"button0", "button1", "button2"}, {"button2", "button4", "button6"}, {"button2", "button5", "button8"} };

    String [][] button3Moves = { {"button0", "button3", "button6"}, {"button3", "button4", "button5"} };
    String [][] button4Moves = { {"button1", "button4", "button7"}, {"button3", "button4", "button5"}, {"button2", "button4", "button6"} };
    String [][] button5Moves = { {"button2", "button5", "button8"} };

    String [][] button6Moves = { {"button6", "button4", "button2"}, {"button6", "button7", "button8"}, {"button6", "button3", "button0"} };
    String [][] button7Moves = { {"button0", "button1", "button2"}, {"button6", "button7", "button8"}, {"button0", "button4", "button8"} };
    String [][] button8Moves = { {"button7", "button4", "button1"}, {"button6", "button7", "button8"} };

    public void insertIntoHash(){
        winningMoves.put("button0", button0Moves);
        winningMoves.put("button1", button1Moves);
        winningMoves.put("button2", button2Moves);
        winningMoves.put("button3", button3Moves);
        winningMoves.put("button4", button4Moves);
        winningMoves.put("button5", button5Moves);
        winningMoves.put("button6", button6Moves);
        winningMoves.put("button7", button7Moves);
        winningMoves.put("button8", button8Moves);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertIntoHash();
    }

    //check if any winnings
    public String checkboard(String tileId){
        String winningMove;
       String[][] moves = winningMoves.get(tileId);
       int resID;
       Button tile;
       //iterate over possible winning moves just for this tile
        for(int i =0; i < moves.length; i++){
            winningMove= "true"; //assume winning move
            int j =0;
            //get tile
            resID = getResources().getIdentifier(moves[i][j], "id", getPackageName());
            tile = (Button) findViewById(resID);

            //Search a particular row for matching tiles
            while( (j < moves[i].length) ){
                //Not yet clicked
                if( tile.getTag()==null || (tile.getTag()+"" == "false") ){
                    winningMove = "false";
                    break;
                }
                j +=1;
                if(j < moves[i].length){
                    resID = getResources().getIdentifier(moves[i][j], "id", getPackageName());
                    tile = (Button) findViewById(resID);
                }
            }

            //If it finds a row where all tiles match, return immediately,  or else keep looking
            if(winningMove=="true"){
                Toast.makeText(this, "Someone has won!", Toast.LENGTH_LONG).show();
                return "true";
            }
        }
        winningMove = "false";
        return winningMove;
    }

    public void tileClicked(View view) {
        RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        TextView indicator = (TextView) findViewById(R.id.indicator);
        view.setBackgroundColor(Color.BLACK);
        //Mark board as clicked
        view.setTag("true");

        if ( checkboard(getResources().getResourceEntryName(view.getId())) == "true" ){
            //someone has won
            indicator.setText("Someone has won!");
            background.setBackgroundColor(Color.GREEN);

       }else{
            indicator.setText("NEXT PLAYER!");
        }
    }

    public void newGame(View view) {
        onCreate(new Bundle());
    }
}
