using System;
using System.Collections;
using System.Collections.Generic;
using DG.Tweening;
using NaughtyAttributes;
using UnityEngine;
using UnityEngine.UI;

public class Player : MonoBehaviour
{
    [SerializeField] private bool isReady;
    public bool IsReady
    {
        get => isReady;
        set
        {
            isReady = value;
            if (isReady && enemyPlayer.IsReady)
            {
                GameManager.Instance.StartEventHandle();
            }
        }
    }
    [SerializeField] private Animator animator;
    [SerializeField] private Player enemyPlayer;
    public Player EnemyPlayer
    {
        get => enemyPlayer;
    }
    [SerializeField] private Slider healthBar;
    public Transform answerParent;

    private int health;
    public int Health
    {
        get => health;
        set
        {
            var clamp = Mathf.Clamp(value, 0, LevelManager.Instance.currentLevelQuestions.Count);
            health = clamp;
            healthBar.DOValue(clamp*0.01f,0.2f);
        }
    }

    private void Start()
    {
        Health = LevelManager.Instance.currentLevelQuestions.Count;
        healthBar.maxValue = Health * 0.01f;
    }
    public void Attack()
    {
        animator.SetTrigger("Attack");
    }
    private void EnemyHit()
    {
       CameraManager.Instance.SetShake();
        enemyPlayer.animator.SetTrigger("Hit");
        enemyPlayer.Health -= 1;
        
        GameManager.Instance.CheckWinnerPlayer();
        
    }
    private void NewQuestion()
    {
        QuestionManager.Instance.SetQuestion();
    }
    public void SetReady(Image img)
    {
        IsReady = !IsReady;
        img.color = IsReady ? Color.green : Color.grey;
    }
    public void Win()
    {
        animator.SetTrigger("Win");
    }
    public void Lose()
    {
        animator.enabled = false;
    }
}
