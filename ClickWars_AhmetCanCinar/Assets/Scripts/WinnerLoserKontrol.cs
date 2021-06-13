using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class WinnerLoserKontrol : MonoBehaviour
{
    [SerializeField] Text WhitePlayerText;
    [SerializeField] Text BlackPlayerText;
    void Start()
    {
        WhitePlayerText.text = "SCORE: 0";
        BlackPlayerText.text = "SCORE: 0";
    }

    void Update()
    {
        WhitePlayerText.text = "SCORE: " + PlayerPrefs.GetInt("WhiteScore").ToString();
        BlackPlayerText.text = "SCORE: " + PlayerPrefs.GetInt("BlackScore").ToString();
    }
    public void TryAgainButton()
    {
        SceneManager.LoadScene("MainScene");
    }

    public void MenuButton()
    {
        SceneManager.LoadScene("MenuScene");
    }

}
