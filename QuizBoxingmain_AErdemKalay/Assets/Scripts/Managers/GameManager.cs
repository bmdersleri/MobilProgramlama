using UnityEngine;
using UnityEngine.Events;

public class GameManager : MonoSingleton<GameManager>
{
    public Player[] players;
    [SerializeField] private UnityEvent startEvent,endGameEvent;

    private bool gameStarted;
    public bool GameStarted
    {
        get => gameStarted;
        set => gameStarted = value;
    }
    public void StartEventHandle()
    {
        if (GameStarted) return;
        GameStarted = true;
        startEvent?.Invoke();
    }
    public void EndGameHandle()
    {
        GameStarted = false;
        endGameEvent?.Invoke();
    }
    public void CheckWinnerPlayer()
    {
        if (LevelManager.Instance.currentLevelQuestions.Count > QuestionManager.Instance.SelectedIndex) return;
        EndGameHandle();
        if (players[0].Health > players[1].Health)
        {
            players[0].Win();
            UIManager.Instance.SetEndText("Sol Oyuncu Kazandı!");
            players[1].Lose();
        }
        else if (players[0].Health < players[1].Health)
        {
            players[1].Win();
            UIManager.Instance.SetEndText("Sağ Oyuncu Kazandı!");
            players[0].Lose();
        }
        else
        {
            players[0].Lose();
            UIManager.Instance.SetEndText("Berabere!");
            players[1].Lose();
        }
    }

}
