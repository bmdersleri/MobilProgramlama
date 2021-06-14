using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEditor;
using UnityEngine;
using UnityEngine.UI;

public class PlayerMovement : MonoBehaviour
{
    public Rigidbody2D rb;
    public float moveSpeed = 2f;
    private int score;
    private int highScore;
    public TextMeshProUGUI ScoreText;
    public Camera mainCamera;
   
    
    public static PlayerMovement Instance { get; private set; }
    
    public int Score
    {
        get => score;
        set
        {
            score = value;
            ScoreText.text = ("" + value);
        }
    }

    private void Awake()
    {
        rb = GetComponent<Rigidbody2D>();
    }

    private void Start()
    {
        highScore = PlayerPrefs.GetInt("bestScore");
    }

    // Update is called once per frame
    void Update()
    {
        
        if (GameManager.Instance.isStart)
        {
            MovePlayer();
        }

    }

   

    private float _startPosX, _endPosX, _mousePosDeltaX;

    private void MovePlayer()
    {
        if (!GameManager.Instance.isStart) return;
        
#if UNITY_ANDROID
        if (Input.touchCount > 0)
        {
            Touch tıklama = Input.GetTouch(0);
            if (tıklama.phase == TouchPhase.Moved)
            {
                transform.position = new Vector3(mainCamera.ScreenToWorldPoint(tıklama.position).x, transform.position.y, 0);
            }

        }
        #endif
        
        
        #if UNITY_EDITOR
            if (Input.GetAxisRaw("Horizontal") > 0f)
            {
                rb.velocity = new Vector2(moveSpeed, 0);
            }
        
            else if (Input.GetAxisRaw("Horizontal") < 0f)
            {
                rb.velocity = new Vector2(-moveSpeed, 0);
            }
            else
            {
                rb.velocity = Vector2.zero;
            }
        
        #endif
    }

    void OnTriggerEnter2D(Collider2D other)
        {
            if (!GameManager.Instance.isStart)
            {
                return;
            }

            if (other.gameObject.tag == "platform")
            {
                AudioManager.Instance.explosion.Play();
                GameManager.Instance.Lose();
            }

            if (other.gameObject.tag == "rocketfuel")
            {
                GameManager.Instance.fuelController.AddFuel();
                Destroy(other.gameObject);
            }

            if (other.gameObject.tag == "collidscore")
            {
                Score++;
                if (score > highScore)
                {
                    highScore = score;
                    PlayerPrefs.SetInt("bestScore", highScore);
                }
                else
                {
                    PlayerPrefs.GetInt("bestScore");
                }
                
             
            }


        }

        void OnTriggerExit2D(Collider2D other)
        {
            if (other.gameObject.tag == "collidscore")
            {
                Destroy(other.gameObject, 0.2f);
            }
        }

     

        

    }

