using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class Ball : MonoBehaviour
{
    public bool bitti;
    public float velocity;
    public float jumpHeight;

    public Rigidbody2D rb2D;
    public GameManager managerGame;

    public AudioSource jumpVoice;
    public AudioSource deadVoice;
    public AudioSource skorVoice;



    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Skor")
        {
            skorVoice.Play();
            managerGame.UpdateScore();
        }

        if (collision.gameObject.tag == "zemin")
        {
            deadVoice.Play();
            managerGame.oyunBitti();
        }
    }


    void Update()
    {
        
    }
    public void SolButon()
    {
        jumpVoice.Play();
        rb2D.velocity = new Vector2(+1, 0);
        rb2D.AddForce(new Vector2(+10, jumpHeight));
    }
    public void SagButon()
    {
        jumpVoice.Play();
        rb2D.velocity = new Vector2(-1, 0);
        rb2D.AddForce(new Vector2(-10, jumpHeight));
    }
}
