using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class HareketScript : MonoBehaviour
{
   
    Transform transfrm;
    float x = 0.1f;
    
    void Start()
    {
        transfrm = GetComponent<Transform>();
        
    }

    
    void Update()
    {
        Hareket();
    }
    public void Hareket()
    {


        if (transfrm.transform.position.y <= -2.71f)
        {
            transfrm.transform.position = new Vector2(transfrm.transform.position.x, transfrm.transform.position.y + x);
        }
        else if (transfrm.transform.position.y >= 6.17f)
        {
            transfrm.transform.position = new Vector2(transfrm.transform.position.x, transfrm.transform.position.y - x);
        }
        else
        {

        }

    }
}
