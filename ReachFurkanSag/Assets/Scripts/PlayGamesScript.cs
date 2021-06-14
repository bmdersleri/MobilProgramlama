using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using GooglePlayGames;
using GooglePlayGames.BasicApi;

public class PlayGamesScript : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        PlayGamesClientConfiguration config = new PlayGamesClientConfiguration.Builder().Build();
        PlayGamesPlatform.InitializeInstance(config);
        PlayGamesPlatform.Activate();

        signIn();
    }

    // Update is called once per frame
    void signIn()
    {
        Social.localUser.Authenticate(success => { });
        
    }

    #region Leaderboards
    public static void AddScoreToLeaderBoard(string leaderboardId,long score)
    {
        Social.ReportScore(score, leaderboardId,success => {  });
    }
    public static void ShowLeaderboardUI()
    {
        Social.ShowLeaderboardUI();
    }

    #endregion /Leaderboards


}
