using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class move : MonoBehaviour
{
    public float speed;

    private void Start()
    {
    
        Destroy(gameObject,8);
    }
    
    // Update is called once per frame
    void FixedUpdate()
    {
        transform.position += Vector3.left * speed * Time.deltaTime;

    }
}
