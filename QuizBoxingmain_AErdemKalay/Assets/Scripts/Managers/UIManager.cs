using UnityEngine;
using System.Collections.Generic;
using TMPro;
using UnityEngine.UI;

public class UIManager : MonoSingleton<UIManager>
{
    [SerializeField]private GameUI gameUI;
    public GameUI GameUI
    {
        get => gameUI;
        set => gameUI = value;
    }
    [SerializeField]private HomeUI homeUI;
    public HomeUI HomeUI
    {
        get => homeUI;
        set => homeUI = value;
    }
    [SerializeField] private TextMeshProUGUI endText,categoryText;

    public void SetEndText(string text)
    {
        endText.text = text;
    }
    public void SetCategoryText(string text)
    {
        categoryText.text = text;
    }
}
        