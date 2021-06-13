using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RotateScript : MonoBehaviour
{
    Transform trns;
    void Start()
    {
        trns = GetComponent<Transform>();
        if (transform.rotation.z==0)
        {
            trns.Rotate(new Vector3(0, 0, 180));
        }
    }
}
