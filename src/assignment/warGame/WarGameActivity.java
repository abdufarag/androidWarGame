package assignment.warGame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WarGameActivity extends Activity {
	
    private static final String DEBUG_TAG = null;
	private TextView scoreDisplay, player1CardsLeft, player2CardsLeft, gameOver;
    private ImageView player1Card, player2Card;
    private Button b, end;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final War game = new War();
        scoreDisplay = (TextView)findViewById(R.id.Result);
        player1CardsLeft = (TextView)findViewById(R.id.Player1CardsLeft);
        player2CardsLeft = (TextView)findViewById(R.id.Player2CardsLeft);
        gameOver = (TextView)findViewById(R.id.gameOver);
        player1Card = (ImageView)findViewById(R.id.FirstPlayerCard);
        player2Card = (ImageView)findViewById(R.id.SecondPlayerCard);
        
        b = (Button)findViewById(R.id.Play);
        try{
        	b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				game.play();
				updateUI(game);
			}
		});
        }	catch (Exception e){
        		Log.e(DEBUG_TAG, "Unexpected Error", e);
        }
        
        end = (Button)findViewById(R.id.Exit);
        end.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				System.exit(0);				
			}
		});
    }

	protected void updateUI(War game) {
		player1CardsLeft.setText(game.getPlayer1Deck().size() + "");
		player1Card.setImageResource(game.getPlayer1Card().cardReference);
		player2CardsLeft.setText(game.getPlayer2Deck().size() + "");
		player2Card.setImageResource(game.getPlayer2Card().cardReference);
		scoreDisplay.setText(game.roundResult);
		gameOver.setText(game.replayOffering);
	}
    
}