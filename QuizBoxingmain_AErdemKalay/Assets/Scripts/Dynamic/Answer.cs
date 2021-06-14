using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class Answer : MonoBehaviour
{
    [SerializeField] private TextMeshProUGUI txtAnswerTitle;
    private string answerTitle;
    public string AnswerTitle
    {
        get => answerTitle;
        set
        {
            answerTitle = value;
            txtAnswerTitle.text = value;
        }
    }
    private bool isCorrect;
    public bool IsCorrect
    {
        get => isCorrect;
        set => isCorrect = value;
    }
    private Player ownerPlayer;
    public Player OwnerPlayer
    {
        get => ownerPlayer;
        set => ownerPlayer = value;
    }
}
