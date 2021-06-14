using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class baslat : MonoBehaviour
{
    public GameObject StartScreen;
    public GameObject StartScreen2;
    public GameObject StartScreen3;
 
    // Start is called before the first frame update
    void Start()
    {
        StartScreen2.SetActive(false);
        StartScreen3.SetActive(false);
    }

    // Update is called once per frame
    void Update()
    {
        
    }
   

    public void StartGame()
    {
        StartScreen.SetActive(false);
        StartScreen2.SetActive(true);
        StartScreen3.SetActive(true);

    }
}
