using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class MainController : MonoBehaviour
{
    //Buttonlar tanımlandı
    [SerializeField]
    Button btnPlay, btnExit;

    //Buttonlara tıklandıktan sonra görünecek resimler
    [SerializeField]
    Sprite playOn, exitOn;

    void Start()
    {
        //Buttonların clicklerine ilgili metodlar bağlandı
        btnPlay.onClick.AddListener(goToStageScene);
        btnExit.onClick.AddListener(exitTheApp);
    }

    //Stage ekranına gitme metodu
    private void goToStageScene()
    {
        btnPlay.image.sprite = playOn;
        StartCoroutine(btnWait("play"));
    }

    //Oyundan çıkma metodu
    private void exitTheApp()
    {
        btnExit.image.sprite = exitOn;
        StartCoroutine(btnWait("exit"));
    }

    //Bir süre bekletmek için yazılan metod
    private IEnumerator btnWait(string tag)
    {
        yield return new WaitForSeconds(0.3f);

        if (tag == "play")
            SceneManager.LoadScene("StageScene");
        else
            Application.Quit();
    }
}
