using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pota : MonoBehaviour
{

    public float speed;
    public AudioSource potaVoice;


    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "top")
        {
            potaVoice.Play();
        }
    }

    private void Start()
    {
        Destroy(gameObject, 7);
    }

    void FixedUpdate()
    {
        transform.position += Vector3.left * speed * Time.deltaTime;
    }
}
