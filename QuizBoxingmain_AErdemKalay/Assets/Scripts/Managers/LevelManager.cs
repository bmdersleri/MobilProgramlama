using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class LevelManager : MonoSingleton<LevelManager>
{
    public List<LevelQuestion> allLevels;
    public List<QuestionStruct> currentLevelQuestions;

    private void Start()
    {
        currentLevelQuestions = allLevels[PlayerPrefs.GetInt("Level")].questions;
        UIManager.Instance.SetCategoryText(allLevels[PlayerPrefs.GetInt("Level")].categoryTitle);
    }
    public void LevelUp()
    {
        var level = PlayerPrefs.GetInt("Level");
        PlayerPrefs.SetInt("Level",(level+1)%allLevels.Count);
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }
}
