using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class PlayersScript : MonoBehaviour
{
    [SerializeField] GameObject whitePlayer;
    [SerializeField] GameObject blackPlayer;
    [SerializeField] Button whitePlayerButton;
    [SerializeField] Button blackPlayerButton;
    [SerializeField] float speed = 20;
    public static int whiteScore = 0;
    public static int blackScore = 0;
    void Start()
    {
        whitePlayer.SetActive(false);
        blackPlayer.SetActive(false);
        whiteScore = 0;
        blackScore = 0;
    }
    void Update()
    {
     #if UNITY_EDITOR
       KlavyeKontrol();
       MobileFinish();
       KlavyeFinish();
     #else
        MobileFinish();
     #endif
    }

    void KlavyeKontrol()
    {
        whitePlayer.SetActive(true);
        blackPlayer.SetActive(true); 
        if (Input.GetKey(KeyCode.DownArrow))
        {
            whitePlayer.transform.Translate(Vector2.down * speed * Time.deltaTime);
            blackPlayer.transform.Translate(Vector2.down * speed * Time.deltaTime);
            blackScore += 5;
            PlayerPrefs.SetInt("BlackScore", blackScore);
        }
        if (Input.GetKey(KeyCode.UpArrow))
        {
            
            whitePlayer.transform.Translate(Vector2.up * speed * Time.deltaTime);
            blackPlayer.transform.Translate(Vector2.up * speed * Time.deltaTime);
            whiteScore += 5;
            PlayerPrefs.SetInt("WhiteScore", whiteScore);
        }

    }
    void KlavyeFinish()
    {
        if (whitePlayer.transform.position.y >= 0f || blackPlayer.transform.position.y >= 10f )
        {
            SceneManager.LoadScene("WhiteWinnerScene");
        }
        if (whitePlayer.transform.position.y <= -10f || blackPlayer.transform.position.y <= 0f)
        {
            SceneManager.LoadScene("BlackWinnerScene");
        }
    }
    void MobileFinish()
    {
        if (whitePlayerButton.GetComponent<RectTransform>().localPosition.y >= -65)
        {
            SceneManager.LoadScene("WhiteWinnerScene");

        }
        if (blackPlayerButton.GetComponent<RectTransform>().localPosition.y <= 65)
        {
            SceneManager.LoadScene("BlackWinnerScene");
        }
    }

    public void WhitePlayerButton()
    {
        
        whitePlayerButton.transform.Translate(Vector2.up * speed * Time.deltaTime);
        blackPlayerButton.transform.Translate(Vector2.up * speed * Time.deltaTime);
        whiteScore += 10;
        PlayerPrefs.SetInt("WhiteScore", whiteScore);
    }

    public void BlackPlayerButton()
    {
        whitePlayerButton.transform.Translate(Vector2.down * speed * Time.deltaTime);
        blackPlayerButton.transform.Translate(Vector2.down * speed * Time.deltaTime);
        blackScore += 10;
        PlayerPrefs.SetInt("BlackScore", blackScore);
    }




}
