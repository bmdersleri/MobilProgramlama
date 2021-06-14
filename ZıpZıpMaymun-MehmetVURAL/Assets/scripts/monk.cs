using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class monk : MonoBehaviour
{
    public bool ölüm  = false;
    public float velocity = 1f;
    public Rigidbody2D rb2D;
    public GameManager manager;

    public GameObject DeadScreen;
    

    // Start is called before the first frame update
    void Start()
    {
        Time.timeScale = 1;
       
    }

    // Update is called once per frame
    void Update()
    {
           if(Input.GetMouseButtonDown(0))
     {
        rb2D.velocity= Vector2.up * velocity;
     }   
    }
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.gameObject.name == "scorearea")
        {
            manager.UpdateScore();
        }
    }
    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.gameObject.tag == "DeadArea")
        {
            ölüm = true;
            Time.timeScale=0;
            DeadScreen.SetActive(true);
        }
    }
    
}
