using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameManager : MonoBehaviour
{
    public static GameManager Instance;

    public TextMeshProUGUI scoreText;
    // Start is called before the first frame update
    void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
        }
    }

    private float obsSpeed;
    public float ObsSpeed
    {
        get
        {
            return Mathf.Clamp(player.moveSpeed/10+5, 5,20);
        }
    }
    
    public bool isStart;
    public PlayerMovement player;
    public FuelController fuelController;
    public MapGeneration mGenerator;
    [SerializeField] private GameObject restartPanel,mainMenuPanel,gamePanel;
    public void startToPlay()
    {
        if (isStart) return;
        GameManager.Instance.isStart = true;
        mGenerator.init();
        
    }
    public void RestartGame()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }
    public void Lose()
    {
        if (!isStart) return;
        
        scoreText.text = "BEST \n" + PlayerPrefs.GetInt("bestScore");
        player.rb.bodyType = RigidbodyType2D.Dynamic;
        player.rb.AddForce(Vector2.down * 10);
        player.rb.AddTorque(100);
        
        gamePanel.SetActive(false);
        restartPanel.SetActive(true);
        isStart = false;
    }
}
