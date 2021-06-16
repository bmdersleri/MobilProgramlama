using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class GameManager : MonoBehaviour
{
    public int skor;
    public Text scoretext;
    


    // Start is called before the first frame update
    void Start()
    {
        skor = 0;
        scoretext.text = skor.ToString();
        
    }

    // Update is called once per frame
    void Update()
    {

    }
    public void UpdateScore()
    {
        skor++;
        scoretext.text = skor.ToString();
    }

    public void RestartGame()
    {

        SceneManager.LoadScene(0);


    }
    
}
