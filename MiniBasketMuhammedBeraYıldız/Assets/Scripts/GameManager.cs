using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class GameManager : MonoBehaviour
{

    public int score;
    public int highScore;


    public Text scoreText;
    public Text scoreText2;
    public Text highScoreText;
    public Text highScoreText2;


    public Canvas canvasOyun;
    public Canvas anaMenu;
    public Canvas finish;

    public AudioSource selectVoice;


    void Start()
    {
        Time.timeScale = 0;
        score = 0;
        scoreText.text = "" + score;
        highScoreText.text = "High Score : " + PlayerPrefs.GetInt("highScore");
    }



    public void UpdateScore()
    {
        score++;
        scoreText.text = score.ToString();
    }

    public void oyunBitti()
    {
        Time.timeScale = 0;
        finish.gameObject.SetActive(true);
        canvasOyun.gameObject.SetActive(false);
        anaMenu.gameObject.SetActive(false);

        if (score >= PlayerPrefs.GetInt("highScore", highScore))
        {
            PlayerPrefs.SetInt("highScore", score);
        }
        scoreText2.text = "Score : " + score;
        highScoreText2.text = "High Score : " + PlayerPrefs.GetInt("highScore");

    }

    public void RestartButton()
    {
        SceneManager.LoadScene(0);
    }

    public void StartButton()
    {
        selectVoice.Play();
        anaMenu.gameObject.SetActive(false);
        Time.timeScale = 1;
        finish.gameObject.SetActive(false);
        canvasOyun.gameObject.SetActive(true);

    }

    public void ExitButton()
    {
        selectVoice.Play();
        Application.Quit();
    }
}
