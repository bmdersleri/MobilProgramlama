using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObsMove : MonoBehaviour
{
    void Update()
    {
        if (!GameManager.Instance.isStart) return;
        
        Vector2 pos = transform.position;
        pos.y -= GameManager.Instance.ObsSpeed * Time.deltaTime ;
        transform.position = pos;
        //Debug.Log(mSpeed);
        if (transform.position.y<-10f)
        {
            Destroy(GameObject.FindWithTag("rocketfuel"));
        }
    }
    
}
