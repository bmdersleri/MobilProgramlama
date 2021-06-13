using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class dinamikcollider : MonoBehaviour
{
    public bool left;
    void Start()
    {
        transform.position = Camera.main.ViewportToWorldPoint(new Vector3(left?0:1.01f, 0.5f,Camera.main.nearClipPlane));

    
    }
    private void OnTriggerExit2D(Collider2D col)
    {
        if (col.gameObject.tag=="up" && this.tag=="yik")
        {
            Destroy(col.gameObject);
        }
        if (col.gameObject.tag == "down" && this.tag == "yik")
        {
            Destroy(col.gameObject);
        }
    }



}
