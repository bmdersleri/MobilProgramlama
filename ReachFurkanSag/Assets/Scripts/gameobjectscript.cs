using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class gameobjectscript : MonoBehaviour
{
    public bool up;
    void Start()
    {
        transform.position = Camera.main.ViewportToWorldPoint(new Vector3(0.5f, up ? 1.05f : 0, Camera.main.nearClipPlane));

    }
}

   
