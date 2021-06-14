using System;
using System.Collections;
using System.Collections.Generic;
using NaughtyAttributes;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;
[Serializable]
public struct QuestionStruct
{
    public string questionTitle;
    public List<string> wrongAnswerTitles;
    public string correctAnswerTitle;
}
public class QuestionManager : MonoSingleton<QuestionManager>
{
    [SerializeField] private Question question;
    [SerializeField] private GameObject answerPrefab;
    [SerializeField] private GameObject questionAnswerParent;
    [SerializeField]private int selectedIndex;
    public int SelectedIndex => selectedIndex;

    [Button()]
    public void SetQuestion()
    {
        if (LevelManager.Instance.currentLevelQuestions.Count <= selectedIndex)
        {
            return;
        }
        var currentQuestion = LevelManager.Instance.currentLevelQuestions[selectedIndex];
        questionAnswerParent.SetActive(true);
        ClearAnswerParents();
        question.QuestionTitle = currentQuestion.questionTitle;
        question.TxtQuestionNumber.text = $"{selectedIndex+1}/{LevelManager.Instance.currentLevelQuestions.Count}";
        var rand = Random.Range(2, 10);
        for (int i = 0; i < GameManager.Instance.players.Length; i++)
        {
            for (int j = 0; j < currentQuestion.wrongAnswerTitles.Count; j++)
            {
                SetAnswer(currentQuestion.wrongAnswerTitles[j],false,GameManager.Instance.players[i],rand);
            }
            SetAnswer(currentQuestion.correctAnswerTitle,true,GameManager.Instance.players[i],rand);
        }
        selectedIndex++;
    }
    private void SetAnswer(string title,bool isCorrect,Player ownerPlayer,int randomness)
    {
        var answerObject = Instantiate(answerPrefab, ownerPlayer.answerParent);
        answerObject.transform.SetSiblingIndex(randomness%answerObject.transform.parent.childCount);
        var answer = answerObject.GetComponent<Answer>();
        var answerButton = answerObject.GetComponent<Button>();
        answerButton.onClick.AddListener(delegate {
            questionAnswerParent.SetActive(false);
            if (answer.IsCorrect)
            {
                answer.OwnerPlayer.Attack();
            }
            else
            {
                answer.OwnerPlayer.EnemyPlayer.Attack();
            }
        });
        answer.AnswerTitle = title;
        answer.IsCorrect = isCorrect;
        answer.OwnerPlayer = ownerPlayer;
    }
    private void ClearAnswerParents()
    {
        for (int i = 0; i < GameManager.Instance.players.Length; i++)
        {
            for (int j = 0; j < GameManager.Instance.players[i].answerParent.childCount; j++)
            {
                Destroy(GameManager.Instance.players[i].answerParent.GetChild(j).gameObject);
            }
        }
    }

    private void Start()
    {
        selectedIndex = 0;
        SetQuestion();
    }
}
