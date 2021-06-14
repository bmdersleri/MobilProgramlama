using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class Question : MonoBehaviour
{
    [SerializeField] private TextMeshProUGUI txtQuestionTitle;
    [SerializeField] private TextMeshProUGUI txtQuestionNumber;

    public TextMeshProUGUI TxtQuestionNumber
    {
        get => txtQuestionNumber;
        set => txtQuestionNumber = value;
    }
    private string questionTitle;
    public string QuestionTitle
    {
        get => questionTitle;
        set
        {
            questionTitle = value;
            txtQuestionTitle.text = value;
        }
    }
}
