using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class StageController : MonoBehaviour
{
    //Bölümler için tanımlanan buttonlar
    [SerializeField]
    Button[] btnStages;

    //Son geçilen bölümü bu değişkene atayacağız
    private int lastStage;
    //Bölüm isimleri
    private string[] stageNames = {
        "Stage1",
        "Stage2",
        "Stage3",
        "Stage4",
        "Stage5"
    };

    void Start()
    {
        //PlayerPrefs.SetInt("LastStage", 1);
        //Son geçilen bölümü alıp değişkene atadık
        lastStage = PlayerPrefs.GetInt("LastStage", 1);

        //Geçilen bölüm 5 den büyükse onu yine 5 e eşitledik 
        if (lastStage > 5)
            lastStage = 5;

        //Geçilen bölüme göre buttonları açtık
        for (int i = 0; i < lastStage; i++)
            btnStages[i].gameObject.SetActive(true);
    }

    //Tıklanılan buttona göre bölüme girme kodu
    public void goToPlayScene(int sIn)
    {
        SceneManager.LoadScene(stageNames[sIn]);
    }
}
