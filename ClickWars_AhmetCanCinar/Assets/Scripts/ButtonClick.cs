using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ButtonClick : MonoBehaviour
{
    public GameObject ustGo;
    public GameObject altGo;
    [SerializeField] Button WhitePlayer;
    [SerializeField] Button BlackPlayer;
    [SerializeField] GameObject WhiteSounds;
    [SerializeField] GameObject BlackSounds;
    public float secondLeft = 3;
    private bool timerIsRunning = false;
    [SerializeField] Text geriSayimText;
    void Start()
    {
        geriSayimText.enabled = false;
        WhitePlayer.enabled = false;
        BlackPlayer.enabled = false;
        WhiteSounds.SetActive(false);
        BlackSounds.SetActive(false);
    }
    void Update()
    {
        if (ustGo.activeSelf == false && altGo.activeSelf == false )
        {
            timerIsRunning = true;
            GeriSay();
        }
    }
    public void UstTiklama()
    {  
        ustGo.SetActive(false);
    }
    public void AltTiklama()
    {
        altGo.SetActive(false);
    }
    private void GeriSay()
    {
        geriSayimText.enabled = true;

        if (timerIsRunning)
        {
            if (secondLeft >= 1 )
            {  
                geriSayimText.text = secondLeft.ToString("0");
                secondLeft -= Time.deltaTime;
            }
            else
            {
                timerIsRunning = false;
                geriSayimText.text = "GO!";
                geriSayimText.GetComponent<Animator>().SetTrigger("CountdownTimerAnim");
                WhitePlayer.enabled = true;
                BlackPlayer.enabled = true;
                WhiteSounds.SetActive(true);
                BlackSounds.SetActive(true);
            }

        }
    }
}
